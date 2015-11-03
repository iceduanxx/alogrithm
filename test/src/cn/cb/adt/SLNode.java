package cn.cb.adt;

import com.sun.xml.internal.bind.v2.model.core.Element;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: ÉÏÎç10:20
 * Ë«ÏòÁ´±í
 */
public class SLNode implements Node {
    private Object element;
    private SLNode next;

    public SLNode() {
    }

    public SLNode(Object element, SLNode next) {
        this.element = element;
        this.next = next;
    }

    public SLNode(Object e) {
        this.element = e;
    }

    @Override
    public Object getData() {
        return element;
    }

    @Override
    public void setData(Object element) {
        this.element = element;
    }

    public SLNode getNext() {
        return next;
    }

    public void setNext(SLNode next) {
        this.next = next;
    }

}
