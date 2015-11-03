package cn.autumnstar.offer.meituan;

/**
 * Created by xingxing.duan on 2015/11/3.
 */
public class BinTreeTraverse {

    private static void preOrder(BinTreeNode root) {

        if (root != null) {
            System.out.print(root.getObject() + " ");
            preOrder(root.getlChild());
            preOrder(root.getrChild());
        }

    }

    private static void inOrder(BinTreeNode root) {
        if (root != null) {
            inOrder(root.getlChild());
            System.out.print(root.getObject() + " ");
            inOrder(root.getrChild());
        }
    }

    private static void postOrder(BinTreeNode root) {
        if (root != null) {
            postOrder(root.getlChild());
            postOrder(root.getrChild());
            System.out.print(root.getObject() + " ");
        }
    }

    private static boolean containerSubTree(BinTreeNode tree, BinTreeNode subtree) {
        boolean result = false;
        if (tree.getObject().equals(subtree.getObject())) {
           return containerSubTreeRecurse(tree,subtree);
        }
        if (!result) {
            result = containerSubTree(tree.getlChild(), subtree);
        }

        if (!result) {
            result = containerSubTree(tree.getrChild(), subtree);
        }

        return result;


    }

    private static boolean containerSubTreeRecurse(BinTreeNode tree, BinTreeNode subTree) {
        if (subTree == null) {
            return true;
        }
        if(tree==null){
            return false;
        }
        if(tree.getObject().equals(subTree.getObject())){

            return containerSubTreeRecurse(tree.getlChild(),subTree.getlChild())&&containerSubTreeRecurse(tree.getrChild(),subTree.getrChild());
        }else{
            return false;
        }

    }

    public static void main(String[] args) {
        BinTreeNode root = new BinTreeNode("root");
        BinTreeNode A = new BinTreeNode("A");
        BinTreeNode B = new BinTreeNode("B");
        BinTreeNode C = new BinTreeNode("C");
        BinTreeNode D = new BinTreeNode("D");
        BinTreeNode E = new BinTreeNode("E");
        BinTreeNode F = new BinTreeNode("F");

        BinTreeNode G = new BinTreeNode("root");


        root.setlChild(A);
        root.setrChild(B);
        A.setlChild(C);
        A.setrChild(D);
        B.setlChild(E);
        B.setrChild(F);

        G.setlChild(A);
        G.setrChild(B);
        G.setlChild(E);
        /*preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);*/

        boolean flag=containerSubTree(root,G);
        System.out.print(flag);

    }

}
