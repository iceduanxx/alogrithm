package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/11/1.
 * 链表反转
 */
public class ListReverse {

    private static void reverse(Node start) {
        if (start == null) {
            System.out.print("参数错误");
            return;
        }
        Node pre = start;

        Node curr = pre.getNext();
        Node next;
        while (curr != null) {

            next = curr.getNext();
            curr.setNext(pre);
            if (pre == start) {
                pre.setNext(null);
            }
            pre = curr;
            curr = next;

        }

    }

    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        nodeA.setNext(nodeB);
        nodeB.setNext(nodeC);
        nodeC.setNext(nodeD);
        nodeD.setNext(nodeE);
        reverse(nodeA);
        while (nodeE != null) {
            System.out.print(nodeE.getStr());
            nodeE = nodeE.getNext();
        }

    }
}

class Node {
    String str;
    Node next;

    public Node(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
