package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: 上午11:37
 * 二分查找 O(lgn)
 */
public class BinarySeek {

    public static boolean binarySeek(int a[], int i, int j, int find) {
        boolean result = false;
        if (i <= j) {
            int middle = (i + j) / 2;
            if (a[middle] == find) {
                return true;
            } else if (a[middle] < find) {
                result = binarySeek(a, middle + 1, j, find);
            } else {
                result = binarySeek(a, i, middle - 1, find);
            }
        }
        return result;
    }

    public static void main(String[] args) {
       /* int[] a = {1, 2, 3, 4, 5, 6};*/
        int a[]=new int[1000];
        for(int i=1;i<1000;i++){
            a[i]=i;
        }
        boolean result = binarySeek(a, 0, 999, 999);
        System.out.println(result);
    }
}
