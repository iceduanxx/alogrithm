package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-30
 * Time: обнГ2:37
 * To change this template use File | Settings | File Templates.
 */
public class MoreThanHalfNumber {
    /**
     * ряеепР
     */
    public static void moreThanHalfNumber(int[] a) {
        int currentMax = 1;
        int current = 0;
        for (int i = 1; i < a.length; i++) {

            if (a[i-1] == a[i]) {
                current = a[i];
                currentMax++;
            }else{
               current=a[i];
            }
        }

        System.out.println(current);
        System.out.println(currentMax);

    }

    public static void main(String[] args){
        int a[] ={1,2,2,2,2,2,3,4};
         moreThanHalfNumber(a);
    }
}
