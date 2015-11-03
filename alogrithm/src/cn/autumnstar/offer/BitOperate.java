package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: 下午5:27
 * 位运算
 */
public class BitOperate {
    public static void main(String[] args) {
       /* int a = 10;
        int b = a & 1;*/

        System.out.println(binaryFor1(10));
    }

    /**
     * 二进制中1出现的个数
     *
     * @return
     */
    public static int binaryFor1(int n) {
        int sum = 0;
        while (n>0) {
            if ((n & 1) == 1) {
                sum++;
                n >>= 1;
            }
        }
        return sum;
    }


}
