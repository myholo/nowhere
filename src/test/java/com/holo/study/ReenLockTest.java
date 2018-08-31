package com.holo.study;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  重入锁
 */
public class ReenLockTest implements  Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
           lock.lock();
            //lock.lock(); ①
            try {
                i++;
            } finally {
                lock.unlock();
                //lock.unlock();②
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenLockTest test = new ReenLockTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();t2.start();
        t1.join(); t2.join(); // main线程会等待t1和t2都运行完再执行以后的流程
        System.err.println(i);

    }
}

