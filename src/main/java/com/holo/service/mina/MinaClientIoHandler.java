package com.holo.service.mina;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 
 * @author Holo
 * 2017年9月26日
 */
public class MinaClientIoHandler extends IoHandlerAdapter{
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 关闭连接
	 * @param session
	 * @throws Exception
	 */
	private void releaseSession(IoSession session) throws Exception {
		if (logger.isDebugEnabled())logger.debug("关闭Session"+"["+session.getId()+"]");
		if (session.isConnected()) {
			session.close(false);
		}		
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		if (logger.isDebugEnabled())logger.debug("新建连接创建,session.getId()="+session.getId());
	};
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		if (logger.isDebugEnabled())logger.debug("新建连接打开,session.getId()="+session.getId());

	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if (logger.isDebugEnabled())logger.debug("连接已经关闭,session.getId()="+session.getId());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		if (logger.isDebugEnabled())logger.debug("空闲 sessionIdle,session.getId()="+session.getId());
		/*if(Integer.valueOf(1).equals(usecase.getReleasetype()))
			releaseSession(session);*/
	}

	/**
	 * 如果收到消息，转成相应格式后，到协议数据pdata表中查找'protocol_id=当前协议id and dbindata=消息'的记录，
	 * 找到ID倒序的第一条，把里面的数据内容发出去。
	 * 发送次数：此协议不开新线程，在当前连接中，
	 * 按usecase.sloopnums个循环，每个循环间隔steploop毫秒，把dbdata，一行行的发出去。
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		//当时发送消息的时间
		if (logger.isDebugEnabled())logger.debug("收到消息,session.getId()="+session.getId()+", msg="+ message);
		//消息
		String msg = message.toString();
		if("i miss you".equals(msg)){
			session.write("i miss you too");
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		if (logger.isDebugEnabled())logger.debug("出错,exceptionCaught");
		cause.printStackTrace();
		//releaseSession(session);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {

		if (logger.isDebugEnabled())logger.debug("发送消息,session.getId()="+session.getId()+",msg="+message.toString());
		
	}
}
