package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dell
 * @version 1.0.0
 * @create 2018-12-11 15:55
 */
public class JedisPoolUtil {

    public static final String host = "localhost";
    public static final int port = 6379;

    public static Jedis getJedisResource() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool pool = new JedisPool(poolConfig, host, port);
        return pool.getResource();
    }

    public static void closeJedisResouce(Jedis resource) {
        resource.close();
    }
}