package cn.cb.adt;


/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-8
 * Time: обнГ2:51
 * To change this template use File | Settings | File Templates.
 */
public class Test {


    public static void main(String[] args) {
        BSTree root = new BSTree();
        int data[] = {12, 11, 34, 45, 67, 89, 56, 43, 22, 98};
        for (int i = 0; i < data.length; i++) {
            root.insert(data[i]);
        }

        Iterator iterator = root.inOrder();
        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            System.out.print(((Node) ((Node) iterator.currentItem()).getData()).getData() + " ");
        }

     /*   root.pathTree(68);*/

    }
}
