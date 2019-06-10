package functionride;

import static functionride.FunctionRide.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
private boolean notAdded;
    public ArrayList<Integer> levelsCompleted = new ArrayList<Integer>();

    public static int cLevel;
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        //menu screen
        if (FunctionRide.State == FunctionRide.STATE.MENU) {
            if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                if (my >= 250 && my <= 300) {
                    JFrame getName = new LName();
                    getName.setVisible(true);
                    getName.setLocationRelativeTo(null);
                    FunctionRide.State = FunctionRide.STATE.LEVEL_SCREEN;

                }
            }
            if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                if (my >= 450 && my <= 500) {
                    System.exit(1);
                }

                if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                    if (my >= 550 && my <= 600) {
                        JFrame leaderboard = new leaderboard();
                        leaderboard.setLocationRelativeTo(null);
                        leaderboard.setVisible(true);
                    }
                }
            }

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
                    FunctionRide.State = FunctionRide.STATE.LEVEL3;
                }
            } else if (mx >= 490 && mx <= 550) {
                if (my >= 470 && my <= 520) {
                    FunctionRide.State = FunctionRide.STATE.LEVEL4;
                }
                //check if user clicks exit button 
            } else if (mx >= 900 && mx <= 950) {
                if (my >= 50 && my <= 100) {
                    FunctionRide.State = FunctionRide.STATE.MENU;
                }
            }

        } else if (FunctionRide.State == FunctionRide.STATE.COMPLETED_SCREEN) { //levelscreen
            if (mx >= FunctionRide.WIDTH / 2 - 100 && mx <= FunctionRide.WIDTH / 2 - 100 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    int current = FunctionRide.currentLevel;
                    current++;
                    notAdded = true;

                    for (int levels : levelsCompleted) {

                        if (levels == FunctionRide.preLevel) {
                            notAdded = false;
                        }
                    }
                    if (notAdded) {
                        levelsCompleted.add(FunctionRide.preLevel);
                    }
                    cLevel=levelsCompleted.size();
                    FunctionRide.State = FunctionRide.STATE.LEVEL_SCREEN;
                

                    if (current == 1) {
                        State = FunctionRide.STATE.LEVEL1;
                    } else if (current == 2) {
                        State = FunctionRide.STATE.LEVEL2;
                    } else if (current == 3) {
                        State = FunctionRide.STATE.LEVEL3;
                    } else if (current == 4) {
                        State = FunctionRide.STATE.LEVEL4;
                    }
                    Level.setFunction(null);
                }
            }
        
        } else { //levels
            //check if user clicks the play button
            if (mx >= FunctionRide.WIDTH / 2 - 100 && mx <= FunctionRide.WIDTH / 2 - 100 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    //open screen that allows user to enter a function
                    JFrame funcTab = new functionmaker();;
                    funcTab.setVisible(true);
                    funcTab.setLocationRelativeTo(null);
                }
                //check if user clicks the levels button
            } else if (mx >= FunctionRide.WIDTH / 2 - 280 && mx <= FunctionRide.WIDTH / 2 - 280 + 100) {
                if (my >= FunctionRide.HEIGHT / 2 + 200 && my <= FunctionRide.HEIGHT / 2 + 200 + 40) {
                    //show the level select screen
                    FunctionRide.State = FunctionRide.STATE.MENU;
                    FunctionRide.fileMaker();
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
