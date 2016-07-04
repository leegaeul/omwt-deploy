package com.olleh.webtoon.interceptor;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.olleh.webtoon.common.code.DomainNameCode;
import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPaymentDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMPrdDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMSummaryModel;
import com.olleh.webtoon.common.dao.bluemembership.service.BMOrderService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMPrdService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMTermService;
import com.olleh.webtoon.common.dao.bluemembership.service.InicisService;
import com.olleh.webtoon.common.dao.bluemembership.service.MobiService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.olltoon.common.util.OlltoonAuthUtil;

@Component
public class BluemembershipInterceptor extends HandlerInterceptorAdapter implements AppConstant, DomainNameCode
{

	protected Log						logger	= LogFactory.getLog( BluemembershipInterceptor.class );

	@Inject
	private Provider<BMSummaryModel>	summaryProvider;

	@Autowired
	private BMService					bmService;

	@Autowired
	private BMTermService				bmTermService;

	@Autowired
	private InicisService				inicisService;

	@Autowired
	private MobiService					mobiService;

	@Autowired
	private BMPrdService				prdService;

	@Autowired
	private BMTermService				termService;

	@Autowired
	private BMOrderService				orderService;


	/**
	 * 로그인한 사용자의 블루멤버십 정보를 요약
	 */
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{
		logger.info( "BluemembershipInterceptor.preHandle" );
		
		BMSummaryModel sum = summaryProvider.get();
		
		if( !AuthUtil.isLogin( request )  && !OlltoonAuthUtil.isLogin(request) ) return true;
		

		// 웹툰 회원 정보
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));

		UserDomain user;
		if(ot_token != null && !"".equals(ot_token)) {
			user = OlltoonAuthUtil.getDecryptUserInfo( request );
		}else{
			user = AuthUtil.getDecryptUserInfo( request );
		}
		
		BMJoinDomain join = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() );
		if( join == null ) return true;
		sum.setActiveJoin( join );
		
		BMJoinTermDomain term = bmTermService.getCurrentTermForUser( user.getIdfg(), user.getEmail() );
		sum.setCurrentTerm( term );
		
		BMPaymentDomain payment = bmService.getLastPaymentByJoinseq( join.getJoinseq() );
		sum.setLastPayment( payment );
		
		BMPrdDomain prd = prdService.getPrdByPrdcode( join.getPrdcode() );
		sum.setUsingPrd( prd );
		
		Integer ownBlueBerryCount = orderService.getOwnBlueBerryForUser( user.getIdfg(), user.getEmail() );
		sum.setOwnBlueBerryCount( ownBlueBerryCount );

		return true;
	}
}
