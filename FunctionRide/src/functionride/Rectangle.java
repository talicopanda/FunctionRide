/*
 * Sukhraj Garcha
 * May 27 2019
 * rectangular obstacle class 
 */
package functionride;


public class Rectangle extends AbstractObstacle {
    /**
     * main and only constructor for a rectangle obstacle
     * @param x x location 
     * @param y y location
     * @param height height of rectangle
     * @param width width of rectangle
     */
    public Rectangle(int x, int y, int height, int width) { 
        super(x,y,height,width); 
    }  
    
    /** 
     * clones the current rectangle
     * @return the a new rectangle with the same attributes as given rectangle
     */
    public Rectangle clone() { 
        Rectangle r = new Rectangle(x, y, height, width);
        return r; 
    } 
}
