package cn.cb.adt;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: ÉÏÎç10:23
 * To change this template use File | Settings | File Templates.
 */
public class DLNode implements Node {
    private DLNode pre;
    private Object element;
    private DLNode next;

    public DLNode() {
    }

    public DLNode(DLNode pre, Object element, DLNode next) {
        this.pre = pre;
        this.element = element;
        this.next = next;
    }

    @Override
    public Object getData() {
        return element;
    }

    @Override
    public void setData(Object element) {
        this.element = element;
    }

    public DLNode getPre() {
        return pre;
    }

    public void setPre(DLNode pre) {
        this.pre = pre;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }
}
