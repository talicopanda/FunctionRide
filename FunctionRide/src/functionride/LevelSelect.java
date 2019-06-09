/*
 * Tales, Sergio, Sukhraj
 * June 3 2019
 * the level select menu 
 */
package functionride;

import java.awt.Color; 
import java.awt.Font;  
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Rectangle;
import java.util.Set;

public class LevelSelect {
    
    public Rectangle l1 = new Rectangle (65,400,50,50);
    public Rectangle l2 = new Rectangle (160,315,50,50);
    public Rectangle l3 = new Rectangle (275,500,50,50);
    public Rectangle l4 = new Rectangle (490,470,50,50); 
    
    
    /**
     * draws the level select screen
     * @param g the drawing tool
     */
    public void render (Graphics g) { 
        Graphics2D g2d = (Graphics2D)g;
        Font fnt0 = new Font("arial",Font.BOLD,70);
        g.setFont(fnt0);
        g.setColor(Color.BLUE); 
        g.drawString("Level Select", FunctionRide.WIDTH/2-190, 100);
        Font fnt1 = new Font("arial", Font.PLAIN,30); 
        g.setColor(Color.black);
        g.setFont(fnt1);
        //draw all the buttons
        g2d.fill(l1);
        g2d.fill(l2);
        g2d.fill(l3); 
        g2d.fill(l4);
        g.setColor(Color.white);  
        //draw all the text inside the buttons
        g.drawString("1", l1.x+15, l1.y+35);
        g.drawString("2", l2.x+15, l2.y+35); 
        g.drawString("3", l3.x+15, l3.y+35);
        g.drawString("4", l4.x+15, l4.y+35);
    }
}