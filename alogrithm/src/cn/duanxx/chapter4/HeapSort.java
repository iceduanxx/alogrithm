package cn.duanxx.chapter4;

import java.util.Comparator;

public class HeapSort {
    public static int parent(int i) {
        return (i - 1) >> 1;
    }

    public static int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    public static int right(int i) {
        return (i + 1) << 1;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void heapify(T[] a, int i, Comparator<? super T> c, int size) {
        int l = left(i);
        int r = right(i);
        int next = i;
        if (l < size && c.compare(a[l], a[i]) > 0)
            next = l;
        if (r < size && c.compare(a[r], a[next]) > 0)
            next = r;
        if (i == next)
            return;
        swap(a, i, next);
        heapify(a, next, c, size);
    }

    public static <T> void heapify(T[] a, int i, Comparator<? super T> c) {
        heapify(a, i, c, a.length);
    }

    public static <T> void buildHeap(T[] a, Comparator<? super T> c) {
        for (int i = (a.length + 1) / 2 - 1; i >= 0; i--) {
            heapify(a, i, c);
        }
    }

    public static <T> void heapSort(T[] a, Comparator<? super T> c) {
        buildHeap(a, c);
        for (int i = a.length - 1; i > 0; i--) {
            swap(a, 0, i);
            heapify(a, 0, c, i);
        }
    }

    public static void main(String[] args) {
        //heapSort test
       /* Integer[] temp = new Integer[]{
                5, 2, 4, 6, 1, 3, 2, 6
        };*/
        int n = 10000000;
        Integer[] temp2 = new Integer[n];
        for(int i=0;i<n;i++){
           temp2[i]=n-i;
        }
        long startTime =System.currentTimeMillis();
        heapSort(temp2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("n∂—≈≈–ÚÀ„∑®∫ƒ ±"+(endTime-startTime));
        /*for (int i : temp2) {
            System.out.print(i + " ");
        }
        System.out.println();*/
    }
}
