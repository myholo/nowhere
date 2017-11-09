package com.holo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Controller;


/**
 * 
 * @author Holo
 *
 */
@SpringBootApplication
@ServletComponentScan
@Controller
public class App {
	public static ApplicationInit applicationInit=null;
	
    public static void main( String[] args ){
    	System.err.println(App.class);
    	SpringApplication springApplication=new SpringApplication(App.class);
    	applicationInit=new ApplicationInit();
    	springApplication.addListeners(applicationInit);
    	springApplication.run(args);
  
    	
    }

}
