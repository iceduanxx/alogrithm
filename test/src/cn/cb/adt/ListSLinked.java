package cn.cb.adt;

import cn.cb.adt.Strategy.Strategy;
import cn.cb.adt.exception.OutOfBoundaryException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 上午11:12
 * To change this template use File | Settings | File Templates.
 */
public class ListSLinked implements List {

    private Strategy strategy;
    private SLNode head;
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        SLNode p = head;
        while (p.getNext() != null) {
            if (strategy.equal(p.getData(), e)) {
                return true;
            }
            p = p.getNext();
        }
        return false;
    }

    @Override
    public int indexOf(Object e) {
        SLNode p = head;
        int i = 0;
        while (p.getNext() != null) {
            if (strategy.equal(p.getData(), e)) {
                return i;
            }
            p = p.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public void insert(int i, Object e) throws OutOfBoundaryException {
        SLNode slNode = getPreNode(i);
        SLNode enode = new SLNode();
        enode.setData(e);
        if (slNode.getNext() != null) {
            enode.setNext(slNode.getNext());
        }
        slNode.setNext(enode);

    }

    @Override
    public boolean insertBefore(Object obj, Object e) {
        SLNode p = getPreNode(obj);
        if (p != null) {
            SLNode insertNode = new SLNode(e, p.getNext());
            p.setNext(insertNode);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertAfter(Object obj, Object e) {
        return false;
    }

    @Override
    public Object remove(int i) throws OutOfBoundaryException {
        SLNode p = getPreNode(i);
        if (p != null) {
            SLNode currentNode = p.getNext();
            if (currentNode.getNext() != null) {
                p.setNext(currentNode.getNext());
            } else {
                p.setNext(null);
            }
            return currentNode.getData();
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(Object e) {
        SLNode p = getPreNode(e);
        if (p != null) {
            SLNode enode = p.getNext();
            if (enode.getNext() != null) {
                p.setNext(enode.getNext());
            }
            enode = null;
            return true;
        }
        return false;
    }

    @Override
    public Object replace(int i, Object e) throws OutOfBoundaryException {
        SLNode p = getPreNode(i);
        Object retObj = null;
        if (p != null) {
            retObj = p.getNext().getData();
            p.getNext().setData(e);
        }
        return retObj;
    }

    public SLNode getPreNode(int i) {
        SLNode p = head;
        for (; i > 0; i--) {
            p = p.getNext();
        }
        return p;
    }

    public SLNode getPreNode(Object e) {
        SLNode p = head;
        while (p.getNext() != null) {
            if (strategy.equal(p.getNext().getData(), e)) {
                return p;
            } else {
                p = p.getNext();
            }
        }
        return null;
    }

    @Override
    public Object get(int i) throws OutOfBoundaryException {
        SLNode returnNode = getNode(i);
        return returnNode.getData();

    }

    public SLNode getNode(int i) throws OutOfBoundaryException {
        if (i < 0 || i > size) {
            throw new OutOfBoundaryException("下标越界！");
        }
        SLNode p = head.getNext();
        for (; i > 0; i--) {
            p = p.getNext();
        }
        return p;
    }
}
