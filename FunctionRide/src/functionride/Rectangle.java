/*
 * Sukhraj Garcha
 * May 27 2019
 * rectangular obstacle class 
 */
package functionride;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends AbstractObject {

    /**
     * main and only constructor for a rectangle obstacle
     *
     * @param x x location
     * @param y y location
     * @param height height of rectangle
     * @param width width of rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * draws the rectangle obstacle
     * @param g the drawing utensil
     * @param x the x location of the obstacle
     * @param y the y location of the obstacle
     * @param width the width of the obstacle
     * @param height the height of the obstacle
     */
    public void draw(Graphics2D g, double x, double y, double width, double height) {
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * clones the current rectangle
     *
     * @return the a new rectangle with the same attributes as given rectangle
     */
    public Rectangle clone() {
        Rectangle r = new Rectangle(x, y, height, width);
        return r;
    }
    /**
     * determine which parts of the obstacle will result in a crash
     * @return an array of the values that will result in a crash 
     */
    public double[] getCollisionArea() {
        //0-x1 1-x2 2-y1 3-y2
        double[] area = {x, x + width, y, y - height};
        return area;
    }
}
