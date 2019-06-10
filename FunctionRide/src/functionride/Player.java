/*
 * Sukhraj Garcha
 * May 28th 2019
 * template for a player type object 
 */
package functionride;

import java.awt.Graphics2D;
import java.awt.Point; 
import java.awt.image.BufferedImage;

public class Player extends AbstractObject { 
    //new attribute 
    private int speed;  
    private BufferedImage player;
    public int SIZE = 36;
    /**
     * main and only constructor for a player 
     * @param x x location
     * @param y y location 
     * @param width width of player
     * @param height height of player
     * @param speed the speed that the player will travel at
     * @param game the game class to import spritesheet from 
     */
    public Player(int x, int y) { 
        this.x = x; 
        this.y = y; 
        speed = 1; 
        //gets the spritesheet that was created in the game class to use it in this class
        SpriteSheet ss = new SpriteSheet(FunctionRide.spriteSheet);
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
    
    public void updatePos(int x, int y) { 
        this.x = x;
        this.y = y;
    }
    
    public void tick() { 
        //update player
    }
    /**
     * draws the player sprite
     * @param g2d 2d drawing utensil
     */

    public void render(Graphics2D g2d) { 
         g2d.drawImage(player,(int) x,(int) y, SIZE, SIZE, null);

    }
}
