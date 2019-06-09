/*
 * Tales, Sergio, Sukhraj
 * May 30 2019
 * class keeps track of all mouse input
 */
package functionride;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nm
 */
public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        //if we are on the menu screen
        if (FunctionRide.State == FunctionRide.STATE.MENU) {
            //check if user clicks play button
            if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                if (my >= 250 && my <= 300) {
                    //move on to the level select screen
                    FunctionRide.State = FunctionRide.STATE.LEVEL_SCREEN;
                }
            }
            //check if user clicks the quit button
            if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                if (my >= 450 && my <= 500) {
                    //close the game
                    System.exit(0);
                }
            }
            //if we are on the level select screen
        } else if (FunctionRide.State == FunctionRide.STATE.LEVEL_SCREEN) { //levelscreen
            //check which level button user clicks and open that level
            if (mx >= 65 && mx <= 115) {
                if (my >= 400 && my <= 450) {
                    FunctionRide.State = FunctionRide.STATE.LEVEL1;
                }
            } else if (mx >= 160 && mx <= 210) {
                if (my >= 315 && my <= 365) {
                    FunctionRide.State = FunctionRide.STATE.LEVEL2;
                }
            } else if (mx >= 275 && mx <= 325) {
                if (my >= 500 && my <= 550) {
                    FunctionRide.State = FunctionRide.STATE.LEVEL1;
                }
            } else if (mx >= 490 && mx <= 550) {
                if (my >= 470 && my <= 520) {
                    FunctionRide.State = FunctionRide.STATE.LEVEL1;
                }
                //check if user clicks exit button 
            } else if (mx >= 900 && mx <= 950) {
                if (my >= 50 && my <= 100) {
                    //close the game
                    System.exit(0);
                }
            }
            //if we are on the level complete screen
        } else if (FunctionRide.State == FunctionRide.STATE.COMPLETED_SCREEN) { 
            //check if user wants to move on to the next level
            if (mx >= FunctionRide.WIDTH / 2 - 100 && mx <= FunctionRide.WIDTH / 2 - 100 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    int current = FunctionRide.currentLevel;
                    FunctionRide.currentLevel = current++;
                }
            }
            //if we are on a level screen    
        } else {
            //check if user clicks the play button
            if (mx >= FunctionRide.WIDTH / 2 - 100 && mx <= FunctionRide.WIDTH / 2 - 100 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    //open screen that allows user to enter a function
                    JFrame funcTab = new functionmaker();;
                    funcTab.setVisible(true);
                }
            //check if user clicks the levels button
            } else if (mx >= FunctionRide.WIDTH / 2 - 280 && mx <= FunctionRide.WIDTH / 2 - 280 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    //show the level select screen
                    FunctionRide.State = FunctionRide.STATE.LEVEL_SCREEN;
                }
            //check if user clicks quit
            } else if (mx >= FunctionRide.WIDTH / 2 - 460 && mx <= FunctionRide.WIDTH / 2 - 460 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    //close the game
                    System.exit(0);
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
