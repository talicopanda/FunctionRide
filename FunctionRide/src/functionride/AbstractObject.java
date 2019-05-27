/*
 * Sukhraj Garcha
 * May 23 2019
 * abstract object class for the purpose of inheritance 
 */
package functionride;


abstract public class AbstractObject {
     //attributes 
    protected int x;
    protected int y;
    protected int height;
    protected int width; 

    /**
     * main constructor
     *
     * @param x x location
     * @param y y location
     * @param height height of object
     * @param width width of object
     */
    public AbstractObject(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height; 
        this.width = width;  
    }   
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    } 

    public String toString() {
        return "ABSTRACT OBJECT \nX: " + x + "\nY: " + y +  
                "\nWidth: " + width + "\nHeight: " + height;
    }
}
