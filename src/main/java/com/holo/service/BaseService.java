package com.holo.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.holo.dao.base.BaseRepo;
import com.holo.domain.my.User;







/**
 * 基础service
 * @author Holo
 *
 * @param <T>
 */
public class BaseService <T> {
	private static final Logger logger = Logger.getLogger(BaseService.class);
	
	@Autowired
	BaseRepo<T, Serializable> baseRepo;
	

	/**
	 * 返回第一个查询结果
	 * @param sessionUser
	 * @param Clazz 类名表名 ex.Admin
	 * @param hqlb where 前
	 * @param where ex. a=1
	 * @param order ex. id desc
	 * @param params
	 * @return 返回相应的持久化PO实例
	 */
	public T getFirst(User sessionUser,String where,String order,Object[] params){
		Class<T> c = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String Clazz=c.getSimpleName();
		//where=SqlUtils.whereAdd(where,permitCheckUtils.addWhere(sessionUser,Clazz));		
		try {
			return baseRepo.getFirst(Clazz, where,null, params);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {

		} 
		return null;
	}
}
