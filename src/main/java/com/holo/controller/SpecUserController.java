package com.holo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.holo.dao.my.UserRepo;
import com.holo.domain.my.User;
import com.holo.service.spec.SpecRedisService;

@RestController
@RequestMapping(value="/rest")  
public class SpecUserController {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private SpecRedisService specRedisService;
	
	@GetMapping("/{id}")
	public User findById(@PathVariable Integer id){
		
		if(specRedisService.get("user_"+id)!=null){
			String json= specRedisService.get("user_"+id);
	        User user = JSON.parseObject(json, new TypeReference<User>() {});
	        System.err.println(1);
		}
		User findOne =this.userRepo.findOne(id);
		specRedisService.set("user_"+id,JSON.toJSONString(findOne) ); 
		
		return findOne;
	}
}
