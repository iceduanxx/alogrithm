package cn.cb.adt;

import cn.cb.adt.exception.OutOfBoundaryException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: 上午11:13
 * To change this template use File | Settings | File Templates.
 */
public interface LinkedList {

    /**
     * 查询链接表当前的规模
     *
     * @return
     */
    public int getSize();

    /**
     * 判断链接表是否为空
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * 返回第一个结点
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Node first() throws OutOfBoundaryException;

    /**
     * 返回最后一结点
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Node last() throws OutOfBoundaryException;

    /**
     * 返回p之后的结点
     *
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    public Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException;

    /**
     * 返回p之前的结点
     *
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    public Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException;

    /**
     * 将e作为第一个元素插入链接表,并返回e所在结点
     *
     * @param e
     * @return
     */
    public Node insertFirst(Object e);

    /**
     * 将e作为最后一个元素插入列表,并返回e所在结点
     *
     * @param e
     * @return
     */
    public Node insertLast(Object e);

    /**
     * 将e插入至p之后的位置,并返回e所在结点
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    public Node insertAfter(Node p, Object e) throws InvalidNodeException;

    /**
     * 将e插入至p之前的位置,并返回e所在结点
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    public Node insertBefore(Node p, Object e) throws InvalidNodeException;

    /**
     * 删除给定位置处的元素，并返回之
     *
     * @param p
     * @return
     * @throws InvalidNodeException
     */
    public Object remove(Node p) throws InvalidNodeException;

    /**
     * 删除首元素，并返回之
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Object removeFirst() throws OutOfBoundaryException;

    /**
     * 删除末元素，并返回之
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Object removeLast() throws OutOfBoundaryException;

    /**
     * 将处于给定位置的元素替换为新元素，并返回被替换的元素
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    public Object replace(Node p, Object e) throws InvalidNodeException;

    /**
     * 元素迭代器
     *
     * @return
     */
    public Iterator elements();
}