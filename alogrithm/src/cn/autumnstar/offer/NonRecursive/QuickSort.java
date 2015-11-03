package cn.autumnstar.offer.NonRecursive;


import java.util.Stack;

public class QuickSort {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        QuickSort t = new QuickSort();
        t.test();
    }

    public void test() {
       /* int a[]={10,1,4,7,8,6,3,4,4,4,4,4,2,5,9,4,2};*/
       /* int a[] = {5,4,3};*/
        long startDate = System.currentTimeMillis();
        int n=1000;
        int [] a = new int[n];
        for(int i=0;i<n;i++){
           a[i]=n-i;
        }
        //printArray(a);
        /*quickSort(a,0,a.length-1);*/
        //nonRecrutSort(a);
        nonRecrutQuickSort(a);
        long endDate = System.currentTimeMillis();

        System.out.println("1000000数据排序，耗费的时间为："+(endDate-startDate));
        /*printArray(a);*/
        //partition(a, 0, 5);
    }

    public void quickSort(int[] a, int start, int end) {//递归快排
        if (start < end) {
            quickSort(a, start, partition(a, start, end) - 1);
            quickSort(a, partition(a, start, end) + 1, end);
        }
    }

    public void nonRecrutSort(int[] a) {//非递归快排，两个栈
        //设置两个栈，一个用于保存
        if (a == null || a.length < 0) return;
        Stack<Integer> startStack = new Stack<Integer>();//保存当前划分的最高位
        Stack<Integer> endStack = new Stack<Integer>();//保存当前划分的最低位
        int start = 0;
        int end = a.length - 1;

        int pivotPos;

        startStack.push(start);
        endStack.push(end);

        while (!startStack.isEmpty()) {
            start = startStack.pop();
            end = endStack.pop();
            pivotPos = partition(a, start, end);
            if (start < pivotPos - 1) {
                startStack.push(start);
                endStack.push(pivotPos - 1);
            }
            if (end > pivotPos + 1) {
                startStack.push(pivotPos + 1);
                endStack.push(end);
            }
        }
    }

    public void nonRecrutQuickSort(int a[]) {
        if (a == null || a.length <= 0) return;
        Stack<Integer> index = new Stack<Integer>();
        int start = 0;
        int end = a.length - 1;

        int pivotPos;

        index.push(start);
        index.push(end);

        while (!index.isEmpty()) {
            end = index.pop();
            start = index.pop();

            pivotPos = partition(a, start, end);
            if (start < pivotPos - 1) {
                index.push(start);
                index.push(pivotPos - 1);
            }
            if (end > pivotPos + 1) {
                index.push(pivotPos + 1);
                index.push(end);
            }
        }
    }

    public int partition(int[] a, int start, int end) {//分块方法，在数组a中，对下标从start到end的数列进行划分
        int pivot = a[start];                     //把比pivot(初始的pivot=a[start]小的数移动到pivot的左边
        while (start < end) {                       //把比pivot大的数移动到pivot的右边
            while (start < end && a[end] >= pivot) end--;
            a[start] = a[end];
            while (start < end && a[start] <= pivot) start++;
            a[end] = a[start];
        }
        a[start] = pivot;
        return start;//返回划分后的pivot的位置
        //printArray(a);
    }

    public void printArray(int a[]) {//打印数组内容的方法，用于测试
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

}
