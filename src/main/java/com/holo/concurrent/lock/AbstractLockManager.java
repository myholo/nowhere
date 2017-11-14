/**
 * 
 */
package com.holo.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author Holo
 *
 */
public abstract class AbstractLockManager implements LockManager {

	private final Map<String, Lock> lockStore = new HashMap<>(128);
	private final Map<String, ReadWriteLock> readWriteLockStore = new HashMap<>(
			128);

	@Override
	public Lock getLock(String lockName) {
		Lock lock = lockStore.get(lockName);
		if (lock != null) {
			return lock;
		}
		synchronized (lockStore) {
			//在JAVA8的Map接口中，增加了一个方法computeIfAbsent
			//此方法首先判断缓存MAP中是否存在指定key的值，如果不存在，会自动调用mappingFunction(key)计算key的value，
			//然后将key = value放入到缓存Map,java8会使用thread-safe的方式从cache中存取记录。
			//因为函数式接口的方法参数对应于隐式方法调用时的参数，所以被引用方法签名可以通过放宽类型，
			//装箱以及组织到参数数组中的方式对其参数进行操作，就像在调用实际方法一样：
			// :: 方法引用
			return lockStore.computeIfAbsent(lockName, this::createLock);
		}
		
	}

	@Override
	public ReadWriteLock getReadWriteLock(String lockName) {
		ReadWriteLock lock = readWriteLockStore.get(lockName);
		if (lock != null) {
			return lock;
		}
		synchronized (readWriteLockStore) {
			return readWriteLockStore.computeIfAbsent(lockName,
					this::createReadWriteLock);
		}
	}

	protected abstract Lock createLock(String lockName);

	protected abstract ReadWriteLock createReadWriteLock(String lockName);

}
