/**
 * 
 */
package com.holo.concurrent.test;

import com.holo.concurrent.annotation.Lock;
import org.springframework.stereotype.Service;

import com.holo.concurrent.annotation.ReadLock;
import com.holo.concurrent.annotation.WriteLock;



/**
 * @author Holo
 *
 */
/**
 * TODO 完成注释
 *
 * @author zhouhao
 */
public class LockService {

    private long counter = 0;

    @Lock("lock_${#key}")
    public long checkLockSleep(String key, long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return counter;
    }

    @Lock("lock_${#key}")
    public long testLock(String key) {
        return counter++;
    }

    @ReadLock("lock_${#key}")
    public long testReadLock(String key) {
        return counter++;
    }

    @WriteLock("lock_${#key}")
    public long testWriteLock(String key) {
        return counter++;
    }

    public long getCounter() {
        return counter;
    }

    public void reset() {
        counter = 0;
    }
}