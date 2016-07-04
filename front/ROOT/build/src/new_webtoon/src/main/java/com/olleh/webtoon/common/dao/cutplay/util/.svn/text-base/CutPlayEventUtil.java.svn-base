package com.olleh.webtoon.common.dao.cutplay.util;

import java.util.List;

import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayCommentDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayJoinDomain;
import com.olleh.webtoon.common.util.MessageUtil;

public class CutPlayEventUtil {

    /**
     * 이미지 도에인 설정 메소드 
     * @param void
     * @return String : 유저아이디 정보
     */
    public static void imageValidate(List list){
    	if(list != null && list.size() > 0) {
    		for(int i = 0; i < list.size(); i++){
    			imageValidate(list.get(i));
        	}
    	}
    }
    
    /**
     * 이미지 도에인 설정 메소드 
     * @param void
     * @return String : 유저아이디 정보
     */
    public static void imageValidate(Object obj){

		if(obj instanceof CutPlayJoinDomain) {
			
			CutPlayJoinDomain domain = (CutPlayJoinDomain)obj;
			if(domain.getWebtoonimagepath() != null && domain.getWebtoonimagepath().indexOf("http") < 0)
				if(domain.getWebtoonimagepath().indexOf("/images") < 0) {
					domain.setWebtoonimagepath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getWebtoonimagepath());
				}
    			
    		if(domain.getImagepath() != null && domain.getImagepath().indexOf("http") < 0) { 
    			if(domain.getImagepath().indexOf("/images") < 0) {
    				domain.setImagepath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getImagepath());
    			}
    		}
    		
    		if(!"webtooncut".equals(domain.getEventfg())) {
    			if(domain.getWebtoonimagepath() != null && domain.getWebtoonimagepath().indexOf("http") < 0) { 
        			domain.setImagepath(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/event"+domain.getImagepath());
        		}
    		}
			
		}else if(obj instanceof CutPlayCommentDomain) {
			
			CutPlayCommentDomain domain = (CutPlayCommentDomain)obj;
			
			if(domain.getNameconurl() != null && domain.getNameconurl().indexOf("http") < 0) {
				if(domain.getNameconurl().indexOf("/images") < 0) {
					domain.setNameconurl(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/namecon"+domain.getNameconurl());
				}
			}
    		
    		if(domain.getMnameconurl() != null && domain.getMnameconurl().indexOf("http") < 0) {
    			if(domain.getMnameconurl().indexOf("/images") < 0) {
    				domain.setMnameconurl(MessageUtil.getSystemMessage("system.image.server.domain.ssl") + "/download/namecon"+domain.getMnameconurl());
    			}
    		}
    			
		}
    }
}
