package cn.autumnstar.offer.bczm;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 14-1-21
 * Time: ����11:10
 * Ѱ�ҷ���ˮ��
 */
public class Find {

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 2, 5, 2, 2, 3, 2, 5, 2};
        System.out.println(find(arr, arr.length));
    }

    static int find(int ID[], int n) {
        int nTimes = 0, i, candidate = 0;

        for (i = 0; i < n; i++) {
            if (nTimes == 0) {
                candidate = ID[i];
                nTimes = 1;
            } else {
                if (candidate == ID[i]) {
                    nTimes++;
                } else {
                    nTimes--;
                }
            }
        }
        return candidate;
    }
}
