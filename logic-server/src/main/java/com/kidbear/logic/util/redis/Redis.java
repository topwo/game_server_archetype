package com.kidbear.logic.util.redis;

import com.kidbear.logic.core.GameInit;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * JedisAPI操作工具类
 */
public class Redis {
    private static Redis instance;
    /**
     * The constant log.
     */
    public static Logger log = LoggerFactory.getLogger(Redis.class);
    /**
     * 全局数据库
     */
    public static final int GLOBAL_DB = 0;// 全局
    /**
     * 逻辑数据库
     */
    public static final int LOGIC_DB = GameInit.serverId;// 模块库
    /**
     * 数据库默认密码
     */
    public static String password = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Redis getInstance() {
        if (instance == null) {
            instance = new Redis();
        }
        return instance;
    }

    /**
     * The Host.
     */

    public String host;
    /**
     * jedis pool
     */
    private JedisPool pool;
    /**
     * sentinel pool
     */
    // private JedisSentinelPool sentinelPool;
    /**
     * The Port.
     */
    public int port;

    /**
     * Gets jedis.
     *
     * @return the jedis
     */
    public Jedis getJedis() {
        return this.pool.getResource();
    }

    /**
     * Return resource.
     *
     * @param jedis the jedis
     */
    public void returnResource(Jedis jedis) {
        jedis.close();
        // this.sentinelPool.returnResource(jedis);
    }

    /**
     * sentinel初始化
     */
    // public void init() {
    // String redisServer = null;
    // if (GameInit.cfg != null) {
    // redisServer = GameInit.cfg.get("redisServer");
    // password = GameInit.cfg.get("redisPwd");
    // }
    // if (redisServer == null) {
    // redisServer = "127.0.0.1:6440";
    // }
    // redisServer = redisServer.trim();
    // String[] tmp = redisServer.split(":");
    // host = tmp[0];
    // port = Integer.parseInt(tmp[1]);
    // if (tmp.length == 2) {
    // port = Integer.parseInt(tmp[1].trim());
    // }
    // log.info("Redis sentinel at {}:{}", host, port);
    // // sentinelPool = new JedisPool(host, port);
    // Set sentinels = new HashSet();
    // sentinels.add(new HostAndPort(host, port).toString());
    // pool = new JedisSentinelPool("master1", sentinels);
    // }

    /**
     * Jedis初始化
     */
    public void init() {
        String redisServer = null;
        if (GameInit.cfg != null) {
            redisServer = GameInit.cfg.get("redisServer");
            password = GameInit.cfg.get("redisPwd");
        }
        if (redisServer == null) {
            redisServer = "127.0.0.1:6446";
            password = "123456";
        }
        redisServer = redisServer.trim();
        String[] tmp = redisServer.split(":");
        host = tmp[0];
        port = Integer.parseInt(tmp[1]);
        if (tmp.length == 2) {
            port = Integer.parseInt(tmp[1].trim());
        }
        log.info("Redis at {}:{}", host, port);
        // sentinelPool = new JedisPool(host, port);
        JedisPoolConfig config = new JedisPoolConfig();
        pool = new JedisPool(config, host, port, 100000, password);
    }

    /**
     * Test.
     */
    public void test() {
        Jedis j = getJedis();
        j.auth(password);
        returnResource(j);
    }

    /**
     * Select.
     *
     * @param index the index
     */
    public void select(int index) {
        Jedis j = getJedis();
        j.auth(password);
        j.select(index);
        returnResource(j);
    }

