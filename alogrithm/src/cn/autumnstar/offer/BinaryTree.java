package cn.autumnstar.offer;

public class BinaryTree {

    int data;

    BinaryTree left;

    BinaryTree right;

    BinaryTree(int data) {
        this.data = data;
        left = null;
        right = null;
    }

//插入节点

    public void insertTree(BinaryTree root, int data) {
        if (data >= root.data) {
            if (root.right == null) {
                root.right = new BinaryTree(data);
            } else {
                insertTree(root.right, data);
            }
        } else {
            if (root.left == null) {
                root.left = new BinaryTree(data);
            } else {
                insertTree(root.left, data);
            }
        }
    }

    // 结果返回树的高度
    public int height() {
        int heightOfTree;
        if (this == null)
            return -1;
        int leftHeight = (left == null ? 0 : left.height());
        int rightHeight = (right == null ? 0 : right.height());
        heightOfTree = leftHeight < rightHeight ? rightHeight : leftHeight;
        return 1 + heightOfTree;
    }

    // 前序遍历二叉树
    public void preOrder(BinaryTree parent) {
        if (parent == null)
            return;
        System.out.print(parent.data + " ");
        preOrder(parent.left);
        preOrder(parent.right);
    }

    // 中序遍历二叉树
    public void inOrder(BinaryTree parent) {
        if (parent == null)
            return;
        inOrder(parent.left);
        System.out.print(parent.data + " ");
        inOrder(parent.right);
    }

    // 后序遍历二叉树
    public void postOrder(BinaryTree parent) {
        if (parent == null)
            return;
        postOrder(parent.left);
        postOrder(parent.right);
        System.out.print(parent.data + " ");

    }

    //查找
    public boolean searchkey(BinaryTree root, int key) {
        boolean bl = false;
        if (root == null) {
            bl = false;
            return bl;
        } else if (root.data == key) {
            bl = true;
            return bl;
        } else if (key >= root.data) {
            return searchkey(root.right, key);
        }
        return searchkey(root.left, key);
    }
}