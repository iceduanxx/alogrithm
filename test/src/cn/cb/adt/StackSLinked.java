package cn.cb.adt;

import cn.cb.adt.exception.StackEmptyException;
import sun.font.EAttribute;

import javax.tools.ToolProvider;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: ÏÂÎç2:52
 * To change this template use File | Settings | File Templates.
 */
public class StackSLinked implements Stack {
    private SLNode top;
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
    public void push(Object e) {
        SLNode slNode = new SLNode(e, top);
        top = slNode;
        size++;
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (size < 1)
            throw new StackEmptyException("¶ÑÕ»ÔªËØÎª¿Õ£¡");
        Object obj = top.getData();
        top = top.getNext();
        size--;
        return obj;
    }

    @Override
    public Object peek() throws StackEmptyException {
        if (size < 1)
            throw new StackEmptyException("¶ÑÕ»ÔªËØÎª¿Õ£¡");
        Object obj = top.getData();
        return obj;
    }
}
