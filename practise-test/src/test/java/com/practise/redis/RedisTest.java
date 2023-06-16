package com.practise.redis;

import com.practise.BaseTest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        log.info("name: {}", name);
        Assert.assertNotNull(name);
    }

    @Test
    public void lPush() {
        Long count = redisRepo.lPush("users", User.builder().name("lyl").build(), User.builder().name("zy").build());
        Assert.assertTrue(count > 0);
    }

    @Test
    public void rPop() {
        User u = redisRepo.rPop("users", User.class);
        log.info("list rPop:{}", u);
        Assert.assertEquals("lyl", u.getName());
    }

    @Test
    public void lPop() {
        User u = redisRepo.lPop("users", User.class);
        log.info("list lPop:{}", u);
        Assert.assertEquals("zy", u.getName());
    }

    @Test
    public void lRange() {
        List<User> users = redisRepo.lRange("users", 0, Integer.MAX_VALUE, User.class);
        Assert.assertEquals("zy", users.get(0).getName());
        Assert.assertEquals("lyl", users.get(1).getName());
    }

    @Test
    public void setHash() {
        Map<String, User> map = new HashMap<>();
        map.put("userMaps", User.builder().name("lyl").build());
        redisRepo.setHash("user", map);
    }

    @Test
    public void hSet() {
        Long count = redisRepo.hSet("users", User.builder().name("lyl").build(), User.builder().name("zy").build());
        Assert.assertTrue(count == 2);
    }

    @Test
    public void zSet() {
    }

    @Test
    public void zRange() {
        Set users = redisRepo.zRange("users", 0, Integer.MAX_VALUE);
        Assert.assertNotNull(users);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User implements Serializable {
        private static final long serialVersionUID = -8658561148292296167L;
        private String name;
        private Integer age;

    }
}
