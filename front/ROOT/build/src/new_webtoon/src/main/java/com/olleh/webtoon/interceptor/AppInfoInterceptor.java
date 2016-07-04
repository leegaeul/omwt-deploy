package com.olleh.webtoon.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.client.RestTemplate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.olleh.webtoon.common.util.AppInfoUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.UrlBuildUtil;


public class AppInfoInterceptor extends HandlerInterceptorAdapter {

	//private static final Logger logger = LoggerFactory.getLogger(AppInfoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	
		String uri = RequestUtil.getRequestUri(request); //Request URI
		
		if(uri.indexOf(".kt") > 0 && AppInfoUtil.isMobile(request)) {
			if(AppInfoUtil.isMobile(request) && AppInfoUtil.getAppInfo(request) != null){
				request.setAttribute("ot_uuid", AppInfoUtil.getUuid(request));
				request.setAttribute("ot_channelfg", AppInfoUtil.getChannelfg(request));
			}else if(AppInfoUtil.isMobile(request)){
				request.setAttribute("ot_channelfg", "mobile");
			}else {
				request.setAttribute("ot_channelfg", "pc");
			}
		}
		
		
		//로고 처리			
		Map<String,Object> param = new HashMap<String,Object>();
		
		if(AppInfoUtil.isMobile(request))
			param.put("displayfg", "mobile");
		else
			param.put("displayfg", "pc");
		
		String apiUrl = UrlBuildUtil.webtoonApiBuild("/api/logo/getLogo.kt", param);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> data = restTemplate.getForObject(apiUrl, HashMap.class);	// GET Method
		
		if(data != null){
			try{
				Map<String, Object> logoInfo = (Map<String, Object>)data.get("logoInfo");
				
				request.setAttribute("logoInfo", logoInfo);
			}catch(Exception e){ }
		}
		
		return true;
	}
}
