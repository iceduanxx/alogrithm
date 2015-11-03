package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-12
 * Time: ÏÂÎç5:48
 * To change this template use File | Settings | File Templates.
 */
public class Sum {

    public static int sum(int a[]) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 10, 11, 20, 22, 44, 55, 110};
        System.out.println(sum(a));
    }
}
