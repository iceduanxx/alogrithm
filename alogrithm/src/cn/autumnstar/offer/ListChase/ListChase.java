package cn.autumnstar.offer.ListChase;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-14
 * Time: 上午11:01
 * 链表追赶问题
 */
public class ListChase {

    /**
     * 1.先判断带不带环
     * 判断是否有环，返回bool，如果有环，返回环里的节点
     * 思路：用两个指针，一个指针步长为1，一个指针步长为2，判断链表是否有环
     *
     * @param head
     * @return
     */
    public static boolean isCircle(Node head, Nodes nodes) {

        Node fast = head.getNextNode();
        Node slow = head;

        while (fast != slow && fast != null && slow != null) {
            if (fast.getNextNode() != null) {
                fast = fast.getNextNode();
            }
            if (fast.getNextNode() == null) {
                nodes.setLastNode(fast);
            }
            if (slow.getNextNode() == null) {
                nodes.setLastNode(slow);
            }
            fast = fast.getNextNode();
            slow = slow.getNextNode();
        }
        if (fast == slow && fast != null && slow != null) {
            nodes.setCircleNode(fast);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断带环不带环时链表是否相交
     * 2.如果都不带环，就判断尾节点是否相等
     * 3.如果都带环，判断一链表上俩指针相遇的那个节点，在不在另一条链表上。
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean detect(Node head1, Node head2) {
        Nodes nodes1 = new Nodes();
        Nodes nodes2 = new Nodes();

        boolean isCircle1 = isCircle(head1, nodes1);
        boolean isCircle2 = isCircle(head2, nodes2);

        //一个有环，一个无环
        if (isCircle1 != isCircle2)
            return false;
            //两个都无环，判断最后一个节点是否相等
        else if (!isCircle1 && !isCircle2) {
            return nodes1.getLastNode() == nodes2.getLastNode();
        }
        //两个都有环，判断环里的节点是否能到达另一个链表环里的节点
        else {
            Node temp = nodes1.getCircleNode().getNextNode();  //updated，多谢苍狼 and hyy。
            while (temp != nodes1.getCircleNode()) {
                if (temp == nodes2.getCircleNode())
                    return true;
                temp = temp.getNextNode();
            }
            return false;
        }

    }

    public static void main(String[] args) {

        Node p1 = new Node(11);
        Node p2 = new Node(22);

        Node head1 = p1;
        Node head2 = p2;

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);


        n1.setNextNode(n2);
        n2.setNextNode(n3);
        n3.setNextNode(n4);
        n4.setNextNode(n5);
        n5.setNextNode(n6);
        n6.setNextNode(n1);
        p2.setNextNode(n1);
        p1.setNextNode(p2);



        boolean ret = detect(head1, head2);

        System.out.println(ret);

    }
}
