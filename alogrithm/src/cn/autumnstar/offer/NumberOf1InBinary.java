package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-10
 * Time: ����5:41
 * ��������1�ĸ���
 */
public class NumberOf1InBinary {
    //1.
    public static int numberOf1InBinary(int num) {
        int sum = 0;
        while (num >= 1) {
            if (num % 2 == 1) {
                sum++;
            }
            num = num >> 1;
        }
        return sum;
    }

    //2.
    public static int number1OfInBinary2(int num) {
        int sum = 0;
        while (num >= 1) {
            if ((num & 0x01) == 1) {
                sum++;
            }
            num = num >> 1;
        }
        return sum;
    }

    //3.todo:ò�Ʋ���
    public static int number1OfInBinary3(int num) {
        int sum = 0;
        while (num >=1) {
           num &=(num-1);
            sum++;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(number1OfInBinary3(12));
    }
}
