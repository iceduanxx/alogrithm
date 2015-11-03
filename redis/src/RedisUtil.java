import redis.clients.jedis.*;

import java.util.*;

/**
 * �ڴ����ݿ�Redis�ĸ����࣬������ڴ����ݿ�����в���
 * @version V1.0
 * @author fengjc
 */
public class RedisUtil {

	// ����Դ
	private ShardedJedisPool shardedJedisPool;

	/**
	 * ִ������{@link com.futurefleet.framework.base.redis.RedisUtil}�ĸ����࣬
	 * ����֤��ִ�в���֮���ͷ�����ԴreturnResource(jedis)
	 * @version V1.0
	 * @author fengjc
	 * @param <T>
	 */
	abstract class Executor<T> {

		ShardedJedis jedis;
		ShardedJedisPool shardedJedisPool;

		public Executor(ShardedJedisPool shardedJedisPool) {
			this.shardedJedisPool = shardedJedisPool;
			jedis = this.shardedJedisPool.getResource();
		}

		/**
		 * �ص�
		 * @return ִ�н��
		 */
		abstract T execute();

		/**
		 * ����{@link #execute()}������ִ�н��
		 * ����֤��ִ��{@link #execute()}֮���ͷ�����ԴreturnResource(jedis)
		 * @return ִ�н��
		 */
		public T getResult() {
			T result = null;
			try {
				result = execute();
			} catch (Throwable e) {
				throw new RuntimeException("Redis execute exception", e);
			} finally {
				if (jedis != null) {
					shardedJedisPool.returnResource(jedis);
				}
			}
			return result;
		}
	}

