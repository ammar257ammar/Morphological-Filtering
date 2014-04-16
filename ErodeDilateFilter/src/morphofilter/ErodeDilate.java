package morphofilter;

/*------------------------------------------------------------------------------------------------*/
public class ErodeDilate {

    /*-------------------------------------------------------------------------*/
    public int[][] erode(int[][] image, float[][] structure) {

        int half_h = structure.length / 2;
        int half_w = structure[0].length / 2;

        int height = image.length;
        int width = image[0].length;

        int[][] output = new int[height][width];
        int pixel = 0;

        for (int j = half_h; j < height - half_h; j++) {
            for (int i = half_w; i < width - half_w; i++) {

                pixel = 255;
                for (int jj = -half_w; jj <= half_w; jj++) {
                    for (int ii = -half_h; ii <= half_h; ii++) {

                        if (structure[ii + half_h][jj + half_w] == 1) {
                            if (image[j - ii][i - jj] < pixel) {
                                pixel = image[j - ii][i - jj];
                            }
                        }
                    }
                }
                output[j][i] = pixel;
            }
        }

        return output;
    }
    /*-------------------------------------------------------------------------*/

    public int[][] dilate(int[][] image, float[][] structure) {

        int half_h = structure.length / 2;
        int half_w = structure[0].length / 2;

        int height = image.length;
        int width = image[0].length;

        int[][] ouput = new int[height][width];
        int pixel = 0;

        for (int j = half_h; j < height - half_h; j++) {
            for (int i = half_w; i < width - half_w; i++) {

                pixel = 0;
                for (int jj = -half_w; jj <= half_w; jj++) {
                    for (int ii = -half_h; ii <= half_h; ii++) {

                        if (structure[ii + half_h][jj + half_w] == 1) {
                            if (image[j - ii][i - jj] > pixel) {
                                pixel = image[j - ii][i - jj];
                            }
                        }
                    }
                }
                ouput[j][i] = pixel;
            }
        }

        return ouput;
    }
    /*-------------------------------------------------------------------------*/
}
/*------------------------------------------------------------------------------------------------*/
