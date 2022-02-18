package com.github.fernandotaa.springcache.service;

import com.github.fernandotaa.springcache.entity.Data;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class RandomService {

    private final Random ramdom = new Random();

    @Cacheable(value = "DATA", key = "\"[]\"", cacheManager = "oneMinuteCacheManager")
    public Data getData() {
        return Data.builder()
            .timestamp(LocalDateTime.now())
            .number(ramdom.nextInt())
            .build();
    }

}
