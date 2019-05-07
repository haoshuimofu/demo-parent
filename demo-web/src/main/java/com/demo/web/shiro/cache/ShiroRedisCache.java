package com.demo.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

/**
 * Shiro-Redis-Cache
 *
 * @Author ddmc
 * @Create 2019-05-07 18:59
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private RedisTemplate redisTemplate;

    public ShiroRedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        Assert.notNull(this.redisTemplate, ShiroRedisCache.class.getName() +"'s redisTemplate must not be null.");
    }

    @Override
    public V get(K k) throws CacheException {
        return (V) redisTemplate.opsForValue().get(k);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return (V) redisTemplate.opsForValue().getAndSet(k, v);
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = (V) redisTemplate.opsForValue().get(k);
        redisTemplate.delete(k);
        return v;
    }

    /**
     * 清除缓存
     * @throws CacheException
     */
    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}