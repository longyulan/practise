package com.practise.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redisson锁
 * 1,redisson锁在集群环境中要一半以上的节点都加锁成功才返回成功
 * 2,有自动锁续期(不设置锁过期时间leaseTime就会开起看门狗机制，看门狗机制会自动设置一个30s的过期时间，预防down机的场景),每10秒查看一下自己的任务是否执行完毕，未执行结束则执行lua脚本延长锁时间
 * 3,redisson是通过lua加锁脚本实现可重入的
 * @author longyulan
 * @date 2023/6/19
 */
@Component
public class RedisLockUtils {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取到锁并且执行任务
     * @param prefix 锁键前缀
     * @param key 锁键
     * @param runnable 任务
     */
    public void lock(String prefix, String key, Runnable runnable) {
        String lockKey = prefix + key;
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        try {
            runnable.run();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }
}
