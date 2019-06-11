/*
 * Sukhraj, Tales, Sergio
 * May 30 2019
 * the main menu class
 */
package functionride;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Rectangle;

public class Menu {
    //the buttons that will be on the menu
    public Rectangle playButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 250, 100, 50);
    public Rectangle helpButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 350, 100, 50);
    public Rectangle quitButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 450, 100, 50);
    public Rectangle leaderboardButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 550, 100, 50);
    
    /**
     *draw the components of the menu 
     * @param g the drawing utensil
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //3 different fonts that we will need
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        Font fnt1 = new Font("arial", Font.BOLD, 25);
        Font fnt2 = new Font("arial", Font.BOLD, 15);
        g2d.setFont(fnt0);
        g2d.setColor(Color.WHITE);
        //draw title
        g2d.drawString("FUNCTION RIDE", FunctionRide.WIDTH / 2 - 180, 100);
        //draw the buttons
        g2d.fill(playButton);
        g2d.fill(helpButton);
        g2d.fill(quitButton);
        g2d.fill(leaderboardButton);
        g2d.setColor(Color.BLACK);
        //outline the buttons
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
        g2d.draw(leaderboardButton);
        g2d.setFont(fnt1);
        //draw the text inside the buttons 
        g2d.drawString("Play", playButton.x + 20, playButton.y + 30);
        g2d.drawString("Help", helpButton.x + 20, helpButton.y + 30);
        g2d.drawString("Quit", quitButton.x + 20, quitButton.y + 30);
        g2d.setFont(fnt2);
        g2d.drawString("Leaderboard", leaderboardButton.x + 5, leaderboardButton.y + 30);
    }

}
