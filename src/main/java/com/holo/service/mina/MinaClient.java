package com.holo.service.mina;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;



public class MinaClient {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	static int CONNECT_TIMEOUT = 10000;
	
	private SocketConnector connector;
	private	IoSession session;
	private ConnectFuture cf;
	
	
	public MinaClient(){
		init();
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		//创建一个socket连接 
		System.err.println("init");
		connector = new NioSocketConnector();
		//长连接
		connector.getSessionConfig().setKeepAlive(true);
		//设置链接超时时间 
		connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
			
		String chset="UTF-8";
		//添加协议解析，采用mina现成的UTF-8字符串处理方式，标准文本编码解码器，如果要转成对象就要使用自定义编码解码器 没有协议就是byte[]
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName(chset))));
		
		//消息核心处理器
		connector.setHandler(new MinaClientIoHandler());
	}
	
	/**
	 * 连接
	 */
	public void conn(){
		// 连接服务器，端口、地址      
		cf = connector.connect(new InetSocketAddress("127.0.0.1", 8888));  
		// 等待连接创建完成      
		cf.awaitUninterruptibly();  
		session=cf.getSession();
		if (logger.isDebugEnabled())
			logger.debug("客户端创建连接"+"["+session.getId()+"]");

		//session.getCloseFuture().awaitUninterruptibly();  
		//connector.dispose();  		
	}
	/**
	 * 发送
	 */
	public void send() {
		String msg="i miss you";
		if(session==null){
			logger.error("session==null");
			return;
		}
		if(Integer.valueOf(1).equals(0)){
			IoBuffer buffer = IoBuffer.allocate(20); 
			// 自动扩容  
			buffer.setAutoExpand(true);  
			// 自动收缩  
			buffer.setAutoShrink(true);  
			//buffer.put(StringUtilz.hexStringToBytes(msg));  
			buffer.flip();  
			session.write(buffer);  
		}else{
			session.write(msg);	
		}
	}
	/**
	 * 关闭
	 */
	public void close(){
		connector.dispose();
		if (session.isConnected()) {
			session.close(false);
		}
	}
	public static void main(String[] args) {
		MinaClient client = new MinaClient();
		client.conn();
		client.send();
	}
}
