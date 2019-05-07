package com.demo.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Shiro-Redis缓存管理器
 *
 * @Author wude
 * @Create 2019-05-07 17:28
 */
public class ShiroRedisCacheManager implements CacheManager {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisTemplate.opsForValue().get(s);
    }
}