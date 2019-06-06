/*
Function Ride App
5/27/2019
Tales Scopinho, Sukhraj Garcha, Sergio Hernandez
 */
package functionride;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author tasco0966
 */
public class FunctionRide extends Canvas implements Runnable {

 


    public static final int WIDTH = 960;
    public static final int HEIGHT = 640;
    public static final int SCALE = 1;
    public static final String TITLE = "Function Ride";
    
    private boolean running = false;
    private Thread thread;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private static Image icon=null;
    private Menu menu;
    private Player p;
    
  
    
    public static  enum STATE{
        MENU,
        LEVELSCREEN,
        LEVEL1
    };
    
    public static  STATE State = STATE.MENU;
    
    public void init(){
        System.out.println("init");
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("res\\sprite_sheet.png");
            background = loader.loadImage("res\\background.jpg");
            icon = new ImageIcon(("res\\rollercoastergif.gif")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SpriteSheet ss = new SpriteSheet(spriteSheet);
        menu=new Menu();
        p = new Player(200, 200, this);
        this.addMouseListener(new MouseInput());
        //menu = new Menu();
    }
  
    
    private synchronized void start(){
        if(running) return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
        
    }
    
    private synchronized void stop(){
        if(!running) return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
    
    public void run(){
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        long now;
        while(running){ 
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                delta--;
                updates++;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        if(State == STATE.LEVEL1){
           p.tick();
        }
        
    }
  
    
    private void render(){
       
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            System.out.println("bs = null");
            createBufferStrategy(2);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
    
        //////////////////////////////////////////// drawing area
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        
        if(State == STATE.LEVEL1){
        g.drawImage(background, 0 ,0, getWidth(), getHeight(), this);
        p.render(g); 
        } else if(State == STATE.MENU){
           
             g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);  
            menu.render(g);
        

          
        }
        
        
        ///////////////////////////////////////////
       g.dispose();
        bs.show();
    }
    
   

   

    public static void main(String args[]){
        FunctionRide game = new FunctionRide();
     
        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        
        JFrame frame = new JFrame(game.TITLE);
       
    frame.setLayout(new BorderLayout());
  
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }
    
    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
}
