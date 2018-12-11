package redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redis->Hash数据类型测试
 *
 * @author dell
 * @version 1.0.0
 * @create 2018-12-11 16:22
 */
public class HashDataTypeTest {

    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedisResource();
        Map<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("name", "wude");

        // hset命令设置hash的一个field, 如果参数是Map则必须保证keySet长度是1
//        Long hsetResult = jedis.hset("user", userInfoMap);
//        System.out.println("Hash.hset Reulst is: " + hsetResult);

        userInfoMap.put("age", "30");
        userInfoMap.put("company", "voyageone");
        // hmset设置hash, 返回OK
        String hmsetResult = jedis.hmset("user", userInfoMap);
        System.out.println(hmsetResult);


        String name = jedis.hget("user", "name");
        System.out.println("hget name=" + name);

        List<String> userFields = jedis.hmget("user", "name", "age", "company");
        userFields.forEach(field -> {
            System.out.println("hmget -->" + field);
        });

        System.exit(0);

    }
}