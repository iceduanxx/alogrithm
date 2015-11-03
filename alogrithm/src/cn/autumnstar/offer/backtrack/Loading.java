package cn.autumnstar.offer.backtrack;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * email:duanxx@staff.chinabyte.com
 * Date: 13-10-16
 * Time: 下午2:45
 * 最优装载问题回溯法
 */
public class Loading {
    private int n;//集装箱数
    private int[] w;//集装箱重量数组
    private int c;//第一艘轮船的载重量
    private int cw;//当前载重量
    private int bestw;//当前最优载重量
    private int r;//剩余集装箱重量
    private int[] x;//当前解
    private int[] bestx;//当前最优解

    /**
     *
     * @param i
     */
    public void backtrace(int i) {
        //1.到达叶节点
        if (i > n-1) {   //i此时的值=叶节点+1
            if (cw > bestw) {
                for (int j = 0; j < n; j++) {
                    bestx[j] = x[j];
                    bestw = cw;
                }
                return;
            }
        }
        r -= w[i];
        //2.搜索左子树
        if (cw + w[i] < c) {   //x[i =1
            x[i] = 1;
            cw += w[i];
            backtrace(i + 1);
            cw -= w[i];
        }
        //3.搜索右子树
        if (cw + r > bestw) {
            x[i] = 0;
            backtrace(i + 1);
        }
        r += w[i];
    }

    public static void main(String[] args) {
        Loading X = new Loading();
        /*String s1 = JOptionPane.showInputDialog(null, "输入货物数量：",
                "最优装载问题", JOptionPane.QUESTION_MESSAGE);*/
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        X.n = Integer.parseInt(s1);
        X.w = new int[X.n];
        X.x = new int[X.n];
        X.bestx= new int[X.n];
        System.out.println("输出货物的重量数组：");
        for (int i = 0; i < X.n; i++) {
            X.w[i] = (int) (100 * Math.random());
            System.out.println(X.w[i]);
        }
        /*String s2 = JOptionPane.showInputDialog(null, "输入第一艘轮船的载重量：",
                "最优装载问题", JOptionPane.QUESTION_MESSAGE);*/

        String s2 = scanner.nextLine();
        X.c = Integer.parseInt(s2);

        for (int i = 0; i < X.n; i++)
            X.r += X.w[i];
        X.backtrace(0);
        System.out.print("输出当前最优解:");
        for (int i = 0; i < X.n; i++) System.out.print(X.bestx[i] + " ");
        System.out.println();
        System.out.println("最优解：" + X.bestw);
    }


}
