package cn.cb.adt.Strategy;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 上午10:44
 * 两个数的比较策略
 */
public interface Strategy {
    public boolean equal(Object object1, Object object2);

    /**
     * 比较两个数据元素的大小
     * 如果obj1 < obj2 返回-1
     * 如果obj1 = obj2 返回0
     * 如果obj1 > obj2 返回1
     */
    public int compare(Object object1, Object object2);
}
