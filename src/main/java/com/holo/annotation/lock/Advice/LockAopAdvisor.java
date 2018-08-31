package com.holo.annotation.lock.Advice;

import com.holo.aop.MethodInterceptorHolder;
import com.holo.concurrent.LockManager;
import com.holo.concurrent.annotation.Lock;
import com.holo.concurrent.annotation.ReadLock;
import com.holo.concurrent.annotation.WriteLock;
import com.holo.web.util.AopUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/31 14:59
 *   锁注解解释器
 * 4
 */
@Slf4j
public class LockAopAdvisor extends StaticMethodMatcherPointcutAdvisor {

    public LockAopAdvisor(LockManager lockManager) {
        Objects.requireNonNull(lockManager);
        setAdvice((MethodInterceptor) methodInvocation -> {
            MethodInterceptorHolder holder = MethodInterceptorHolder.create(methodInvocation);
            Lock lockAnn = holder.findMethodAnnotation(Lock.class);
            ReadLock readLockAnn = holder.findMethodAnnotation(ReadLock.class);
            WriteLock writeLock = holder.findMethodAnnotation(WriteLock.class);
            List<LockProcessor> lockProcessors = new ArrayList<>();
            if (null != lockAnn) {
                lockProcessors.add(initLockInfo(lockAnn.timeout(), lockAnn.timeUnit(),
                        LockProcessor.<Lock, java.util.concurrent.locks.Lock>build(lockAnn, holder)
                                .lockNameIs(Lock::value)
                                .lockIs(lockManager::getLock)));
            }
            if (null != readLockAnn) {
                lockProcessors.add(initLockInfo(readLockAnn.timeout(), readLockAnn.timeUnit(),
                        LockProcessor.<ReadLock, java.util.concurrent.locks.Lock>build(readLockAnn, holder)
                                .lockNameIs(ReadLock::value)
                                .lockIs(name -> lockManager.getReadWriteLock(name).readLock())));
            }
            if (null != writeLock) {
                lockProcessors.add(initLockInfo(writeLock.timeout(), writeLock.timeUnit(),
                        LockProcessor.<WriteLock, java.util.concurrent.locks.Lock>build(writeLock, holder)
                                .lockNameIs(WriteLock::value)
                                .lockIs(name -> lockManager.getReadWriteLock(name).writeLock())));
            }
            boolean lockError = false;
            try {
                for (LockProcessor processor : lockProcessors) {
                    Throwable e = processor.doLock();
                    if (e != null) {
                        lockError = true;
                        throw e;
                    }
                }
                return methodInvocation.proceed();
            } finally {
                for (LockProcessor processor : lockProcessors) {
                    try {
                        processor.doUnlock();
                    } catch (Exception e) {
                        if (!lockError) {
                            log.error("unlock {} error", methodInvocation.getMethod(), e);
                        }
                    }
                }
            }
        });
    }

    protected <A extends Annotation> LockProcessor<A, java.util.concurrent.locks.Lock> initLockInfo(long timeout, TimeUnit timeUnit, LockProcessor<A, java.util.concurrent.locks.Lock> lockProcessor) {
        return lockProcessor
                .lock(lock -> lock.tryLock(timeout, timeUnit))
                .unlock(lock -> {
                    lock.unlock();
                    return true;
                }).init();
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        Lock lock = AopUtils.findMethodAnnotation(aClass, method, Lock.class);
        if (null != lock) {
            return true;
        }
        ReadLock readLock = AopUtils.findMethodAnnotation(aClass, method, ReadLock.class);
        if (null != readLock) {
            return true;
        }
        WriteLock writeLock = AopUtils.findMethodAnnotation(aClass, method, WriteLock.class);
        if (null != writeLock) {
            return true;
        }
        return false;
    }
}
