package cn.duanxx.chapter4.blog;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 13-9-25
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
public class Perm {
    private static int n;

    public static void swap(int a[], int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }

    public static void perm(int list[], int k, int m) {
        int i;
        if (k > m) {
            for (i = 0; i <= m; i++)
          /*      System.out.print(list[i]);
            System.out.println();*/
            n++;
        } else {
            for (i = k; i <= m; i++) {
                swap(list, k, i);
                perm(list, k + 1, m);
                swap(list, k, i);
            }
        }
    }

    public static void main(String[] args) {
        long startTime =System.currentTimeMillis();
        int[] list =new int[1000];
        for(int i=0;i<list.length;i++){
            list[i]=i+1;
        }
        perm(list, 0, list.length - 1);
        long endTime =System.currentTimeMillis();
        System.out.println("共计多少个排列"+n);
        System.out.println("10000个整数全排列用时"+(endTime-startTime)/1000+"秒！");

    }
}

