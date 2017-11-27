package com.holo.service.mina;

import java.util.Date;

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
public class MinaServerHander extends IoHandlerAdapter {
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	/**连接数*/
	int count=0;
	
	/**
	 * 关闭连接
	 * @param session
	 * @throws Exception
	 */
	private void releaseSession(IoSession session) throws Exception {
		if (logger.isDebugEnabled())logger.debug("关闭releaseSession"+"["+session.getId()+"]");
		if (session.isConnected()) {
			session.close(false);
		}		
	}
	/**
	 * 有错
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		if (logger.isDebugEnabled())logger.debug("出错连接"+"["+session.getId()+"]");
		cause.printStackTrace();
	}
	
	public void sessionCreated(IoSession session) {
		if (logger.isDebugEnabled())logger.debug("新客户端连接"+"["+session.getId()+"]"+"ip="+session.getRemoteAddress());
	}
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		count++;
		if (logger.isDebugEnabled())
			logger.debug("新建连接打开 "+"["+session.getId()+"]" +",第"+ count +"次Open,["+session.getRemoteAddress()+"]");		
	}
    /*
     * 这个方法是目前这个类里最主要的，
     * 如果收到消息，转成相应格式后，到协议数据pdata表中查找'protocol_id=当前协议id and dbindata=消息'的记录，
     * 找到ID倒序的第一条，把里面的数据内容发出去。
	 * 发送次数：此协议不开新线程，在当前连接中，按usecase.sloopnums个循环，
     * 每个循环间隔steploop毫秒，把dbdata，一行行的发出去。
     * 如果是quit，则关闭客户端连接，*/
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		if (logger.isDebugEnabled())
			logger.debug("收到消息"+"["+session.getId()+"]"+",msg=" + msg +",ip="+session.getRemoteAddress()+"");
		if("i miss you".equals(msg)){
			session.write("i miss you too");
		}
		//应答
		//如果是byte[]则转成16进制
		
		super.messageReceived(session, message);

	}
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		if (logger.isDebugEnabled())logger.debug("消息已发"+"["+session.getId()+"]"+",msg=" + message.toString());
		super.messageSent(session, message);
	}
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("客户端断开连接"+"["+session.getId()+"]");
		super.sessionClosed(session);
	}
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("服务端sessionIdle"+"["+session.getId()+"]");
		/*if(Integer.valueOf(1).equals(usecase.getReleasetype()))
			releaseSession(session);*/
    }    
}
