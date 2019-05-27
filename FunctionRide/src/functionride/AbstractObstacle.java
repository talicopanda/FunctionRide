/*
 * Sukhraj Garcha
 * May 27 2019
 * abstract class that extends the object class that is used for obstacle inheritance
 */
package functionride;
import java.awt.Graphics2D;

public class AbstractObstacle extends AbstractObject {

 

    /**
     * secondary constructor (runs constructor from super class)
     *
     * @param x x location
     * @param y y location
     * @param height height of rectangle
     * @param width width of rectangle
     */
    public AbstractObstacle(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
    /**
     * draw a rectangle 
     * @param g2d drawing utensil
     */
    public void draw (Graphics2D g2d) { 
        g2d.drawRect(x,y,width,height);
        
    }
    
    /**
     * determine the area when the player is colliding with an obstacle
     * @return an array of x/y locations 
     */
    public int[] getCollisionArea() { 
         
    }
    
}
