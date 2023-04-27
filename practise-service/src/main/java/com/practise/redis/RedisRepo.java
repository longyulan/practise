package com.practise.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author longyulan
 * @date 2023/4/23
 */
@Component
public class RedisRepo {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void put(String key, String value) {
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps(key);
        boundValueOperations.set(value);
    }

    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public <T> T get(String key, Class<T> tClass) {
        String str = stringRedisTemplate.opsForValue().get(key);
        return JSON.parseObject(str, tClass);
    }
}
