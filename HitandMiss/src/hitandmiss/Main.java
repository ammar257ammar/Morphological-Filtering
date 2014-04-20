package hitandmiss;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Main {
    
    public static final int[][] topRight = {
        { -1, 0,  0 },
        {  1, 1,  0 },
        { -1, 1, -1 }
    };
    
     public static final int[][] bottomRight = {
        { -1, 1, -1 },
        {  1, 1,  0 },
        { -1, 0,  0 }
    };
     
    public static final int topLeft[][] = {
        { 0,  0, -1 },
        { 0,  1,  1 },
        { -1, 1, -1 }
    };

    public static final int[][] bottomLeft = {
        { -1, 1, -1 },
        { 0,  1,  1 },
        { 0,  0, -1 }
    };

    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {

        int[][] image = ImageRead("src/images/assignment06HitAndMiss.png");
        HitAndMiss hit = new HitAndMiss();

        int[][] oimage = hit.convolve(image, bottomRight);
        ImageWrite("src/output/bottomRight.png", oimage);
        
        oimage = hit.convolve(image, topLeft);
        ImageWrite("src/output/topLeft.png", oimage);
        
        oimage = hit.convolve(image, topRight);
        ImageWrite("src/output/topRight.png", oimage);
        
        oimage = hit.convolve(image, bottomLeft);
        ImageWrite("src/output/bottomLeft.png", oimage);
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
