package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: ����10:36
 * ��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳�����У�ÿһ�ж����մ��ϵ��µĵ������У�
 * ���������һ������������������һ����ά�����һ���������ж��������Ƿ��и�Ԫ�ء�
 */
public class FindInPartiallySortedMatrix {
    /**
     * ÿ��ѡȡ���Ͻǵ�����
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
