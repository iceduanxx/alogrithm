package cn.duanxx.chapter4.blog;

/**
 * Created by IntelliJ IDEA.
 * User: dell--30
 * Date: 13-9-25
 * Time: ÉÏÎç11:03
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main(String[] args) {
        String string = "Ã«×ÏÞ± Lagerstroemia villosa Wall. ex Kurz ÖÐ¹úÖ²ÎïÍ¼Ïñ¿â     ";
        string = string.replaceAll("(\\s[\u4E00-\u9FA5]+)|([\u4E00-\u9FA5]+\\s)", "");

        string = string.replace("\0x3000","");
        System.out.println("[" + string + "]");
    }
}
