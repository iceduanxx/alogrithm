package cn.autumnstar.offer.backtrack;

import java.util.*;

public class FlowShop {
    static int n; //��ҵ��
    static int f1; //����1��ɴ���ʱ��
    static int f; //���ʱ���
    static int bestf; //��ǰ����ֵ

    static int[][] m; //����ҵ����Ҫ�Ĵ���ʱ��
    static int[] x;  //��ǰ��ҵ����
    static int[] bestx; //��ǰ������ҵ����
    static int[] f2; //����2��ɴ���ʱ��

    public static void trackback(int i) {
        //i����ָʾ����Ĳ������ڼ�������0��ʼ����ͬʱҲָʾ��ǰִ����ڼ�������
        if (i == n) { //�ó�һ������ֵ
            for (int j = 0; j < n; j++) {
                bestx[j] = x[j];
            }
            bestf = f;
        } else {
            for (int j = i; j < n; j++) {  //j����ָʾѡ�����ĸ�����Ҳ����ִ��˳��  tb(0)�����ˣ�������ô�ݹ飬����j=0,1,2���������̣���˿϶��ܱ�����ȫ
                f1 += m[x[j]][0];  //ѡ���x[j]��������ִ��
                if (i > 0) {  //ѡ����Ĳ��ǵ�һ������
                    f2[i] = ((f2[i - 1] > f1) ? f2[i - 1] : f1) + m[x[j]][1];  //��f2[i - 1] �� f1��ѡһ����ĳ���
                } else {//ѡ������ǵ�һ������
                    f2[i] = f1 + m[x[j]][1];
                }
                f += f2[i];
                if (f < bestf) {
                    swap(x, i, j);  //�ؼ�����ѡ���������j������ǰִ�е�λ��i
                    trackback(i + 1);  //ѡ����һ������ִ��
                    swap(x, i, j);  //�ݹ��ָ�ԭ��
                }
                f1 -= m[x[j]][0];  //�ݹ��ָ�ԭ��
                f -= f2[i];
            }
        }
    }

    private static void swap(int[] x, int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    private static void test() {
        n = 3;
        int[][] testm = {{2, 1}, {3, 1}, {2, 3}};
        m = testm;
        int[] testx = {0, 1, 2};
        x = testx;

        bestx = new int[n];
        f2 = new int[n];

        f1 = 0;
        f = 0;
        bestf = Integer.MAX_VALUE;
        trackback(0);  //���ɱ���trackback(0)�������һ���㿪ʼ����Ҫ��trackback(1)
        System.out.println(Arrays.toString(bestx));
        System.out.println(bestf);
    }

    public static void main(String[] args) {
        test();
        System.out.println("Hello World!");
    }
}