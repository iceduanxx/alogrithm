package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-12
 * Time: ����4:07
 * ��Ŀ������һ�������һ�����֣��������в�����������ʹ�����ǵĺ�������������Ǹ����֡�
 * Ҫ��ʱ�临�Ӷ���O(n)������ж�����ֵĺ͵�����������֣��������һ�Լ��ɡ�
 * ������������1��2��4��7��11��15������15������4+11=15��������4��11��
 */
public class SumIsComfirm {

    public static int sumIsConfirm(int[] a, int sum) {
        int[] b = new int[a.length];
        int c = 0;      //����


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
