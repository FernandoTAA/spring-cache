package com.github.fernandotaa.springcache.cache;

import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;

public class TtlDurationCacheManager extends RedisCacheManager {

    public TtlDurationCacheManager(
        RedisCacheWriter cacheWriter,
        RedisCacheConfiguration defaultCacheConfiguration,
        Duration ttlDuration) {
        super(cacheWriter, defaultCacheConfiguration.entryTtl(ttlDuration));
    }

}