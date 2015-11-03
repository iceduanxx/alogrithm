package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-16
 * Time: ÉÏÎç11:14
 * To change this template use File | Settings | File Templates.
 */

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.util.*;

public class Perm {

    public static void perm(List<String> list, int k, int m) {
        if (k == m) {
            for (int i = 0; i <= m; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        } else {
            for (int i = k; i <= m; i++) {
                Collections.swap(list, i, k);
                perm(list, k + 1, m);
                Collections.swap(list, i, k);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        String str = scanner.nextLine();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.substring(i, i + 1));
        }
        perm(list, 0, list.size() - 1);

    }

}
