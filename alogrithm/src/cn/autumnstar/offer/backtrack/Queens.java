package cn.autumnstar.offer.backtrack;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-17
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public class Queens {
    private int n;
    private int[] x;
    private int sum;

    /**
     * 判断两个皇后是否在行，列或者同一对角线
     *
     * @param k
     * @return
     */
    public boolean place(int k) {
        boolean ret = false;
        for (int j = 0; j < k; j++) {
            //判断是否在对角线上，或者在一行和一列
            if (Math.abs(k - j) == Math.abs(x[k] - x[j]) || x[j] == x[k]) {
                return false;
            }
        }
        return true;
    }

    public void backtrace(int t) {
        if (t > n - 1) {  //到达叶节点
            sum++;
        } else {
            for (int i = 0; i < n; i++) { //从第一行一直尝试，直到找到 place(t)为true的位置
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
        System.out.println("最优解：" + Q.sum);
    }


}
