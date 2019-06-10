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
    
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    } 

    public String toString() {
        return "ABSTRACT OBJECT \nX: " + x + "\nY: " + y +  
                "\nWidth: " + width + "\nHeight: " + height;
    }
}
