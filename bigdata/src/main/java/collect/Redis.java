package collect;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Redis实现类
 * Created by Telis on 17/6/5.
 */
public class Redis {

    private static String REDIS_IP ;
    private static String REDIS_PORT ;
    private static String REDIS_PASSWORD;
    private static int REDIS_TIMEOUT = 2000;
    private static JedisPool pool;

    public Redis(Map<String,String> map){
        REDIS_IP = map.get("redis.ip");
        REDIS_PORT = map.get("redis.port");
        REDIS_PASSWORD = map.get("redis.password");
        REDIS_TIMEOUT = 2000;
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.valueOf(map.get("redis.pool.maxTotal")));
            config.setMaxIdle(Integer.valueOf(map.get("redis.pool.maxIdle")));
            config.setMaxWaitMillis(Integer.valueOf(map.get("redis.pool.maxWait")));
            config.setTestOnBorrow(Boolean.valueOf(map.get("redis.pool.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(map.get("redis.pool.testOnReturn")));
            pool = new JedisPool(config, REDIS_IP, Integer.valueOf(REDIS_PORT), REDIS_TIMEOUT, REDIS_PASSWORD);
        }
    }


    /**
     * 从redis访问池中获取redis实例
     *
     * @return redis实例
     */
    private static Jedis getPool() {
        return pool.getResource();
    }

    public static void returnResource(Jedis redis) {
        pool.returnResource(redis);
    }

    /**
     * 获取redis实例
     *
     * @return redis实例
     */
    public static Jedis getSingle() {
        Jedis redis = new Jedis(REDIS_IP, Integer.valueOf(REDIS_PORT), REDIS_TIMEOUT);
        redis.auth(REDIS_PASSWORD);
        return redis;
    }


    /**
     *
     * 用于插入redis队列方法 参数为 对应的key和value
     * @param key key
     * @param value value
     */
    public static void insert(String key,String value) {
        Jedis redis =getPool();
        redis.lpush(key,value);
        List<String> list=redis.lrange(key,0,-1);
        pool.returnResource(redis);
    }


    /**
     * 用于插入redis队列方法 参数为list
     */
    public static void insert(Map<String,HashSet<String>> redisMap) {
        Jedis redis =getPool();
        //redis.set(key,value);
        for(String key:redisMap.keySet()) {
            for(String list :redisMap.get(key)){
               //debugStatic(key+"||"+list);
                 redis.lpush(key, list);
            }
        }
        pool.returnResource(redis);
    }
}
