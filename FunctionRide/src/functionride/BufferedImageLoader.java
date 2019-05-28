/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionride;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author tasco0966
 */
public class BufferedImageLoader {
    
    private BufferedImage image;
    
    public  BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
