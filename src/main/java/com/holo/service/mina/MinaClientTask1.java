package com.holo.service.mina;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holo.domain.my.Usecase;



public class MinaClientTask1 implements Runnable{
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	Usecase usecase;
	public MinaClientTask1(Usecase _usecase){
		this.usecase=_usecase;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		MinaClient client = new MinaClient();
		client.conn();
		try {
			if(usecase.getSloopnums()==null || usecase.getSloopnums().intValue()==0){
				while(true){
					client.send();
					Thread.sleep(usecase.getSteploop());
					/*if(!MinaClientExecutor.isAlive(usecase.getId())){
						if (logger.isDebugEnabled())
							logger.debug("执行器池结束"+",线程名="+Thread.currentThread().getName());
						break;
					}*/
						
				}
			}else{
				for (int i = 0; i < usecase.getSloopnums(); i++) {
					client.send();
					Thread.sleep(usecase.getSteploop());
				/*	if(!MinaClientExecutor.isAlive(usecase.getId()))
						break;*/
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
