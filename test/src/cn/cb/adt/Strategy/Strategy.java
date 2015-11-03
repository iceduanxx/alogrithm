package cn.cb.adt.Strategy;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: ����10:44
 * �������ıȽϲ���
 */
public interface Strategy {
    public boolean equal(Object object1, Object object2);

    /**
     * �Ƚ���������Ԫ�صĴ�С
     * ���obj1 < obj2 ����-1
     * ���obj1 = obj2 ����0
     * ���obj1 > obj2 ����1
     */
    public int compare(Object object1, Object object2);
}
