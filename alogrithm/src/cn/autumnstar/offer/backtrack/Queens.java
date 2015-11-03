package cn.autumnstar.offer.backtrack;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-17
 * Time: ����3:35
 * To change this template use File | Settings | File Templates.
 */
public class Queens {
    private int n;
    private int[] x;
    private int sum;

    /**
     * �ж������ʺ��Ƿ����У��л���ͬһ�Խ���
     *
     * @param k
     * @return
     */
    public boolean place(int k) {
        boolean ret = false;
        for (int j = 0; j < k; j++) {
            //�ж��Ƿ��ڶԽ����ϣ�������һ�к�һ��
            if (Math.abs(k - j) == Math.abs(x[k] - x[j]) || x[j] == x[k]) {
                return false;
            }
        }
        return true;
    }

    public void backtrace(int t) {
        if (t > n - 1) {  //����Ҷ�ڵ�
            sum++;
        } else {
            for (int i = 0; i < n; i++) { //�ӵ�һ��һֱ���ԣ�ֱ���ҵ� place(t)Ϊtrue��λ��
                x[t] = i;
                if (place(t)) backtrace(t + 1);
            }
        }
    }

    public static void main(String[] args) {
        Queens Q = new Queens();
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        Q.n = Integer.valueOf(s1).intValue();
        Q.x = new int[Q.n];
        Q.backtrace(0);
        for (int i = 0; i < Q.n; i++) System.out.print(Q.x[i] + " ");
        System.out.println();
        System.out.println("���Ž⣺" + Q.sum);
    }


}
