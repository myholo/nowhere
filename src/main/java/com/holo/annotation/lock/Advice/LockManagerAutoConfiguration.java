package com.holo.annotation.lock.Advice;

import com.holo.concurrent.LockManager;
import com.holo.concurrent.SimpleLockManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2 * @Author: Holo
 * 3 * @Date: 2018/8/31 16:02
 * 4
 */
@Configuration
public class LockManagerAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(LockManager.class)
    public LockManager lockManager() {
        return new SimpleLockManager();
    }

    @Bean
    public LockAopAdvisor aopLockAdvisor(LockManager lockManager) {
        return new LockAopAdvisor(lockManager);
    }
}
