package com.olleh.webtoon.olltoon.mediapack.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.olltoon.mediapack.service.iface.MediaPackServiceIface;

@Controller
public class MediaPackController {
	
	@Autowired
	private MediaPackServiceIface mediaPackService;
	
	@RequestMapping(value = "/ot/media/mediapack.kt")
	public ModelAndView otUserDetail(HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("olltoon/media/mediapack");
				
		return modelAndView;
	}
	
	@RequestMapping(value = "/ot/media/registMediaPackUser.kt")
	public ModelAndView registMediaPackUser(HttpServletRequest request, @RequestParam Map<String,Object> params) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//비정상적인 접속시 에러 처리
		if(!AuthUtil.isLogin(request)) {
			throw new Exception();
		}
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		
		if(!"open".equals(user.getIdfg())) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errorcode", "IDFG_ERROR");
			modelAndView.addObject("errormsg", "오픈아이디를 이용해주세요.");
			return modelAndView;
		}
		
		params.put("idfg", user.getIdfg());
		params.put("userid", user.getEmail());
		Map<String, Object> registUser = mediaPackService.getMediaPackUser(params);
		if(registUser != null) {
			if("N".equals((String)registUser.get("chargeyn"))) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "이미 신청한 휴대폰 번호 입니다.\n휴대폰 번호를 확인해주세요.");
				return modelAndView;
			}
			
			if("Y".equals((String)registUser.get("chargeyn"))) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "고객님, 이미 블루베리가 지급되었습니다.\n'MY베리' 메뉴에서 확인해주세요.");
				return modelAndView;
			}	
		}
		
		modelAndView.addObject("erroryn", "N");
		params.put("regdt", DateUtil.getNowDate(1));
		mediaPackService.registMediaPackUser(params);
		
		return modelAndView;
	}
}