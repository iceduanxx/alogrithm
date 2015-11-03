import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import redis.clients.jedis.Jedis;


public class Test {
    /**
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        Jedis redis = new Jedis("192.168.50.235");
        //connect���Բ�Ҫ����Ϊ��ִ��set������ʱ����Ƚ����жϿͻ����Ƿ��ڷ������˽��������ӣ����ޣ����������ӹ���
        redis.connect();
        String set = redis.set("mingyuan", "1");
        System.out.println(" set result \t" + set);
        redis.incr("mingyuan");
        String string = redis.get("mingyuan");
        System.out.println(" get result of key 'mingyuan' \t" + string);


        //�����ǶԶ�����д洢�Ĳ��Դ���
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        Person person = new Person();
        person.setAge(1);
        person.setName("mingyuan");
        oos.writeObject(person);
        byte[] byteArray = bos.toByteArray();
        oos.close();
        bos.close();
        String setObjectRet = redis.set("mingyuan".getBytes(), byteArray);
        System.out.println(" set object return \t" + setObjectRet);


        byte[] bs = redis.get("mingyuan".getBytes());


        ByteArrayInputStream bis = new ByteArrayInputStream(bs);
        ObjectInputStream inputStream = new ObjectInputStream(bis);
        Person readObject = (Person) inputStream.readObject();
        System.out.println(" read object \t" + readObject.toString());
        inputStream.close();
        bis.close();

        redis.disconnect();


    }


}
