/**
 * 
 */
package com.holo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.holo.common.exception.HoException;
import com.holo.common.util.Json;
import com.holo.domain.my.User;
import com.holo.service.impl.UserServiceImpl;

/**
 * @author Holo
 *
 */
@RestController
@RequestMapping(value="/rest") 
public class UserRestController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Json login(HttpServletRequest request,HttpServletResponse respons,ModelMap model,User usere,HttpSession session) throws HoException{
		System.err.println(request.getParameter("username"));
		System.err.println(request.getParameter("password"));
		System.err.println(usere);
		User user2session=userServiceImpl.loginCheck(usere);
		request.getSession().setAttribute("user", user2session);
		System.err.println(request.getSession().getAttribute("user").toString());
		Map<String,Object> ret=new HashMap<String, Object>();
		ret.put("user",user2session);
		return new Json(true,"登录成功！",ret);
	}
	
	@RequestMapping(value="/xxx",method=RequestMethod.GET)
	public Json xxx(){
		return new Json(true,"登录成功！");
	}
}