	/**
	 * ɾ��ģ��ƥ���key
	 * @param likeKey ģ��ƥ���key
	 * @return ɾ���ɹ�������
	 */
	public long delKeysLike(final String likeKey) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				Collection<Jedis> jedisC = jedis.getAllShards();
				Iterator<Jedis> iter = jedisC.iterator();
				long count = 0;
				while (iter.hasNext()) {
					Jedis _jedis = iter.next();
					Set<String> keys = _jedis.keys(likeKey + "*");
					count += _jedis.del(keys.toArray(new String[keys.size()]));
				}
				return count;
			}
		}.getResult();
	}

	/**
	 * ɾ��
	 * @param key ƥ���key
	 * @return ɾ���ɹ�������
	 */
	public Long delKey(final String key) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.del(key);
			}
		}.getResult();
	}

	/**
	 * ɾ��
	 * @param keys ƥ���key�ļ���
	 * @return ɾ���ɹ�������
	 */
	public Long delKeys(final String[] keys) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				Collection<Jedis> jedisC = jedis.getAllShards();
				Iterator<Jedis> iter = jedisC.iterator();
				long count = 0;
				while (iter.hasNext()) {
					Jedis _jedis = iter.next();
					count += _jedis.del(keys);
				}
				return count;
			}
		}.getResult();
	}

	/**
	 * Ϊ���� key ��������ʱ�䣬�� key ����ʱ(����ʱ��Ϊ 0 )�����ᱻ�Զ�ɾ����
	 * �� Redis �У���������ʱ��� key ����Ϊ���ɻӷ���(volatile)�ġ�
	 * @param key key
	 * @param expire �������ڣ���λΪ��
	 * @return 1: ���óɹ� 0: �Ѿ���ʱ��key������
	 */
	public Long expire(final String key, final int expire) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.expire(key, expire);
			}
		}.getResult();
	}

	/**
	 * һ����jvm��id��������������redisԭ���Բ������ص�
	 * @param key id��key
	 * @return �������ɵ�Id
	 */
	public long makeId(final String key) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				long id = jedis.incr(key);
				if ((id + 75807) >= Long.MAX_VALUE) {
					// ������������ã�getSet����֮ǰ����incr��ӣ�75807����Ԥ���Ĳ�ӿռ�
					jedis.getSet(key, "0");
				}
				return id;
			}
		}.getResult();
	}

	/* ======================================Strings====================================== */

	/**
	 * ���ַ���ֵ value ������ key ��
	 * ��� key �Ѿ���������ֵ�� setString �͸�д��ֵ���������͡�
	 * ����ĳ��ԭ����������ʱ�䣨TTL���ļ���˵�� �� setString �ɹ����������ִ��ʱ�� �����ԭ�е� TTL ���������
	 * ʱ�临�Ӷȣ�O(1)
	 * @param key key
	 * @param value string value
	 * @return �����ò����ɹ����ʱ���ŷ��� OK ��
	 */
	public String setString(final String key, final String value) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				return jedis.set(key, value);
			}
		}.getResult();
	}

	/**
	 * ��ֵ value ������ key ������ key ������ʱ����Ϊ expire (����Ϊ��λ)��
	 * ��� key �Ѿ����ڣ� ����д��ֵ��
	 * ������������������:
	 * SET key value
	 * EXPIRE key expire # ��������ʱ��
	 * ��֮ͬ�������������һ��ԭ����(atomic)����������ֵ����������ʱ��������������ͬһʱ������ɣ��� Redis ��������ʱ���ǳ�ʵ�á�
	 * ʱ�临�Ӷȣ�O(1)
	 * @param key key
	 * @param value string value
	 * @param expire ��������
	 * @return ���óɹ�ʱ���� OK ���� expire �������Ϸ�ʱ������һ������
	 */
	public String setString(final String key, final String value, final int expire) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				return jedis.setex(key, expire, value);
			}
		}.getResult();
	}

	/**
	 * �� key ��ֵ��Ϊ value �����ҽ��� key �����ڡ��������� key �Ѿ����ڣ��� setStringIfNotExists �����κζ�����
	 * ʱ�临�Ӷȣ�O(1)
	 * @param key key
	 * @param value string value
	 * @return ���óɹ������� 1 ������ʧ�ܣ����� 0 ��
	 */
	public Long setStringIfNotExists(final String key, final String value) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.setnx(key, value);
			}
		}.getResult();
	}

	/**
	 * ���� key ���������ַ���ֵ����� key ��������ô��������ֵ nil ��
	 * ���� key �����ֵ�����ַ������ͣ�����һ��������Ϊ getString ֻ�����ڴ����ַ���ֵ��
	 * ʱ�临�Ӷ�: O(1)
	 * @param key key
	 * @return �� key ������ʱ������ nil �����򣬷��� key ��ֵ����� key �����ַ������ͣ���ô����һ������
	 */
	public String getString(final String key) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				return jedis.get(key);
			}
		}.getResult();
	}

	/**
	 * ������ {@link #setString(String, String)}
	 * @param pairs ��ֵ������{�����һ��Ԫ��Ϊkey���ڶ���Ԫ��Ϊvalue}
	 * @return ����״̬�ļ���
	 */
	public List<Object> batchSetString(final List<Pair<String, String>> pairs) {
		return new Executor<List<Object>>(shardedJedisPool) {

			@Override
            List<Object> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				for (Pair<String, String> pair : pairs) {
					pipeline.set(pair.getKey(), pair.getValue());
				}
				return pipeline.syncAndReturnAll();
			}
		}.getResult();
	}

	/**
	 * ������ {@link #getString(String)}
	 * @param keys key����
	 * @return value�ļ���
	 */
	public List<String> batchGetString(final String[] keys) {
		return new Executor<List<String>>(shardedJedisPool) {

			@Override
			List<String> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				List<String> result = new ArrayList<String>(keys.length);
				List<Response<String>> responses = new ArrayList<Response<String>>(keys.length);
				for (String key : keys) {
					responses.add(pipeline.get(key));
				}
				pipeline.sync();
				for (Response<String> resp : responses) {
					result.add(resp.get());
				}
				return result;
			}
		}.getResult();
	}

	/* ======================================Hashes====================================== */

	/**
	 * ����ϣ�� key �е��� field ��ֵ��Ϊ value ��
	 * ��� key �����ڣ�һ���µĹ�ϣ������������ hashSet ������
	 * ����� field �Ѿ������ڹ�ϣ���У���ֵ�������ǡ�
	 * ʱ�临�Ӷ�: O(1)
	 * @param key key
	 * @param field ��
	 * @param value string value
	 * @return ��� field �ǹ�ϣ���е�һ���½��򣬲���ֵ���óɹ������� 1 �������ϣ������ field �Ѿ������Ҿ�ֵ�ѱ���ֵ���ǣ����� 0 ��
	 */
	public Long hashSet(final String key, final String field, final String value) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.hset(key, field, value);
			}
		}.getResult();
	}

	/**
	 * ����ϣ�� key �е��� field ��ֵ��Ϊ value ��
	 * ��� key �����ڣ�һ���µĹ�ϣ������������ hashSet ������
	 * ����� field �Ѿ������ڹ�ϣ���У���ֵ�������ǡ�
	 * @param key key
	 * @param field ��
	 * @param value string value
	 * @param expire �������ڣ���λΪ��
	 * @return ��� field �ǹ�ϣ���е�һ���½��򣬲���ֵ���óɹ������� 1 �������ϣ������ field �Ѿ������Ҿ�ֵ�ѱ���ֵ���ǣ����� 0 ��
	 */
	public Long hashSet(final String key, final String field, final String value, final int expire) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				Pipeline pipeline = jedis.getShard(key).pipelined();
				Response<Long> result = pipeline.hset(key, field, value);
				pipeline.expire(key, expire);
				pipeline.sync();
				return result.get();
			}
		}.getResult();
	}

	/**
	 * ���ع�ϣ�� key �и����� field ��ֵ��
	 * ʱ�临�Ӷ�:O(1)
	 * @param key key
	 * @param field ��
	 * @return �������ֵ���������򲻴��ڻ��Ǹ��� key ������ʱ������ nil ��
	 */
	public String hashGet(final String key, final String field) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				return jedis.hget(key, field);
			}
		}.getResult();
	}

	/**
	 * ���ع�ϣ�� key �и����� field ��ֵ�� �����ϣ�� key ���ڣ�ͬʱ������� key ������ʱ��
	 * @param key key
	 * @param field ��
	 * @param expire �������ڣ���λΪ��
	 * @return �������ֵ���������򲻴��ڻ��Ǹ��� key ������ʱ������ nil ��
	 */
	public String hashGet(final String key, final String field, final int expire) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				Pipeline pipeline = jedis.getShard(key).pipelined();
				Response<String> result = pipeline.hget(key, field);
				pipeline.expire(key, expire);
				pipeline.sync();
				return result.get();
			}
		}.getResult();
	}

	/**
	 * ͬʱ����� field-value (��-ֵ)�����õ���ϣ�� key �С�
	 * ʱ�临�Ӷ�: O(N) (NΪfields������)
	 * @param key key
	 * @param hash field-value��map
	 * @return �������ִ�гɹ������� OK ���� key ���ǹ�ϣ��(hash)����ʱ������һ������
	 */
	public String hashMultipleSet(final String key, final Map<String, String> hash) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				return jedis.hmset(key, hash);
			}
		}.getResult();
	}

	/**
	 * ͬʱ����� field-value (��-ֵ)�����õ���ϣ�� key �С�ͬʱ������� key ������ʱ��
	 * @param key key
	 * @param hash field-value��map
	 * @param expire �������ڣ���λΪ��
	 * @return �������ִ�гɹ������� OK ���� key ���ǹ�ϣ��(hash)����ʱ������һ������
	 */
	public String hashMultipleSet(final String key, final Map<String, String> hash, final int expire) {
		return new Executor<String>(shardedJedisPool) {

			@Override
			String execute() {
				Pipeline pipeline = jedis.getShard(key).pipelined();
				Response<String> result = pipeline.hmset(key, hash);
				pipeline.expire(key, expire);
				pipeline.sync();
				return result.get();
			}
		}.getResult();
	}

	/**
	 * ���ع�ϣ�� key �У�һ�������������ֵ������������򲻴����ڹ�ϣ����ô����һ�� nil ֵ��
	 * ʱ�临�Ӷ�: O(N) (NΪfields������)
	 * @param key key
	 * @param fields field������
	 * @return һ���������������Ĺ���ֵ�ı���ֵ������˳��͸��������������˳��һ����
	 */
	public List<String> hashMultipleGet(final String key, final String... fields) {
		return new Executor<List<String>>(shardedJedisPool) {

			@Override
			List<String> execute() {
				return jedis.hmget(key, fields);
			}
		}.getResult();
	}

	/**
	 * ���ع�ϣ�� key �У�һ�������������ֵ������������򲻴����ڹ�ϣ����ô����һ�� nil ֵ��
	 * ͬʱ������� key ������ʱ��
	 * @param key key
	 * @param fields field������
	 * @param expire �������ڣ���λΪ��
	 * @return һ���������������Ĺ���ֵ�ı���ֵ������˳��͸��������������˳��һ����
	 */
	public List<String> hashMultipleGet(final String key, final int expire, final String... fields) {
		return new Executor<List<String>>(shardedJedisPool) {

			@Override
			List<String> execute() {
				Pipeline pipeline = jedis.getShard(key).pipelined();
				Response<List<String>> result = pipeline.hmget(key, fields);
				pipeline.expire(key, expire);
				pipeline.sync();
				return result.get();
			}
		}.getResult();
	}

	/**
	 * ������{@link #hashMultipleSet(String, Map)}���ڹܵ���ִ��
	 * @param pairs ���hash�Ķ��field
	 * @return ����״̬�ļ���
	 */
	public List<Object> batchHashMultipleSet(final List<Pair<String, Map<String, String>>> pairs) {
		return new Executor<List<Object>>(shardedJedisPool) {

			@Override
			List<Object> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				for (Pair<String, Map<String, String>> pair : pairs) {
					pipeline.hmset(pair.getKey(), pair.getValue());
				}
				return pipeline.syncAndReturnAll();
			}
		}.getResult();
	}

	/**
	 * ������{@link #hashMultipleSet(String, Map)}���ڹܵ���ִ��
	 * @param data Map<String, Map<String, String>>��ʽ������
	 * @return ����״̬�ļ���
	 */
	public List<Object> batchHashMultipleSet(final Map<String, Map<String, String>> data) {
		return new Executor<List<Object>>(shardedJedisPool) {

			@Override
			List<Object> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				for (Map.Entry<String, Map<String, String>> iter : data.entrySet()) {
					pipeline.hmset(iter.getKey(), iter.getValue());
				}
				return pipeline.syncAndReturnAll();
			}
		}.getResult();
	}

	/**
	 * ������{@link #hashMultipleGet(String, String...)}���ڹܵ���ִ��
	 * @param pairs ���hash�Ķ��field
	 * @return ִ�н���ļ���
	 */
	public List<List<String>> batchHashMultipleGet(final List<Pair<String, String[]>> pairs) {
		return new Executor<List<List<String>>>(shardedJedisPool) {

			@Override
			List<List<String>> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				List<List<String>> result = new ArrayList<List<String>>(pairs.size());
				List<Response<List<String>>> responses = new ArrayList<Response<List<String>>>(pairs.size());
				for (Pair<String, String[]> pair : pairs) {
					responses.add(pipeline.hmget(pair.getKey(), pair.getValue()));
				}
				pipeline.sync();
				for (Response<List<String>> resp : responses) {
					result.add(resp.get());
				}
				return result;
			}
		}.getResult();

	}

	/**
	 * ���ع�ϣ�� key �У����е����ֵ���ڷ���ֵ�����ÿ������(field name)֮�������ֵ(value)�����Է���ֵ�ĳ����ǹ�ϣ���С��������
	 * ʱ�临�Ӷ�: O(N)
	 * @param key key
	 * @return ���б���ʽ���ع�ϣ���������ֵ���� key �����ڣ����ؿ��б�
	 */
	public Map<String, String> hashGetAll(final String key) {
		return new Executor<Map<String, String>>(shardedJedisPool) {

			@Override
			Map<String, String> execute() {
				return jedis.hgetAll(key);
			}
		}.getResult();
	}

	/**
	 * ���ع�ϣ�� key �У����е����ֵ���ڷ���ֵ�����ÿ������(field name)֮�������ֵ(value)�����Է���ֵ�ĳ����ǹ�ϣ���С��������
	 * ͬʱ������� key ������ʱ��
	 * @param key key
	 * @param expire �������ڣ���λΪ��
	 * @return ���б���ʽ���ع�ϣ���������ֵ���� key �����ڣ����ؿ��б�
	 */
	public Map<String, String> hashGetAll(final String key, final int expire) {
		return new Executor<Map<String, String>>(shardedJedisPool) {

			@Override
			Map<String, String> execute() {
				Pipeline pipeline = jedis.getShard(key).pipelined();
				Response<Map<String, String>> result = pipeline.hgetAll(key);
				pipeline.expire(key, expire);
				pipeline.sync();
				return result.get();
			}
		}.getResult();
	}

	/**
	 * ������{@link #hashGetAll(String)}
	 * @param keys key������
	 * @return ִ�н���ļ���
	 */
	public List<Map<String, String>> batchHashGetAll(final String... keys) {
		return new Executor<List<Map<String, String>>>(shardedJedisPool) {

			@Override
			List<Map<String, String>> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				List<Map<String, String>> result = new ArrayList<Map<String, String>>(keys.length);
				List<Response<Map<String, String>>> responses = new ArrayList<Response<Map<String, String>>>(keys.length);
				for (String key : keys) {
					responses.add(pipeline.hgetAll(key));
				}
				pipeline.sync();
				for (Response<Map<String, String>> resp : responses) {
					result.add(resp.get());
				}
				return result;
			}
		}.getResult();
	}

	/**
	 * ������{@link #hashMultipleGet(String, String...)}����{@link #batchHashGetAll(String...)}��ͬ���ǣ�����ֵΪMap����
	 * @param keys key������
	 * @return ���hash������filed��value
	 */
	public Map<String, Map<String, String>> batchHashGetAllForMap(final String... keys) {
		return new Executor<Map<String, Map<String, String>>>(shardedJedisPool) {

			@Override
			Map<String, Map<String, String>> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();

				// ����map������ֹrehash
				int capacity = 1;
				while ((int) (capacity * 0.75) <= keys.length) {
					capacity <<= 1;
				}
				Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>(capacity);
				List<Response<Map<String, String>>> responses = new ArrayList<Response<Map<String, String>>>(keys.length);
				for (String key : keys) {
					responses.add(pipeline.hgetAll(key));
				}
				pipeline.sync();
				for (int i = 0; i < keys.length; ++i) {
					result.put(keys[i], responses.get(i).get());
				}
				return result;
			}
		}.getResult();
	}

	/* ======================================List====================================== */

	/**
	 * ��һ������ֵ value ���뵽�б� key �ı�β(���ұ�)��
	 * @param key key
	 * @param values value������
	 * @return ִ�� listPushTail �����󣬱�ĳ���
	 */
	public Long listPushTail(final String key, final String... values) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.rpush(key, values);
			}
		}.getResult();
	}

	/**
	 * ��һ������ֵ value ���뵽�б� key �ı�ͷ
	 * @param key key
	 * @param value string value
	 * @return ִ�� listPushHead ������б�ĳ��ȡ�
	 */
	public Long listPushHead(final String key, final String value) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.lpush(key, value);
			}
		}.getResult();
	}

	/**
	 * ��һ������ֵ value ���뵽�б� key �ı�ͷ, ���б����ָ�������ǾͶ��б�����޼�(trim)
	 * @param key key
	 * @param value string value
	 * @param size ������������Ⱦ��޼�Ԫ��
	 * @return ִ�� listPushHeadAndTrim ������б�ĳ��ȡ�
	 */
	public Long listPushHeadAndTrim(final String key, final String value, final long size) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				Pipeline pipeline = jedis.getShard(key).pipelined();
				Response<Long> result = pipeline.lpush(key, value);
				// �޼��б�Ԫ��, ��� size - 1 �� end �±껹Ҫ��Redis�� size ��ֵ����Ϊ end ��
				pipeline.ltrim(key, 0, size - 1);
				pipeline.sync();
				return result.get();
			}
		}.getResult();
	}

	/**
	 * ������{@link #listPushTail(String, String...)}�������ķ�ʽʵ��
	 * @param key key
	 * @param values value������
	 * @param delOld ���key���ڣ��Ƿ�ɾ������true ɾ����false: ��ɾ����ֻ������β׷��
	 */
	public void batchListPushTail(final String key, final String[] values, final boolean delOld) {
		new Executor<Object>(shardedJedisPool) {

			@Override
			Object execute() {
				if (delOld) {
					RedisLock lock = new RedisLock(key, shardedJedisPool);
					lock.lock();
					try {
						Pipeline pipeline = jedis.getShard(key).pipelined();
						pipeline.del(key);
						for (String value : values) {
							pipeline.rpush(key, value);
						}
						pipeline.sync();
					} finally {
						lock.unlock();
					}
				} else {
					jedis.rpush(key, values);
				}
				return null;
			}
		}.getResult();
	}

	/**
	 * ͬ{@link #batchListPushTail(String, String[], boolean)},��ͬ��������redis������������ʵ��
	 * @param key key
	 * @param values value������
	 * @return null
	 */
	public Object updateListInTransaction(final String key, final List<String> values) {
		return new Executor<Object>(shardedJedisPool) {

			@Override
			Object execute() {
				Transaction transaction = jedis.getShard(key).multi();
				transaction.del(key);
				for (String value : values) {
					transaction.rpush(key, value);
				}
				transaction.exec();
				return null;
			}
		}.getResult();
	}

	/**
	 * ��key��Ӧlist��β��������ַ���Ԫ��,���key���ڣ�ʲôҲ����
	 * @param key key
	 * @param values value������
	 * @return ִ��insertListIfNotExists�󣬱�ĳ���
	 */
	public Long insertListIfNotExists(final String key, final String[] values) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				RedisLock lock = new RedisLock(key, shardedJedisPool);
				lock.lock();
				try {
					if (!jedis.exists(key)) {
						return jedis.rpush(key, values);
					}
				} finally {
					lock.unlock();
				}
				return 0L;
			}
		}.getResult();
	}

	/**
	 * ����list����Ԫ�أ��±��0��ʼ����ֵ��ʾ�Ӻ�����㣬-1��ʾ������һ��Ԫ�أ�key�����ڷ��ؿ��б�
	 * @param key key
	 * @return list����Ԫ��
	 */
	public List<String> listGetAll(final String key) {
		return new Executor<List<String>>(shardedJedisPool) {

			@Override
			List<String> execute() {
				return jedis.lrange(key, 0, -1);
			}
		}.getResult();
	}

	/**
	 * ����ָ�������ڵ�Ԫ�أ��±��0��ʼ����ֵ��ʾ�Ӻ�����㣬-1��ʾ������һ��Ԫ�أ�key�����ڷ��ؿ��б�
	 * @param key key
	 * @param beginIndex �±꿪ʼ������������
	 * @param endIndex �±������������������
	 * @return ָ�������ڵ�Ԫ��
	 */
	public List<String> listRange(final String key, final long beginIndex, final long endIndex) {
		return new Executor<List<String>>(shardedJedisPool) {

			@Override
			List<String> execute() {
				return jedis.lrange(key, beginIndex, endIndex - 1);
			}
		}.getResult();
	}

	/**
	 * һ�λ�ö�����������
	 * @param keys key������
	 * @return ִ�н��
	 */
	public Map<String, List<String>> batchGetAllList(final List<String> keys) {
		return new Executor<Map<String, List<String>>>(shardedJedisPool) {

			@Override
			Map<String, List<String>> execute() {
				ShardedJedisPipeline pipeline = jedis.pipelined();
				Map<String, List<String>> result = new HashMap<String, List<String>>();
				List<Response<List<String>>> responses = new ArrayList<Response<List<String>>>(keys.size());
				for (String key : keys) {
					responses.add(pipeline.lrange(key, 0, -1));
				}
				pipeline.sync();
				for (int i = 0; i < keys.size(); ++i) {
					result.put(keys.get(i), responses.get(i).get());
				}
				return result;
			}
		}.getResult();
	}

	/* ======================================Pub/Sub====================================== */

	/**
	 * ����Ϣ message ���͵�ָ����Ƶ�� channel��
	 * ʱ�临�Ӷȣ�O(N+M)������ N ��Ƶ�� channel �Ķ������������� M ����ʹ��ģʽ����(subscribed patterns)�Ŀͻ��˵�������
	 * @param channel Ƶ��
	 * @param message ��Ϣ
	 * @return ���յ���Ϣ message �Ķ�����������
	 */
	public Long publish(final String channel, final String message) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				Jedis _jedis = jedis.getShard(channel);
				return _jedis.publish(channel, message);
			}
			
		}.getResult();
	}

	/**
	 * ���ĸ�����һ��Ƶ������Ϣ��
	 * @param jedisPubSub ������
	 * @param channel Ƶ��
	 */
	public void subscribe(final JedisPubSub jedisPubSub, final String channel) {
		new Executor<Object>(shardedJedisPool) {

			@Override
			Object execute() {
				Jedis _jedis = jedis.getShard(channel);
				// ע��subscribe��һ��������������Ϊ��ǰ�߳�Ҫ��ѯRedis����ӦȻ�����subscribe
				_jedis.subscribe(jedisPubSub, channel);
				return null;
			}
		}.getResult();
	}

	/**
	 * ȡ������
	 * @param jedisPubSub ������
	 */
	public void unSubscribe(final JedisPubSub jedisPubSub) {
		jedisPubSub.unsubscribe();
	}

	/* ======================================Sorted set================================= */

	/**
	 * ��һ�� member Ԫ�ؼ��� score ֵ���뵽���� key ���С�
	 * @param key key
	 * @param score score ֵ����������ֵ��˫���ȸ�������
	 * @param member ���򼯵ĳ�Ա
	 * @return ���ɹ���ӵ��³�Ա����������������Щ�����µġ��Ѿ����ڵĳ�Ա��
	 */
	public Long addWithSortedSet(final String key, final double score, final String member) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.zadd(key, score, member);
			}
		}.getResult();
	}

	/**
	 * ����� member Ԫ�ؼ��� score ֵ���뵽���� key ���С�
	 * @param key key
	 * @param scoreMembers score��member��pair
	 * @return ���ɹ���ӵ��³�Ա����������������Щ�����µġ��Ѿ����ڵĳ�Ա��
	 */
	public Long addWithSortedSet(final String key, final Map<Double, String> scoreMembers) {
		return new Executor<Long>(shardedJedisPool) {

			@Override
			Long execute() {
				return jedis.zadd(key, scoreMembers);
			}
		}.getResult();
	}

	/**
	 * �������� key �У� score ֵ���� max �� min ֮��(Ĭ�ϰ������� max �� min )�����еĳ�Ա��
	 * ���򼯳�Ա�� score ֵ�ݼ�(�Ӵ�С)�Ĵ������С�
	 * @param key key
	 * @param max score���ֵ
	 * @param min score��Сֵ
	 * @return ָ�������ڣ����� score ֵ(��ѡ)�����򼯳�Ա���б�
	 */
	public Set<String> revrangeByScoreWithSortedSet(final String key, final double max, final double min) {
		return new Executor<Set<String>>(shardedJedisPool) {

			@Override
			Set<String> execute() {
				return jedis.zrevrangeByScore(key, max, min);
			}
		}.getResult();
	}

	/* ======================================Other====================================== */

	/**
	 * ��������Դ
	 * @param shardedJedisPool ����Դ
	 */
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	/**
	 * ����Pair��ֵ��
	 * @param key key
	 * @param value value
	 * @return ��ֵ��
	 */
	public <K, V> Pair<K, V> makePair(K key, V value) {
		return new Pair<K, V>(key, value);
	}

	/**
	 * ��ֵ��
	 * @version V1.0
	 * @author fengjc
	 * @param <K> key
	 * @param <V> value
	 */
	public class Pair<K, V> {

		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
