package com.holo.concurrent.test;

import com.holo.concurrent.LockManager;
import com.holo.concurrent.SimpleLockManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.function.Consumer;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/29 15:07
 * 4
 */
public class SimpleLockTests {
    private LockManager lockManager = new SimpleLockManager();

    private long counter = 0;

    @Test
    public void testLock() throws InterruptedException {
        counter = 0;
        Lock lock = lockManager.getLock("foo");
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                lock.lock();
                for (int i1 = 0; i1 < 100; i1++) {
                    counter++;
                }
               lock.unlock();
            }).start();
        }
        Thread.sleep(1000);
        System.err.print(counter);
        Assert.assertEquals(counter, 100 * 100);
    }
    //读写锁 非公平竞争
    @Test
    public void testReadWriteLock() throws InterruptedException {
        counter = 0;
        ReadWriteLock readWriteLock = lockManager.getReadWriteLock("foo");

        Lock readLock = readWriteLock.readLock();

        Lock writeLock = readWriteLock.writeLock();
        Consumer<Long>[] consumer = new Consumer[1];
        consumer[0] = System.out::println;
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                    writeLock.lock();
                    long tmp = ++counter;

                    //判断增加的值与 读取的值一致
                    consumer[0] = l -> Assert.assertEquals(Long.valueOf(tmp), l);
                    System.out.println("write:" + counter);
                    writeLock.unlock();
                }
            }).start();
            new Thread(() -> {
                for (int i1 = 0; i1 < 10; i1++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                    readLock.lock();
                    consumer[0].accept(counter);
                    System.out.println("read:" + counter);
                    readLock.unlock();
                }
            }).start();
        }
        Thread.sleep(5000);
    }
}
