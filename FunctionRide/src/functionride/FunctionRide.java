/*
Function Ride App
5/27/2019
Tales Scopinho, Sukhraj Garcha, Sergio Hernandez
The main game class
 */
package functionride;

import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

    boolean askedName = false;

    public static ArrayList<Integer> levelsCompleted = new ArrayList<Integer>();

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage background = null;
    private BufferedImage levelSelectCoaster = null;
    private BufferedImage levelSelectBackground = null;
    private BufferedImage levelSelectQuit = null;
    private BufferedImage levelComplete = null;
    private BufferedImage icon = null;
    private BufferedImage stars = null;
    private Menu menu;
    private LevelSelect ls;
    public static int preLevel;
    public static ArrayList<CompletedLevels> highScores = new ArrayList();
    private static Player p;
    private Level[] levels;
    //private Menu menu;

    public static enum STATE {
        MENU,
        LEVEL_SCREEN,
        COMPLETED_SCREEN,
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4
    };

    public static int currentLevel;
    //start the intial state as the menu scree n
    public static STATE State = STATE.MENU;

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        //try to load all of the images
        try {
            spriteSheet = loader.loadImage("res\\sprite_sheet.png");
            background = loader.loadImage("res\\background.jpg");
            levelSelectCoaster = loader.loadImage("res\\coaster.png");
            levelSelectBackground = loader.loadImage("res\\scene.jpg");
            levelSelectQuit = loader.loadImage("res\\quit.jpg");
            levelComplete = loader.loadImage("res\\level_complete.jpg");
            icon = loader.loadImage("res\\rollercoaster.jpg");
            stars = loader.loadImage("res\\stars.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //initialize components that we need 
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        menu = new Menu();
        ls = new LevelSelect();
        p = new Player(200, 200);
        levels = readLevel("res\\Level.txt");
        this.addMouseListener(new MouseInput());
    }
    //starts the program
    private synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();

    }
    //stops the program
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
    //makes the program run 60 fps
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
        
        //draws each screen
        if (State == STATE.MENU) {
            g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
            menu.render(g);
            if (!askedName) {
                JFrame getName = new LName();
                getName.setVisible(true);
                getName.setLocationRelativeTo(null);
                askedName = true;
                getHighScore();
            }
        } else if (State == STATE.LEVEL_SCREEN) {
            g.drawImage(levelSelectBackground, 0, 0, getWidth(), getHeight() + 50, this);
            g.drawImage(levelSelectCoaster, 0, getHeight() / 2, getWidth(), getHeight() / 2, this);
            g.drawImage(levelSelectQuit, 900, 50, 50, 50, this);
            ls.render(g);
        } else if (State == STATE.COMPLETED_SCREEN) {
            LevelCompleted completedScreen = new LevelCompleted(currentLevel);
            g.drawImage(levelComplete, 0, 0, getWidth(), getHeight(), this);
            g.drawImage(stars, getWidth() / 2 - 250, getHeight() / 2 - 70, this);
            completedScreen.render(g);
        } else if (State == STATE.LEVEL1) {
            currentLevel = 1;
            boolean completed = false;
            //only renders level if it has not been completed
            for (int levels : levelsCompleted) {
                if (levels == currentLevel) {
                    completed = true;
                }
            }
            if (!completed) {
                levels[0].render(g);
            } else{ //otherwise render completed screen
                State = State.COMPLETED_SCREEN;
            }
        } else if (State == STATE.LEVEL2) {
            currentLevel = 2;
            boolean completed = false;
            //only renders level if it has not been completed
            for (int levels : levelsCompleted) {
                if (levels == currentLevel) {
                    completed = true;
                }
            }
            if (!completed) {
                levels[1].render(g);
            } else{ //otherwise render completed screen
                State = State.COMPLETED_SCREEN;
            }
        } else if (State == STATE.LEVEL3) {
            currentLevel = 3;
            boolean completed = false;
            //only renders level if it has not been completed
            for (int levels : levelsCompleted) {
                if (levels == currentLevel) {
                    completed = true;
                }
            }
            if (!completed) {
                levels[2].render(g);
            } else{ //otherwise render completed screen
                State = State.COMPLETED_SCREEN;
            }
        } else if (State == STATE.LEVEL4) {
            currentLevel = 4;
            boolean completed = false;
            //only renders level if it has not been completed
            for (int levels : levelsCompleted) {
                if (levels == currentLevel) {
                    completed = true;
                }
            }
            if (!completed) {
                levels[3].render(g);
            } else{ //otherwise render completed screen
                State = State.COMPLETED_SCREEN;
            }
        }

        preLevel = currentLevel;

        ///////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    public static void getHighScore() {
        FileReader readfile = null;
        BufferedReader reader = null;
        try {
            readfile = new FileReader("res\\HighScore.txt");
            reader = new BufferedReader(readfile);
            boolean eof = false;
            //loop until we hit the end
            while (!eof) {
                String Name = reader.readLine();
                int levelsCleared = Integer.parseInt(reader.readLine());
                CompletedLevels b = new CompletedLevels(levelsCleared, Name);
                //add the book to the list
                highScores.add(b);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileMaker() {
        File scoreFile = new File("res\\HighScore.txt");
        String input = "";
        try {
            FileReader fr = new FileReader(scoreFile);
            BufferedReader br = new BufferedReader(fr);
            boolean eof = false;
            while (!eof) {
                String temp = br.readLine();
                if (temp == null) {
                    eof = true;
                } else {
                    input += temp + "\n";
                }
            }

        } catch (Exception e) {
        }
        try {
            if (!scoreFile.exists()) {
                scoreFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        try {
            writeFile = new FileWriter(scoreFile);
            writer = new BufferedWriter(writeFile);
            writer.write(input + LName.name + "\n" + levelsCompleted.size());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
            }
        }

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
                Rectangle[] obstacles = new Rectangle[numObstacles]; //set size of obstacle array 
                //loop through and create all obstacles
                for (int j = 0; j < numObstacles; j++) {
                    double obstacleX = Double.parseDouble(br.readLine());
                    double obstacleY = Double.parseDouble(br.readLine());
                    //if the obstacle is a rectangle, it has a width and a heigth
                    double obstacleWidth = Double.parseDouble(br.readLine());
                    double obstacleHeight = Double.parseDouble(br.readLine());
                    //create a new rectangle and add it to the array 
                    Rectangle rect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
                    obstacles[j] = rect;
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
