package cn.autumnstar.offer.backtrack;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class MaxLoading {

    public static void main(String[] args) {
        String s1 = JOptionPane.showInputDialog(null, "�������������",
                "����װ������", JOptionPane.QUESTION_MESSAGE);
        n = Integer.parseInt(s1);
        w = new int[n];
        System.out.println("���������������飺");
        for (int i = 0; i < n; i++) {
            w[i] = (int) (100 * Math.random());
            System.out.println(w[i]);
        }

        String s2 = JOptionPane.showInputDialog(null, "�����һ���ִ�����������",
                "����װ������", JOptionPane.QUESTION_MESSAGE);
        c = Integer.parseInt(s2);
        //x=new int [w.length];
        maxLoading(w, c, x);
        System.out.print("�����ǰ���Ž�:");
        for (int i = 1; i < n + 1; i++) System.out.print(x[i] + " ");
        System.out.println();
        System.out.println("���Ž⣺" + bestw);

        // Show frame
        //frame.setVisible(true);
    }

    static int n;//��װ����
    static int[] w;//��װ����������
    static int c;//��һ���ִ���������
    static int cw;//��ǰ������
    static int bestw;//��ǰ����������
    static int r;//ʣ�༯װ������
    static int[] x;//��ǰ��
    static int[] bestx;//��ǰ���Ž�

    public static int maxLoading(int[] ww, int cc, int[] xx) {
        n = ww.length - 1;
        w = ww;
        c = cc;
        bestw = 0;
        x = new int[n + 1];
        bestx = xx;
        for (int i = 1; i <= n; i++)
            r += w[i];
        //��������װ������
        backtrack(1);
        return bestw;
    }

    public static void backtrack(int i) {
        //������i����
        if (i > n) {//����Ҷ���
            if (cw > bestw) {
                for (int j = 1; j <= n; j++)
                    bestx[j] = x[j];
                bestw = cw;
            }
            return;
        }
        r -= w[i];

        if (cw + w[i] <= c) {//����������
            x[i] = 1;
            cw += w[i];
            backtrack(i + 1);
            cw -= w[i];
        }
        if (cw + r > bestw) {
            x[i] = 0;
            backtrack(i + 1);
        }
        r += w[i];
    }

}
