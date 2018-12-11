package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * Redis->String数据类型测试
 *
 * @author dell
 * @version 1.0.0
 * @create 2018-12-11 15:50
 */
public class StringDataTypeTest {

    public static void main(String[] args) {
        Jedis jedis = JedisPoolUtil.getJedisResource();
        SetParams setParams = new SetParams();
//        setParams.ex(30); // 过期秒数
        setParams.px(60000); // 过期毫秒数
//        String result = jedis.set("name", "wude", setParams);
//        System.out.println(result); // result=OK

        String name = jedis.get("name");
        System.out.println(name); // 当key不存在或者过期时返回null

        Boolean nameExists = jedis.exists("name");
        System.out.println(nameExists);


        // key无则设置并返回1, 有则跳过返回去0
        Long setnxReulst = jedis.setnx("name", "wude");
        System.out.println(setnxReulst);

        // 设置过期
        jedis.expire("name", 30);
//        jedis.pexpire("name", 30000);

        Long ttl = jedis.ttl("name");
        System.out.println("ttl = " + ttl);
        
        JedisPoolUtil.closeJedisResouce(jedis);
        System.exit(0);
    }
}