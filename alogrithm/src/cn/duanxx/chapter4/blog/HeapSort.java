package cn.duanxx.chapter4.blog;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-26
 * Time: 上午10:24
 * <p>堆排序算法</p>
 */
public class HeapSort {
    /**
     * 一个节点的父节点下标
     *
     * @param i
     * @return
     */
    public static int parent(int i) {
        return (i - 1) >> 1;
    }

    /**
     * 一个节点的左孩子节点下标
     *
     * @param i
     * @return
     */
    public static int left(int i) {
        return ((i + 1) << 1) - 1;
    }

    /**
     * 一个节点的右孩子节点下标
     *
     * @param i
     * @return
     */
    public static int right(int i) {
        return (i + 1) << 1;
    }

    public static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T> void heapify(T[] a, int i, Comparator<? super T> c, int size) {
        int l = left(i);
        int r = right(i);
        int next = i;
        if (l < size && c.compare(a[l], a[i]) > 0) {
            next = l;
        }
        if (l < size && c.compare(a[r], a[next]) > 0) {
            next = r;
        }
        if (next == i) {
            return;
        }
        swap(a, i, next);
        heapify(a, next, c, size);
    }

    public static <T> void heapify(T[] a, int i, Comparator<? super T> c) {
        heapify(a, i, c, a.length-1);
    }

    public static <T> void buildHeap(T[] a, Comparator<? super T> c) {
        for (int i = (a.length + 1) / 2 - 1; i >= 0; i--) {
             heapify(a,i,c);
        }

    }

    public static <T> void heapSort(T[] a,Comparator<? super T> c){
        buildHeap(a,c);
        for(int i=a.length-1;i>=0;i--){
            swap(a,0,i);
            heapify(a,0,c,i);
        }
    }

    public static void main(String[] args) {
        //heapSort test
        Integer[] temp = new Integer[]{
                5, 2, 4, 6, 1, 3, 2, 6
        };
        /*Integer[] temp2 = new Integer[10000000];
        for(int i=0;i<1000000000;i++){
           temp2[i]=i+1;
        }*/
        heapSort(temp, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i : temp) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
