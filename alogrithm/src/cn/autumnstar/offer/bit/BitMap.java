package cn.autumnstar.offer.bit;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-14
 * Time: ����4:24
 * ʹ��λͼ����������
 */
public class BitMap {

    /**
     * ʹ��λͼ����������
     *
     * @param arr
     */
    public static void bitmapSort(int[] arr) {
        // �ҳ���������ֵ
        int max = arr[0];
        int min = max;
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }
        // �õ�λͼ����
        int[] newArr = new int[max - min + 1];
        // ���µ���arr�е�Ԫ��
        int index = 0;
        for (int i = 0; i < newArr.length; i++) {
            while (newArr[i] > 0) {
                arr[index] = i + min;
                index++;
                newArr[i]--;
            }
        }
    }

    public static void main(String[] args) {

        int[] data = {4, 10, 19, 1, 9, 6, 4, 9};
        //[1-20] ��Ϊ��Щ���ֶ���1-20֮��
        int[] a = new int[20];//Ĭ��ȫ0
        for (int i = 0; i < data.length; i++) {
            if (a[data[i]] != 1) {
                a[data[i]] = 1;
            } else {
                System.out.println(data[i] + " ���ظ�������");
            }
        }
        System.out.println("������");
        for (int i = 1; i <= 19; i++) {
            if (a[i] == 1) {
                //����±�
                System.out.print(i + " ");
            }
        }

        System.out.println("\n10�Ƿ��������������?");
        System.out.println(a[10] == 1);
    }
}
