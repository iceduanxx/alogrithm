package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/10/31.
 * 按对角线打印矩阵
 */
public class MatrixPrint {

    /* example:
    * [ 1, 2, 3, 4]
    * [ 5, 6, 7, 8]
    * [ 9,10,11,12]
    * [13,14,15,16]
    * <p/>
    * print
    * 1
    * 2,5
    * 3,6,9
    * 4,7,10,13
    * ............
    */
    private static void print(int[][] matrix, int cols, int rows) {

        int level = rows + cols; //一共打印的层数
        for (int l = 0; l < level; l++) {
            int j;

            if (l < cols) {
                j = l;
            } else {
                j = cols - 1;
            }

            int i = l - j;
            while (i <= l && i < rows) {
                System.out.print(matrix[i][j] + " ");
                /*System.out.print("i: " + i + " j: " + j + "  ");*/
                j--;
                i++;
            }
            System.out.println();
        }

    }

    /**
     * 沿对角线打印
     * 同一层级的横坐标和纵坐标之和相等
     *
     * @param
     */
    private static void slash(int[][] matrix) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        //层级
        for (int l = 0; l < cols + rows - 1; l++) {
            int sum = l;
            for (int i = 0; i < rows; i++) {
                int j = sum - i;
                if (i >= 0 && i < rows && j >= 0 && j < cols) {
                    System.out.print(matrix[i][j] + " ");
                    //System.out.print("i: " + i + " j: " + j + "  ");
                }

            }
            System.out.println();
        }

    }

    private static void backSlash(int[][] matrix) {
        int cols = matrix[0].length;
        int rows = matrix.length;

        for (int l = 0; l < rows + cols - 1; l++) {
            int diff = cols - l - 1;
            for (int j = 0; j < cols; j++) {
                int i = j - diff;
                if (i >= 0 && i < rows && j >= 0 && j < cols) {
                    /*System.out.print("i: " + i + " j: " + j + "  ");*/
                    System.out.print(matrix[i][j] + " ");
                }

            }
            System.out.println();
        }

    }

    public static void main(String[] args) {

        int[][] martix = new int[4][4];
        int v = 1;
        for (int i = 0; i < martix.length; i++) {
            for (int j = 0; j < martix[i].length; j++) {
                martix[i][j] = v;
                v++;
            }
        }

        slash(martix);
        System.out.println("========================");
        backSlash(martix);
    }
}
