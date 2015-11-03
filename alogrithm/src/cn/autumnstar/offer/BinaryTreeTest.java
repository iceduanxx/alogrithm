package cn.autumnstar.offer;

public class BinaryTreeTest {
    public static void main(String args[]) {
        BinaryTreeTest b = new BinaryTreeTest();
        int data[] = {12, 11, 34, 45, 67, 89, 56, 43, 22, 98,13};
        BinaryTree root = new BinaryTree(data[0]);
        for (int i = 1; i < data.length; i++) {
            root.insertTree(root, data[i]);
        }
        System.out.println();
        root.postOrder(root);
        System.out.println();
        root.preOrder(root);
        System.out.println();
        root.inOrder(root);
        System.out.println(data[data.length - 1]);
        int key = Integer.parseInt("34");
        if (root.searchkey(root, key)) {
            System.out.println("找到了:" + key);
        } else {
            System.out.println("没有找到：" + key);
        }
    }

}