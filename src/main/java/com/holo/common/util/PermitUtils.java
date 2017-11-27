package com.holo.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holo.web.util.MapDb;

@Service
public class PermitUtils {
	/**表及字段的Map<tbname,List<{fieldname,fieldalias}>>*/
	Map<String,List<String[]>> tfMap=new HashMap<String, List<String[]>>();
	/**表的List<{tbname,tbalias}>*/
	List<String[]> tList=new ArrayList<String[]>();
	@RequestMapping(value="/user/paraminit",method=RequestMethod.GET)
	public void init(){
		System.err.println("init");
		putfield();
		permitTable();
	}
	public void putfield(){
		List<String[]> fl;
		fl=new ArrayList<String[]>();
		fl.add(new String[]{"id","序号"});
		fl.add(new String[]{"gmtCreate","创建时间"});
		fl.add(new String[]{"gmtModified","修改时间"});
		fl.add(new String[]{"status","状态"});
		tfMap.put("User", fl);
		tList.add(new String[]{"User","账号信息修改"});
	}
	/**
	 * 所有的权限之表管理
	 */
	public void permitTable(){
		for (String[] atl : tList) {
			p1(atl[0], atl[1]);
		}
	}
	/**
	 * 根据表名保存系列role的permitTable
	 * @param tbname 表名
	 * @param tbalias 表说明
	 */
	public void p1(String tbname,String tbalias){
	/*	List<Permittable> listTbAdd=new ArrayList<Permittable>();
		List<Permitfield> listFieldAdd=new ArrayList<Permitfield>();*/
		Map<String,String> roleMap=MapDb.getInstance().getMymap().get("User.RoleId");
		Iterator<Entry<String,String>> iter = roleMap.entrySet().iterator();
		int  pall=0, btnnew=0, btnedit=0, btndel=0, btnshow=0;
		String queryaddhql="", modigvy="";
		while (iter.hasNext()) {
			Entry<String,String> entry = iter.next(); 
			String key = entry.getKey();
			//String val = entry.getValue();
			Integer roleId=Integer.parseInt(key);
			System.err.println(roleId+"--------------"+tbname);
		}
	}
	
}