    /**
     * Object to hash map.
     *
     * @param obj the obj
     * @return the map
     * @throws IntrospectionException    the introspection exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     */
    public static Map<String, String> objectToHash(Object obj)
            throws IntrospectionException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Map<String, String> map = new HashMap<String, String>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            if (!property.getName().equals("class")) {
                map.put(property.getName(), ""
                        + property.getReadMethod().invoke(obj));
            }
        }
        return map;
    }

    /**
     * Hash to object.
     *
     * @param map the map
     * @param obj the obj
     * @throws IllegalAccessException    the illegal access exception
     * @throws InvocationTargetException the invocation target exception
     */
    public static void hashToObject(Map<?, ?> map, Object obj)
            throws IllegalAccessException, InvocationTargetException {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getValue().equals("null")) {
                entry.setValue(null);
            }
        }
        BeanUtils.populate(obj, (Map) map);
    }

    /**
     * Hash to object t.
     *
     * @param <T> the type parameter
     * @param map the map
     * @param c   the c
     * @return the t
     * @throws IllegalAccessException    the illegal access exception
     * @throws InvocationTargetException the invocation target exception
     * @throws InstantiationException    the instantiation exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T hashToObject(Map<?, ?> map, Class c)
            throws IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Object obj = c.newInstance();
        hashToObject(map, obj);
        return (T) obj;
    }

    /**
     * Hmget list.
     *
     * @param db     the db
     * @param key    the key
     * @param fields the fields
     * @return the list
     */
    public List<String> hmget(int db, String key, String... fields) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        List<String> ret = redis.hmget(key, fields);
        redis.select(db);
        returnResource(redis);
        return ret;
    }

    /**
     * Hmset string.
     *
     * @param db     the db
     * @param key    the key
     * @param object the object
     * @return the string
     * @throws IllegalAccessException    the illegal access exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     * @throws IntrospectionException    the introspection exception
     */
    public String hmset(int db, String key, Object object)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, IntrospectionException {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        String ret = redis.hmset(key, objectToHash(object));
        returnResource(redis);
        return ret;
    }

    /**
     * Hmset string.
     *
     * @param db     the db
     * @param key    the key
     * @param fields the fields
     * @return the string
     * @throws IllegalAccessException    the illegal access exception
     * @throws IllegalArgumentException  the illegal argument exception
     * @throws InvocationTargetException the invocation target exception
     * @throws IntrospectionException    the introspection exception
     */
    public String hmset(int db, String key, Map<String, String> fields)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, IntrospectionException {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        String ret = redis.hmset(key, fields);
        returnResource(redis);
        return ret;
    }

    /**
     * Hkeys set.
     *
     * @param db  the db
     * @param key the key
     * @return the set
     */
    public Set<String> hkeys(int db, String key) {
        if (key == null) {
            return null;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ret = redis.hkeys(key);
        returnResource(redis);
        return ret;
    }

    /**
     * Hlen long.
     *
     * @param tx  the tx
     * @param db  the db
     * @param key the key
     * @return the long
     */
    public long hlen(Transaction tx, int db, String key) {
        if (key == null) {
            return -1;
        }
        tx.select(db);
        Long ret = tx.hlen(key).get();
        ret = ret == null ? -1 : ret;
        return ret;
    }

    /**
     * Hlen long.
     *
     * @param db  the db
     * @param key the key
     * @return the long
     */
    public long hlen(int db, String key) {
        if (key == null) {
            return -1;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Long ret = redis.hlen(key);
        ret = ret == null ? -1 : ret;
        returnResource(redis);
        return ret;
    }

    /**
     * Incr by long.
     *
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @param value the value
     * @return the long
     */
    public long incrBy(int db, String key, String field, long value) {
        if (key == null) {
            return -1;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Long ret = redis.incrBy(key, value);
        ret = ret == null ? -1 : ret;
        returnResource(redis);
        return ret;
    }

    /**
     * Hincr by long.
     *
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @param value the value
     * @return the long
     */
    public long hincrBy(int db, String key, String field, long value) {
        if (key == null) {
            return -1;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Long ret = redis.hincrBy(key, field, value);
        ret = ret == null ? -1 : ret;
        returnResource(redis);
        return ret;
    }

    /**
     * Hexist boolean.
     *
     * @param tx    the tx
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @return the boolean
     */
    public boolean hexist(Transaction tx, int db, String key, String field) {
        if (key == null) {
            return false;
        }
        tx.select(db);
        boolean ret = tx.hexists(key, field).get();
        return ret;
    }

    /**
     * Hexist boolean.
     *
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @return the boolean
     */
    public boolean hexist(int db, String key, String field) {
        if (key == null) {
            return false;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        boolean ret = redis.hexists(key, field);
        returnResource(redis);
        return ret;
    }

    /**
     * Hdel long.
     *
     * @param db     the db
     * @param key    the key
     * @param fields the fields
     * @return the long
     */
    public Long hdel(int db, String key, String... fields) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Long cnt = redis.hdel(key, fields);
        returnResource(redis);
        return cnt;
    }

    /**
     * Begin transaction transaction.
     *
     * @param redis the redis
     * @return the transaction
     */
    public Transaction beginTransaction(Jedis redis) {
        redis.auth(password);
        Transaction tx = redis.multi();
        return tx;
    }

    /**
     * End transaction boolean.
     *
     * @param tx    the tx
     * @param jedis the jedis
     * @return the boolean
     */
    public boolean endTransaction(Transaction tx, Jedis jedis) {
        jedis.unwatch();
        List<Object> ret = tx.exec();
        returnResource(jedis);
        return !(ret == null || ret.isEmpty());
    }

    /**
     * Hget string.
     *
     * @param tx    the tx
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @return the string
     */
    public String hget(Transaction tx, int db, String key, String field) {
        if (key == null) {
            return null;
        }
        Jedis redis = getJedis();
        tx.select(db);
        String ret = tx.hget(key, field).get();
        returnResource(redis);
        return ret;
    }

    /**
     * Hget string.
     *
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @return the string
     */
    public String hget(int db, String key, String field) {
        if (key == null) {
            return null;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        String ret = redis.hget(key, field);
        returnResource(redis);
        return ret;
    }

    /**
     * Hget all map.
     *
     * @param tx  the tx
     * @param db  the db
     * @param key the key
     * @return the map
     */
    public Map<String, String> hgetAll(Transaction tx, int db, String key) {
        if (key == null) {
            return null;
        }
        tx.select(db);
        Map<String, String> ret = tx.hgetAll(key).get();
        return ret;
    }

    /**
     * Hget all map.
     *
     * @param db  the db
     * @param key the key
     * @return the map
     */
    public Map<String, String> hgetAll(int db, String key) {
        if (key == null) {
            return null;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Map<String, String> ret = redis.hgetAll(key);
        returnResource(redis);
        return ret;
    }

    /**
     * Hset.
     *
     * @param tx    the tx
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @param value the value
     */
    public void hset(Transaction tx, int db, String key, String field,
                     String value) {
        if (field == null || field.length() == 0) {
            return;
        }
        if (value == null || value.length() == 0) {
            return;
        }
        tx.select(db);
        tx.hset(key, field, value);
    }

    /**
     * Hset.
     *
     * @param db    the db
     * @param key   the key
     * @param field the field
     * @param value the value
     */
    public void hset(int db, String key, String field, String value) {
        if (field == null || field.length() == 0) {
            return;
        }
        if (value == null || value.length() == 0) {
            return;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.hset(key, field, value);
        returnResource(redis);
    }

    /**
     * Map 的存放和获取
     *
     * @param db     the db
     * @param group  the group
     * @param values the values
     */
    public void add(int db, String group, Map<String, String> values) {
        if (values == null || values.size() == 0) {
            return;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.hmset(group, values);
        returnResource(redis);
    }

    /**
     * Add.
     *
     * @param db    the db
     * @param group the group
     * @param key   the key
     * @param value the value
     */
    public void add(int db, String group, String key, String value) {
        if (value == null || key == null) {
            return;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.hset(group, key, value);
        returnResource(redis);
    }

    /**
     * Set.
     *
     * @param db    the db
     * @param key   the key
     * @param value the value
     */
    public void set(int db, String key, String value) {
        if (value == null || key == null) {
            return;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.set(key, value);
        returnResource(redis);
    }

    /**
     * H del builder long.
     *
     * @param db    the db
     * @param group the group
     * @param keys  the keys
     * @return the long
     */
    public Long hDelBuilder(int db, String group, String... keys) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        byte[][] fields = new byte[keys.length][];
        for (int i = 0; i < keys.length; i++) {
            fields[i] = keys[i].getBytes();
        }
        Long cnt = redis.hdel(group.getBytes(), fields);
        returnResource(redis);
        return cnt;
    }

    /**
     * Gets map.
     *
     * @param db    the db
     * @param group the group
     * @return the map
     */
    public Map<String, String> getMap(int db, String group) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Map<String, String> ret = redis.hgetAll(group);
        returnResource(redis);
        return ret;
    }

    /**
     * Get string.
     *
     * @param db  the db
     * @param key the key
     * @return the string
     */
    public String get(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        String ret = redis.get(key);
        returnResource(redis);
        return ret;
    }

    /**
     * 添加元素到集合中
     *
     * @param db      the db
     * @param key     the key
     * @param element the element
     * @return the boolean
     */
    public boolean sadd(int db, String key, String... element) {
        if (element == null || element.length == 0) {
            return false;
        }

        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        boolean success = redis.sadd(key, element) == 1;
        returnResource(redis);
        return success;
    }

    /**
     * Smove boolean.
     *
     * @param db      the db
     * @param oldKey  the old key
     * @param newKey  the new key
     * @param element the element
     * @return the boolean
     */
    public boolean smove(int db, String oldKey, String newKey, String element) {
        if (element == null) {
            return false;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        boolean success = (redis.smove(oldKey, newKey, element) == 1);
        returnResource(redis);
        return success;
    }

    /**
     * 删除指定set内的元素
     *
     * @param db      the db
     * @param key     the key
     * @param element the element
     * @return the boolean
     */
    public boolean sremove(int db, String key, String... element) {
        if (element == null) {
            return false;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        boolean success = (redis.srem(key, element) == 1);
        returnResource(redis);
        return success;
    }

    /**
     * Sget set.
     *
     * @param db  the db
     * @param key the key
     * @return the set
     */
    public Set<String> sget(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> m = redis.smembers(key);
        returnResource(redis);
        return m;
    }

    /**
     * 返回set的的元素个数
     *
     * @param db  the db
     * @param key the key
     * @return long
     * @Title: zcard_
     * @Description:
     */
    public long scard_(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        long size = redis.scard(key);
        returnResource(redis);
        return size;
    }

    /**
     * Ladd list.
     *
     * @param db       the db
     * @param key      the key
     * @param elements the elements
     */
    public void laddList(int db, String key, String... elements) {
        if (elements == null || elements.length == 0) {
            return;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.lpush(key, elements);
        returnResource(redis);
    }

    /**
     * Lpush.
     *
     * @param db  the db
     * @param key the key
     * @param id  the id
     * @Title: lpush_
     * @Description:
     */
    public void lpush_(int db, String key, String id) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.lpush(key, id);
        returnResource(redis);
    }

    /**
     * Rpush.
     *
     * @param db  the db
     * @param key the key
     * @param id  the id
     */
    public void rpush_(int db, String key, String id) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.rpush(key, id);
        returnResource(redis);
    }

    /**
     * add by wangzhuan
     *
     * @param db    the db
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return list
     * @Title: lrange
     * @Description:
     */
    public List<String> lrange(int db, String key, int start, int end) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        List<String> list = redis.lrange(key, start, end);
        returnResource(redis);
        return list;
    }

    /**
     * Lget list list.
     *
     * @param db  the db
     * @param key the key
     * @return the list
     */
    public List<String> lgetList(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        long len = redis.llen(key);
        List<String> ret = redis.lrange(key, 0, len - 1);
        returnResource(redis);
        return ret;
    }

    /**
     * 列表list中是否包含value
     *
     * @param db    the db
     * @param key   the key
     * @param value the value
     * @return boolean
     */
    public boolean lexist(int db, String key, String value) {
        List<String> list = lgetList(db, key);
        return list.contains(value);
    }

    /**
     * Lget list list.
     *
     * @param db  the db
     * @param key the key
     * @param len the len
     * @return the list
     */
    public List<String> lgetList(int db, String key, long len) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        long max = redis.llen(key);
        long l = max > len ? len : max;
        List<String> ret = redis.lrange(key, 0, l - 1);
        returnResource(redis);
        return ret;
    }

    /**
     * Del long.
     *
     * @param db  the db
     * @param key the key
     * @return the long
     */
    public Long del(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Long cnt = redis.del(key);
        returnResource(redis);
        return cnt;
    }

    /**
     * 模糊删除
     *
     * @param db  the db
     * @param key the key
     * @return long
     */
    public Long delKeyLikes(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> keys = redis.keys(key);
        Long cnt = redis.del(keys.toArray(new String[keys.size()]));
        returnResource(redis);
        return cnt;
    }

    /**
     * 测试元素是否存在
     *
     * @param db      the db
     * @param key     the key
     * @param element the element
     * @return boolean
     */
    public boolean sexist(int db, String key, String element) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        boolean ret = redis.sismember(key, element);
        returnResource(redis);
        return ret;
    }

    /**
     * 判断某一个key值得存储结构是否存在
     *
     * @param db  the db
     * @param key the key
     * @return boolean
     * @Title: exist_
     * @Description:
     */
    public boolean exist_(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        boolean yes = redis.exists(key);
        returnResource(redis);
        return yes;
    }

    /**********************************************************************
     * 排行用到的SortedSet
     * @param db the db
     * @param key the key
     * @param score the score
     * @param member the member
     * @return the long
     */
    public long zadd(int db, String key, int score, String member) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        long ret = 0;
        try {
            ret = redis.zadd(key, score, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
        return ret;
    }

    /**
     * Zadd long.
     *
     * @param tx     the tx
     * @param db     the db
     * @param key    the key
     * @param score  the score
     * @param member the member
     * @return the long
     */
    public long zadd(Transaction tx, int db, String key, int score,
                     String member) {
        tx.select(db);
        long ret = 0;
        try {
            ret = tx.zadd(key, score, member).get();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
        }
        return ret;
    }

    /**
     * 添加 分数，并返回修改后的值
     *
     * @param db     the db
     * @param key    the key
     * @param update the update
     * @param member the member
     * @return double
     */
    public double zincrby(int db, String key, int update, String member) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        double ret = redis.zincrby(key, update, member);
        returnResource(redis);
        return ret;
    }

    /**
     * 返回有序集 key 中，成员 member 的 score 值,存在返回score，不存在返回-1
     *
     * @param db     the db
     * @param key    the key
     * @param member the member
     * @return double
     */
    public double zscore(int db, String key, String member) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Double ret = redis.zscore(key, member);
        returnResource(redis);
        if (ret == null) {
            return -1;
        }
        return ret;
    }

    /**
     * Zscore double.
     *
     * @param tx     the tx
     * @param db     the db
     * @param key    the key
     * @param member the member
     * @return the double
     */
    public double zscore(Transaction tx, int db, String key, String member) {
        tx.select(db);
        Double ret = tx.zscore(key, member).get();
        if (ret == null) {
            return -1;
        }
        return ret;
    }

    /**
     * 按 从大到小的排名，获取 member的 排名
     *
     * @param db     the db
     * @param key    the key
     * @param member the member
     * @return long
     */
    public long zrevrank(int db, String key, String member) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Long ret = redis.zrevrank(key, member);
        ret = ret == null ? -1 : ret;
        returnResource(redis);
        return ret.longValue();
    }

    /**
     * 按照score的值从小到大排序，返回member的排名 排序是从0开始
     *
     * @param db     the db
     * @param key    the key
     * @param member the member
     * @return 设置为名次从1开始 。返回为-1，表示member无记录
     * @Title: zrank
     * @Description:
     */
    public long zrank(int db, String key, String member) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        long ret = -1;
        Long vv = redis.zrank(key, member);
        if (vv != null) {
            ret = vv.longValue();
        }
        returnResource(redis);
        if (ret != -1) {
            ret += 1;
        }
        return ret;
    }

    /**
     * 返回的是score的值
     *
     * @param db     the db
     * @param key    the key
     * @param member the member
     * @return 返回有序集 key 中，成员 member 的 score 值 如果 member 元素不是有序集 key 的成员，或 key 不存在，返回 null 。
     * @Title: zscore_
     * @Description:
     */
    public int zscore_(int db, String key, String member) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        int ret = -1;
        Double vv = redis.zscore(key, member);
        if (vv != null) {
            ret = (int) vv.doubleValue();
        }
        returnResource(redis);
        if (ret != -1) {
            ret += 1;
        }
        return ret;
    }

    /**
     * min 和max 都是score的值
     *
     * @param db  the db
     * @param key the key
     * @param min the min
     * @param max the max
     * @return set
     * @Title: zrangebyscore_
     * @Description:
     */
// add 20141216
    public Set<String> zrangebyscore_(int db, String key, long min, long max) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ss = redis.zrangeByScore(key, min, max);
        returnResource(redis);
        return ss;
    }

    /**
     * Zrevrange set.
     *
     * @param tx  the tx
     * @param db  the db
     * @param key the key
     * @param min the min
     * @param max the max
     * @return the set
     */
    public Set<String> zrevrange(Transaction tx, int db, String key, long min,
                                 long max) {
        tx.select(db);
        Set<String> ss = tx.zrevrange(key, min, max).get();
        return ss;
    }

    /**
     * Zrevrange set.
     *
     * @param db  the db
     * @param key the key
     * @param min the min
     * @param max the max
     * @return the set
     */
    public Set<String> zrevrange(int db, String key, long min, long max) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ss = redis.zrevrange(key, min, max);
        returnResource(redis);
        return ss;
    }

    /**
     * Zrange set.
     *
     * @param tx  the tx
     * @param db  the db
     * @param key the key
     * @param min the min
     * @param max the max
     * @return the set
     */
    public Set<String> zrange(Transaction tx, int db, String key, long min,
                              long max) {
        tx.select(db);
        Set<String> ss = tx.zrange(key, min, max).get();
        return ss;
    }

    /**
     * Zrange set.
     *
     * @param db  the db
     * @param key the key
     * @param min the min
     * @param max the max
     * @return the set
     */
    public Set<String> zrange(int db, String key, long min, long max) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ss = redis.zrange(key, min, max);
        returnResource(redis);
        return ss;
    }

    /**
     * min 和max 都是score的值 获得一个包含了score的元组集合. 元组（Tuple）
     * 笛卡尔积中每一个元素（d1，d2，…，dn）叫作一个n元组（n-tuple）或简称元组
     *
     * @param db  the db
     * @param key the key
     * @param min the min
     * @param max the max
     * @return set
     * @Title: zrangebyscorewithscores_
     * @Description:
     */
    public Set<Tuple> zrangebyscorewithscores_(int db, String key, long min,
                                               long max) {
        Jedis redis = getJedis();
        if (redis == null) {
            return null;
        }
        redis.auth(password);
        redis.select(db);
        Set<Tuple> result = null;
        try {
            result = redis.zrangeByScoreWithScores(key, min, max);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
        return result;
    }

    /**
     * zrevrangeWithScores ： 从大到小排序 zrangeWithScores ： 从小到大排序
     *
     * @param db    the db
     * @param key   the key
     * @param start ： （排名）0表示第一个元素，-x：表示倒数第x个元素
     * @param end   ： （排名）-1表示最后一个元素（最大值）
     * @return 返回 排名在start 、end之间带score元素
     * @Title: zrangeWithScores
     * @Description:
     */
    public Map<String, Double> zrevrangeWithScores(int db, String key,
                                                   long start, long end) {
        Jedis redis = getJedis();
        if (redis == null) {
            return null;
        }
        redis.auth(password);
        redis.select(db);
        Set<Tuple> result = null;
        try {
            result = redis.zrevrangeWithScores(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
        return tupleToMap(db, result);
    }

    /**
     * zrangeWithScores ： 从大到小排序 zrangeWithScores ： 从小到大排序
     *
     * @param db    the db
     * @param key   the key
     * @param start ： （排名）0表示第一个元素，-x：表示倒数第x个元素
     * @param end   ： （排名）-1表示最后一个元素（最大值）
     * @return 返回 排名在start 、end之间带score元素
     * @Title: zrangeWithScores
     * @Description:
     */
    public Map<String, Double> zrangeWithScores(int db, String key, long start,
                                                long end) {
        Jedis redis = getJedis();
        if (redis == null) {
            return null;
        }
        redis.auth(password);
        redis.select(db);
        Set<Tuple> result = null;
        try {
            result = redis.zrangeWithScores(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
        return tupleToMap(db, result);
    }

    /**
     * Tuple to map map.
     *
     * @param db       the db
     * @param tupleSet the tuple set
     * @return Map<String Double> ： 返回的是 有序<element, score>
     * @Title: tupleToMap
     * @Description:
     */
    public Map<String, Double> tupleToMap(int db, Set<Tuple> tupleSet) {
        if (tupleSet == null)
            return null;
        Map<String, Double> map = new LinkedHashMap<String, Double>();
        for (Tuple tup : tupleSet) {
            map.put(tup.getElement(), tup.getScore());
        }
        return map;
    }

    /**
     * 删除key中的member
     *
     * @param db     the db
     * @param key    the key
     * @param member the member
     * @return long
     * @Title: zrem
     * @Description:
     */
    public long zrem(int db, String key, String member) {
        Jedis redis = getJedis();
        if (redis == null) {
            return -1;
        }
        redis.auth(password);
        redis.select(db);
        long result = -1;
        try {
            result = redis.zrem(key, member);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
        return result;
    }

    /**
     * 从高到低排名，返回前 num 个score和member
     *
     * @param db  the db
     * @param key the key
     * @param num the num
     * @return set
     */
    public Set<Tuple> ztopWithScore(int db, String key, int num) {
        if (num <= 0) {
            return null;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<Tuple> ret = redis.zrevrangeWithScores(key, 0, num - 1);
        returnResource(redis);
        return ret;
    }

    /**
     * 返回score区间的member
     *
     * @param db  the db
     * @param key the key
     * @param max the max
     * @param min the min
     * @return set
     */
    public Set<String> zrankByScore(int db, String key, int max, int min) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ret = redis.zrevrangeByScore(key, max, min);
        returnResource(redis);
        return ret;
    }

    /**
     * 从高到低排名，返回前 num 个
     *
     * @param db  the db
     * @param key the key
     * @param num the num
     * @return set
     */
    public Set<String> ztop(int db, String key, int num) {
        if (num <= 0) {
            return null;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ret = redis.zrevrange(key, 0, num - 1);
        returnResource(redis);
        return ret;
    }

    /**
     * 从高到低排名，返回start到end的前 num 个
     *
     * @param db    the db
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return set
     */
    public Set<String> ztop(int db, String key, int start, int end) {
        if (end <= start) {
            return null;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        Set<String> ret = redis.zrevrange(key, start, end - 1);
        returnResource(redis);
        return ret;
    }

    /**
     * 返回zset的的元素个数
     *
     * @param db  the db
     * @param key the key
     * @return long
     * @Title: zcard_
     * @Description:
     */
    public long zcard_(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        long size = redis.zcard(key);
        returnResource(redis);
        return size;
    }

    /**
     * Destroy.
     */
    public static void destroy() {
        getInstance().pool.destroy();
    }

    /**
     * Publish.
     *
     * @param db      the db
     * @param channel the channel
     * @param message the message
     */
    public void publish(int db, String channel, String message) {
        if (channel == null || message == null) {
            return;
        }
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.publish(channel, message);
        returnResource(redis);
    }

    /**
     * Lpop string.
     *
     * @param db  the db
     * @param key the key
     * @return the string
     */
    public String lpop(int db, String key) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        String value = redis.lpop(key);
        returnResource(redis);
        return value;
    }

    /**
     * Lrem.
     *
     * @param db    the db
     * @param key   the key
     * @param count the count
     * @param value the value
     */
    public void lrem(int db, String key, int count, String value) {
        Jedis redis = getJedis();
        redis.auth(password);
        redis.select(db);
        redis.lrem(key, count, value);
        returnResource(redis);
    }
}
