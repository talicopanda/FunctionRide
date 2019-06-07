/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionride;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Usuario
 */
public class LevelCompleted {
    private int currentLevel;
    
    public LevelCompleted(int currentLevel){
        this.currentLevel = currentLevel;
    }
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(198, 168, 103));
        g2d.fillRect(0, 0, FunctionRide.WIDTH + 32, FunctionRide.HEIGHT + 32);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Congratulations you just cleared level "+currentLevel, 500, 500);
    }
    
    public void drawButtons(Graphics2D g2d){
        //draw play again
        //draw next level
        //draw go back to main menu
    }
}
