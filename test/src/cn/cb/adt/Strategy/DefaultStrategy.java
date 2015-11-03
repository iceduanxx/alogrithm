package cn.cb.adt.Strategy;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 上午10:49
 *比较策略
 */
public class DefaultStrategy implements Strategy {
    @Override
    public boolean equal(Object object1, Object object2) {
        return object1.toString().equals(object2.toString());
    }

    @Override
    public int compare(Object object1, Object object2) {
        int compare = object1.toString().compareTo(object2.toString());
        if (compare > 0) return 1;
        else if (compare == 0) return 0;
        return -1;
    }
}
