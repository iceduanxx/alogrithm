package cn.autumnstar.offer.ListChase;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-14
 * Time: ����11:01
 * ����׷������
 */
public class ListChase {

    /**
     * 1.���жϴ�������
     * �ж��Ƿ��л�������bool������л������ػ���Ľڵ�
     * ˼·��������ָ�룬һ��ָ�벽��Ϊ1��һ��ָ�벽��Ϊ2���ж������Ƿ��л�
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
     * �жϴ���������ʱ�����Ƿ��ཻ
     * 2.����������������ж�β�ڵ��Ƿ����
     * 3.������������ж�һ��������ָ���������Ǹ��ڵ㣬�ڲ�����һ�������ϡ�
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

        //һ���л���һ���޻�
        if (isCircle1 != isCircle2)
            return false;
            //�������޻����ж����һ���ڵ��Ƿ����
        else if (!isCircle1 && !isCircle2) {
            return nodes1.getLastNode() == nodes2.getLastNode();
        }
        //�������л����жϻ���Ľڵ��Ƿ��ܵ�����һ��������Ľڵ�
        else {
            Node temp = nodes1.getCircleNode().getNextNode();  //updated����л���� and hyy��
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
