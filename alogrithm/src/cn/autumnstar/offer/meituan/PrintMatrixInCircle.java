package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/11/2.
 */
public class PrintMatrixInCircle {

    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        int cols = matrix[0].length;
        int rows = matrix.length;

        int i = 0;
        int j=0;
        for(j=0;j<cols;j++){
           System.out.print(matrix[i][j]);
        }
        i = 1;
        for(;i<rows;i++){
            System.out.print(matrix[i][j]);
        }
        j--;
        for(;j>=0;j--){
            System.out.print(matrix[i][j]);
        }




    }
}
