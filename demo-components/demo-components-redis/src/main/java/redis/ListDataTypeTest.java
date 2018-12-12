package redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Redis->Lists数据类型测试
 *
 * @author dell
 * @version 1.0.0
 * @create 2018-12-11 17:03
 */
public class ListDataTypeTest {

    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedisResource();

        // lpush从左向List添加元素, 返回是元素个数(List.size())
//        Long lpushResult = jedis.lpush("members", "zhangsan", "lisi");
//        System.out.println("lpush Result=" + lpushResult); // lpush Result=2
//        lpushResult = jedis.lpush("members", "wangwu");
//        System.out.println("lpush Result=" + lpushResult); // lpush Result=3

        List<String> members = jedis.lrange("members", 0, 3);
//        System.out.println("*******lrange*******");
//        members.forEach(System.out::println);
//        System.out.println("*******lrange*******");

//        jedis.rpush("members", "sunliu");
//        members = jedis.lrange("members", 0, 5);
        System.out.println("*******lrange*******");
        members.forEach(System.out::println);
        System.out.println("*******lrange*******");

//        String lpopResult = jedis.lpop("members");
//        System.out.println("lpop Result=" + lpopResult);

        System.exit(0);
    }
}