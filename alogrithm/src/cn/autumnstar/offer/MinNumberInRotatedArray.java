package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-29
 * Time: 下午3:19
 * 旋转数组的最小数字 （采用二分法，时间复杂度为O（lgn））
 * 把一个数组最开始的若干元素搬到数组的末尾，我们称之为数组的旋转
 * 输入一个递增排序的数组的一个旋转旋转，输出旋转元素的最小数字
 * 例如数组{3, 4, 5, 1, 2}为{1,2,3,4,5}的一个旋转。
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
