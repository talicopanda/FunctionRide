package functionride;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author seher4467
 */
public class Menu {

    public Rectangle playButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 250, 100, 50);
    public Rectangle helpButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 350, 100, 50);
    public Rectangle quitButton = new Rectangle(FunctionRide.WIDTH / 2 - 50, 450, 100, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g2d.setFont(fnt0);
        Font fnt1 = new Font("arial", Font.BOLD, 25);
        g2d.setColor(Color.WHITE);
        g2d.drawString("FUNCTION RIDE", FunctionRide.WIDTH / 2 - 180, 100);
        g2d.fill(playButton);
        g2d.fill(helpButton);
        g2d.fill(quitButton);
        g2d.setColor(Color.BLACK);
        g2d.setFont(fnt1);
        g2d.drawString("Play", playButton.x + 20, playButton.y + 30);
        g2d.drawString("Help", helpButton.x + 20, helpButton.y + 30);
        g2d.drawString("Quit", quitButton.x + 20, quitButton.y + 30);
    }

}
