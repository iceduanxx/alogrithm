package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-10
 * Time: 下午3:40
 * 根据二叉树的先序和中序重建二叉树
 */
public class ConstructBinaryTree {

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinTreeNode binTreeNode = recusionConstruct(0, preOrder.length - 1, 0, inOrder.length - 1, preOrder, inOrder);
        System.out.println(binTreeNode);

    }
    //todo:边界值有问题
    public static BinTreeNode recusionConstruct(int preStartRecord, int preEndRecord, int inStartRecord, int inEndRecord, int[] pre, int[] in) {
        int root = pre[preStartRecord];
        BinTreeNode p = new BinTreeNode();
        p.setKey(root);
        for (int i = 0; i <= (inEndRecord - inStartRecord); i++) {
            if (root == pre[i]) {
                //构建左子书
                if (i - inStartRecord > 0) {
                    p.setLeftChild(recusionConstruct(preStartRecord + 1, preEndRecord + i - inStartRecord, inStartRecord, i - 1, pre, in));
                }

                //构建右子树
                if (i + 1 < inEndRecord) {
                    p.setRightChild(recusionConstruct(preEndRecord + i - inStartRecord + 1, preEndRecord, i + 1, inEndRecord, pre, in));
                }
                break;
            }

        }
        return p;
    }
}
