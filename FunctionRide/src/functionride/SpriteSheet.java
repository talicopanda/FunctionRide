/*
 * Sukhraj, Tales, Sergio
 * May 28 2019
 * the spritesheet that contains the image for the player
 */
package functionride;

import java.awt.image.BufferedImage;

/**
 *
 * @author tasco0966
 */
public class SpriteSheet {
    //attribute
    private BufferedImage image;
    
    /**
     * constructor for a new spritesheet
     * @param image the image
     */
    public SpriteSheet(BufferedImage image){
        this.image = image;
    }
    /**
     * load in an image from the spritesheet
     * @param col the column the image is in
     * @param row the row the image is in
     * @param width the width of the image
     * @param height the height of the image
     * @return 
     */
    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
        return img;
    } 
}
