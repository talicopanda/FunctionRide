/*
Function Ride App
5/27/2019
Tales Scopinho, Sukhraj Garcha, Sergio Hernandez
 */
package functionride;

import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author tasco0966
 */
public class FunctionRide extends Canvas implements Runnable {

    public static final int WIDTH = 960;
    public static final int HEIGHT = 640;
    public static final int SCALE = 1;
    public static final String TITLE = "Function Ride";
    public static BufferedImage spriteSheet = null;

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage background = null;
    private Menu menu;
    private static Player p;
    private Level[] levels;
    //private Menu menu;

    public static enum STATE {
        MENU,
        LEVELSCREEN,
        LEVEL1,
        LEVEL2
    };

    public static STATE State = STATE.LEVEL1;

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("res\\sprite_sheet.png");
            background = loader.loadImage("res\\background.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpriteSheet ss = new SpriteSheet(spriteSheet);
        menu = new Menu();
        p = new Player(200,200);
        levels = readLevel("res\\Level.txt");
        this.addMouseListener(new MouseInput());
    }

    private synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    private synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                delta--;
                updates++;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        if (State == STATE.LEVEL1) {
        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////////////// drawing area

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        
        if (State == STATE.MENU) {
            menu.render(g);
        } else if (State == STATE.LEVELSCREEN) {
            //
        } else if (State == STATE.LEVEL1) {
            levels[0].render(g);
        } else if (State == STATE.LEVEL2) {
            levels[1].render(g);
        }

        ///////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void main(String args[]) {
        FunctionRide game = new FunctionRide();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    /**
     * reads in a data file that contains info about a level
     *
     * @param path the file path to the data file
     */
    public static Level[] readLevel(String path) {
        //try to open a connection to the file 
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            int numLevels = Integer.parseInt(br.readLine());
            Level[] levels = new Level[numLevels];
            //read in the starting and ending points
            for (int i = 0; i < numLevels; i++) {
                double sx = Double.parseDouble(br.readLine());
                double sy = Double.parseDouble(br.readLine());
                double ex = Double.parseDouble(br.readLine());
                double ey = Double.parseDouble(br.readLine());
                //tells us how many obstacles are in the level
                int numObstacles = Integer.parseInt(br.readLine());
                AbstractObstacle[] obstacles = new AbstractObstacle[numObstacles]; //set size of obstacle array
                //loop through and create all obstacles
                for (int j = 0; j < numObstacles; j++) {
                    String type = br.readLine();
                    int obstacleX = Integer.parseInt(br.readLine());
                    int obstacleY = Integer.parseInt(br.readLine());
                    //if the obstacle is a rectangle, it has a width and a heigth
                    if (type.equals("Rectangle")) {
                        int obstacleHeight = Integer.parseInt(br.readLine());
                        int obstacleWidth = Integer.parseInt(br.readLine());
                        //create a new rectangle and add it to the array 
                        Rectangle rect = new Rectangle(obstacleX, obstacleY, obstacleHeight, obstacleWidth);
                        obstacles[j] = rect;
                        //otherwise it must be a ring and it has a radius
                    } else {
                        int obstacleRadius = Integer.parseInt(br.readLine());
                        //create a new ring and add it to the array
                        Ring r = new Ring(obstacleX, obstacleY, obstacleRadius);

                        obstacles[j] = r;
                    }

                }
                Level level = new Level(sx, sy, ex, ey, obstacles, p);
                levels[i] = level;
            }
            return levels;
            //catch a file not found error
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
