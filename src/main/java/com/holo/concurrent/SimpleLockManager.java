/**
 * 
 */
package com.holo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * JDK 方式实现
 * @author Holo
 *
 */
public class SimpleLockManager extends AbstractLockManager {
    @Override
    protected Lock createLock(String lockName) {
        return new ReentrantLock();
    }

    @Override
    protected ReadWriteLock createReadWriteLock(String lockName) {
        return new ReentrantReadWriteLock(true);
    }
}
