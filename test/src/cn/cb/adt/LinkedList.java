package cn.cb.adt;

import cn.cb.adt.exception.OutOfBoundaryException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: ����11:13
 * To change this template use File | Settings | File Templates.
 */
public interface LinkedList {

    /**
     * ��ѯ���ӱ�ǰ�Ĺ�ģ
     *
     * @return
     */
    public int getSize();

    /**
     * �ж����ӱ��Ƿ�Ϊ��
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * ���ص�һ�����
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Node first() throws OutOfBoundaryException;

    /**
     * �������һ���
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Node last() throws OutOfBoundaryException;

    /**
     * ����p֮��Ľ��
     *
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    public Node getNext(Node p) throws InvalidNodeException, OutOfBoundaryException;

    /**
     * ����p֮ǰ�Ľ��
     *
     * @param p
     * @return
     * @throws InvalidNodeException
     * @throws OutOfBoundaryException
     */
    public Node getPre(Node p) throws InvalidNodeException, OutOfBoundaryException;

    /**
     * ��e��Ϊ��һ��Ԫ�ز������ӱ�,������e���ڽ��
     *
     * @param e
     * @return
     */
    public Node insertFirst(Object e);

    /**
     * ��e��Ϊ���һ��Ԫ�ز����б�,������e���ڽ��
     *
     * @param e
     * @return
     */
    public Node insertLast(Object e);

    /**
     * ��e������p֮���λ��,������e���ڽ��
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    public Node insertAfter(Node p, Object e) throws InvalidNodeException;

    /**
     * ��e������p֮ǰ��λ��,������e���ڽ��
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    public Node insertBefore(Node p, Object e) throws InvalidNodeException;

    /**
     * ɾ������λ�ô���Ԫ�أ�������֮
     *
     * @param p
     * @return
     * @throws InvalidNodeException
     */
    public Object remove(Node p) throws InvalidNodeException;

    /**
     * ɾ����Ԫ�أ�������֮
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Object removeFirst() throws OutOfBoundaryException;

    /**
     * ɾ��ĩԪ�أ�������֮
     *
     * @return
     * @throws OutOfBoundaryException
     */
    public Object removeLast() throws OutOfBoundaryException;

    /**
     * �����ڸ���λ�õ�Ԫ���滻Ϊ��Ԫ�أ������ر��滻��Ԫ��
     *
     * @param p
     * @param e
     * @return
     * @throws InvalidNodeException
     */
    public Object replace(Node p, Object e) throws InvalidNodeException;

    /**
     * Ԫ�ص�����
     *
     * @return
     */
    public Iterator elements();
}