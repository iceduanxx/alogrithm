package cn.cb.adt;

import java.util.Vector;

/**
 * 矩阵连乘的动态规划算法
 *
 * @author MJ
 * @description
 */
public class MatrixMultiple {

    private Vector<int[][]> vMatrix = new Vector<int[][]>();
    private int m[][];// 代价数组
    private int s[][];// 最佳k值数组
    private int size;// 矩阵个数

    public MatrixMultiple(Vector<int[][]> vMatrix) {
        this.vMatrix = vMatrix;
        size = vMatrix.size();
        m = new int[size][size];
        s = new int[size][size];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
// TODO Auto-generated method stub
        int a1[][] = new int[30][35];
        int a2[][] = new int[35][15];
        int a3[][] = new int[15][5];
        int a4[][] = new int[5][10];
        int a5[][] = new int[10][20];
        int a6[][] = new int[20][25];
        Vector<int[][]> v = new Vector<int[][]>();
        v.add(a1);
        v.add(a2);
        v.add(a3);
        v.add(a4);
        v.add(a5);
        v.add(a6);
        MatrixMultiple mm = new MatrixMultiple(v);
        int result[][] = mm.matrixMultiple();
        mm.printMatrix(result);
    }

    /**
     * 矩阵连乘方法
     *
     * @return
     */
    public int[][] matrixMultiple() {
        int order[] = getMatrixSizeOrder();
        matrixChainOrder(order);
        printOptimalParens(0, size - 1);
        System.out.print("\n");
        int result[][] = matrixMultiple(0, size - 1);
        return result;
    }

    /**
     * 打印矩阵
     *
     * @param a
     */
    public void printMatrix(int a[][]) {
        int rowA = a.length;
        int columnA = a[0].length;
        System.out.println("\n");
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < columnA; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * 将矩阵初始化为0
     *
     * @param a
     * @return
     */
    public int[][] initMatrix(int[][] a) {
        int rowA = a.length;
        int columnA = a[0].length;
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < columnA; j++) {
                a[i][j] = 0;
            }
        }
        return a;
    }

    /**
     * 获取给定矩阵序列的行列序列
     *
     * @return
     */
    private int[] getMatrixSizeOrder() {
        int matrixSize = vMatrix.size();
        int order[] = new int[matrixSize + 1];
        for (int i = 0; i < matrixSize; i++) {
            order[i] = vMatrix.get(i).length;
        }
        order[matrixSize] = vMatrix.get(matrixSize - 1)[0].length;
        return order;
    }

    /**
     * 获取矩阵连乘的代价和最佳划分位置
     *
     * @param order
     */
    private void matrixChainOrder(int[] order) {
        for (int i = 0; i < size; i++) {
            m[i][i] = 0;
        }// 初始化长度为1的情况
        for (int l = 2; l <= size; l++) {
            for (int i = 0; i <= size - l; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + order[i] * order[k + 1]
                            * order[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }// l表示连乘的矩阵长度
    }

    /**
     * 输出最佳矩阵括弧
     *
     * @param i
     * @param j
     */
    private void printOptimalParens(int i, int j) {
        if (i == j)
            System.out.print("A" + i);
        else {
            System.out.print("(");
            printOptimalParens(i, s[i][j]);
            printOptimalParens(s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    /**
     * 矩阵连乘
     *
     * @param i
     * @param j
     * @return
     */
    private int[][] matrixMultiple(int i, int j) {
        if (i == j)
            return vMatrix.get(i);
        else if (j - i == 1) {
            System.out.println("A" + i + "与A" + j + "相乘");
            return multiple(vMatrix.get(i), vMatrix.get(j));
        } else {
            String s1 = "A(" + i + "~" + s[i][j] + ")";
            String s2 = "A(" + (s[i][j] + 1) + "~" + j + ")";
            if (i == s[i][j])
                s1 = "A" + i;
            if (s[i][j] + 1 == j)
                s2 = "A" + j;
            System.out.println(s1 + "与" + s2 + "相乘");
            return multiple(matrixMultiple(i, s[i][j]),
                    matrixMultiple(s[i][j] + 1, j));
        }
    }

    /**
     * 两个矩阵相乘
     *
     * @param a
     * @param b
     * @return
     */
    private int[][] multiple(int[][] a, int[][] b) {
        int rowA = a.length;
        int columnA = a[0].length;
        int columnB = b[0].length;
        int c[][] = new int[rowA][columnB];
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < columnB; j++) {
                for (int k = 0; k < columnA; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

}
