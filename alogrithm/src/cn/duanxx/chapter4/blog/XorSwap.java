package cn.duanxx.chapter4.blog;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 13-9-25
 * Time: ����9:39
 * <p>
 * ������㽻��Ԫ��
 * </p>
 */
public class XorSwap {

    public static void main(String[] args) {
        int a = 3, b = 4;
        swap(a, b);
    }

    //�����ڲ���ʹ��+-*/���ϣ�Ҳ������ѭ�����.
   /*  public int add(int a, int b) {
        if (!a) return b;
        else
            return add((a & b) << 1, a ^ b);
    }*/

    /**
     * ��������ֽ���������㣬��Ϊ���ڶ���������ӷ�������ͬ��ֻ�ǲ�����λ��0^0=0��0^1=1��1^0=1��1^1=0������λ����
     * 3�ö����Ʊ�ʾ��011��4�ö����Ʊ�ʾ��100������һ�β�����λ�ļӷ����㣺011^100= 111
     *
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        a = a ^ b;//���
        b = b ^ a;
        a = a ^ b;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }


}
