package redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Redis->SortedSet数据类型测试
 *
 * @author dell
 * @version 1.0.0
 * @create 2018-12-12 14:15
 */
public class SortedSetDataTypeTest {

    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedisResource();

//        Map<String, Double> valueScoreMap = new HashMap<>();
//        valueScoreMap.put("a", 1D);
//        valueScoreMap.put("b", 2D);
//        valueScoreMap.put("c", 3D);
//        Long zaddResult = jedis.zadd("scores", valueScoreMap);
//        System.out.println("add Result=" + zaddResult); // 返回的SortedSets的长度

        Set<String> sets = jedis.zrange("scores", 0, 1);
        sets.forEach(System.out::println);

        JedisPoolUtil.closeJedisResouce(jedis);
        System.exit(0);


    }
}