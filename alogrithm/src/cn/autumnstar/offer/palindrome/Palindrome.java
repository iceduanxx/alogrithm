package cn.autumnstar.offer.palindrome;

import java.util.ArrayList;
import java.util.List;

public class Palindrome {

    /**
     * �ҳ�һ���ַ�������Ļ����Ӵ�
     * ���ַ����е�i���ַ���ʼ�����зǿջ����Ӵ��ĸ���, ����ΪCi. �˷����ĸ��Ӷ�Ϊ
     * O(C1 + C2 + ... + Cn)
     * ���ַ��������������ǿջ����Ӵ�����ʼλ�ò�ͬʱ, C1 = C2 = ... = Cn = 1, ���Ӷ�ΪO(N);
     * ���ַ��������ַ���Ϊͬһ�ַ�ʱ, Ci = n - i, ��ʱ���Ӷ�ΪO(N*N);
     * �ڶ��������, �˷����ĸ��Ӷ�Զ����O(N*N).
     * @param theString
     * @return
     */
    public List<String> getLongestPalindrome(String theString) {

        int strLen = theString.length();
        List<String> results = new ArrayList<String>(strLen);
        if (strLen == 0) {
            return results;
        }

        // �ӵ�i��λ�ÿ�ʼ�����л����Ӵ��Ľ���λ��.
        int[] endIndice = new int[strLen + 1];

        // endIndice����Ч���ݵĳ���.
        int numberOfPalindromes = 1;

        // ������Ӵ��ĳ���. ���ڷǿմ����ٿ����ҵ�����Ϊ1�Ļ����Ӵ�.
        int maxLen = 1;

        results.add(theString.substring(strLen - 1));

        // ����ӵ�i��λ�ÿ�ʼ�����л����Ӵ�. �������Ӵ���Ϊ����:
        // 1. �ڴӵ�i+1��λ�ÿ�ʼ�Ļ����Ӵ��Ļ�����, �����˼�����ͬ���ַ�;
        // 2. ����Ϊ1�Ļ����Ӵ�;
        // 3. �մ�.
        for (int i = strLen - 2; i >= 0; i--) {
            int j = 0, k = 0;
            while (j < numberOfPalindromes) {
                if (theString.charAt(i) == theString.charAt(endIndice[j])) {
                    endIndice[k] = endIndice[j] + 1;
                    int newLength = endIndice[k] - i;
                    if (newLength >= maxLen) {
                        if (newLength > maxLen) {
                            maxLen = newLength;
                            results.clear();
                        }
                        results.add(theString.substring(i, endIndice[k]));
                    }
                    if (endIndice[k] < strLen) {
                        k++;
                    }
                }
                j++;
            }
            // ���볤��Ϊ1���Ӵ�
            endIndice[k++] = i + 1;
            if (maxLen == 1) {
                results.add(theString.substring(i, i + 1));
            }
            // ����մ�
            endIndice[k++] = i;
            numberOfPalindromes = k;
        }
        return results;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        printList(p.getLongestPalindrome("gabcecbaefd"));
       /* printList(p.getLongestPalindrome("bbcbaefccfg"));
        printList(p.getLongestPalindrome("aaaaaaaaaaa"));
        printList(p.getLongestPalindrome("abcdefghijk"));
        printList(p.getLongestPalindrome("abcdeeddejk"));
        printList(p.getLongestPalindrome(""));*/
    }

    public static void printList(List<? extends Object> list) {
        System.out.println("**************************");
        System.out.println(list.size() + " result(s):");
        for (Object o : list) {
            System.out.println(o);
        }
    }
}