package cn.autumnstar.offer;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-10
 * Time: ����2:21
 * �����������
 */
public class ReversePrintList {
    /**
     * �ǵݹ��㷨
     * @param node
     */
    public static void reversePrintNoRecursion(Node node) {
        Stack<Node> stack = new Stack<Node>();
        Node p = node;
        while (p != null) {
            stack.push(p);
            p = p.getNext();
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getKey()+" ");
        }
    }

    /**
     * �ݹ��㷨
     * @param node
     */
    public static void reversePrintRecursion(Node node) {
        Node p = node;
        if (p != null) {
            reversePrintRecursion(p.getNext());
            System.out.print(p.getKey()+" ");
        }
    }

     public static void main(String[] args) {

         Node node = new Node();
         Node root = node;
         node.setKey(0);

        for(int i=1;i<6;i++){
            Node nodePer = new Node();
            nodePer.setKey(i);
            node.setNext(nodePer);
            node = nodePer;
        }

        reversePrintRecursion(root);
    }
}
