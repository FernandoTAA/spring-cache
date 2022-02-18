package com.github.fernandotaa.springcache.config;

import com.github.fernandotaa.springcache.cache.TtlDurationCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
public class RedisCacheConfig {

    @Bean
    @Primary
    public RedisCacheConfiguration redisCacheConfiguration(
        @Qualifier(value = "stringRedisSerializer") RedisSerializer<String> stringRedisSerializer,
        @Qualifier(value = "jackson2JsonRedisSerializer") RedisSerializer<Object> jackson2JsonRedisSerializer) {
        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(fromSerializer(stringRedisSerializer))
            .serializeValuesWith(fromSerializer(jackson2JsonRedisSerializer));
    }

    @Bean
    public RedisCacheWriter redisCacheWriter(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
    }

    @Bean
    @Primary
    public CacheManager cacheManager(
        RedisCacheWriter redisCacheWriter,
        RedisCacheConfiguration redisCacheConfiguration) {
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    @Bean
    public CacheManager oneMinuteCacheManager(
        RedisCacheWriter cacheWriter,
        RedisCacheConfiguration cacheConfiguration) {
        return new TtlDurationCacheManager(cacheWriter, cacheConfiguration, Duration.ofMinutes(1));
    }

}
