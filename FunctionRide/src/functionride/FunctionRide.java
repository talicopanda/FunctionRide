/*
Function Ride App
5/27/2019
Tales Scopinho, Sukhraj Garcha, Sergio Hernandez
 */
package functionride;

import java.awt.Dimension;
import java.awt.Canvas;
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
    
    private boolean running = false;
    private Thread thread;
    
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
    
    @Override
    public void run(){
        while(running){
            System.out.println("working");
        }
        stop();
    }
    
    public static void main(String args[]){
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
}
