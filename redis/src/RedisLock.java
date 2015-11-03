import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Random;

public class RedisLock {

    /**
     * ������־
     */
    public static final String LOCKED = "TRUE";
    /**
     * �������΢��Ļ��㵥λ 1���� = 1000000��΢��
     */
    public static final long MILLI_NANO_CONVERSION = 1000 * 1000L;
    /**
     * Ĭ�ϳ�ʱʱ�䣨���룩
     */
    public static final long DEFAULT_TIME_OUT = 1000;
    public static final Random RANDOM = new Random();
    /**
     * ���ĳ�ʱʱ�䣨�룩������ɾ��
     */
    public static final int EXPIRE = 3 * 60;

    private ShardedJedisPool shardedJedisPool;
    private ShardedJedis jedis;
    private String key;
    // ��״̬��־
    private boolean locked = false;

    /**
     * This creates a RedisLock
     *
     * @param key              key
     * @param shardedJedisPool ����Դ
     */
    public RedisLock(String key, ShardedJedisPool shardedJedisPool) {
        this.key = key + "_lock";
        this.shardedJedisPool = shardedJedisPool;
        this.jedis = this.shardedJedisPool.getResource();
    }

    /**
     * ����
     * Ӧ���ԣ�
     * lock();
     * try {
     * doSomething();
     * } finally {
     * unlock()��
     * }
     * �ķ�ʽ����
     *
     * @param timeout ��ʱʱ��
     * @return �ɹ���ʧ�ܱ�־
     */
    public boolean lock(long timeout) {
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (this.jedis.setnx(this.key, LOCKED) == 1) {
                    this.jedis.expire(this.key, EXPIRE);
                    this.locked = true;
                    return this.locked;
                }
                // �������ߣ�������ֻ���
                Thread.sleep(3, RANDOM.nextInt(500));
            }
        } catch (Exception e) {
            throw new RuntimeException("Locking error", e);
        }
        return false;
    }

    /**
     * ����
     * Ӧ���ԣ�
     * lock();
     * try {
     * doSomething();
     * } finally {
     * unlock()��
     * }
     * �ķ�ʽ����
     *
     * @param timeout ��ʱʱ��
     * @param expire  ���ĳ�ʱʱ�䣨�룩������ɾ��
     * @return �ɹ���ʧ�ܱ�־
     */
    public boolean lock(long timeout, int expire) {
        long nano = System.nanoTime();
        timeout *= MILLI_NANO_CONVERSION;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (this.jedis.setnx(this.key, LOCKED) == 1) {
                    this.jedis.expire(this.key, expire);
                    this.locked = true;
                    return this.locked;
                }
                // �������ߣ�������ֻ���
                Thread.sleep(3, RANDOM.nextInt(500));
            }
        } catch (Exception e) {
            throw new RuntimeException("Locking error", e);
        }
        return false;
    }

    /**
     * ����
     * Ӧ���ԣ�
     * lock();
     * try {
     * doSomething();
     * } finally {
     * unlock()��
     * }
     * �ķ�ʽ����
     *
     * @return �ɹ���ʧ�ܱ�־
     */
    public boolean lock() {
        return lock(DEFAULT_TIME_OUT);
    }

    /**
     * ����
     * �����Ƿ�����ɹ�������Ҫ����unlock
     * Ӧ���ԣ�
     * lock();
     * try {
     * doSomething();
     * } finally {
     * unlock()��
     * }
     * �ķ�ʽ����
     */
    public void unlock() {
        try {
            if (this.locked) {
                this.jedis.del(this.key);
            }
        } finally {
            this.shardedJedisPool.returnResource(this.jedis);
        }
    }
}
