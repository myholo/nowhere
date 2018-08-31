package com.holo.controller;

import com.holo.concurrent.LockManager;
import com.holo.concurrent.SimpleLockManager;
import com.holo.concurrent.test.LockService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/17 15:43
 * 4
 */
@RestController
@RequestMapping(value="/rest")
public class SpringLockController {

    @Autowired
    private LockService lockService;

    private LockManager lockManager = new SimpleLockManager();

    @RequestMapping(value="/lock",method= RequestMethod.GET)
    public void holoLock() throws InterruptedException{
        new Thread(() -> {
            try {
                System.out.println("锁住");
                lockManager.getLock("lock_test").lock();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("解锁");
            lockManager.getLock("lock_test").unlock();
        }).start();
        Thread.sleep(2000);
        System.out.println("开始任务1");
        lockService.testLock("test");
        System.out.println("任务1结束");
        for (int i = 0; i < 100; i++) {
            new Thread(() -> lockService.testLock("test")).start();
        }
        Thread.sleep(5000);
        Assert.assertEquals(lockService.getCounter(), 101);

        lockService.reset();
    }
}
