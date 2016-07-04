package com.olleh.webtoon.http;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

@SuppressWarnings("serial")
public class HttpParameters extends ArrayList<NameValuePair> {

	public void addParams(String url) {
		
		// 파라미터 분리
		if(url != null && !"".equals(url))
		{
			String[] parseParam = url.split("\\&");
			String[] paramAndValue = null;
			
			for(int i = 0; i < parseParam.length;++i)
			{
				// 변수이름 값 분리 및 파라미터 저장
				paramAndValue = parseParam[i].split("\\=");
				add(new BasicNameValuePair(paramAndValue[0], paramAndValue[1]));
			}
		}
	}
	
	public void addParams(String name, String value) {
		add(new BasicNameValuePair(name, value));
	}
	
	public void removeParams(String name, String value) {
		for(NameValuePair param : this) {
			if (param.getName().equals(name) == true) {
				remove(param); return;				
			}
		}
	}
	
	public String convertParamsToUrl(String url) throws Exception {
		Iterator <NameValuePair> iterator = iterator();
		StringBuffer strBuffer = new StringBuffer(url);
		
		try {
			URL albumUrl = new URL(url);			
			String query = albumUrl.getQuery();
			
			if (size() > 0) 
			{
				if (query == null) {
					strBuffer.append("?");
				}
				else {
					strBuffer.append("&");
				}
			}
			
			while(iterator.hasNext() == true) {
				NameValuePair urlParam = (NameValuePair) iterator.next();
				
				strBuffer.append(urlParam.getName());
				strBuffer.append("=");
				strBuffer.append(URLEncoder.encode(urlParam.getValue(), "UTF-8"));
							
				if(iterator.hasNext() == true) { strBuffer.append("&"); } 
			}
		}
		catch (MalformedURLException urlException) {
			throw new Exception(urlException.getMessage());
		}
		catch (UnsupportedEncodingException encException) {
			throw new Exception(encException.getMessage());
		}
	
		return strBuffer.toString();
	}
}
