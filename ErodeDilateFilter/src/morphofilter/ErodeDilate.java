package morphofilter;

/*------------------------------------------------------------------------------------------------*/
public class ErodeDilate {

    /*-------------------------------------------------------------------------*/
    public int[][] erode(int[][] inImage, float[][] kernel) {

        int half_h = kernel.length / 2;
        int half_w = kernel[0].length / 2;

        int height = inImage.length;
        int width = inImage[0].length;

        int[][] outImage = new int[height][width];
        int pixel = 0;

        for (int j = half_h; j < height - half_h; j++) {
            for (int i = half_w; i < width - half_w; i++) {

                pixel = 255;
                for (int jj = -half_w; jj <= half_w; jj++) {
                    for (int ii = -half_h; ii <= half_h; ii++) {

                        if (kernel[ii + half_h][jj + half_w] == 1) {
                            if (inImage[j - ii][i - jj] < pixel) {
                                pixel = inImage[j - ii][i - jj];
                            }
                        }
                    }
                }
                outImage[j][i] = pixel;
            }
        }

        return outImage;
    }
    /*-------------------------------------------------------------------------*/

    public int[][] dilate(int[][] inImage, float kernel[][]) {

        int half_h = kernel.length / 2;
        int half_w = kernel[0].length / 2;

        int height = inImage.length;
        int width = inImage[0].length;

        int[][] outImage = new int[height][width];
        int pixel = 0;

        for (int j = half_h; j < height - half_h; j++) {
            for (int i = half_w; i < width - half_w; i++) {

                pixel = 0;
                for (int jj = -half_w; jj <= half_w; jj++) {
                    for (int ii = -half_h; ii <= half_h; ii++) {

                        if (kernel[ii + half_h][jj + half_w] == 1) {
                            if (inImage[j - ii][i - jj] > pixel) {
                                pixel = inImage[j - ii][i - jj];
                            }
                        }
                    }
                }
                outImage[j][i] = pixel;
            }
        }

        return outImage;
    }
    /*-------------------------------------------------------------------------*/
}
/*------------------------------------------------------------------------------------------------*/
