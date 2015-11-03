package cn.cb.adt.exception;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-16
 * Time: обнГ4:15
 * To change this template use File | Settings | File Templates.
 */
public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String err) {
        super(err);
    }
}
