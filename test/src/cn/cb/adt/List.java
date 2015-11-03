package cn.cb.adt;

import cn.cb.adt.exception.OutOfBoundaryException;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 上午11:02
 * To change this template use File | Settings | File Templates.
 */
public interface List {


    /**
     * 返回线性表的大小，即数据元素的个数
     *
     * @return
     */
    public int getSize();

    /**
     * 如果线性表为空返回true，否则返回false。
     *
     * @return
     */
    public boolean isEmpty();

    /**
     * 判断线性表是否包含数据元素e
     *
     * @param e
     * @return
     */
    public boolean contains(Object e);

    /**
     * 返回数据元素e在线性表中的序号
     *
     * @param e
     * @return
     */
    public int indexOf(Object e);

    /**
     * 将数据元素e插入到线性表中i号位置
     *
     * @param i
     * @param e
     * @throws OutOfBoundaryException
     */
    public void insert(int i, Object e) throws OutOfBoundaryException;

    /**
     * 将数据元素e插入到元素obj之前
     *
     * @param obj
     * @param e
     * @return
     */
    public boolean insertBefore(Object obj, Object e);

    /**
     * 将数据元素e插入到元素obj之后
     *
     * @param obj
     * @param e
     * @return
     */
    public boolean insertAfter(Object obj, Object e);

    /**
     * 删除线性表中序号为i的元素,并返回之
     *
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    public Object remove(int i) throws OutOfBoundaryException;

    /**
     * 删除线性表中第一个与e相同的元素
     *
     * @param e
     * @return
     */
    public boolean remove(Object e);

    /**
     * 替换线性表中序号为i的数据元素为e，返回原数据元素
     *
     * @param i
     * @param e
     * @return
     * @throws OutOfBoundaryException
     */
    public Object replace(int i, Object e) throws OutOfBoundaryException;

    /**
     * 返回线性表中序号为i的数据元素
     *
     * @param i
     * @return
     * @throws OutOfBoundaryException
     */
    public Object get(int i) throws OutOfBoundaryException;
}
