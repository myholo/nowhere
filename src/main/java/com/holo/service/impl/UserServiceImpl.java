package com.holo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.holo.common.exception.HoException;
import com.holo.common.util.Json;
import com.holo.dao.my.UserRepo;
import com.holo.domain.my.User;
import com.holo.service.BaseService;
import com.holo.service.my.UserService;

/**
 * 
 * @author holo
 *
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService{

	@Autowired
	UserRepo userRepo;
	
	
	/**
	 *  创建excel
	 *  20171007 
	 */
	@Override
	public HSSFWorkbook csexcelexport() {
		List<Object> params=new ArrayList<Object>();
		// 创建一个Excel文件  
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("导出1");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式    
        HSSFCell cell=null;
        int nowrow=0;
         //excel表格第一行，插入的是字段英文名。
        HSSFRow row = sheet.createRow(nowrow++);
        String[] fielde={"Id","GmtCreate"};
        for (int i = 0; i < fielde.length; i++) {
        	cell=row.createCell(i);
	        cell.setCellStyle(style);
	        cell.setCellValue(fielde[i]);  
		}
        //excel表格第二行，插入的是字段中文名
        row = sheet.createRow(nowrow++);
        String[] fieldc={"序号ID","创建时间"};
        for (int i = 0; i < fieldc.length; i++) {
        	cell=row.createCell(i);
	        cell.setCellStyle(style);
	        cell.setCellValue(fieldc[i]);  
		}	
        
        row = sheet.createRow(nowrow++);
        int nowcol=0;
        //放序号ID
    	cell=row.createCell(nowcol++);
        cell.setCellStyle(style);
		cell.setCellValue(1);  
        //放创建时间
    	cell=row.createCell(nowcol++);
        cell.setCellStyle(style);
		cell.setCellValue("20171009");  
		
		return wb;
		
	}
	
	@Override
	public User loginCheck(User user) throws HoException {
		if(user==null){
			throw new HoException("账号密码有误");	
		}
		User userDb=getFirst(null, "username = ?0",null,new Object[]{user.getUsername()});
		if(userDb==null){
			throw new HoException("账号密码有误");
		}
		System.err.println(userDb);
		return userDb;
	}
	
	@Override
	public Json createUser(User user) throws Exception {
		if(user.getRoleId()==null){
			//放个默认值
			user.setRoleId(1);
		}
		saveCreate(user);
		Map<String,Object> ret=new HashMap<String, Object>();
		ret.put("user",user);
		return new Json(true,"注册成功",ret);
		
	}
	public void saveCreate(User obj) throws Exception{
		save(null,obj);
	}
	
	/**
	 * 保存，新建或更新，有ID就更新，否则新建
	 * @param sessionUser
	 * @param user
	 */
	public void save(User sessionUser,User user){
		userRepo.save(user);
	}
	/**
	 * 更新
	 * @param sessionUser
	 * @param user
	 */
	public void update(User sessionUser,User user){
		userRepo.save(user);
	}
}
