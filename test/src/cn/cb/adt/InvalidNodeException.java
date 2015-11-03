package cn.cb.adt;

/**
 * Created by IntelliJ IDEA.
 * User: dell--30
 * Date: 13-9-13
 * Time: обнГ4:15
 * To change this template use File | Settings | File Templates.
 */
public class InvalidNodeException extends RuntimeException {
    public InvalidNodeException(String message) {
        super(message);
    }
}
