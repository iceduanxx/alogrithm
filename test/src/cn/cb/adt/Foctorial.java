package cn.cb.adt;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-9
 * Time: обнГ4:02
 * To change this template use File | Settings | File Templates.
 */
public class Foctorial {

    public int factorial(int m) {
        if (m < 0)
            return 0;
        else if (m == 1 || m == 0)
            return 1;
        else
            return m * factorial(m - 1);
    }

    public int noFactorial(int m) {
        if (m < 0)
            return 0;
        else if (m == 1)
            return 1;
        else {
            int sum = 1;
            for (int i = 2; i <= m; i++)
                sum = sum * i;
            return sum;

        }
    }

    static int factorial2(int n) {
        java.util.Stack<Integer> st = new java.util.Stack<Integer>();
        st.push(n);
        int temp;
        int total = 1;
        while ((temp = st.peek()) != 1) {
            total *= temp;
            st.pop();
            st.push(temp - 1);
        }
        return total;
    }

}
