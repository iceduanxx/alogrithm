package cn.cb.adt;

import cn.cb.adt.exception.OutOfBoundaryException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: ����11:02
 * To change this template use File | Settings | File Templates.
 */
public interface List {


    /**
     * �������Ա�Ĵ�С��������Ԫ�صĸ���
     *
     * @return
     */
    public int getSize();

    /**
     * ������Ա�Ϊ�շ���true�����򷵻�false��
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * �ж����Ա��Ƿ��������Ԫ��e
     *
     * @param e
     * @return
     */
    public boolean contains(Object e);

    /**
     * ��������Ԫ��e�����Ա��е����
     *
     * @param e
     * @return
     */
    public int indexOf(Object e);

    /**
     * ������Ԫ��e���뵽���Ա���i��λ��
     *
     * @param i
     * @param e
     * @throws OutOfBoundaryException
     */
    public void insert(int i, Object e) throws OutOfBoundaryException;

    /**
     * ������Ԫ��e���뵽Ԫ��obj֮ǰ
     *
     * @param obj
     * @param e
     * @return
     */
    public boolean insertBefore(Object obj, Object e);

    /**
     * ������Ԫ��e���뵽Ԫ��obj֮��
     *
     * @param obj
     * @param e
     * @return
     */
    public boolean insertAfter(Object obj, Object e);

    /**
     * ɾ�����Ա������Ϊi��Ԫ��,������֮
     *
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    public Object remove(int i) throws OutOfBoundaryException;

    /**
     * ɾ�����Ա��е�һ����e��ͬ��Ԫ��
     *
     * @param e
     * @return
     */
    public boolean remove(Object e);

    /**
     * �滻���Ա������Ϊi������Ԫ��Ϊe������ԭ����Ԫ��
     *
     * @param i
     * @param e
     * @return
     * @throws OutOfBoundaryException
     */
    public Object replace(int i, Object e) throws OutOfBoundaryException;

    /**
     * �������Ա������Ϊi������Ԫ��
     *
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    public Object get(int i) throws OutOfBoundaryException;
}
