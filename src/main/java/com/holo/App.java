/**
 * 
 */
package com.holo;
import com.alibaba.druid.pool.DruidDataSource;
import com.holo.concurrent.test.LockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.web.bind.annotation.RestController;
import com.holo.config.Rt;
import com.holo.web.ApplicationInit;


/**
 * 
 * @author Holo
 *
 */
@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy
@RestController
public class App {
	public static ApplicationInit applicationInit=null;

    public static void main( String[] args ){
    	//System.err.println(App.class);
    	//SpringApplication springApplication=new SpringApplication(App.class);
    	//applicationInit=new ApplicationInit();
    	/*springApplication.addListeners(new ApplicationListener<ContextRefreshedEvent>(){

			@Override
			public void onApplicationEvent(ContextRefreshedEvent event) {
				// TODO Auto-generated method stub
				Rt.applicationContext=event.getApplicationContext();
			}
    		
    	});*/
    	//springApplication.addListeners(event -> Rt.applicationContext = ((ApplicationContextEvent) event).getApplicationContext());
		SpringApplication.run(App.class, args);
  
    	
    }

    @Bean
    public LockService lockService() {
        return new LockService();
    }
  
}
