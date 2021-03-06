/*
 * Tales, Sergio, Sukhraj
 * June 2 2019
 * the level completed screen
 */
package functionride;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Usuario
 */
public class LevelCompleted {
    //attribute to keep track of which level user is on
    private int currentLevel;
    
    /**
     * main and only constructor
     * @param currentLevel the level that we are on
     */
    public LevelCompleted(int currentLevel){
        this.currentLevel = currentLevel;
    }
    /**
     * draw components of the screen
     * @param g drawing utensil
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g2d.setColor(Color.BLACK); 
        Level.drawInfo(g, "Congratulations!", fnt0, FunctionRide.WIDTH/2 - 200, 100); 
        Font fnt1 = new Font("arial", Font.PLAIN, 30);
        Level.drawInfo(g, "You just cleared level " + currentLevel + "!", fnt1, FunctionRide.WIDTH/2-160,
        FunctionRide.HEIGHT/2-100);
        drawButtons(g2d);
    }
    /**
     * draw the buttons on the screen
     * @param g2d drawing utensil
     */
    public void drawButtons(Graphics2D g2d){
        g2d.setColor(Color.white);
        //draw buttons
        g2d.fillRoundRect(FunctionRide.WIDTH / 2 + 100, FunctionRide.HEIGHT / 2 + 200, 100, 40,20,20);
        g2d.fillRoundRect(FunctionRide.WIDTH / 2 - 200, FunctionRide.HEIGHT / 2 + 200, 100, 40,20,20);
        g2d.fillRoundRect(FunctionRide.WIDTH / 2 - 50, FunctionRide.HEIGHT / 2 + 200, 100, 40,20,20);
        //draw text 
        g2d.setColor(Color.BLACK);
        Level.drawInfo(g2d, "Next", Level.btnFont, FunctionRide.WIDTH / 2 + 100 + 29, FunctionRide.HEIGHT / 2 + 200 + 25);
        Level.drawInfo(g2d, "Menu", Level.btnFont, FunctionRide.WIDTH / 2 - 200 + 29, FunctionRide.HEIGHT / 2 + 200 + 25);
        Level.drawInfo(g2d, "Levels", Level.btnFont, FunctionRide.WIDTH / 2 - 50 + 20, FunctionRide.HEIGHT / 2 + 200 + 25); 
    }
}