package cn.cb.adt.exception;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-13
 * Time: ����11:03
 * //���Ա��г������Խ��ʱ�׳����쳣
 */
public class OutOfBoundaryException extends RuntimeException {
    public OutOfBoundaryException(String error) {
        super(error);
    }
}
