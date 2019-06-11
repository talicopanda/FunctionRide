/*
 * Sukhraj, Tales, Sergio
 * May 28 2019 
 * class allows us to load in an image
 */
package functionride;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author tasco0966
 */
public class BufferedImageLoader {
    //the image to load
    private BufferedImage image;
    /**
     * loads in an image from a specified file path
     * @param path the file path
     * @return an image
     * @throws IOException - catch the error if the image is not found 
     */
    public  BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(new File(path));
        return image;
    }
}
