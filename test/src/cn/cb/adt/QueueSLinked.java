package cn.cb.adt;

import cn.cb.adt.exception.QueueEmptyException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class QueueSLinked implements Queue {
    private SLNode front;
    private SLNode rear;
    private int size;

    public QueueSLinked() {
        front = new SLNode();
        rear = front;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(Object e) {
        SLNode p = new SLNode(e);
        rear.setNext(p);
        rear = p;
        size++;

    }

    @Override
    public Object dequeue() throws QueueEmptyException {
        if (size < 1)
            throw new QueueEmptyException("队列为空！");
        SLNode p = front.getNext();
        front.setNext(p.getNext());
        size--;
        if (size < 1) rear = front;    //如果队列为空,rear指向头结点
        return p.getData();
    }

    @Override
    public Object peek() throws QueueEmptyException {
        if (size < 1)
            throw new QueueEmptyException("队列为空！");
        Object obj = front.getData();
        return obj;
    }
}
