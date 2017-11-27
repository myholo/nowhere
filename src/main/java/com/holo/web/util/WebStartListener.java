package com.holo.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebListener
public class WebStartListener implements ServletContextListener{
	private final Log logger = LogFactory.getLog(getClass());
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("web stop!");
	}
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.err.println("web start");
		logger.info("web stop!");
	}

}
