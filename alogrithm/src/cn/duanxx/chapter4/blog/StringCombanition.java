package cn.duanxx.chapter4.blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 13-9-25
 * Time: ����3:18
 * <p>�ַ����������
 *     ��Ŀ������һ���ַ�����������ַ������ַ���������ϡ�
 *     �ٸ����ӣ��������abc�����������a��b��c��ab��ac��bc��abc��
 * </p>
 */
public class StringCombanition {

    public void strCombanition(String sourceStr){
        List list = new ArrayList();
        for(int i=0;i<sourceStr.length();i++){
            combination(sourceStr, i, list);
        }
    }

    private void combination(String sourceStr, int i, List list) {


    }
}
