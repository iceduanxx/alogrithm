package cn.cb.adt;

import cn.cb.adt.exception.OutOfBoundaryException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 下午4:30
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListIterator implements Iterator {
    private Node element;
    private LinkedList list;

    public LinkedListIterator(LinkedList list) {
        this.list = list;
        if (list.isEmpty()) {
            element = null;
        } else {
            list.first();
        }

    }

    @Override
    public void first() {
        if (list.isEmpty()) {
            element = null;
        } else {
            element = list.first();

        }
    }

    @Override
    public void next() {
        if (isDone()) {
            throw new OutOfBoundaryException("错误：已经没有元素。");
        }
        if (list.last() == element) {
            element = null;
        } else {
            element = list.getNext(element);
        }

    }

    @Override
    public boolean isDone() {
        return element == null;

    }

    @Override
    public Object currentItem() {
        if (isDone())
            throw new OutOfBoundaryException("错误：已经没有元素。");
        return element;
    }
}
