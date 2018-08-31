/**
 * 
 */
package com.holo.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * 锁工厂 用于创建锁
 * @author Holo
 */
public interface LockManager {
	
	/**
	 *  根据锁名获取锁
	 * @param lockName
	 * @return
	 */
	Lock getLock(String lockName);
	
	/**
	 *  根据锁名称获取读写锁,相同的名称,则锁也相同
	 * @param lockName
	 * @return
	 */
	ReadWriteLock getReadWriteLock(String lockName);

}
