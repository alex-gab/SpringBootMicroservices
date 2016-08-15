package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class CacheConfig {

    public RedisCacheManager cacheManager(final StringRedisTemplate template) {
        return new RedisCacheManager(template);
    }
}
