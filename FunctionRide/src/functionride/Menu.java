package functionride;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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
    public Rectangle playButton=new Rectangle (FunctionRide.WIDTH/2-50,250,100,50);
    public Rectangle helpButton=new Rectangle (FunctionRide.WIDTH/2-50,350,100,50);
    public Rectangle quitButton=new Rectangle (FunctionRide.WIDTH/2-50,450,100,50);
    
    public void render (Graphics g){
       Graphics2D g2d=(Graphics2D)g;
        Font fnt0=new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
         Font fnt1=new Font("arial",Font.BOLD,25);
        g.setColor(Color.white);
        g.drawString("FUNCTION RIDE",FunctionRide.WIDTH/2-180,100);
        g.setFont(fnt1);
        g.drawString("Play",playButton.x+20,playButton.y+30);
        g.drawString("Help",helpButton.x+20,helpButton.y+30);
        g.drawString("Quit",quitButton.x+20,quitButton.y+30);
       g2d.draw(playButton);
       g2d.draw(helpButton);
       g2d.draw(quitButton);
        
    }
    
}
