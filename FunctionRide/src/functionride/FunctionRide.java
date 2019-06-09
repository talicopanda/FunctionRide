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
    //images needed
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage levelSelectCoaster = null;
    private BufferedImage levelSelectBackground = null;
    private BufferedImage levelSelectQuit = null;
    private BufferedImage levelComplete = null;
    //different attributes of the game
    private Menu menu;
    private LevelSelect ls;
    private static Player p;
    private Level[] levels;
    //private Menu menu;

    public static enum STATE {
        MENU,
        LEVEL_SCREEN,
        COMPLETED_SCREEN,
        LEVEL1,
        LEVEL2
    };

    public static int currentLevel;
    //set the initial state to the menu screen
    public static STATE State = STATE.MENU;
    
    /**
     * initialize components of the game
     */
    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        //try to load in images
        try {
            spriteSheet = loader.loadImage("res\\sprite_sheet.png");
            levelSelectCoaster = loader.loadImage("res\\coaster.png");
            levelSelectBackground = loader.loadImage("res\\scene.jpg");
            levelSelectQuit = loader.loadImage("res\\quit.jpg");
            levelComplete = loader.loadImage("res\\level_complete.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //create new spritesheet to load in player
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        menu = new Menu();
        ls = new LevelSelect();
        p = new Player(200, 200);
        //read in levels
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
    /**
     * draw components of the game
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////////////// drawing area

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        //draw the different components of the game depending on which state we are on
        if (State == STATE.MENU) {
            menu.render(g);
        } else if (State == STATE.LEVEL_SCREEN) {
            g.drawImage(levelSelectBackground, 0, 0, getWidth(), getHeight()+50, this); 
            g.drawImage(levelSelectCoaster, 0, getHeight()/2, getWidth(), getHeight()/2, this);
            g.drawImage(levelSelectQuit, 900, 50, 50, 50, this); 
            ls.render(g);

        } else if (State == STATE.COMPLETED_SCREEN) {
            LevelCompleted completedScreen = new LevelCompleted(currentLevel);
            g.drawImage(levelComplete, 0, 0, getWidth(), getHeight(), this);
            completedScreen.render(g);
            currentLevel = 0;
        //draw level depending on which level we are on
        } else if (State == STATE.LEVEL1) {
            currentLevel = 1;
            levels[0].render(g);
        } else if (State == STATE.LEVEL2) {
            currentLevel = 2;
            levels[1].render(g);
        }

        if (State != State.COMPLETED_SCREEN) {
            if (currentLevel == 1) {
                State = STATE.LEVEL1;
            } else if (currentLevel == 2) {
                State = STATE.LEVEL2;
            }
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
    /**
     * get the spritesheet to draw the player
     * @return the spritesheet 
     */
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
                    double obstacleX = Double.parseDouble(br.readLine());
                    double obstacleY = Double.parseDouble(br.readLine());
                    //if the obstacle is a rectangle, it has a width and a heigth
                    if (type.equals("Rectangle")) {
                        double obstacleWidth = Double.parseDouble(br.readLine());
                        double obstacleHeight = Double.parseDouble(br.readLine());
                        //create a new rectangle and add it to the array 
                        Rectangle rect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
                        obstacles[j] = rect;
                        //otherwise it must be a ring and it has a radius
                    } else {
                        double obstacleWidth = Double.parseDouble(br.readLine());
                        double obstacleHeight = Double.parseDouble(br.readLine());
                        //create a new rectangle and add it to the array 
                        Ring ring = new Ring(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
                        obstacles[j] = ring;
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
