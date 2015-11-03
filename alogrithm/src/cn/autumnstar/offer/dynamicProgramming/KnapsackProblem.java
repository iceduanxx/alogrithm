package cn.autumnstar.offer.dynamicProgramming;

import java.util.ArrayList;

/**
 * 动态规划求解背包问题：
 * 给定 n个背包，其重量分别为 w1,w2,……,wn, 价值分别为 v1,v2,……,vn
 * 要放入总承重为 totalWeight 的箱子中，
 * 求可放入箱子的背包价值总和的最大值。
 * <p/>
 * 设前n个背包，总承重为j的最优值为 v[n,j], 最优解背包组成为 b[n];
 * 求解最优值：
 * 1. 若 j < wn, 则 ： v[n,j] = v[n-1,j];
 * 2. 若 j >= wn, 则：v[n,j] = max{v[n-1,j], vn + v[n-1,j-wn]}。
 */
public class KnapsackProblem {

    private int[] weight; //背包重量
    private int[] value; //背包物品价值
    private int totalWeight;  //总承重
    private int n;  //给定背包数量
    private int[][] bestValues;  //前n 个背包，总承重为 totalWeight 的最优值矩阵
    private int bestValue; //前 n 个背包，总承重为 totalWeight 的最优值
    private ArrayList bestSolution;  //前 n个背包，总承重为totalWeight的最优解的物品组成

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

        // 求解背包组成
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