package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User:  duanxx
 * Date: 13-10-10
 * Time: 下午3:11
 * 二叉树节点
 */
public class BinTreeNode {
    private int key;
    private BinTreeNode leftChild;
    private BinTreeNode rightChild;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BinTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
