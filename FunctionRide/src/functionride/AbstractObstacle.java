/*
 * Sukhraj Garcha
 * May 27 2019
 * abstract class that extends the object class that is used for obstacle inheritance
 */
package functionride;
import java.awt.Color;
import java.awt.Graphics2D;

public class AbstractObstacle extends AbstractObject {

    /**
     * empty constructor for inheritance 
     */ 
    public AbstractObstacle() { 
        
    } 
 
    /**
     * secondary constructor (runs constructor from super class)
     *
     * @param x x location
     * @param y y location
     * @param height height of rectangle
     * @param width width of rectangle
     */
    public AbstractObstacle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    /**
     * draw a rectangle 
     * @param g2d drawing utensil
     */
    public void draw (Graphics2D g, int x, int y, int width, int height) {
        g.setColor(Color.cyan);
        g.fillRect(x,y,width,height);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,width,height);
    }
    
    /**
     * determine the area when the player is colliding with an obstacle
     * @return an array of x/y locations 
     */
    public double[] getCollisionArea() { 
        //0-x1 1-x2 2-y1 3-y2
        double[] area = {x, x+width, y, y - height};
        return area;
    }
    
}
