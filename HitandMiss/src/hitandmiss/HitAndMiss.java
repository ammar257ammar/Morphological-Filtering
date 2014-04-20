package hitandmiss;

public class HitAndMiss {

    /*-------------------------------------------------------------------------*/
    public int[][] convolve(int[][] image, int[][] structure) {

        int halfH = structure.length / 2;
        int halfW = structure[0].length / 2;

        int height = image.length;
        int width = image[0].length;

        int[][] outImage = new int[height][width];
        int match = 0;

        /*-------------------------- IMAGE ------------------------------*/
        for (int j = halfH; j < height - halfH; j++) {
            for (int i = halfW; i < width - halfW; i++) {

                match = 0;
                /*-------------------- KERNEL --------------------*/
                for (int jj = -halfH; jj <= halfH; jj++) {
                    for (int ii = -halfW; ii <= halfW; ii++) {

                        if ((structure[ii + halfH][jj + halfW] == 1)
                            && image[j - ii][i - jj] > 0) {
                            match++;

                        } else if ((structure[ii + halfH][jj + halfW] == 0)
                            && image[j - ii][i - jj] == 0) {
                            match++;

                        } else if (structure[ii + halfH][jj + halfW] == -1) {
                            match++;                             
                        }
                    }
                }
                if (match == 9) {
                    System.out.println("Found: Corner at j=" + j + " i=" + i);
                    outImage[j][i] = 255;
                }
                /*----------------- ENF OF KERNEL -----------------*/
            }
        }
        /*------------------------ END OF IMAGE ------------------------------*/
        return outImage;
    }
    /*-------------------------------------------------------------------------*/
    public void convolve(int[][] image, int[][] outImage, int[][] structure) {

        int halfH = structure.length / 2;
        int halfW = structure[0].length / 2;

        int height = image.length;
        int width = image[0].length;

        int match = 0;

        /*-------------------------- IMAGE ------------------------------*/
        for (int j = halfH; j < height - halfH; j++) {
            for (int i = halfW; i < width - halfW; i++) {

                match = 0;
                /*-------------------- KERNEL --------------------*/
                for (int jj = -halfH; jj <= halfH; jj++) {
                    for (int ii = -halfW; ii <= halfW; ii++) {

                        if ((structure[ii + halfH][jj + halfW] == 1)
                            && image[j - ii][i - jj] > 0) {
                            match++;

                        } else if ((structure[ii + halfH][jj + halfW] == 0)
                            && image[j - ii][i - jj] == 0) {
                            match++;

                        } else if (structure[ii + halfH][jj + halfW] == -1) {
                            match++;                             
                        }
                    }
                }
                if (match == 9) {
                    System.out.println("Found: Corner at j=" + j + " i=" + i);
                    outImage[j][i] = 255;
                }
                /*----------------- ENF OF KERNEL -----------------*/
            }
        }
        /*------------------------ END OF IMAGE ------------------------------*/

    }
    
    /*-------------------------------------------------------------------------*/
}
