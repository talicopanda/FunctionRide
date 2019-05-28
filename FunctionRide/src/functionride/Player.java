/*
 * Sukhraj Garcha
 * May 28th 2019
 * template for a player type object 
 */
package functionride;

import java.awt.Graphics2D;
import java.awt.Point; 

public class Player extends AbstractObject {
    //new attribute 
    private int speed; 
    /**
     * main and only constructor for a player 
     * @param x x location
     * @param y y location 
     * @param width width of player
     * @param height height of player
     * @param speed the speed that the player will travel at
     */
    public Player(int x, int y, int width, int height, int speed) { 
        super(x,y,width,height); 
        this.speed = speed; 
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
    
    public void draw(Graphics2D g, String imageLoc) { 
        
    }  
    /**
     * updates the position of the player to follow the function that is drawn
     * @param graphPoints an array of all the points on the graph 
     */
    public void updatePos(Point[] graphPoints) { 
        
    }
}
