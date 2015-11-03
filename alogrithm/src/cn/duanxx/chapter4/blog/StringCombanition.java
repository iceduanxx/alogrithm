package cn.duanxx.chapter4.blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User:
 * Date: 13-9-25
 * Time: 下午3:18
 * <p>字符串组合问题
 *     题目：输入一个字符串，输出该字符串中字符的所有组合。
 *     举个例子，如果输入abc，它的组合有a、b、c、ab、ac、bc、abc。
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
