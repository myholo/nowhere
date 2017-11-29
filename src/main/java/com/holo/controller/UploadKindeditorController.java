/**
 * 
 *//*
package com.holo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.holo.common.util.StringUtilz;
import com.holo.domain.my.User;

*//**
 * @author Holo
 *
 *//*
public class UploadKindeditorController {
	*//**图的扩展名*//*
	public static String IMAGE_EXTS="(jpg)|(png)|(jpeg)|(bmp)|(gif)";
	
	*//**日志实例*//*
	private final Log logger = LogFactory.getLog(getClass());
	
	*//**
	 * 上传一个字串，返回一个url
	 * @param request
	 * @param response
	 * @param txt
	 * @return
	 * @throws IOException
	 *//*
	@RequestMapping(value="/txtUpload")
	@ResponseBody
	public Map<String, Object> txtUpload(HttpServletRequest request, HttpServletResponse response,String txt)
			throws IOException {
		User sessionUser = userFromRequest(request);
		String fileName = FileNameUtils.generateRandomFilenameYMdHms() + ".txt";//文件名
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(txt)){
			hashMap.put("error", -1);
			hashMap.put("url", "");
			return hashMap;
		}
		String url = WebStaticUtils.writeUploadDisk(sessionUser.getId(),
				fileName, txt.getBytes());
		hashMap.put("error", 0);
		hashMap.put("url", url);
		return hashMap;
	}
	public User userFromRequest(HttpServletRequest request){
		HttpSession session=(request==null)?null:request.getSession();
		if(session==null)
			return null;
		if(session.getAttribute("user")==null)
			return null;
		return (User)session.getAttribute("user");
	}
	
	*//**
	 * 沿用之前的上传图片 保存在本地
	 * kindeditor 本地上传图片
	 * @param file 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 *//*
	@PostMapping("/kindfileUpload")
	@ResponseBody
	public Map<String, Object> kindfileUploadtest(
			@RequestParam("imgFile") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User sessionUser = sessionUserUtils.userFromRequest(request);
		String fileName = file.getOriginalFilename();// 原文件名
		if (StringUtilz.haveChineseCharacter(fileName)) {
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
			fileName = FileNameUtils.generateRandomFilenameYMdHms() + "." + ext;
		}
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String url = WebStaticUtils.writeUploadDisk(sessionUser.getId(),
				fileName, file.getBytes());
		hashMap.put("error", 0);
		hashMap.put("url", url);
		return hashMap;
	}
	
	*//**
	 * kindeditor 图片空间 
	 * ex.kindfileManager?path=&order=NAME&dir=image&1508138234971
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 *//*
	@GetMapping("/kindfileManager")
	@ResponseBody
	public KindEditResult kindfileManager(
			HttpServletRequest request, HttpServletResponse response,String path,String order,String dir)
			throws IOException {
		String hddr=propMy.getHddir();
		if(hddr.indexOf("file://")==0)hddr=hddr.substring(7);
		String rootPath=hddr+"/static/userfiles/";
		User sessionUser = sessionUserUtils.userFromRequest(request);
		//根据path参数，设置各路径和URL  
		if (path==null) path ="";
		//图片空间的当前路径 "http://sts.kongjianhao.com/sts/userfiles/";
		String currentUrl = propMy.getImgbase()+propSys.getBase()+"/userfiles/";  
		//向上一级移动按钮 路径
		String moveup_dir_path=null;
		if (!"".equals(path)) {
			//根据path更新图片空间当前路径
			currentUrl=currentUrl+path.substring(rootPath.length());
			// 根据path更新 向上一级移动按钮 路径
			if (path.length() > rootPath.length()) {
				int moveupInt = path.substring(0, path.length() - 1).lastIndexOf("/");
				moveup_dir_path = path.substring(0, moveupInt + 1);
			} else {
				moveup_dir_path = rootPath;
			}
		}else{
			//默认刚进图片空间路径
			path=rootPath;
		}
		//根据path 加载该路径下的文件 
		File currentPathFile = new File(path);
		KindEditResult kindEditResult = new KindEditResult();
		List<KindEditFile> fileList = new ArrayList<KindEditFile>();
		if(currentPathFile.listFiles() != null) {   
			for (File file : currentPathFile.listFiles()) {
				KindEditFile kindEditFile = new KindEditFile();
				String fileName = file.getName();
				if (file.isDirectory()) {
					//是目录的情况
					kindEditFile.setIs_dir(true);
					kindEditFile.setHas_file(file.listFiles() != null);
					kindEditFile.setFilesize(0L);
					kindEditFile.setIs_photo(false);
					kindEditFile.setFiletype("");
					kindEditFile.setFilename(fileName);
				} else if (file.isFile()) {
					//是文件的情况
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					if (fileExt.matches("^["+IMAGE_EXTS+"]+$")) {
						kindEditFile.setIs_photo(true);
					}
					kindEditFile.setIs_dir(false);
					kindEditFile.setHas_file(false);
					kindEditFile.setFilesize(file.length());
					kindEditFile.setFiletype(fileExt);
					kindEditFile.setFilename(fileName);
				}
				fileList.add(kindEditFile);
			}
		}
		//整理返回数据
		//该path路径下的文件 包含filename等信息 filename关系图片的展示
		kindEditResult.setFile_list(fileList);
		kindEditResult.setCurrent_url(currentUrl);
		//当时是目录是需记录该路径返回
		kindEditResult.setCurrent_dir_path(path);
		//返回当前path的上一级目录 上面以对path出来过赋值给maveup_dir_path
		kindEditResult.setMoveup_dir_path(moveup_dir_path);
		return kindEditResult;
	}
}


*/