package cn.duanxx.chapter4.chapter;

import java.awt.Point;
import java.util.LinkedList;

/** */

/**
 * <p>
 * Title: LoonFramework
 * </p>
 * <p>
 * Description:����·���ڵ�����
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: LoonFramework
 * </p>
 * <p>
 * License: http://www.apache.org/licenses/LICENSE-2.0
 * </p>
 *
 * @author chenpeng
 * @version 0.1
 * @email��ceponline@yahoo.com.cn
 */
public class Node implements Comparable {
    // ����
    public Point _pos;

    // ��ʼ�ص���ֵ
    public int _costFromStart;

    // Ŀ��ص���ֵ
    public int _costToObject;

    // ���ڵ�
    public Node _parentNode;

    private Node() {
    }

    /** */
    /**
     * ��ע������㷽ʽ��ʼ��Node
     *
     * @param _pos
     */
    public Node(Point _pos) {
        this._pos = _pos;
    }

    /** */
    /**
     * ����·���ɱ�
     *
     * @param node
     * @return
     */
    public int getCost(Node node) {
        // ����������ֵ ��ʽ��(x1, y1)-(x2, y2)
        int m = node._pos.x - _pos.x;
        int n = node._pos.y - _pos.y;
        // ȡ���ڵ��ŷ����¾��루ֱ�߾��룩��Ϊ����ֵ�����Ի�óɱ�
        return (int) Math.sqrt(m * m + n * n);
    }

    /** */
    /**
     * ���node�����Ƿ����֤����һ��
     */
    public boolean equals(Object node) {
        // ��֤����Ϊ�ж�����
        if (_pos.x == ((Node) node)._pos.x && _pos.y == ((Node) node)._pos.y) {
            return true;
        }
        return false;
    }

    /** */
    /**
     * �Ƚ������Ի����С�ɱ�����
     */
    public int compareTo(Object node) {
        int a1 = _costFromStart + _costToObject;
        int a2 = ((Node) node)._costFromStart + ((Node) node)._costToObject;
        if (a1 < a2) {
            return -1;
        } else if (a1 == a2) {
            return 0;
        } else {
            return 1;
        }
    }

    /** */
    /**
     * ����������Ҹ�����ƶ���������
     *
     * @return
     */
    public LinkedList getLimit() {
        LinkedList limit = new LinkedList();
        int x = _pos.x;
        int y = _pos.y;
        // �������Ҹ�����ƶ�����(����б�ӵ�ͼ�����Կ���ע�͵�ƫ�Ʋ��֣���ʱ������8����λ)
        // ��
        limit.add(new Node(new Point(x, y - 1)));
        // ����
        // limit.add(new Node(new Point(x+1, y-1)));
        // ��
        limit.add(new Node(new Point(x + 1, y)));
        // ����
        // limit.add(new Node(new Point(x+1, y+1)));
        // ��
        limit.add(new Node(new Point(x, y + 1)));
        // ����
        // limit.add(new Node(new Point(x-1, y+1)));
        // ��
        limit.add(new Node(new Point(x - 1, y)));
        // ����
        // limit.add(new Node(new Point(x-1, y-1)));

        return limit;
    }

}