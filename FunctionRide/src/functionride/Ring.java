/*
 * Sukhraj Garcha
 * May 28th 2019
 * template for a ring type obstacle object
 */
package functionride;
import java.awt.Color;
import java.awt.Graphics2D;


public class Ring extends AbstractObstacle {  
    
    private int size = 10;
    /**
     * main and only constructor that creates a new ring with all attributes
     * @param x x location
     * @param y y location
     * @param radius the radius of the ring
     */
    public Ring(double x, double y, double width, double height) { 
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * draws a circle 
     * @param g the drawing utensil
     */
    public void draw(Graphics2D g, int x, int y, int width, int height) {
        g.setColor(Color.YELLOW);
        g.fillOval( x, y, width, height);
    }
    /**
     * makes an exact copy of a specific ring obstacle
     * @return the copied version  
     */
    public Ring clone() { 
        Ring r2 = new Ring(x,y,width, height);
        return r2;
    }
    
}
