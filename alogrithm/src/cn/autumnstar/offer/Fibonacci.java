package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: ����4:26
 * 쳲������ĵݹ�ͷǵݹ�,���������ʹ������£��Է��������ջ�����
 */
public class Fibonacci {

    public static int recursion(int n) {
        if (n == 0) return 1;
        else if (n == 1) return 1;
        return recursion(n - 1) + recursion(n - 2);
    }

    public static int noRecursion(int n) {
        int result[] = {1, 1};
        if (n < 2) {
            return result[n];
        }
        int sum = 0;
        int fibonacciOne = 1;
        int fibonacciTwo = 1;
        for (int i = 2; i <= n; i++) {
            sum = fibonacciOne + fibonacciTwo;
            fibonacciOne = fibonacciTwo;
            fibonacciTwo = sum;

        }
        return sum;
    }

    public static void main(String[] args) {
        /*  int result = noRecursion(10000);
        System.out.println(result);*/

        int result = recursion(10000);
        System.out.println(result);

    }


}
