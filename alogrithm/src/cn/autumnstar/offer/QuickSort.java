package cn.autumnstar.offer;

/**
 * ¿ìËÙÅÅĞòËã·¨
 *
 * @author icecookstar
 */
public class QuickSort {
    public static void quickSort(int a[], int low, int high) {
        int i = low;
        int j = high;
        int temp = a[low];

        while (i < j) {
            while (temp < a[j] && i < j)
                j--;
            if (i < j) {
                a[i] = a[j];
                i++;
            }

            while (a[i] < temp && i < j)
                i++;
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = temp;
        if (low < i) quickSort(a, low, i - 1);
        if (i < high) quickSort(a, j + 1, high);
    }


    public static void main(String[] args) {

        int a[] = {36, 55, 48, 37, 10, 90, 84, 60};

      /*  int n=10000;
        int [] a = new int[n];
        for(int i=0;i<n;i++){
           a[i]=n-i;
        }*/

        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length - 1; i++) {
            System.out.print(a[i] + ",");
        }
    }
}
