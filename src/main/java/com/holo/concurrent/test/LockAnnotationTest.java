package com.holo.concurrent.test;

import com.holo.App;
import com.holo.concurrent.LockManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/30 17:31
 * 4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LockAnnotationTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private LockService lockService;

   /* @Autowired
    private LockManager lockManager;*/

    @Test
    public void testLock() throws InterruptedException {
        new Thread(() -> {
            System.out.println("锁住");
            lockService.checkLockSleep("test", 2000);
            System.out.println("解锁");
        }).start();
        Thread.sleep(200);
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
