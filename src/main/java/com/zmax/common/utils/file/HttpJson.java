/*package com.zmax.common.utils.file;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
*//**
 * Http 发送 Json
 * POST等同于如下HTML
 * 	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#post").click(function(){
				ajaxPOST();
			});
		});

		function ajaxPOST() {
			var url="http://iss.kongjianhao.com/iss/rest/ygs";
			var event="{\"eventId\":57,\"lockcode\":\"00158d0000112233\",\"type\":\"ONLINE\",\"info\":null,\"username\":null,\"time\":1498617508000,\"lock\":{\"id\":2,\"lock_id\":\"2d461e1a0ecb3f2c8732bf701a6fad76\",\"room_id\":2,\"gateway_id\":\"1fc4e5ac784f3de297796e7e51e9aceb\",\"lock_code\":\"00158D0000112233\",\"room\":{\"id\":2,\"name\":\"储物柜1\",}}}";
			$.ajax({
				type : "post",
				contentType : "application/json;charset=UTF-8",
				url : url,
				dataType : "json",
				processData : false,
				data : event,
				success : function(ret) {
					//javascript已自动将返回的json数据转为对象了
					alert("success:"+ret.msg);
				},
				error : function() {
					alert("try again!");
				}
			});
		}

	</script>
 * @author zmax
 *
 *//*
public class HttpJson {
	*//**
	 * Http Post
	 * @param url String 地址
	 * @param json String 包体
	 * @param headers NameValuePair[] 更多头
	 * @param params NameValuePair[] 更多参 表单 ex.new NameValuePair[]{new NameValuePair("username","111"),new NameValuePair("pwd","aaa")};  
	 * @return
	 * @throws Exception
	 *//*
	public static String httpPostMethordWithJSON(String url, String json,NameValuePair[] headers,NameValuePair[] params) throws Exception {
		PostMethod method = null; 
		try  
		{  
			HttpClient client = new HttpClient();  
			method = new PostMethod(url);  

			RequestEntity entity = new StringRequestEntity(json,"application/json","UTF-8");
			method.setRequestEntity(entity);
			method.addRequestHeader("connection","keep-alive");  
			method.setRequestHeader("Content-Type","application/json;charset=UTF-8");

			//更多头
			if(headers!=null){
				for (NameValuePair nameValuePair : headers) {
					method.setRequestHeader(nameValuePair.getName(), nameValuePair.getValue());	
				}				
			}
			//更多参
			if(params!=null){
				method.addParameters(params);  							
			}
			
			int statusCode = client.executeMethod(method);  
			//System.out.println("statusCode="+statusCode);  
			if(statusCode != HttpStatus.SC_OK)  
			{  
				return null;  
			}  
			//System.out.println("ret:");
			//System.out.println(method.getResponseBodyAsString());  
			return method.getResponseBodyAsString();  

		}  
		finally  
		{  
			if(null != method)  
			{  
				method.releaseConnection();  
			}  
		} 
	} 
	*//**
	 * http get方法
	 * @param url
	 * @param headers NameValuePair[] 更多头
	 * @return
	 * @throws Exception
	 *//*
	public static String httpGetMethordWithJSON(String url,NameValuePair[] headers) throws Exception {
		GetMethod method = null; 
		try  
		{  
			HttpClient client = new HttpClient();  
			method = new GetMethod(url);  
			method.addRequestHeader("connection","keep-alive");
			method.setRequestHeader("Content-Type","application/json;charset=UTF-8");
			
			//更多头
			if(headers!=null){
				for (NameValuePair nameValuePair : headers) {
					method.setRequestHeader(nameValuePair.getName(), nameValuePair.getValue());	
				}				
			}

			
			int statusCode = client.executeMethod(method);  
			//System.out.println("statusCode="+statusCode);  
			
			if(statusCode == 400)  
			{  
				//return method.getResponseBodyAsString();  
				return null;  
			}  
			
			if(statusCode != HttpStatus.SC_OK)  
			{  
				return null;  
			}  
			//System.out.println("ret:");
			//System.out.println(method.getResponseBodyAsString());  
			return method.getResponseBodyAsString();  

		}  
		finally  
		{  
			if(null != method)  
			{  
				method.releaseConnection();  
			}  
		} 
	} 

}
*/