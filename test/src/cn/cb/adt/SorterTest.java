package cn.cb.adt;

import cn.cb.adt.Strategy.DefaultStrategy;
import cn.cb.adt.Strategy.Strategy;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-9
 * Time: œ¬ŒÁ3:08
 * To change this template use File | Settings | File Templates.
 */
public class SorterTest {

    private Strategy strategy;

    public SorterTest(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * ÷±Ω”≤Â»Î≈≈–Ú
     *
     * @param r
     * @param low
     * @param high
     */
    public void insertSort(Object[] r, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int j = i;
            int k = i;
            Object temp = r[j];
            for (; j > 0; j--) {
                if (strategy.compare(temp, r[j - 1]) < 0) {
                    r[j] = r[j - 1];
                    k--;
                }
            }
            r[k] = temp;

        }
    }

    /**
     * √∞≈›≈≈–Ú
     *
     * @param r
     * @param low
     * @param high
     */
    public void bubbleSort(Object[] r, int low, int high) {
        for (int i = 0; i <= high; i++) {
            for (int j = low; j < high - i; j++) {
                if (strategy.compare(r[j], r[j + 1]) > 0) {
                    swap(r, j, j + 1);
                }
            }
        }
    }

    public void swap(Object[] r, int i, int j) {
        Object temp = r[i];
        r[i] = r[j];
        r[j] = temp;
    }

    /**
     * —°‘Ò≈≈–Ú
     *
     * @param r
     * @param low
     * @param high
     */
    public void selectSort(Object[] r, int low, int high) {
        for (int i = low; i <= high; i++) {
            int min = i;
            for (int j = i; j <= high; j++) {
                if (strategy.compare(r[j], r[min]) < 0) {
                    min = j;
                }
            }
            swap(r, min, i);
        }
    }

    /**
     * πÈ≤¢≈≈–Ú
     *
     * @param r
     * @param low
     * @param high
     */
    public void mergeSort(Object[] r, int low, int high) {
        if (low < high) {
            mergeSort(r, low, (low + high) / 2);
            mergeSort(r, (low + high) / 2 + 1, high);
            merge(r, low, (low + high) / 2, high);
        }
    }

    public void merge(Object[] a, int p, int q, int r) {
        Object[] b = new Object[r - p + 1];
        int s = p;
        int t = p + 1;
        int k = 0;
        while (s < t && t < r) {
            if (strategy.compare(a[s], a[t]) < 0) {
                b[k] = a[s];
                k++;
                s++;
            } else {
                b[k] = a[t];
                k++;
                t++;
            }
        }
        while (s < t) {
            b[k] = a[s];
            s++;
            k++;
        }
        while (t < r) {
            b[k] = a[t];
            t++;
            k++;
        }
        for (int i = 0; i < b.length; i++) {
            a[p + i] = b[i];
        }

    }


    public static void main(String[] args) {
        SorterTest sorter = new SorterTest(new DefaultStrategy());
        String[] r = {"26", "53", "48", "01", "03", "38", "32", "15", "09"};

        /* sorter.insertSort(r, 0, r.length - 1);*/
        /*sorter.bubbleSort(r, 0, r.length - 1);*/
        /*sorter.selectSort(r, 0, r.length - 1);*/
        sorter.mergeSort(r, 0, r.length - 1);
        for (int i = 0; i < r.length; i++)
            System.out.print(r[i] + "\t");
    }
}
