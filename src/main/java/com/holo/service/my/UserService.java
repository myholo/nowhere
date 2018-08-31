package com.holo.service.my;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.holo.common.exception.HoException;
import com.holo.common.util.Json;
import com.holo.domain.my.User;


/**
 * 
 * @author Holo
 *
 */
public interface UserService{
	
	User loginCheck(User user) throws HoException;

	Json createUser(User user) throws Exception;


	
	void save(User sessionUser,User user);
	
	void update(User sessionUser,User user);
	
	HSSFWorkbook csexcelexport();
}
