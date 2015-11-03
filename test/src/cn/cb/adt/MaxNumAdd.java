package cn.cb.adt;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-9
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
public class MaxNumAdd {
    public static Byte[] StringToByte(String number) {
        int len = number.length();
        Byte[] result = new Byte[len];
        //从高位到低位依次转换,转换后字符串"1234"变为4321,这样方便以后计算
        for (int i = 0; i < number.length(); i++) {
            byte curByte = Integer.valueOf(number.charAt(i) + "").byteValue();
            result[len - i - 1] = curByte;
        }

        return result;
    }

    public static Byte[] add(Byte[] number1, Byte[] number2) {
        int len = (number1.length > number2.length) ? number1.length : number2.length;
        Byte[] result = new Byte[len + 1];
        Integer sum = new Integer(0);
        Integer carry = new Integer(0);
        int i;
        if (number1.length > number2.length) {
            for (i = 0; i < number2.length; i++) {
                sum = number1[i].intValue() + number2[i].intValue();
                sum = sum + carry;
                carry = ((sum - 10) >= 0) ? 1 : 0;
                sum = ((sum - 10) >= 0) ? (sum - 10) : sum;
                result[i] = sum.byteValue();
                sum = 0;
            }
            for (; i < number1.length; i++) {
                sum = sum + carry;
                carry = ((sum - 10) >= 0) ? 1 : 0;
                sum = ((sum - 10) >= 0) ? (sum - 10) : sum;
                result[i] = sum.byteValue();
                sum = 0;
            }
            if (carry != 0) {
                result[i] = carry.byteValue();
            } else {
                result[i] = 0;
            }
        } else {
            for (i = 0; i < number1.length; i++) {
                sum = number1[i].intValue() + number2[i].intValue();
                sum = sum + carry;
                carry = ((sum - 10) >= 0) ? 1 : 0;
                sum = ((sum - 10) >= 0) ? (sum - 10) : sum;
                result[i] = sum.byteValue();
                sum = 0;
            }
            for (; i < number2.length; i++) {
                sum = number2[i].intValue() + carry;
                carry = ((sum - 10) >= 0) ? 1 : 0;
                sum = ((sum - 10) >= 0) ? (sum - 10) : sum;
                result[i] = sum.byteValue();
                sum = 0;
            }
            if (carry != 0) {
                result[i] = carry.byteValue();
            } else {
                result[i] = 0;
            }
        }

        return result;
    }
}
