package com.practise.redis;

import com.practise.BaseTest;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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
    public void setStr() {
        redisRepo.setStr("name", "lyl");
    }

    @Test
    public void getStr() {
        String name = redisRepo.getStr("name");
        log.info("name: ", name);
        Assert.assertNotNull(name);
    }

    @Test
    public void lPush() {
        redisRepo.lPush("users", User.builder().name("lyl").build(), User.builder().name("zy").build());
    }

    @Test
    public void rPop() {
        User u = (User)redisRepo.rPop("users");
        Assert.assertEquals("lyl", u.getName());
    }

    @Test
    public void lPop() {
        User u = (User)redisRepo.lPop("users");
        Assert.assertEquals("zy", u.getName());
    }

    @Test
    public void setHash() {
        redisRepo.setHash("user", User.builder().name("lyl").build());
    }

    @Test
    public void hSet() {
    }

    @Test
    public void zSet() {
    }

    @Data
    @Builder
    public static class User {
        private String name;
        private Integer age;
    }
}
