package cn.duanxx.chapter4.blog;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 13-9-25
 * Time: ����11:15
 * ���������������
 * ��ʱO(n)
 */
public class SubArrayMaxSum {

    public static int subArrayMaxSum(int a[]) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (sum < 0) {
                sum = a[i];
            } else {
                sum += a[i];
            }
        }
        return sum;
    }

    public static void main(String[] args){

        int[] a ={ 1, -2, 3, 10, -4, 7, 2, -500, 100, 1, 2, 8, -50, 55};
        System.out.println(subArrayMaxSum(a));

    }
}
