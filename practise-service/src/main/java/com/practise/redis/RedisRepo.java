package com.practise.redis;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入String类型
     * (可写入jsonString)
     * @param key
     * @param value
     */
    public Boolean setStr(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
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
     * 写入到list集合类型(一个key，对应一个List集合value，写入值可以一次写入多个，读可以一次读多个，也可以只读一个)
     * 无序
     * @param key
     * @param o
     * @return
     */
    public Long lPush(String key, Object... o) {
        return redisTemplate.opsForList().leftPushAll(key, o);
    }

    /**
     * 取指定位置范围的数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public <T> List<T> lRange(String key, Integer start, Integer end, Class<T> tClass) {
        List<Object> list = redisTemplate.opsForList().range(key, start, end);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(o -> JSON.parseObject(JSON.toJSONString(o), tClass)).collect(Collectors.toList());
    }

    /**
     * 获取list集合类型数据 先进先出
     * @param key
     * @return
     */
    public <T> T rPop(String key, Class<T> tClass) {
        Object o = redisTemplate.opsForList().rightPop(key);
        if (o == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(o), tClass);
    }

    /**
     * 获取list集合类型数据 先进后出
     * @param key
     * @return
     */
    public <T> T lPop(String key, Class<T> tClass) {
        Object o = redisTemplate.opsForList().leftPop(key);
        if (o == null) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(o), tClass);
    }

    /**
     * 写入hash 多用于写入对象
     * @param key
     * @param map
     */
    public <T> void setHash(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Map<Object, Object> getHash(String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        return entries;
    }

    /**
     * 写入到set类型 无序
     * @param key
     * @param o
     * @return
     */
    public Long hSet(String key, Object... o) {
        return redisTemplate.opsForSet().add(key, o);
    }

    /**
     * 写入到zSet类型 有序
     * @param key
     * @param set
     * @return
     */
    public Long zSet(String key, Set<ZSetOperations.TypedTuple<Object>> set) {
        return redisTemplate.opsForZSet().add(key, set);
    }

    /**
     * 按下标范围读取zSet数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set zRange(String key, Integer start, Integer end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

}
