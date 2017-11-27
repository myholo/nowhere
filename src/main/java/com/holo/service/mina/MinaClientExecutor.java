package com.holo.service.mina;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holo.domain.my.Usecase;


public class MinaClientExecutor {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	public void run(){
		try {
		    ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		    //工厂类 Executor 提供三种类型的ThreadPoolExecutor类
		    //newCachedThreadPool 是大小无界的线程池 适合用于执行很多的短期异步任务小程序 或者是负载较轻的服务器
		    //newFixedThreadPool  创建固定线程数的适用负载较重的服务器
		    //newSingleThreadExecutor 单线程 保证不会有多线程活动。意义何在？
		    Usecase usecase = new Usecase(
		    		"" , //String 标题   
		    		1 , //Integer 运行状态 default=0  {'0':'未运行','1':'正在运行'}
		    		1 , //Integer 设备ID   
		    		1 , //Integer 数据ID   
		    		1 , //Integer 所在服务器节点ID   
		    		"192.168.1.16" , //String 发送服务器地址  http是url地址，tcp是ip地址 
		    		8888 , //Integer 服务器端口  http用不到，tcp要用到，如果是tcpclient是对方端口，tcpserver是自己端口 
		    		5 , //Integer 线程数 default=1  
		    		5 , //Integer 循环次数 default=1 0表示无限循环 
		    		100 , //Integer 循环间隔毫秒 default=1000  
		    		0 , //Integer 空闲关闭连接 default=1  {'0':'否','1':'是'}
		    		null
		    	);
		    for (int threadindex = 0; threadindex < usecase.getSthreadnums(); threadindex++){
		    	//一个任务通过 execute(Runnable)方法被添加到线程池
		    	System.err.println("xxx");
	            executor.execute(new MinaClientTask1(usecase));           
	        }
		    if(logger.isDebugEnabled())
		    	logger.debug("executor.shutdown");
				
		    executor.shutdown();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
