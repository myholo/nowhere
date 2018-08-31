package com.holo.concurrent.redis;

import com.holo.concurrent.AbstractLockManager;
import org.redisson.api.RedissonClient;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/29 15:38
 * 4
 */
public class RedissonLockManager extends AbstractLockManager{
    //分布式锁
    private RedissonClient redisson;

    public RedissonLockManager(RedissonClient redisson) {
        if (null == redisson) {
            throw new NullPointerException();
        }
        this.redisson = redisson;
    }

    @Override
    protected Lock createLock(String lockName) {
        return redisson.getFairLock(lockName);
    }

    @Override
    protected ReadWriteLock createReadWriteLock(String lockName) {
        return redisson.getReadWriteLock(lockName);
    }
}
