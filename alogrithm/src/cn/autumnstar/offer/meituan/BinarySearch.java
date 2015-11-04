package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/11/3.
 */
public class BinarySearch {

    private static int binarySearch(int[] matrix, int i, int j, int find) {
        if (i < j) {
            return -1;
        }
        int middle = (i + j) / 2;
        if (matrix[middle] == find) {
            return middle;
        } else if (matrix[middle] > find) {
            return binarySearch(matrix, middle + 1, j, find);
        } else {
            return binarySearch(matrix, i, middle - 1, find);
        }
    }

    public static void main(String[] args) {
        int[] matrix = new int[]{2, 4, 6, 7, 8};
        int num = binarySearch(matrix, 0, matrix.length - 1, 4);
        System.out.print(num);
    }
}
