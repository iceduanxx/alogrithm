package cn.duanxx.chapter4.blog;

/**
 * Created by IntelliJ IDEA.
 * User: dell--30
 * Date: 13-9-25
 * Time: ����11:03
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main(String[] args) {
        String string = "ë��ޱ Lagerstroemia villosa Wall. ex Kurz �й�ֲ��ͼ���     ";
        string = string.replaceAll("(\\s[\u4E00-\u9FA5]+)|([\u4E00-\u9FA5]+\\s)", "");

        string = string.replace("\0x3000","");
        System.out.println("[" + string + "]");
    }
}
