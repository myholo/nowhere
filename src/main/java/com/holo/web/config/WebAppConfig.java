/**
 * 
 */
package com.holo.web.config;

import com.holo.web.filter.CsrfFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.holo.web.interceptor.UserInterceptor;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Holo
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
	/**日志实例*/
	private final Log logger = LogFactory.getLog(getClass());
	
	 /**
     * 配置拦截器
     * @author lance
     * @param registry
     */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截	
		System.err.println("123");
		String interceptorUrl="";
		//interceptorUrl=UrlUtil.urlAdd(propSys.getBase(), "/user")+"/**";
		interceptorUrl="/**";
    	registry.addInterceptor(new UserInterceptor()).addPathPatterns(interceptorUrl);
    	if (logger.isDebugEnabled())
			logger.debug("拦截器,interceptorUrl=" + interceptorUrl);
		
	}

	//@Bean
	public FilterRegistrationBean getCsrfFilter(){
		CsrfFilter csrfFilter=new CsrfFilter();
		FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(csrfFilter);
		List<String> urlPatterns=new ArrayList<String>();
		urlPatterns.add("/*");//拦截路径，可以添加多个
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
