import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: duanxx
 * Date: 13-9-17
 * Time: ����10:31
 * To change this template use File | Settings | File Templates.
 */
public class RedisUtil1 {

    private static Logger logger = Logger.getLogger(RedisUtil1.class);

    private static JedisPool pool = null;

    static {
        init();
    }

    /**
     * ��ʼ�����ӳ�
     */
    public static void init() {
        Properties properties = new Properties();
        JedisPoolConfig config = new JedisPoolConfig();
        try {
            properties.load(new FileInputStream(RedisUtil1.class.getResource("/redis.properties").getFile()));
            config.setMaxActive(Integer.valueOf(properties
                    .getProperty("redis.pool.maxActive")));
            config.setMaxIdle(Integer.valueOf(properties
                    .getProperty("redis.pool.maxIdle")));
            config.setMaxWait(Long.valueOf(properties.getProperty("redis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(properties
                    .getProperty("redis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(properties
                    .getProperty("redis.pool.testOnReturn")));
        } catch (IOException e) {
            logger.warn("not found  finle memcache.properties in classpath", e);
        }
        logger.info("Initializing memcached pool");
        if (pool == null) {
            pool = new JedisPool(config, properties.getProperty("redis.ip"),
                    Integer.valueOf(properties.getProperty("redis.port")));
        }
    }

    /**
     * �ӳ��л�ȡjedis
     *
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = pool.getResource();
        return jedis;
    }

    /**
     * ��jedis�黹��
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }

    }

    /**
     * �ر����ӳ�
     */
    public static void closePool() {
        pool.destroy();
        pool = null;
        logger.info("Memcached pool closed");
    }

    /**
     * ���л�����
     *
     * @param obj
     */
    public static byte[] serializablePojo(Object obj) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] byteArray = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            byteArray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("���л������쳣" + e);
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArray;
    }

