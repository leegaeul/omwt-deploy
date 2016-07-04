/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : MainController.java
 * DESCRIPTION    : 웹툰 메인 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.applay.domain.HotAppDomain;
import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.RecommendAppService;
import com.olleh.webtoon.common.dao.banner.domain.BannerDomain;
import com.olleh.webtoon.common.dao.banner.service.iface.BannerService;
import com.olleh.webtoon.common.dao.toon.domain.TodayToonDomain;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CommentUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.pc.cache.MainCache;

@Controller
public class MainController {
			
	@Autowired
	ToonService toonService;
	
	@Autowired
	BannerService bannerService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	RecommendAppService recommendAppService;
	
	protected static Log logger = LogFactory.getLog(MainController.class);
	private final String[] dayOfWeek = new String[]{"monday", "tuesday", "wendesday", "thursday", "friday", "saturday", "sunday"};
			
	/**
	 * 웹툰 메인 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/main.kt")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();		
		
		//비밀번호 변경기간이 3개월 이상이면 변경팝업 노출
		if (AuthUtil.isLogin(request))
		{
			String u_pw_chg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
					com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_chg"), "");
			
			if("".equals(u_pw_chg)){
				UserDomain user = AuthUtil.getDecryptUserInfo(request);
				
				if("open".equals(user.getIdfg())){	
					UserDomain userDomain = userServiceIface.getUserInfoByEmail(user.getEmail());	
					
					//비밀번호 변경기간이 3개월 이상이면 변경팝업 노출 위한 쿠키
					String pwchange_monthdiff = userDomain.getPwchange_monthdiff();
					int monthdiff = new Integer(pwchange_monthdiff);
					
					if(monthdiff >= 3){
						String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
						String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
						
						CookieUtil.setCookie(response, "u_pw_chg", "Y", -1, cookiePath, cookieDomain);
						CookieUtil.setCookie(response, "u_pw_chg_pg", "main", -1, cookiePath, cookieDomain);
						
						modelAndView.setViewName("pc/main_redirect");
						return modelAndView;
					}
				}
			}
			
			//비밀번호 8자리로 변경하기 팝업 노출을 위한 쿠키 - 20150303
			String u_pw_leg_chg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
					com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_leg_chg"), "");
			
			String u_pw_chg_pg = com.olleh.webtoon.common.util.StringUtil.defaultStr(
					com.olleh.webtoon.common.util.CookieUtil.getCookie(request, "u_pw_chg_pg"), "");
			
			if("".equals(u_pw_leg_chg)){
				UserDomain user = AuthUtil.getDecryptUserInfo(request);
				
				if("open".equals(user.getIdfg())){
					
					String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
					String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
					
					CookieUtil.setCookie(response, "u_pw_leg_chg", "Y", -1, cookiePath, cookieDomain);
					
					modelAndView.setViewName("pc/main_redirect");
					return modelAndView;
				}
			}
			
			if(!"main".equals(u_pw_chg_pg)){
				UserDomain user = AuthUtil.getDecryptUserInfo(request);
				
				if("open".equals(user.getIdfg())){
					
					String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
					String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
					
					CookieUtil.setCookie(response, "u_pw_chg_pg", "main", -1, cookiePath, cookieDomain);
					
					modelAndView.setViewName("pc/main_redirect");
					return modelAndView;
				}
			}
		}
		
		modelAndView.setViewName("pc/main");
		
		String host = RequestUtil.getHost(request);
		
		//모바일 체크
		boolean isMobile = false;
		String userAgent = request.getHeader("User-Agent").toLowerCase();		
		if(userAgent.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")
				|| userAgent.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
			isMobile = true;	
		}
		
		if(isMobile) {
			modelAndView.setViewName("redirect:/m/toon/weekList.kt");	
		} else if(host.indexOf("m.webtoon.olleh") != -1) {
			modelAndView.setViewName("redirect:/m/toon/weekList.kt");	
		} else {
			
			//메인화면의 ModelAndView객체를 캐시에서 가져오고 없으면 구성해서 넣는다.
			MainCache mainCache = MainCache.getInstance();
			ModelAndView cachedModelAndView = mainCache.getCachedModelAndView();
			
			if(cachedModelAndView != null) {
				modelAndView = cachedModelAndView;
			} else {
				//메인배너 가져오기(Cache 처리)
				List<BannerDomain> bannerList = bannerService.bannerList("pcmain");
				modelAndView.addObject("bannerList", bannerList);
				
				//상단 웹툰 가져오기(Cache 처리)
				List<ToonDomain> topToonList = toonService.topToonList("day");
				modelAndView.addObject("topToonList", topToonList);
				
				//오늘의 웹툰 오늘 날짜 기준 요일(월~일) 목록 가져오기
				Map<String, Object> week = toonService.mainTodayWebtoonDateList(DateUtil.getDayOfWeekList());
								
				//요일별 메인 오늘의 웹툰 가져오기(Cache 처리)
				List<Map<String,Object>> webtoonTypeList = toonService.mainTodayWebtoonTypeList(week);
				List<TodayToonDomain> webtoonItemList = toonService.mainTodayWebtoonItemList(week);
				
				int typeIdx = 0;
				int itemIdx = 0;
				
				for(int i = 0; i < week.size(); i++)
				{
					List<Map<String,Object>> weekWebtoonTypeList = new ArrayList<Map<String,Object>>(); 
					List<TodayToonDomain> weekWebtoonItemList = new ArrayList<TodayToonDomain>();
					
					//요일별 템플릿 정보 가져오기
					for(int j = typeIdx; j < webtoonTypeList.size(); j++)
					{
						Map<String, Object> type = (Map<String, Object>)webtoonTypeList.get(j);
						
						if(StringUtil.defaultInt(type.get("weekorder").toString()) == i + 1)
							weekWebtoonTypeList.add(webtoonTypeList.get(j));
						else{
							typeIdx = j;
							break;
						}
					}
					
					//요일별 웹툰 정보 가져오기
					for(int z = itemIdx; z < webtoonItemList.size(); z++)
					{
						TodayToonDomain item = (TodayToonDomain)webtoonItemList.get(z);
						
						if(item.getWeekorder() == i + 1)
							weekWebtoonItemList.add(webtoonItemList.get(z));
						else{
							itemIdx = z;
							break;
						}
					}
					
					modelAndView.addObject(dayOfWeek[i] + "WebtoonTypeList", weekWebtoonTypeList);
					modelAndView.addObject(dayOfWeek[i] + "WebtoonItemList", weekWebtoonItemList);
				}
				
				//Hot앱 정보 가져오기(Cache 처리)
				HotAppDomain mainHotappDetail = recommendAppService.mainHotappDetail();
				List<HotAppDomain> mainHotappItemList = recommendAppService.mainHotappItemList();		
				
				//인기앱 정보 가져오기
				RecommendAppDomain recommendAppDomain = recommendAppService.rnbPopularApp();
				modelAndView.addObject("recommendAppDomain", recommendAppDomain);
				
				modelAndView.addObject("mainHotappDetail", mainHotappDetail);
				modelAndView.addObject("mainHotappItemList", mainHotappItemList);
				
				//정보를 모두 취합한 후 캐시에 넣는다.
				mainCache.putCache(modelAndView);
			}
		
		}		
		
		return modelAndView;
		
	}
	
	/**
	 * 웹툰 메인 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	
	/**
	 * 웹툰 메인화면에서 최신 댓글 목록 처리하는 RequestMapping 메소드
	 * 클라이언트와 JSON 규격으로 통신
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/commentJsonList.kt")
	public ModelAndView commecntJsonList(@RequestParam Map<String,Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//모니터링 유저 설정
		param.put("musers", CommentUtil.getMoniteringUsers());
		
		//실시간 댓글 목록 가져오기(JSON 처리)
		List<Map<String,Object>> mainRealTimeCommentList = toonService.mainRealTimeCommentList(param);
		modelAndView.addObject("commentList", mainRealTimeCommentList);
		
		return modelAndView;
		
	}
	
}