package com.holo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holo.service.impl.UserServiceImpl;

/**
 * 
 * @author Holo
 * 2017年10月9日
 */
@RestController
@RequestMapping(value="/rest") 
public class SpecOutExcelController {
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping("/excelexport")
	public void outExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HSSFWorkbook excel = userServiceImpl.csexcelexport();
		OutputStream out = response.getOutputStream();	   
		response.reset();// 清空输出流 
		String filename="test.xls";
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
		response.setContentType("application/msexcel;charset=UTF-8");  
		excel.write(out);	
		out.close();  
	}
}
