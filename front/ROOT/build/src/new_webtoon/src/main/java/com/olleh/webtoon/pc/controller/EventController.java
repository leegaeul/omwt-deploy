package com.olleh.webtoon.pc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.applay.domain.EventDomain;
import com.olleh.webtoon.common.dao.applay.domain.EventImageDomain;
import com.olleh.webtoon.common.dao.applay.domain.SnsDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.EventService;
import com.olleh.webtoon.common.dao.banner.domain.BannerDomain;
import com.olleh.webtoon.common.dao.banner.service.iface.BannerService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	BannerService bannerService;
	
	/** 
	 * 이벤트 리스트화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/applay/eventList.kt")
	public ModelAndView eventList(@ModelAttribute("eventDomain") EventDomain eventDomain) throws Exception{
	
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(eventDomain.getPageSize() == 0) eventDomain.setPageSize(10);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = eventService.eventListCnt(eventDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//Event 목록을 조회한다.
		List<EventDomain>list = eventService.eventList(eventDomain);
		modelAndView.addObject("eventList", list);
		
		//이벤트 상단 배너
		List<BannerDomain> bannerList = bannerService.bannerList("event");
		modelAndView.addObject("bannerList", bannerList);
			
		modelAndView.setViewName("pc/applay/eventList");
		return modelAndView;
	}
	
	/**
	 * 이벤트 상세화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/applay/eventDetail.kt")
	public ModelAndView eventDetail(@ModelAttribute("eventDomain") EventDomain event) throws Exception{
				
		ModelAndView modelAndView = new ModelAndView();
		
		//이벤트 상세 조회
		EventDomain eventDomain = eventService.eventDetail(event);
		modelAndView.addObject("eventDomain",eventDomain);
		
		//이벤트 이미지 리스트
		List<EventImageDomain> list = eventService.eventImageList(event.getEventseq());
		modelAndView.addObject("eventImageList",list);
		
		modelAndView.setViewName("pc/applay/eventDetail");
		
		return modelAndView;
	}
	
	/**
	 * 공유하기 정보를 insert하는 RequestMapping 메소드 
	 * @return
	 */
	@RequestMapping(value="/applay/setSocialShare.kt")
	public ModelAndView setSocialShare(@ModelAttribute("snsDomain") SnsDomain snsDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(AuthUtil.isLogin(request))
		{
			//유저정보 가져오기
			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
	
			snsDomain.setRegid(user.getEmail());
			snsDomain.setIdfg(user.getIdfg());
		}

		snsDomain.setArticlefg(StringUtil.cleanXSS(StringUtil.defaultStr(snsDomain.getArticlefg(), "")));
		snsDomain.setSnsfg(StringUtil.cleanXSS(StringUtil.defaultStr(snsDomain.getSnsfg(), "")));
		snsDomain.setRegdt(DateUtil.getNowDate(1));
		
		eventService.insertSns(snsDomain);

		modelAndView.setViewName("mappingJacksonJsonView");			
		
		modelAndView.addObject("snsDomain", null);

		return modelAndView;
	}
	
	/**
	 * 이벤트 상세화면으로 redirect 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value="/event/newwebtoon/event.kt")
	public ModelAndView eventRedirect(HttpServletRequest request) throws Exception {		
		ModelAndView modelAndView = new ModelAndView();
		
		String num = RequestUtil.getParameter(request, "num", true);
		
		String host = RequestUtil.getHost(request);
		
		//모바일 체크
		boolean isMobile = false;
		String userAgent = request.getHeader("User-Agent").toLowerCase();		
		if(userAgent.matches(".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*")
				|| userAgent.substring(0,4).matches("1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-")) {
			isMobile = true;	
		}
		
		if(isMobile || host.indexOf("m.webtoon.olleh") != -1) {
			modelAndView.setViewName("redirect:/m/event/newwebtoon/event.kt?num="+num);
		}else{
			modelAndView.setViewName("pc/applay/eventRedirect");	
		}

		return modelAndView;
	}
}