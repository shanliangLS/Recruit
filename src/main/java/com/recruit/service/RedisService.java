package com.recruit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, 60 * 5, TimeUnit.SECONDS);
    }

    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delObject(String key) {
        redisTemplate.opsForValue().set(key, "0", 5, TimeUnit.SECONDS);
    }
}
