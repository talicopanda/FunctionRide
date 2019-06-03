/*
 * Sukhraj Garcha
 * May 28th 2019 
 * template for a level type object
 */
package functionride; 
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.*;
public class Level {
    //attributes 
    private Point startPoint;
    private Point endPoint;
    private AbstractObstacle[] obstacles; 
    

    /**
     * constructor for a new level with all attributes 
     * @param startPoint the position of the starting platform
     * @param endPoint the position of the ending platform 
     * @param obstacles an array of all obstacles in the level 
     */
    public Level (Point startPoint, Point endPoint, AbstractObstacle[] obstacles) { 
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.obstacles = obstacles; 
    }
    
    public void setStartPoint(Point startPoint) { 
        this.startPoint = startPoint; 
    }
    
    public Point getStartPoint() { 
        return startPoint;
    }
     public void setEndPoint(Point endPoint) { 
        this.endPoint = endPoint; 
    }
    
    public Point getendPoint() { 
        return endPoint;
    }
    /**
     * draws the starting platform
     * @param g the drawing utensil
     */
    public void drawStart(Graphics2D g) { 
        g.drawRect((int)startPoint.getX(),(int)startPoint.getY(), 30, 10);
    }
     /**
     * draws the ending platform
     * @param g the drawing utensil 
     */
    public void drawEnd(Graphics2D g) { 
        g.drawRect((int)endPoint.getX(),(int)endPoint.getY(), 30, 10);
    }
    public boolean checkCollision(Player p, AbstractObstacle[] obstacles) { 
        return false;  
    }
    /**
     * draws all components of the level
     * @param g the drawing utensil 
     */
    public void showLevel(Graphics2D g) { 
        drawStart(g); 
        drawEnd(g); 
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i].draw(g); 
        }
    } 
    /**
     * makes an exact copy of a level
     * @return the copied version of a level 
     */
    public Level clone() { 
        Level l2 = new Level(startPoint, endPoint, obstacles); 
        return l2;
    }
    
    public String toString() { 
        return "LEVEL:\nStart Point: " + startPoint + "\nEnd Point: " + endPoint + 
                "\nObstacles: " + obstacles;
    }
}
