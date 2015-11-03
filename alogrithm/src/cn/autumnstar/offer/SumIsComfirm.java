package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-12
 * Time: 下午4:07
 * 题目：输入一个数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。
 * 要求时间复杂度是O(n)。如果有多对数字的和等于输入的数字，输出任意一对即可。
 * 例如输入数组1、2、4、7、11、15和数字15。由于4+11=15，因此输出4和11。
 */
public class SumIsComfirm {

    public static int sumIsConfirm(int[] a, int sum) {
        int[] b = new int[a.length];
        int c = 0;      //两个


        for (int i = 0; i < a.length; i++) {
            if (a[i] <= sum) {
                b[i] = sum - a[i];
            }
        }
        int i = 0;
        int j = a.length - 1;
        while (i <= j) {
            if (a[i] == b[j]) {
                c = a[i];
                break;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j--;
            }
        }
        return c;

    }

    public static void main(String[] args) {
        int[] a = {1, 8, 4, 7, 11, 15};
        int b =sumIsConfirm(a,15);
        int c = 15-b;
        System.out.print(b+" ");
        System.out.println(c);
    }
}
