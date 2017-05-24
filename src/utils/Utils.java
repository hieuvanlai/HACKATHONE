package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ADMIN on 5/8/2017.
 */
public class Utils {
    public static Image loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Biến Lấy Kết Quả Randrom
    public  static Random random = new Random();
    public static  Image  FlipImage (Image image){
        // Flip the image vertically
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter((BufferedImage) image, null);


// Flip the image vertically and horizontally; equivalent to rotating the image 180 degrees
        tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-image.getWidth(null), -image.getHeight(null));
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter((BufferedImage) image, null);
        return image;
    }
    public static void playSound(String audioUrl, boolean repeat) {


        try {
            File soundFile = new File(audioUrl);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();

            clip.open(audioIn);

            clip.start();


            if(repeat) {

                clip.loop(100);

            }

            else {

                clip.loop(0);

            }

        } catch (UnsupportedAudioFileException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (LineUnavailableException e) {

            e.printStackTrace();

        }

    }


}
