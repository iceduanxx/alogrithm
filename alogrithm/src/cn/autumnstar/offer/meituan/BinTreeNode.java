package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/11/3.
 */
public class BinTreeNode {

    private Object object;
    private BinTreeNode lChild;
    private BinTreeNode rChild;

    public BinTreeNode() {
    }

    public BinTreeNode(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public BinTreeNode getlChild() {
        return lChild;
    }

    public void setlChild(BinTreeNode lChild) {
        this.lChild = lChild;
    }

    public BinTreeNode getrChild() {
        return rChild;
    }

    public void setrChild(BinTreeNode rChild) {
        this.rChild = rChild;
    }
}
