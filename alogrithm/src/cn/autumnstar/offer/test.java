package cn.autumnstar.offer;

public class test {
    public static void main(String args[]) {
        String s = "abaccdccabcdcba";
        System.out.println(getMax(s));
    }

    static String getMax(String s) {
        char[] b = s.toCharArray();
        int start = 0;
        int maxLength = 0;
        for (int i = 0; i < b.length; i++) {
            int tempLength = 0;
            int tempStart = 0;
            for (int j = 0; j <= i && j < b.length - i; j++) {
                System.out.println(b[i - j] + "===" + b[i + j]);
                if (b[i - j] == b[i + j]) {
                    tempStart = i - j;
                    tempLength += 2;
                } else
                    break;
            }

            if (tempLength > maxLength) {
                start = tempStart;
                maxLength = tempLength;
            }
            System.out.println("临时起始位置" + tempStart + ":" + tempLength + "|起始位置" + start + ":" + maxLength);
        }

        String result = s.substring(start, maxLength);
        return result;
    }
}