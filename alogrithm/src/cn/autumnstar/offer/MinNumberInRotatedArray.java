package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: ����3:19
 * ��ת�������С���� �����ö��ַ���ʱ�临�Ӷ�ΪO��lgn����
 * ��һ�������ʼ������Ԫ�ذᵽ�����ĩβ�����ǳ�֮Ϊ�������ת
 * ����һ����������������һ����ת��ת�������תԪ�ص���С����
 * ��������{3, 4, 5, 1, 2}Ϊ{1,2,3,4,5}��һ����ת��
 */
public class MinNumberInRotatedArray {

    public static int minNumberInRotatedArray(int a[], int i, int j) {
        int resultMin = -1;
        if (i <= j) {
            int middle = (i + j) / 2;
            if (a[i] < a[middle]) {
                resultMin = minNumberInRotatedArray(a, middle + 1, j);
            } else if (a[j] > a[middle]) {
                resultMin = minNumberInRotatedArray(a, i, middle);
            }
            if ((j-i)<=1) {
                resultMin = a[j];
            }
        }
        return resultMin;
    }

    public static void main(String[] args) {
         int a[] = {3, 4, 5, 1, 2};
       /*  int a[] = {3, 1, 2};*/
       /* int a[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};*/

        int result = minNumberInRotatedArray(a, 0, a.length - 1);
        System.out.println(result);

    }
}
