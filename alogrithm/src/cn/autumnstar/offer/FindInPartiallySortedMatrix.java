package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: 上午10:36
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排列，每一列都按照从上到下的递增序列，
 * 请完成这样一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该元素。
 */
public class FindInPartiallySortedMatrix {
    /**
     * 每次选取右上角的数字
     * @param matrix
     * @param row
     * @param col
     * @param find
     * @return
     */
    public static boolean findInPartiallySortedMatrix(int[][] matrix, int row, int col, int find) {

        int rows = 0;
        while (rows < row && col >= 0) {
            if (find < matrix[rows][col - 1]) {
                --col;
            } else if (find > matrix[rows][col - 1]) {
                ++rows;
            } else {
                return true;
            }

           // System.out.println("["+rows+"]"+"["+col+"]");
        }
        return false;

    }

    // 1  2  8  9
    // 2  4  9  12
    // 4  7  10  13
    // 6  8  11  15
    public static void main(String[] args) {
        int matrix[][] = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};

        boolean result=findInPartiallySortedMatrix(matrix,4,4,6);
        System.out.println(result);
    }
}
