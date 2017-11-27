package com.holo.service.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class MinaServer {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	

	/**
	 * 初始化
	 * @return
	 * @throws Exception
	 */
	
	public void listen() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("MINA服务器开始监听"+8888);
		System.err.println("MINA服务器开始监听"+8888);
		// 服务器端的主要对象
		IoAcceptor acceptor = new NioSocketAcceptor();
		
		// 设置Filter链 
		//acceptor.getFilterChain().addLast("logger", new LoggingFilter());
	
		if(Integer.valueOf(1).equals(0)){
			//没有协议就是byte[]
		}else{
			String chset="UTF-8";
			// 协议解析，采用mina现成的UTF-8字符串处理方式，标准文本编码解码器，如果要转成对象就要使用自定义编码解码器
			acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName(chset))));
		}
	
		// 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
		acceptor.setHandler(new MinaServerHander()); ////服务器接收信息后的处理器
		//acceptor.setHandler( ); ////服务器接收信息后的处理器
		// 设置接收缓存区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
		try {
			// 服务器开始监听
			acceptor.bind( new InetSocketAddress(8888) );
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		new MinaServer().listen();
	}
}
