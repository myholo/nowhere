/**
 * 
 */
package com.holo.service.spec;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.holo.common.exception.HoException;
import com.zmax.common.exception.BoException;
import com.zmax.common.exception.NeedLoginException;
import com.zmax.common.exception.RightException;
import com.zmax.common.utils.qr.QRCodeUtil;
import com.zmax.hos.domain.bean.Article;
import com.zmax.hos.domain.bean.User;
import com.zmax.hos.web.controller.restful.entity.ClientInfo;
import com.zmax.hos.web.controller.restful.entity.RestPage;

/**
 * @author Holo
 *
 */
public class SpecRestService {

	@RequestMapping(value = "/saveQr", method = RequestMethod.GET)
	public void saveQr(HttpServletRequest request,HttpServletResponse response,Integer id) throws HoException, Exception{
		String filename=id+".JPG";
		String content = "http://www.homeownership.cn/index_m.html#/tab/ArticleShow/"+id;
		String realPath = request.getSession().getServletContext().getRealPath("/userfiles/articleqr/"+filename);
		//返回给前端的路径
		String qrcodeUrl = "";

		//二维码存储的空间地址
		File qrcode = new File(realPath+"/"+filename);
		//判断二维码图片的上级目录是否存在，不存在则创建一个
		File qrcodeParent = new File(qrcode.getParent());
			if(!qrcodeParent.exists()){
				qrcodeParent.mkdirs();
			}
		QRCodeUtil.encoderQRCode(content, realPath, "jpg");
	}
}
