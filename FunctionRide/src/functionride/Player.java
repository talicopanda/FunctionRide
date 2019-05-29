/*
 * Sukhraj Garcha
 * May 28th 2019
 * template for a player type object 
 */
package functionride;

import java.awt.Graphics;
import java.awt.Point; 
import java.awt.image.BufferedImage;

public class Player extends AbstractObject { 
    //new attribute 
    private int speed;  
    private BufferedImage player;
    /**
     * main and only constructor for a player 
     * @param x x location
     * @param y y location 
     * @param width width of player
     * @param height height of player
     * @param speed the speed that the player will travel at
     * @param game the game class to import spritesheet from 
     */
    public Player(int x, int y, int width, int height, int speed, FunctionRide game) { 
        super(x,y,width,height); 
        this.speed = speed; 
        //gets the spritesheet that was created in the game class to use it in this class
        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        //set the player to the correct sprite 
        player = ss.grabImage(1,1,32,32);  
    }
    /**
     * allows user to set the player's speed
     * @param speed the speed that the user wants to set to
     */
    public void setSpeed(int speed) { 
        this.speed = speed;
    }
    /**
     * allows user to access the speed of the player
     * @return the speed of the player
     */
    public int getSpeed() { 
        return speed;
    }
    
    public static void tick() { 
        
    } 
    /**
     * draws the player sprite
     * @param g 2d drawing utensil
     */
    public void render(Graphics g) { 
         g.drawImage(player, x, y, null);
    }  
    /**
     * updates the position of the player to follow the function that is drawn
     * @param graphPoints an array of all the points on the graph 
     */
    public void updatePos(Point[] graphPoints) { 
        
    }
}
