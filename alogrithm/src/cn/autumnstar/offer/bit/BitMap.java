package cn.autumnstar.offer.bit;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-14
 * Time: 下午4:24
 * 使用位图法进行排序
 */
public class BitMap {

    /**
     * 使用位图法进行排序
     *
     * @param arr
     */
    public static void bitmapSort(int[] arr) {
        // 找出数组中最值
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
        // 得到位图数组
        int[] newArr = new int[max - min + 1];
        // 重新调整arr中的元素
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
        //[1-20] 因为这些数字都在1-20之间
        int[] a = new int[20];//默认全0
        for (int i = 0; i < data.length; i++) {
            if (a[data[i]] != 1) {
                a[data[i]] = 1;
            } else {
                System.out.println(data[i] + " 是重复的数字");
            }
        }
        System.out.println("排序结果");
        for (int i = 1; i <= 19; i++) {
            if (a[i] == 1) {
                //输出下标
                System.out.print(i + " ");
            }
        }

        System.out.println("\n10是否在这个数组里面?");
        System.out.println(a[10] == 1);
    }
}
