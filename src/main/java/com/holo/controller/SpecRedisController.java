package com.holo.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.holo.service.impl.UserServiceImpl;
import com.holo.service.spec.SpecRedisService;
import com.jcraft.jsch.Session;

@RestController
@RequestMapping(value="/rest")  
public class SpecRedisController {
	   @Autowired  
	    private UserServiceImpl userService;  
	      
	    @Autowired  
	    private SpecRedisService specRedisService;  
	      
	 /*   @RequestMapping("/users")  
	    public void users(){  
	        List<User> users = userService. 
	  
	        return modal;  
	    }  
	      */
	    @RequestMapping("/redis/set")  
	    public String redisSet(HttpServletRequest request){  
	    	HttpSession session=(request==null)?null:request.getSession();
	    	UUID uid = (UUID) session.getAttribute("uid");
	        if (uid == null) {
	            uid = UUID.randomUUID();
	        }
	        session.setAttribute("uid", uid);
	        return session.getId();
	       // boolean isOk = specRedisService.set("name", value);  

	    }  
	      
	    @RequestMapping("/redis/get")  
	    public void redisGet(){  
	        String name = specRedisService.get("name");  
	        System.err.println(name);
	   
	    }  
}
