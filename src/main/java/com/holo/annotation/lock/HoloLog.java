package com.holo.annotation.lock;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 * @author Holo
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HoloLog {
    String value();
}
