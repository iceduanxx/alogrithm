package cn.cb.adt.exception;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: 上午11:03
 * //线性表中出现序号越界时抛出该异常
 */
public class OutOfBoundaryException extends RuntimeException {
    public OutOfBoundaryException(String error) {
        super(error);
    }
}
