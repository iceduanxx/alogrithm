package cn.duanxx.chapter4;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {

        int[] a = {2, 5, 4, 7, 8, 26, 1};
        new Test().quickSort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public void quickSort(int a[], int p, int r) {
        int q = partition(a, p, r);
        if (p < r) {
            quickSort(a, p, q);
            quickSort(a, q + 1, r);
        }

    }

    public int partition(int a[], int p, int r) {
        int i = p - 1;
        int x = a[r];

        for (int j = p; j < r; j++) {
            if (a[j] < x) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        a[r] = a[i + 1];
        a[i + 1] = x;
        return i + 1;
    }

}
