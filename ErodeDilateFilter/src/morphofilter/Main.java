package morphofilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/*------------------------------------------------------------------------------------------------*/
public class Main {

    public static final float[][] square = {
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}
    };

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        Erode("src/images/Erode.png"); 
        Dilate("src/images/Dilate.png");
        Opening("src/images/Open.png");
        Closing("src/images/Close.png");
    }
    /*--------------------------------------------------------------------------------------------*/
    private static void Erode(String filename) throws IOException {

        int[][] image = ImageRead(filename);
        ErodeDilate erode = new ErodeDilate();
        int[][] out = erode.erode(image, square);
        ImageWrite("src/output/Eroded.png", out);

    }
    /*--------------------------------------------------------------------------------------------*/
    private static void Dilate(String filename) throws IOException {

        int[][] image = ImageRead(filename);
        line();
        ErodeDilate dilate = new ErodeDilate();
        int[][] out = dilate.dilate(image, square);
        ImageWrite("src/output/Dilated.png", out);
           
    }      
    
    /*--------------------------------------------------------------------------------------------*/
    private static void Opening(String filename) throws IOException {

        int[][] image = ImageRead(filename);
        
        ErodeDilate opening = new ErodeDilate();
        int[][] out = opening.erode(image, square);
        int[][] out2 = opening.dilate(out, square);                
        ImageWrite("src/output/Opened.png", out2);
           
    }        
    /*--------------------------------------------------------------------------------------------*/
    private static void Closing(String filename) throws IOException {

        int[][] image = ImageRead(filename);
        
        ErodeDilate opening = new ErodeDilate();
        int[][] out = opening.dilate(image, square);
        int[][] out2 = opening.erode(out, square);                
        ImageWrite("src/output/Closed.png", out2);
           
    }        
    
  

    /*--------------------------------------------------------------------------------------------*/
    private static int[][] ImageRead(String filename) throws IOException {

        File infile = new File(filename);
        BufferedImage bi = ImageIO.read(infile);

        int red[][] = new int[bi.getHeight()][bi.getWidth()];
        int grn[][] = new int[bi.getHeight()][bi.getWidth()];
        int blu[][] = new int[bi.getHeight()][bi.getWidth()];

        for (int i = 0; i < red.length; ++i) {
            for (int j = 0; j < red[i].length; ++j) {
                red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                blu[i][j] = bi.getRGB(j, i) & 0xFF;
            }
        }
        return grn;
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void ImageWrite(String filename, int img[][]) throws IOException {

        BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < bi.getHeight(); ++i) {
            for (int j = 0; j < bi.getWidth(); ++j) {
                int val = img[i][j];
                int pixel = (val << 16) | (val << 8) | (val);
                bi.setRGB(j, i, pixel);
            }
        }

        File outputfile = new File(filename);
        ImageIO.write(bi, "png", outputfile);
    }
    /*--------------------------------------------------------------------------------------------*/
    public static void ImageDisplay(int img[][]) {

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                System.out.format("%3d ", img[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    public static void line() {
        char[] chars = new char[100];
        Arrays.fill(chars, '-');
        System.out.println(String.valueOf(chars));
    }
    /*--------------------------------------------------------------------------------------------*/
}
