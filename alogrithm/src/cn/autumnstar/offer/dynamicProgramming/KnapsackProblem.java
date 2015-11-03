package cn.autumnstar.offer.dynamicProgramming;

import java.util.ArrayList;

/**
 * ��̬�滮��ⱳ�����⣺
 * ���� n���������������ֱ�Ϊ w1,w2,����,wn, ��ֵ�ֱ�Ϊ v1,v2,����,vn
 * Ҫ�����ܳ���Ϊ totalWeight �������У�
 * ��ɷ������ӵı�����ֵ�ܺ͵����ֵ��
 * <p/>
 * ��ǰn���������ܳ���Ϊj������ֵΪ v[n,j], ���Žⱳ�����Ϊ b[n];
 * �������ֵ��
 * 1. �� j < wn, �� �� v[n,j] = v[n-1,j];
 * 2. �� j >= wn, ��v[n,j] = max{v[n-1,j], vn + v[n-1,j-wn]}��
 */
public class KnapsackProblem {

    private int[] weight; //��������
    private int[] value; //������Ʒ��ֵ
    private int totalWeight;  //�ܳ���
    private int n;  //������������
    private int[][] bestValues;  //ǰn ���������ܳ���Ϊ totalWeight ������ֵ����
    private int bestValue; //ǰ n ���������ܳ���Ϊ totalWeight ������ֵ
    private ArrayList bestSolution;  //ǰ n���������ܳ���ΪtotalWeight�����Ž����Ʒ���

    /**
     *
     */
    public void solution() {
        for (int j = 0; j < totalWeight; j++) {
            for (int i = 0; i < n; i++) {
                if (i == 0 || j == 0) {
                    bestValues[i][j] = 0;
                }
                if (j < weight[i - 1]) {
                    bestValues[i][j] = bestValues[i - 1][j];
                } else {
                    bestValues[i][j] = Math.max(bestValues[i - 1][j], bestValues[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        // ��ⱳ�����
        int tempWeight = totalWeight;
        for (int i = n; i >= 1; i--) {
            if (bestValues[i][tempWeight] > bestValues[i - 1][tempWeight]) {
                bestSolution.add(value[i - 1]);
                tempWeight = totalWeight - weight[i - 1];
            }
        }
    }


    public static void main(String[] args) {

    }


}