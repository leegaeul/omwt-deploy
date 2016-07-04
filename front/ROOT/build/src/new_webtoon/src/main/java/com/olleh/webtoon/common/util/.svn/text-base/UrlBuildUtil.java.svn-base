package com.olleh.webtoon.common.util;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

public class UrlBuildUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(UrlBuildUtil.class);
	
	public static String build(String basicUrl){		
		return build(basicUrl, null);
	}
	
	public static String build(String basicUrl, Map<String,Object> params){
		String url = basicUrl;
		
		
		if(params !=  null){	
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);	
			
			for (Entry<String, Object> entry : params.entrySet())
				builder.queryParam(entry.getKey(), entry.getValue());
			
			url = builder.build().toString();
		}
		
		return url;
	}
	
	public static String webtoonApiBuild(String basicUrl){		
		return webtoonApiBuild(basicUrl, null);
	}
	
	public static String webtoonApiBuild(String basicUrl, Map<String,Object> params){
		String url = MessageUtil.getSystemMessage("system.api.domain") + basicUrl;	

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		
		//모든 웹툰 API 호출시 인증키 전송
		builder.queryParam("key", MessageUtil.getSystemMessage("system.api.authkey"));
		
		if(params !=  null){			
			for (Entry<String, Object> entry : params.entrySet())
				builder.queryParam(entry.getKey(), entry.getValue());
		}
		
		url = builder.build().toString();

		LOG.debug("API URL : " + url);
		
		return url;
	}
}
