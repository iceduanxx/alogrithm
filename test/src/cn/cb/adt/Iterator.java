package cn.cb.adt;

public interface Iterator {
    /**
     * �ƶ�����һ��Ԫ��
     */
    public void first();

    /**
     * �ƶ�����һ��Ԫ��
     */
    public void next();

    /**
     * �����������Ƿ���ʣ���Ԫ��
     *
     * @return
     */
    public boolean isDone();

    /**
     * ���ص�ǰԪ��
     *
     * @return
     */
    public Object currentItem();
}