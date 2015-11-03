package cn.duanxx.chapter4.blog;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 13-9-25
 * Time: 上午9:39
 * <p>
 * 异或运算交换元素
 * </p>
 */
public class XorSwap {

    public static void main(String[] args) {
        int a = 3, b = 4;
        swap(a, b);
    }

    //函数内不能使用+-*/符合，也不能用循环语句.
   /*  public int add(int a, int b) {
        if (!a) return b;
        else
            return add((a & b) << 1, a ^ b);
    }*/

    /**
     * 异或运算又叫做半加运算，因为它在二进制下与加法基本相同，只是不带进位：0^0=0，0^1=1，1^0=1，1^1=0（不进位）。
     * 3用二进制表示是011，4用二进制表示是100，进行一次不带进位的加法运算：011^100= 111
     *
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        a = a ^ b;//异或
        b = b ^ a;
        a = a ^ b;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }


}
