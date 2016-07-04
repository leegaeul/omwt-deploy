/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : RequestUtil.java
 * DESCRIPTION    : HttpServletRequest 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.olleh.webtoon.olltoon.payment.controller.OlltoonPaymentController;

public class OlltoonRequestUtil {	
	protected static Log logger = LogFactory.getLog(OlltoonRequestUtil.class);
 
	/**
	 * HttpServletRequest 객체로부터 모바일 여부를 체크하는 메소드.
	 * 
	 * @param HttpServletRequest request : HttpServletRequest 객체
	 * @return boolean                    : 모바일 여부
	 */
	public static boolean isMobile(HttpServletRequest request) {		
		//모바일 체크
		boolean isMobile = false;
		String userAgent = request.getHeader("User-Agent").toLowerCase();		
		if(userAgent.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")
				|| userAgent.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
			isMobile = true;	
		}

		//개발용 모바일 테스트
		HttpSession session = request.getSession();		
		if(session != null && "Y".equals(session.getAttribute("isMobile"))){
			isMobile = true;
		}
		
		return isMobile;
		
	}
    
    /**
	 * HttpServletRequest 객체로부터 App 정보를 반환
	 * @param request
	 * @return appinfo
	 */
    public static String[]  getAppInfo(HttpServletRequest request) {
    	String ua = request.getHeader( "User-Agent" );
    	
    	//모바일 앱에서 로그인시 Device정보에 로그인 정보를 update
		if(ua.indexOf("UUID") > -1){
			String[] ua_array = ua.split("\\|\\|");
			String	appinfo = ua_array[1];
			
			if(appinfo.indexOf("UUID") > -1){
				String[] appinfo_array = appinfo.split(",");
				
				if(appinfo_array.length > 0)
					appinfo_array[0] = appinfo_array[0].substring(appinfo_array[0].indexOf("=")+1);

				if(appinfo_array.length > 1)
					appinfo_array[1] = appinfo_array[1].substring(appinfo_array[1].indexOf("=")+1);
				
				return appinfo_array;
			}
		}
		 
		 return null;
    }
    
    /**
	 * HttpServletRequest 객체로부터 UUID 정보를 반환
	 * @param request
	 * @return uuid
	 */
    public static String  getUuid(HttpServletRequest request) {
    	String uuid = "";
    	
    	String[] appinfo = getAppInfo(request);
    	
    	if(appinfo != null) uuid = appinfo[0];
		 
		 return uuid;
    }
    

    
    /**
	 * HttpServletRequest 객체로부터 채널 정보를 반환
	 * @param request
	 * @return channelfg
	 */
    public static String  getChannelfg(HttpServletRequest request) {
    	String channelfg = "";
    	
    	String[] appinfo = getAppInfo(request);
    	
    	if(appinfo != null) channelfg = appinfo[1];
		 
		 return channelfg;
    }
    
}