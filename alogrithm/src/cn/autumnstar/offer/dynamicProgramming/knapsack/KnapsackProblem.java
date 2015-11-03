package cn.autumnstar.offer.dynamicProgramming.knapsack;

import java.util.ArrayList;

/**
 * ��ⱳ�����⣺
 * ���� n���������������ֱ�Ϊ w1,w2,����,wn, ��ֵ�ֱ�Ϊ v1,v2,����,vn
 * Ҫ�����ܳ���Ϊ totalWeight �������У� 
 * ��ɷ������ӵı�����ֵ�ܺ͵����ֵ��
 * 
 * NOTE: ʹ�ö�̬�滮����ⱳ������
 * ��ǰn���������ܳ���Ϊj������ֵΪ v[n,j], ���Žⱳ�����Ϊ b[n];
 * �������ֵ��
 * 1. �� j < wn, �� �� v[n,j] = v[n-1,j];
 * 2. �� j >= wn, ��v[n,j] = max{v[n-1,j], vn + v[n-1,j-wn]}��
 * 
 */

public class KnapsackProblem {
 
         /** ָ������ */
         private Knapsack[] bags;
 
         /** �ܳ���  */
         private int totalWeight;
 
          /** ������������  */
          private int n;
 
          /** ǰn ���������ܳ���Ϊ totalWeight ������ֵ����  */
          private int[][] bestValues;
 
          /** ǰ n ���������ܳ���Ϊ totalWeight ������ֵ */
          private int bestValue;
 
          /** ǰ n���������ܳ���ΪtotalWeight�����Ž����Ʒ��� */
          private ArrayList bestSolution;
 

          public KnapsackProblem(Knapsack[] bags, int totalWeight, int n) {
                  this.bags = bags;
                  this.totalWeight = totalWeight;
                  this.n = n;
                  if (bestValues == null) {
                         bestValues = new int[n+1][totalWeight+1];
                  }
                  if (bestSolution == null)
                         bestSolution = new ArrayList();
          }
 
          /**
           * ���ǰ n �������������ܳ���Ϊ totalWeight �µı�������
           * 
           */
          public void solution() {
  
                System.out.println("����������");
                for(Knapsack b: bags) {
                           System.out.println(b);
                }
                System.out.println("�����ܳ���: " + totalWeight);
  
                // �������ֵ
                for (int j = 0; j <= totalWeight; j++) {
                    for (int i = 0; i <= n; i++) {
   
                         if (i == 0 || j == 0) {
                                bestValues[i][j] = 0;
                         } 
                         else 
                         {
                               // �����i���������������ܳ��أ������Ž������ǰi-1�������У�
                               // ע�⣺�� i �������� bags[i-1]
                               if (j < bags[i-1].getWeight()) {
                                   bestValues[i][j] = bestValues[i-1][j];
                               } 
                               else 
                               {
                                   // ����� i �������������ܳ��أ������Ž�Ҫô�ǰ����� i �����������Ž⣬
                                   // Ҫô�ǲ������� i �����������Ž⣬ ȡ�������ֵ����������˷������۷�
                                   // �� i ������������ iweight �ͼ�ֵ ivalue
                                   int iweight = bags[i-1].getWeight();
                                   int ivalue = bags[i-1].getValue();
                                   bestValues[i][j] = 
                                       Math.max(bestValues[i-1][j], ivalue + bestValues[i-1][j-iweight]);
                                 } // else
                             } //else   
                        } //for
                   } //for
  
                  // ��ⱳ�����
                  int tempWeight = totalWeight;
                  for (int i=n; i >= 1; i--) {
                       if (bestValues[i][tempWeight] > bestValues[i-1][tempWeight]) {
                                bestSolution.add(bags[i-1]);
                                tempWeight = totalWeight - bags[i-1].getWeight();
                       }
                  }
          }
 
         /**
           * ���ǰ  n �������� �ܳ���Ϊ totalWeight �ı�����������Ž�ֵ
           * ���������� �����ȵ��� solution ����
           * 
           */
          public int getBestValue() {
  
                    bestValue = bestValues[n][totalWeight];
                    return bestValue;
          }
 
          /**
            * ���ǰ  n �������� �ܳ���Ϊ totalWeight �ı�����������Ž�ֵ����
            * ���������� �����ȵ��� solution ����
            * 
            */
          public int[][] getBestValues() {
     
                  return bestValues;
          }
    
          /**
            * ���ǰ  n �������� �ܳ���Ϊ totalWeight �ı�����������Ž�ֵ����
            * ���������� �����ȵ��� solution ����
            * 
            */
           public ArrayList getBestSolution() {
                  return bestSolution;
           }

 
}