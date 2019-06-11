/*
 * Sukhraj Garcha
 * May 23 2019
 * abstract object class for the purpose of inheritance 
 */
package functionride;

import java.awt.Graphics2D;


abstract public class AbstractObject implements Object {
     //attributes 
    protected double x;
    protected double y;
    protected double height;
    protected double width; 
    
    /**
     * empty constructor for inheritance 
     */
    public AbstractObject() { 
        
    }

    /**
     * main constructor
     * @param x x location
     * @param y y location
     * @param height height of object
     * @param width width of object
     */
    public AbstractObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.height = height; 
        this.width = width;  
    }   
    
    /**
     * allows us to access the x location
     * @return the x location
     */
    public double getX() {
        return x;
    }
    /**
     * allows us to set the x location
     * @param x the new x location
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * allows us to access the y location
     * @return the y location
     */
    public double getY() {
        return y;
    }
    /**
     * allows us to set the y location
     * @param y the new y location
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * allows us to access the width of an object
     * @return the width
     */
    public double getWidth() {
        return width;
    }
    /**
     * allows us to set the width of an object
     * @param width the new width
     */
    public void setWidth(double width) {
        this.width = width;
    }
    /**
     * allows us to access the height of an object
     * @return the height
     */
    public double getHeight() {
        return height;
    }
    /**
     * allows us to set the height of an object
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }  
    /**
     * prints out all the info about an object
     * @return a string containing all the info
     */
    public String toString() {
        return "ABSTRACT OBJECT \nX: " + x + "\nY: " + y +  
                "\nWidth: " + width + "\nHeight: " + height;
    }
}
