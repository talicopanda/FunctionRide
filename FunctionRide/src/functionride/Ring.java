/*
 * Sukhraj Garcha
 * May 28th 2019
 * template for a ring type obstacle object
 */
package functionride;
import java.awt.Graphics2D;


public class Ring extends AbstractObstacle {
    //additional attribute
    private int radius;   
    
    /**
     * main and only constructor that creates a new ring with all attributes
     * @param x x location
     * @param y y location
     * @param radius the radius of the ring
     */
    public Ring(int x, int y, int radius) { 
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    
    /**
     * draws a circle 
     * @param g the drawing utensil
     */
    public void draw(Graphics2D g) { 
        g.drawOval(x, y, radius, radius);
    }
    /**
     * allows user to set the size of the ring 
     * @param radius the radius of the circle
     */
    public void setRadius(int radius) { 
        this.radius = radius;
    }
    /**
     * allows user to get the radius of the ring
     * @return return the radius
     */
    public int getRadius() { 
        return radius;
    }
    /**
     * makes an exact copy of a specific ring obstacle
     * @return the copied version  
     */
    public Ring clone() { 
        Ring r2 = new Ring(x,y,radius);
        return r2;
    }
    
}
