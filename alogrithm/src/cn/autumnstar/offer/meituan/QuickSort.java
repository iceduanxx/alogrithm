package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/11/3.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] matrix = new int[]{11, 5, 80, 20, 10, 9, 30};
        quickSort(matrix, 0, matrix.length - 1);
        for (int i : matrix) {
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] matrix, int i, int j) {

        int k = partition(matrix, i, j);
        if (i < k - 1) quickSort(matrix, i, k - 1);
        if (j > k + 1) quickSort(matrix, k + 1, j);
    }

    private static int partition(int[] matrix, int i, int j) {
        int rand = matrix[i];
        while (i < j) {
            while (i < j && rand <= matrix[j]) j--;
            matrix[i] = matrix[j];
            while (i < j && rand >= matrix[i]) i++;
            matrix[j] = matrix[i];
        }
        matrix[i] = rand;
        return i;
    }
}
