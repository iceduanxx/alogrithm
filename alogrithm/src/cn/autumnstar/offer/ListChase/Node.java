package cn.autumnstar.offer.ListChase;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-14
 * Time: ����10:56
 * ����ڵ�
 */
public class Node {
    int value;
    Node nextNode;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

}
