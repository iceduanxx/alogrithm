package cn.autumnstar.offer.meituan;

/**
��ά���� �Խ������ ��������
����������飺
{ 1, 2, 3, 4 }, 
{ 5, 6, 7, 8 },
{ 9, 10, 11, 12 }, 
{ 13, 14, 15, 16 },

slash���������
1 
5 2 
9 6 3 
13 10 7 4 
14 11 8 
15 12 
16 

backslash�����
4 
3 8 
2 7 12 
1 6 11 16 
5 10 15 
9 14 
13 

������������������Բ���ȣ���ͬһ�е�Ԫ�ظ���������ȣ���Ϊ"����"������
 */
public class PrintArray {

    public static void main(String[] args) {
        /*// row != col:
        int[][] arr = new int[][] { 
                { 1, 2, 3, 4 }, 
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }, 
                //{ 13, 14, 15, 16 }, 
        };
        */
        int[][] arr = new int[][] { 
                { 1, 2, 3, 4 }, 
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }, 
                { 13, 14, 15, 16 },
                { 17, 18, 19, 20}
        };

        slash(arr);
        System.out.println("======");
        backslash(arr);
        
        
    }
    
    public static void slash(int[][] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("arr cannot be null");
        }
        int col = arr[0].length;
        int row = arr.length;
        for( int k = 0 ; k < col+row -1 ; k++ ) {     //k�����ж������������col����
            int sum = k;                              //ͬһ�е�Ԫ�أ����±�����±�ĺ����
            for( int i = 0 ; i < row ; i++ ) {
                int j = sum - i;
                if(isValidIndex(i, row) && isValidIndex(j, col)) {
                    System.out.print( arr[i][j] + " " );
                }
            }
            System.out.println();
        }
    }
    
    public static void backslash(int[][] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("arr cannot be null");
        }
        int row = arr.length;
        int col = arr[0].length;
        for (int k = 0; k < col * 2 - 1; k++) {     //k�����ж������������col����
            int diff = col - k - 1;                 //ͬһ�е�Ԫ�أ����±�����±�Ĳ����
            for (int j = 0; j < col; j++) {
                int i = j - diff;
                if (isValidIndex(i, row) && isValidIndex(j, col)) {
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isValidIndex(int i, int n) {
        return i >= 0 && i < n;
    }

}
