/*
 * Sukhraj, Tales, Sergio
 * June 9 2019
 * interface contains everything something must have to be an object
 */
package functionride;


public interface Object { 
    //get the x location
    public double getX(); 
    //set the x location
    public void setX(double x);
    //get the y location
    public double getY();
    //set the y locations
    public void setY(double y);
    //get the width size
    public double getWidth();
    //set the width size
    public void setWidth(double width);
    //get the height size
    public double getHeight();
    //set the height size
    public void setHeight(double height);
    //print all info about an object as a string 
    public String toString(); 
}
