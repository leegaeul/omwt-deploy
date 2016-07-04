package com.olleh.webtoon.common.dao.photoevent.util;

import java.util.List;

import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventImageDomain;
import com.olleh.webtoon.common.util.MessageUtil;

public class PhotoEventUtil {

	protected static String MONITORING_USERS = "c.na@me.com"
											 + ",rinaens@gmail.com"
											 + ",inates@naver.com"
											 + ",zikes@daum.net"
											 + ",kimyongjee@hanmail.net"
											 + ",kim.youngsik@kt.com"
											 + ",ujung626@gmail.com"
											 + ",triea@naver.com"
											 + ",khb2khb@naver.com"
											 + ",romis@naver.com";
	
	/**
     * 모니터링 유저아이디 정보
     * @param void
     * @return String : 유저아이디 정보
     */
    public static String getMoniteringUsers(){
    	
    	return "'" + MONITORING_USERS.replaceAll(",", "','") + "'";
    }
    
    /**
     * 이미지 도에인 설정 메소드 
     * @param void
     * @return String : 유저아이디 정보
     */
    public static void imageValidate(List<PhotoEventImageDomain> list){
    	
    	if(list == null) 
    		return;
    	
    	for(int i = 0; i < list.size(); i++){
    		
    		PhotoEventImageDomain domain = list.get(i);
    		
    		if(domain.getThumbpath() != null && domain.getThumbpath().indexOf("http") < 0)
    			domain.setThumbpath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getThumbpath());
    		
    		if(domain.getMthumbpath() != null && domain.getMthumbpath().indexOf("http") < 0)
    			domain.setMthumbpath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getMthumbpath());
    		
    		if(domain.getDetailimagepath() != null && domain.getDetailimagepath().indexOf("http") < 0)
    			domain.setDetailimagepath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getDetailimagepath());
    		
    		if(domain.getMdetailimagepath() != null && domain.getMdetailimagepath().indexOf("http") < 0)
    			domain.setMdetailimagepath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getMdetailimagepath());
    		
    		if(domain.getImagepath() != null && domain.getImagepath().indexOf("http") < 0)
    			domain.setImagepath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getImagepath());
    	}
    }
}
