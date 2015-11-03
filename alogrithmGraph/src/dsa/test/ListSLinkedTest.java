package dsa.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import dsa.adt.List;
import dsa.adt.ListSLinked;


/**
 * Created by IntelliJ IDEA.
 * User: dell--30
 * Date: 13-9-13
 * Time: обнГ2:26
 * To change this template use File | Settings | File Templates.
 */
public class ListSLinkedTest {

    public static void main(String[] args) {
        List list = new ListSLinked();
        for (int i = 0; i < 10; i++) {
           list.insert(i,i+1);
        }

        System.out.println(list.get(3));
    }

}
