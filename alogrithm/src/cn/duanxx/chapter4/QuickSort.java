package cn.duanxx.chapter4;

import java.util.Comparator;

public class QuickSort {

    private static int sum=0;
	public static <T> void quickSort(T[] a, Comparator<? super T> c) {
		quickSort(a, c, 0, a.length);
	}

	public static <T> void quickSort(T[] a, Comparator<? super T> c, int p,
			int r) {
         sum++;
        System.out.println(sum);
		if (p < r) {
			int q = partition(a, c, p, r);
			quickSort(a, c, p, q);
			quickSort(a, c, q + 1, r);
		}
	}

	public static <T> int partition(T[] a, Comparator<? super T> c, int p, int r) {

		T t = a[r - 1];
		int i = p - 1;
		for (int j = p; j < r - 1; j++) {
			if (c.compare(a[j], t) <= 0) {
				i++;
				T tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}
		}
		T tmp = a[i + 1];
		a[i + 1] = a[r - 1];
		a[r - 1] = tmp;
		return i + 1;
	}

	public static void main(String[] args) {
		/*Integer[] temp = new Integer[] { 2,4,7,8,26,1,5 };*/
        int n=10000;
        Integer [] temp = new Integer[n];
        for(int i=0;i<n;i++){
           temp[i]=n-i;
        }
		quickSort(temp, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		/*for (int i : temp) {
			System.out.print(i + " ");
		}*/
		/*System.out.println(sum);
        System.out.println(sum);*/
	}
}