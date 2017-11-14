/**
 * 
 */
package com.holo.concurrent.lock.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 写锁注解,在方法上注解,则对此方法加锁
 * 满足条件才锁
 * e.g.
 * <pre>
 *     &#064;WriteLock(value="my_lock_${#id}",condition="#id!=null")
 *     public void foo(String id){
 *
 *     }
 * </pre>
 *  * @author Holo
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface WriteLock {
    /**
     * 锁名,支持表达式,表达式使用 ${} 进行标识;如果此值为空,则使用方法名称作为锁名
     * e.g.
     * <pre>
     *     &#064;ReadLock("my_lock_${#id}")
     *     public void foo(String id){
     *
     *     }
     * </pre>
     *
     * @return 锁名称, 支持spel表达式
     * @see org.hswebframework.web.concurrent.lock.LockManager#getReadWriteLock(String)
     */
    String[] value() default {};

    /**
     * 锁的条件表达式,当满足条件的时候才执行锁
     * e.g.
     * <pre>
     *     &#064;WriteLock(value="my_lock_${#id}",condition="#id!=null")
     *     public void foo(String id){
     *
     *     }
     * </pre>
     *
     * @return 条件表达式
     */
    String condition() default "";

    /**
     * 超时时间,超过此时间不能获取锁则抛出异常{@link InterruptedException},如果设置为-1,则认为不设置超时时间
     *
     * @return 超时时间, 默认10秒
     */
    long timeout() default 10;

    /**
     * @return 超时时间单位, 秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}