package hough;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*------------------------------------------------------------------------------------------------*/
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage gray = ReadBI2Gray("src/images/HoughInput.png");    
        HoughTransformation hough = new HoughTransformation(
                                              180
                                            , gray.getWidth()
                                            , gray.getHeight());
        hough.addPoints(gray);
        System.out.println("Total Points: " + hough.totalPoints);
        BufferedImage oimage = hough.getHoughImage();
        
        File outputfile = new File("src/images/HoughOutput.png");
        ImageIO.write(oimage, "png", outputfile);           
        
    }
    /*--------------------------------------------------------------------------------------------*/          
    private static BufferedImage ReadBI2Gray(String filename) throws IOException {
        
        BufferedImage image = ImageIO.read(new File(filename));
        BufferedImage gray = new BufferedImage( image.getWidth()
                                              , image.getHeight()
                                              , BufferedImage.TYPE_BYTE_GRAY);
        
        //Convert the original colored image to grayscale
        ColorConvertOp op = new ColorConvertOp(
                image.getColorModel().getColorSpace()
                , gray.getColorModel().getColorSpace(),null);
        op.filter(image,gray);
        return gray;
    }
    /*--------------------------------------------------------------------------------------------*/      
    
}
