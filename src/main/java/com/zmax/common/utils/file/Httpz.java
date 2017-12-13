/*package com.zmax.common.utils.file;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;

*//**
 * 基于httpClient的 get post
 * @author zmax
 *
 *//*
public class Httpz {
	*//**
	 * http get方法
	 * 
	 * @param url
	 * @param headers
	 *            NameValuePair[] 更多头
	 * @return
	 * @throws Exception
	 *//*
	public static String httpGet(String url, NameValuePair[] headers) throws Exception {
		GetMethod method = null;
		try {
			HttpClient client = new HttpClient();
			method = new GetMethod(url);
			method.addRequestHeader("connection", "keep-alive");
			method.setRequestHeader("Content-Type",
					"application/json;charset=UTF-8"); // 用json

			// 更多头
			if (headers != null) {
				for (NameValuePair nameValuePair : headers) {
					method.setRequestHeader(nameValuePair.getName(),
							nameValuePair.getValue());
				}
			}

			int statusCode = client.executeMethod(method);
			// System.out.println("statusCode="+statusCode);

			if (statusCode == 400) {
				// return method.getResponseBodyAsString();
				// return null;
			}

			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception(""+statusCode);
				// return null;
			}
			// System.out.println("ret:");
			// System.out.println(method.getResponseBodyAsString());
			String ret=method.getResponseBodyAsString();
			return ret;

		} finally {
			if (null != method) {
				method.releaseConnection();
			}
		}
	}

	
	*//**
	 * post
	 * 有表单加表单 有json加json
	 * @param url
	 * @param headers 更多头
	 * @param json 如果不空就整个body放json
	 * @param map 参数 表单
	 * @return
	 * @throws Exception
	 *//*
	public static String httpPost(String url, NameValuePair[] headers, String json, HashMap<String, String> map)
			throws Exception {
		PostMethod method = null;
		try {
			HttpClient client = new HttpClient();
			method = new PostMethod(url);
			
			// 更多头
			if (headers != null) {
				for (NameValuePair nameValuePair : headers) {
					method.setRequestHeader(nameValuePair.getName(),
							nameValuePair.getValue());
				}
			}
			
			// 有Json放Json
			if (StringUtils.isNotBlank(json)) {
				RequestEntity entity = new StringRequestEntity(json,
						"application/json", "UTF-8");
				method.setRequestEntity(entity);
				method.setRequestHeader("Content-Type",
						"application/json;charset=UTF-8");
			}
			method.addRequestHeader("connection", "keep-alive");

			// 有表单，加表单
			if (map != null && map.size() > 0) {
				NameValuePair[] params = new NameValuePair[map.size()];
				Iterator<Entry<String, String>> iter = map.entrySet()
						.iterator();
				int i = 0;
				while (iter.hasNext()) {
					Entry<String, String> entry = iter.next();
					String key = entry.getKey();
					String value = entry.getValue();
					params[i] = new NameValuePair(key, value);
					i++;
				}
				method.addParameters(params);
			}

			int statusCode = client.executeMethod(method);
			// System.out.println("statusCode="+statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				throw new Exception(""+statusCode);
			}
			// System.out.println("ret:");
			// System.out.println(method.getResponseBodyAsString());
			String ret=method.getResponseBodyAsString();
			return ret;

		} finally {
			if (null != method) {
				method.releaseConnection();
			}
		}
	}
}
*/