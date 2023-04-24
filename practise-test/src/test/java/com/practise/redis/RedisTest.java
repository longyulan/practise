package com.practise.redis;

import com.practise.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author longyulan
 * @date 2023/4/24
 */
@Slf4j
public class RedisTest extends BaseTest {

    @Resource
    private RedisRepo redisRepo;

    @Test
    public void testPut() {
        redisRepo.put("test","testvalue");
    }

    @Test
    public void testGet() {
        log.info(redisRepo.get("test", String.class));
    }
}
