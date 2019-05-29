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
     * reads in a data file that contains info about a level 
     * @param path the file path to the data file
     */
    public void readLevel(String path){
        //try to open a connection to the file 
        try {
            FileReader fr = new FileReader(path); 
            BufferedReader br = new BufferedReader(fr); 
            //read in the starting and ending points
            startPoint.x = Integer.parseInt(br.readLine());
            startPoint.y = Integer.parseInt(br.readLine()); 
            endPoint.x = Integer.parseInt(br.readLine());
            endPoint.y = Integer.parseInt(br.readLine());
            //tells us how many obstacles are in the level
            int numObstacles = Integer.parseInt(br.readLine()); 
            obstacles = new AbstractObstacle[numObstacles]; //set size of obstacle array
            //loop through and create all obstacles
            for (int i = 0; i < numObstacles; i++) {
                String type = br.readLine();
                int obstacleX = Integer.parseInt(br.readLine());
                int obstacleY = Integer.parseInt(br.readLine());
                //if the obstacle is a rectangle, it has a width and a heigth
                if (type.equals("Rectangle")) {  
                    int obstacleHeight = Integer.parseInt(br.readLine()); 
                    int obstacleWidth = Integer.parseInt(br.readLine());
                    //create a new rectangle and add it to the array 
                    Rectangle rect = new Rectangle(obstacleX, obstacleY, obstacleHeight, obstacleWidth);
                    obstacles[i] = rect;
                //otherwise it must be a ring and it has a radius
                }else { 
                    int obstacleRadius = Integer.parseInt(br.readLine());
                    //create a new ring and add it to the array
                    Ring r = new Ring(obstacleX, obstacleY, obstacleRadius);
                    obstacles[i] = r;  
                } 
            }
        //catch a file not found error
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
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
}
