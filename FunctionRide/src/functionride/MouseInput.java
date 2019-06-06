package functionride;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        //menu screen
        if(FunctionRide.State == FunctionRide.STATE.MENU){
            if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                if (my >= 250 && my <= 300) {
                    FunctionRide.State = FunctionRide.STATE.LEVEL1;
                }
            }
            if (mx >= FunctionRide.WIDTH / 2 - 50 && mx <= FunctionRide.WIDTH / 2 + 50) {
                if (my >= 450 && my <= 500) {
                    System.exit(1);
                }
            }
        } else if(FunctionRide.State == FunctionRide.STATE.LEVELSCREEN){ //levelscreen
            //level buttons
        } else { //levels
            if (mx >= FunctionRide.WIDTH/2-100 && mx <= FunctionRide.WIDTH/2-100 +100) {
                if (my >= FunctionRide.HEIGHT/2+200 && my <= FunctionRide.HEIGHT/2+200+40) {
                    Level.setRunBtn(true);
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
