package cn.duanxx.chapter4.blog;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: dell--30
 * Date: 13-9-25
 * Time: ����4:51
 * ԭ�����£���1��2��2��3��4��6���������֣���javaдһ��main��������ӡ�����в�ͬ�����У�
 * �磺612234��412346�ȣ�Ҫ��"4"�����ڵ���λ��"3"��"6"��������.
 * <p/>
 * 1 ��������Ϊͼ�ṹ�ı������⡣ʵ����6�����־���������㣬������������ӳ�������ͨͼ������ÿһ����������ͼ�εı���·����
 * ���н��ı���·������������6�����ֵ�������Ͻ������
 * 2 ��Ȼ����������δ�ﵽ��Ŀ��Ҫ�󡣴����¼������濼�ǣ�
 * 1. 3��6����������ʵ��Ҫ�������ͨͼ�Ľ��3��5֮�䲻����ͨ, ���ڹ���ͼ�ṹʱ�������������Ȼ���ٱ���ͼ��
 * 2. �������ظ�: ���ǵ�������2�����Ի�����ظ���������԰ѽ��������TreeSet�й����ظ����
 * 3. 4�����ڵ���λ: �Ծ��ڽ������ȥ������������Ľ����
 */
public class Rank {

    private String[] b = new String[]{" 1 ", " 2 ", " 2 ", " 3 ", " 4 ", " 6 "};

    private int n = b.length;

    private boolean[] visited = new boolean[n];

    private int[][] a = new int[n][n];

    private String result = "";

    private TreeSet set = new TreeSet();

    public static void main(String[] args) {
        new Rank().start();
    }

    private void start() {

        //  Initial the map a[][]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    a[i][j] = 0;
                } else {
                    a[i][j] = 1;
                }
            }
        }

        //  3 and 5 can not be the neighbor.
        a[3][5] = 0;
        a[5][3] = 0;

        //  Begin to depth search.
        for (int i = 0; i < n; i++) {
            this.depthFirstSearch(i);
        }

        //  Print result treeset.
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String string = (String) it.next();
            System.out.println(string);
        }
    }

    private void depthFirstSearch(int startIndex) {
        visited[startIndex] = true;
        result = result + b[startIndex];
        if (result.length() == n) {
            //    "4" can not be the third position.
            if (result.indexOf(" 4 ") != 2) {
                //     Filt the duplicate value.
                set.add(result);
            }
        }
        for (int j = 0; j < n; j++) {
            if (a[startIndex][j] == 1 && visited[j] == false) {
                depthFirstSearch(j);
            }
        }

        //  restore the result value and visited value after listing a node.
        result = result.substring(0, result.length() - 1);
        visited[startIndex] = false;
    }

}
