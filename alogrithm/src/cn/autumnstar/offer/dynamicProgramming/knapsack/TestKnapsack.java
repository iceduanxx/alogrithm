package cn.autumnstar.offer.dynamicProgramming.knapsack;

public class TestKnapsack {



    public static void main(String[] args) {

        Knapsack[] bags = new Knapsack[]{
                new Knapsack(2, 12), new Knapsack(1, 10),
                new Knapsack(3, 20), new Knapsack(2, 15)
        };
        int totalWeight = 5;
        int n = bags.length;
        KnapsackProblem kp = new KnapsackProblem(bags, totalWeight, n);

        kp.solution();
        System.out.println(" -------- 该背包问题实例的解: --------- ");
        System.out.println("最优值：" + kp.getBestValue());
        System.out.println("最优解【选取的背包】: ");
        System.out.println(kp.getBestSolution());
        System.out.println("最优值矩阵：");
        int[][] bestValues = kp.getBestValues();
        for (int i = 0; i < bestValues.length; i++) {
            for (int j = 0; j < bestValues[i].length; j++) {
                System.out.printf("%-5d", bestValues[i][j]);
            }
            System.out.println();
        }
    }

}
