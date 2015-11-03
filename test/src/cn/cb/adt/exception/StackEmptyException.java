package cn.cb.adt.exception;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: обнГ2:48
 * To change this template use File | Settings | File Templates.
 */
public class StackEmptyException extends RuntimeException {
    public StackEmptyException(String err) {
        super(err);
    }
}
