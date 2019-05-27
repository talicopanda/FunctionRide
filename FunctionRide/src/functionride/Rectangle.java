/*
 * Sukhraj Garcha
 * May 27 2019
 * rectangular obstacle class 
 */
package functionride;


public class Rectangle extends AbstractObstacle {
    public Rectangle(int x, int y, int height, int width) { 
        super(x,y,height,width); 
    }  
     
    public Rectangle clone() { 
        Rectangle r = new Rectangle(x, y, height, width);
        return r; 
    } 
}
