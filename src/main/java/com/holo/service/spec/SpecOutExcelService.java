/*package com.holo.service.spec;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.persister.collection.CollectionPropertyNames;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.regexp.internal.recompile;



@Service
public class SpecOutExcelService {
	*//**日志实例*//*
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	SpecRepo specRepo;
	@Autowired
	PermitCheckUtils permitCheckUtils;
	

	*//**
	 * 保存excle
	 * @param list
	 * @param list2 Permitfield集合
	 *//*
	public void  saveExcel(HttpServletResponse response,List list,List<Permitfield> list2,String tablename){
		//以60000条数据为一张表。多于60000的放在另外一张表中。
		int a=list.size()/60000+1;
		HSSFWorkbook wb = new HSSFWorkbook();  
		List<HSSFSheet> listHSSFSheet=new ArrayList<>();
		List<HSSFRow> listHSSFRow=new ArrayList<>();
		HSSFRow row ;
		HSSFCellStyle style;
		style = wb.createCellStyle(); 
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		HSSFCell cell;
		ArrayList<String> allMethod=ClassUtils.showClassMethordsFull(list.get(0).getClass()); 	 
		for (int i = 0; i <a; i++) {
			HSSFSheet sheet=wb.createSheet("表"+(i+1));
			row= sheet.createRow((int) 0);  
			listHSSFRow.add(row);
			listHSSFSheet.add(sheet);	
		}
		short k=0;
        for (String string : allMethod) {
        	String methodName="";
	        String getMethod="";
	        int atget=-1;
            atget=string.indexOf(";get");
            if(atget==-1)continue;
            getMethod="get"+string.substring(atget+4,string.length()-1);
            methodName=string.substring(atget+4,string.length()-1);     
            //把methodName改成小写，与Permitfield表中的fieldname字段去进行比较，如果相同，就把其中文名保存下来
            String first = methodName.substring(0, 1).toLowerCase();
            String rest = methodName.substring(1, methodName.length());
            String methodNameNew = new StringBuffer(first).append(rest).toString();
            for (int i = 0; i < list2.size(); i++) {
            	if(methodNameNew.equals(list2.get(i).getFieldname())&&!methodNameNew.contains("txt")){
            		cell=listHSSFRow.get(0).createCell((short) k);  
        	        cell.setCellValue(list2.get(i).getFieldalias());  
        	        cell.setCellStyle(style);
        	        k++;
            	}
            }                    	  
        }
        //d为第几张表
		int d=1;
		//g为表中的行数
		int g=1;
	    for (int i = 0; i < list.size(); i++){
	    	if(i!=0&&i%(d*60000)==0){
	    		d=d+1;
	    		g=1;
	    	}
	    	row = listHSSFSheet.get(d-1).createRow((int)g);  
	    	 g++;
	            String methodName="";
	            String getMethod="";
	            int atget=-1;
	            Object value=null;
	            //j是对应的excel表相应行的增加列。例如现在插入的是第三行。j就是从第一列到最后一列。
	            int j=0;	        
	            for (String string : allMethod) {
	                atget=string.indexOf(";get");
	                if(atget==-1)continue;
	                getMethod="get"+string.substring(atget+4,string.length()-1);
	                methodName=string.substring(atget+4,string.length()-1);  
	                //把methodName改成小写，与Permitfield表中的fieldname字段去进行比较，如果相同，就插入对应的值。
	                String first = methodName.substring(0, 1).toLowerCase();
	                String rest = methodName.substring(1, methodName.length());
	                String methodNameNew = new StringBuffer(first).append(rest).toString();	             
	                for (int  m= 0; m < list2.size(); m++) {
	                	//与Permitfield表中的fieldname字段去进行比较
	                	if(methodNameNew.equals(list2.get(m).getFieldname())&&!methodNameNew.contains("txt")){
	                		 String mapNameString=list2.get(0).getTbname()+"."+methodName;
	                		 value=ClassUtils.invokeMethod(list.get(i), getMethod,null);
	                		 //数据库中有些字段是以0或者1去表达状态的。所以相对应的把中文状态给列出，呈现给客户。
	                		 Map<String,String> roleMap=MapDb.getInstance().getMymap().get(mapNameString);
	                		 if(roleMap==null){
	                			 if(value!=null){
	 	     	                	if(value instanceof String && ("".equals(value)||value==null)){
	 	     	                		row.createCell( j).setCellValue("");
	 	     	                    	j++;
	 	     	                		continue;
	 	     	                	}                		
	 	     	                	if(value instanceof Integer && Integer.valueOf(0).equals(value)){
	 	     	                		row.createCell( j).setCellValue("0");
	 	     	                    	j++;
	 	     	                		continue;
	 	     	                	}              
	 	     	                	row.createCell( j).setCellValue(value.toString());
	 	     	                	j++;
	 	     	                }else{
	 	     	                	row.createCell( j).setCellValue("");
	 	     	                	j++;
	 	     	                }
	                		 }else{
	            	    		Iterator<Entry<String,String>> iter = roleMap.entrySet().iterator();
	            				while (iter.hasNext()) {
	            					Entry<String,String> entry = iter.next(); 
	            					String key = entry.getKey();
	            					if(value==null){
	            						row.createCell( j).setCellValue("");
	    	     	                	j++;
	    	     	                	break;
	            					}
	            					if(value.toString().equals(key.toString())){
	            						row.createCell( j).setCellValue(entry.getValue());
	    	     	                	j++;
	    	     	                	break;
	            					}	            				
	            				}
	                		 } 
	                		
	                	}
	                }
	            }
        	
	    }
        //将文件存到指定位置  
        try  	        
        { 
        	OutputStream out = response.getOutputStream();	   
        	 response.reset();// 清空输出流 
        	String filename=tablename+".xls";
        	response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        	response.setContentType("application/msexcel;charset=UTF-8");  
        	wb.write(out);	
        	out.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
}
*/