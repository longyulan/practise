package com.practise.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis操作类
 *
 * @author longyulan
 * @date 2023/4/23
 */
@Slf4j
@Component
public class RedisRepo {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 写入String类型
     * (可写入jsonString)
     * @param key
     * @param value
     */
    public void setStr(String key, String value) {
        redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public static void main(String[] args) {
        RedisTemplate r = new RedisTemplate();
        r.opsForValue().setIfAbsent("name", "lyl");
        log.info("name:", r.opsForValue().get("name"));
    }

    /**
     * 获取String类型数据
     * @param key
     * @return
     */
    public String getStr(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入到list集合类型 无序
     * @param key
     * @param o
     * @return
     */
    public boolean lPush(String key, Object... o) {
        Long count = redisTemplate.opsForList().leftPush(key, o);
        return count > 0;
    }

    /**
     * 获取list集合类型数据 先进先出
     * @param key
     * @return
     */
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 获取list集合类型数据 先进后出
     * @param key
     * @return
     */
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 写入hash 多用于写入对象
     * @param key
     * @param t
     */
    public <T> void setHash(String key, T t) {
        redisTemplate.opsForHash().putAll(key, JSON.parseObject(JSON.toJSONString(t), Map.class));
    }

    /**
     * 写入到set类型 无序
     * @param key
     * @param o
     * @return
     */
    public boolean hSet(String key, Object... o) {
        Long count = redisTemplate.opsForSet().add(key, o);
        return count > 0;
    }

    /**
     * 写入到zSet类型 有序
     * @param key
     * @param collection
     * @return
     */
    public <T> boolean zSet(String key, Collection<T> collection) {
        Set<T> set = new HashSet<T>(collection);
        Long count = redisTemplate.opsForZSet().add(key, set);
        return count > 0;
    }

}