    /**
     * �����л�����
     *
     * @param bytes
     * @return
     */
    public static Object reSerializablePojo(byte[] bytes) {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream in = null;
        Person readObject = null;
        try {
            in = new ObjectInputStream(bis);
            readObject = (Person) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("�����л������쳣" + e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return readObject;
    }


    /**
     * ����String
     *
     * @param key ��
     * @param str ֵ
     */
    public static boolean setString(String key, String str) {
        Jedis jedis = null;
        String reply = null;
        try {
            jedis = getJedis();
            reply = jedis.set(key, str);
        } catch (Exception e) {
            logger.error("Pool setString error!" + e);
        } finally {
            returnResource(jedis);
        }
        return reply.equals("OK") ? true : false;
    }

    /**
     * �������
     *
     * @param key
     * @param obj
     * @return
     */
    public static boolean setPojo(String key, Object obj) {
        Jedis jedis = null;
        String reply = null;
        try {
            jedis = getJedis();
            reply = jedis.set(key.getBytes("utf-8"), serializablePojo(obj));
        } catch (Exception e) {
            logger.error("Pool set error!");
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return reply.equals("OK") ? true : false;
    }

    public static Object getPojo(String key) {
        Jedis jedis = null;
        Object returnObj = null;
        try {
            jedis = getJedis();
            byte[] bs = jedis.get(key.getBytes("utf-8"));
            returnObj = reSerializablePojo(bs);
        } catch (Exception e) {
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return returnObj;
    }

    /**
     * ����list(��list��ÿ��pojoȡ��������(����һ��list��))
     *
     * @param key
     * @param list
     * @return
     */
    public static void setListForPojo(String key, List<? extends Serializable> list) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            for (int i = 0; i < list.size(); i++) {
                byte[] pojoByte = serializablePojo(list.get(i));
                BASE64Encoder enc = new BASE64Encoder();
                String pojoStr = enc.encode(pojoByte);
                pojoStr.getBytes("utf-8");
                jedis.rpush(key, pojoStr);
            }
        } catch (Exception ex) {
            logger.error("set list error" + ex);
            ex.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * ����list���������建�棩
     * list�е�pojo��Ҫʵ�����л��ӿڣ������׳��쳣
     *
     * @param key
     * @param list
     */
    public static void setList(String key, List list) {
        Jedis jedis = null;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutput output = null;

        try {
            jedis = getJedis();
            output = new ObjectOutputStream(byteStream);
            output.writeObject(list);
            byte b[] = byteStream.toByteArray();
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String encode = base64Encoder.encode(b);
            jedis.set(key, encode);

        } catch (IOException e) {
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }


    }

    /**
     * �����ʱ����ͨ��jedis.set(key, encode);����ģ�list�������л�
     *
     * @param key
     * @return
     */
    public static List getList(String key) {
        Jedis jedis = null;
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte buf[] = null;
        List<Object> retList = null;
        try {
            jedis = getJedis();
            String ret = jedis.get(key);
            buf = base64Decoder.decodeBuffer(ret);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
            ObjectInput input = new ObjectInputStream(byteArrayInputStream);
            retList = (List<Object>) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return retList;

    }

    public static List getListForPojo(String key) {
        Jedis jedis = null;
        List<Object> returnList = new ArrayList<Object>();
        try {
            jedis = getJedis();
            List<String> list = jedis.lrange(key, 0, -1);
            for (String li : list) {
                BASE64Decoder dec = new BASE64Decoder();
                byte[] libyte = null;
                try {
                    libyte = dec.decodeBuffer(li);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                returnList.add(reSerializablePojo(libyte));
            }
        } catch (Exception ex) {
            logger.error("" + ex);
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }

        return returnList;
    }

    /**
     * ����String���͵Ļ���
     *
     * @param key  ��
     * @param obj  ֵ
     * @param time ����ʱ�䣨���룩
     */
    public static boolean setString(String key, Serializable obj, int time) {
        Jedis jedis = null;
        String reply = null;
        try {
            jedis = getJedis();
            reply = jedis.setex(key.getBytes("utf-8"), time, serializablePojo(obj));
        } catch (Exception e) {
            logger.error("Pool set error!");
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return reply.equals("OK") ? true : false;
    }


    /**
     * ��ȡString����
     *
     * @param key ��
     * @return ֵ
     */
    public static String getString(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return result;
    }


    /**
     * ɾ������
     *
     * @param key ��
     */
    public static Long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage());
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return new Long(8);
    }


    public static boolean contains(String key) {

        Jedis jedis = null;
        boolean ret = false;
        try {
            jedis = getJedis();
            ret = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            returnResource(jedis);
        } finally {
            returnResource(jedis);
        }
        return ret;
    }

    public static void main(String[] args) {
        /*Jedis jedis = RedisUtil1.getJedis();
        System.out.println(jedis);
        setString("duanxx", "1");
        String username = getString("duanxx");
        System.out.println(username);

        Person person = new Person();
        person.setAge(1);
        person.setName("������");
        //setPojo("person", person);
        person = (Person) getPojo("person");
        System.out.println(person);
        */
        /* List<Person> personList = new ArrayList<Person>();
        delete("list");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Person personPer = new Person();
            personPer.setAge(1);
            personPer.setName("������");
            personList.add(personPer);
        }
        setList("list", personList);
        //List list = getListForPojo("list");
        long endTime =System.currentTimeMillis();
        System.out.println("�����ʱ��Ϊ��"+(endTime-startTime)+"���룡");*/
        //System.out.println(list);

        /*String key2="testse";
		long t1=System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			setString(key2+i, "test"+i);
		}
		System.out.println("set time="+(System.currentTimeMillis()-t1));
		System.out.println("value="+getString(key2+5));*/

        List list = new ArrayList();

        for (int i = 0; i < 10000; i++) {
            Person person = new Person();
            person.setAge(1);
            person.setName("duanxx");
            list.add(person);
        }

        delete("duanxx");

        long t1 = System.currentTimeMillis();
        setList("duanxx", list);
        System.out.println("set time=" + (System.currentTimeMillis() - t1));
        closePool();

    }


}