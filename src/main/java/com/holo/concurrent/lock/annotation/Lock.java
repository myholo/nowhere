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
 * 
 * 自定义锁注解
 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
 * @Target(ElementType.FIELD) //字段、枚举的常量
 * @Target(ElementType.METHOD) //方法
 * @Target(ElementType.PARAMETER) //方法参数
 * @Target(ElementType.CONSTRUCTOR)  //构造函数
 * @Target(ElementType.LOCAL_VARIABLE)//局部变量
 * @Target(ElementType.ANNOTATION_TYPE)//注解
 * @Target(ElementType.PACKAGE) ///包    
 * @Document：说明该注解将被包含在javadoc中
 * @Inherited：说明子类可以继承父类中的该注解
 * @author Holo
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Lock {
	/**
     * 锁名,支持表达式,表达式使用 ${} 进行标识;如果此值为空,则使用方法名称作为锁名
     * e.g.
     * <pre>
     *     &#064;Lock("my_lock_${#id}")
     *     public void foo(String id){
     *
     *     }
     *
     *     &#064;Lock(value="my_lock_${#id}",condition="#id!=null")
     *     public void foo(String id){
     *
     *     }
     * </pre>
     *
     * @return 锁名称, 支持spel表达式
     */
    String[] value() default {};

    /**
     * 锁的条件表达式,当满足条件的时候才执行锁
     * e.g.
     * <pre>
     *     &#064;Lock(value="my_lock_${#id}",condition="#id!=null")
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
