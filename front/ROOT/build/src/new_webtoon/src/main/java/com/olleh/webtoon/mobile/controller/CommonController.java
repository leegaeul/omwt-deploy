package com.olleh.webtoon.mobile.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;

@Controller("MobileCommonController")
public class CommonController
{
	@Autowired
	BMService				bmService;
	
	@Value( "${system.pc.domain.ssl}" )
	private String				SYSTEM_PC_DOMAIN_SSL;

	@Value( "${system.pc.domain}" )
	private String				SYSTEM_PC_DOMAIN;
	
	/**
	 * 헤더 인클루드
	 * 블루멤버십 관련 데이터 전달
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/mobile/include/header.inc.kt" )
	public ModelAndView headerInc( HttpServletRequest request ) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( "mobile/include/header.inc" );
		
		mv.addObject( "SYSTEM_PC_DOMAIN_SSL", SYSTEM_PC_DOMAIN_SSL 	);
		mv.addObject( "SYSTEM_PC_DOMAIN"	, SYSTEM_PC_DOMAIN 		);

		if( !AuthUtil.isLogin( request ) ) return mv;

		//로그인한 사용자 정보
		UserDomain user = AuthUtil.getDecryptUserInfo( request );
		mv.addObject( "user", user );
		
		//마지막 블루멤버십 가입 정보
		BMJoinDomain bmJoin = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() );
		mv.addObject( "bmJoin", bmJoin );

		return mv;
	}
}
