package cn.autumnstar.offer;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-10-10
 * Time: ����3:40
 * ���ݶ�����������������ؽ�������
 */
public class ConstructBinaryTree {

    public static void main(String[] args) {
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinTreeNode binTreeNode = recusionConstruct(0, preOrder.length - 1, 0, inOrder.length - 1, preOrder, inOrder);
        System.out.println(binTreeNode);

    }
    //todo:�߽�ֵ������
    public static BinTreeNode recusionConstruct(int preStartRecord, int preEndRecord, int inStartRecord, int inEndRecord, int[] pre, int[] in) {
        int root = pre[preStartRecord];
        BinTreeNode p = new BinTreeNode();
        p.setKey(root);
        for (int i = 0; i <= (inEndRecord - inStartRecord); i++) {
            if (root == pre[i]) {
                //����������
                if (i - inStartRecord > 0) {
                    p.setLeftChild(recusionConstruct(preStartRecord + 1, preEndRecord + i - inStartRecord, inStartRecord, i - 1, pre, in));
                }

                //����������
                if (i + 1 < inEndRecord) {
                    p.setRightChild(recusionConstruct(preEndRecord + i - inStartRecord + 1, preEndRecord, i + 1, inEndRecord, pre, in));
                }
                break;
            }

        }
        return p;
    }
}
