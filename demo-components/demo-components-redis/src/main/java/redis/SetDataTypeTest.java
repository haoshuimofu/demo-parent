package redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis->Set数据类型测试
 *
 * @author dell
 * @version 1.0.0
 * @create 2018-12-12 10:46
 */
public class SetDataTypeTest {

    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedisResource();

        Long saddResult = jedis.sadd("students", "ZhangSan", "LiSi", "A");
        System.out.println("sadd Result=" + saddResult);

        Set<String> smembers = jedis.smembers("students");
        for (String smember : smembers) {
            System.out.print(smember + " ");
        }

//        jedis.s
        System.exit(0);
    }

}