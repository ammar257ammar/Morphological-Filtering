package operations;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*------------------------------------------------------------------------------------------------*/
public class Main {
    
    private static BufferedImage gray;
    private static File outputfile;
    private static BufferedImage bi;
        
    public static void main(String[] args) throws IOException {
        
        /*--------- Process Dilation of the an Image ----------*/        
        gray = ReadBI2Gray("src/images/ToDilate.png");        
        AbstractOperation dilation = new Dilation();
        bi = dilation.execute(gray);
        outputfile = new File("src/output/Dilated.png");
        ImageIO.write(bi, "png", outputfile);
        
        /*--------- Process Erosion of the an Image ----------*/
        gray = ReadBI2Gray("src/images/ToErode.png");                
        AbstractOperation erode = new Erosion();
        bi = erode.execute(gray);
        outputfile = new File("src/output/Erode.png");
        ImageIO.write(bi, "png", outputfile);
        
        /*--------- Process Closing of the an Image ----------*/
        gray = ReadBI2Gray("src/images/ToClose.png");                
        AbstractOperation close = new Closing();
        bi = close.execute(gray);
        outputfile = new File("src/output/Close.png");
        ImageIO.write(bi, "png", outputfile);
        
        /*--------- Process Opening of the an Image ----------*/
        gray = ReadBI2Gray("src/images/ToOpen.png");                
        AbstractOperation opening = new Opening();
        bi = opening.execute(gray);
        outputfile = new File("src/output/Open.png");
        ImageIO.write(bi, "png", outputfile);        
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
