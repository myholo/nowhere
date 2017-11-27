package com.holo.service.spec;

import com.holo.service.mina.MinaClientExecutor;


/**
 *  mina模拟多个客户端与服务端进行交互
 * @author Holo
 * 2017年9月16日
 */
public class SpecMinaService {
	public static void run(){
		new MinaClientExecutor().run();
	}
	
	public static void main(String[] args) {
		run();
	}
}
