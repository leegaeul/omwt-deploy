package com.olleh.webtoon.pc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.applay.domain.LinkSmsDomain;
import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.RecommendAppService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.SmsSendUtil;

@Controller
public class RecommendAppController {
	
	@Autowired
	RecommendAppService recommendAppService;
	
	/** 
	 * 추천앱 리스트화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/applay/recommendAppList.kt")
	public ModelAndView recommendAppList(@ModelAttribute("recommendAppDomain") RecommendAppDomain recommendAppDomain) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();

		//페이지 사이즈 기본값
		if(recommendAppDomain.getPageSize() == 0) recommendAppDomain.setPageSize(20);
		
		//ModelAndView 객체에 조회한 전체 건수를 넣는다.
		int totalCnt = recommendAppService.recommendAppListCnt(recommendAppDomain);
		modelAndView.addObject("totalCnt",totalCnt);
		
		//추천앱 목록을 조회한다.
		List<RecommendAppDomain>list = recommendAppService.recommendAppList(recommendAppDomain);
		modelAndView.addObject("recommendAppList", list);
			
		modelAndView.setViewName("pc/applay/recommendAppList");
		return modelAndView;
	}
	
	/**
	 * 추천앱 상세화면으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/applay/recommendAppDetail.kt")
	public ModelAndView recommendAppDetail(@ModelAttribute("recommendAppDomain") RecommendAppDomain recommendApp) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		RecommendAppDomain recommendAppDomain = recommendAppService.recommendAppDetail(recommendApp);
		modelAndView.addObject("recommendAppDomain",recommendAppDomain);
		
		modelAndView.setViewName("pc/applay/recommendAppDetail");
		
		return modelAndView;
	}
	
	/**
	 * 작품별 목록 화면으로 이동하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/applay/linkSmsRegistJsonProc.kt")
	public ModelAndView linkSmsRegistJsonProc(LinkSmsDomain linkSmsDomain, HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//로그인 여부 체크
		if(!AuthUtil.isLogin(request)){
			modelAndView.addObject("loginyn", "N");
			return modelAndView;
		}
		
		//회원정보 복호화
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		modelAndView.addObject("loginyn", "Y");
		
		//아이디 구분
		linkSmsDomain.setIdfg(userDomain.getIdfg());
		//이메일 아이디
		linkSmsDomain.setRegid(userDomain.getEmail());
		//현재날짜시간
		linkSmsDomain.setRegdt(DateUtil.getNowDate(1));
		
		//월별 30건 체크 후 저장
		int linkSmsCnt = recommendAppService.linkSmsCnt(linkSmsDomain);
		if(linkSmsCnt < 30){
			//SMS 전송
			if(SmsSendUtil.sendSmsResult(linkSmsDomain.getDestphone(), "", "[올레마켓]"+linkSmsDomain.getMsg())){
				
				//SMS 전송 성공 시 DB에 저장
				recommendAppService.linkSmsRegist(linkSmsDomain);
			}
		}else{
			modelAndView.addObject("linkSmsCnt", linkSmsCnt);
		}
		
		modelAndView.addObject("linkSmsDomain", null);		
		
		return modelAndView;		
	}
}