package com.olleh.webtoon.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import com.olleh.webtoon.app.RequestParamModel;
import com.olleh.webtoon.common.code.ApiResultCode;
import com.olleh.webtoon.common.code.DomainNameCode;
import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;
import com.olleh.webtoon.common.dao.api.service.iface.DeviceService;
import com.olleh.webtoon.common.dao.api.service.iface.MemberService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.exception.ApiException;

@Component
public class ApiInterceptor extends HandlerInterceptorAdapter implements AppConstant, DomainNameCode
{

	protected Log					logger	= LogFactory.getLog( ApiInterceptor.class );

	@Inject
	private Provider<RequestParamModel>	requestParamProvider;

	@Autowired
	private DeviceService			deviceService;

	@Autowired
	private UserServiceIface		userService;

	@Autowired
	private MemberService			memberService;


	/**
	 * request parameter logging
	 * 단말등록이 필요한 요청, 로그인이 필요한 요청에 따라 파라미터 유효성을 검사하고 각각에 대한 유효성을 판단함
	 * 유효한 요청에 대해 디비 쿼리를 통해 DeviceDomain, OllehUserDomain, MemberDomain 을 생성해서 RequestParamModel에 저장함(컨트롤러나 서비스에서 사용할 수 있도록)  
	 */
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{
//		logger.info( "public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception" );
		
		//파라미터 로깅
		try
		{
			Map<String, Object> reqParam = WebUtils.getParametersStartingWith( request, "" );

			if( reqParam.keySet().size() > 0 )
				logger.debug( "#### API CALL URL:" + request.getRequestURI() );

			Iterator<String> it = reqParam.keySet().iterator();
			while( it.hasNext() )
			{
				String key = it.next();
				Object val = reqParam.get( key );
				if( val.getClass().isArray() )
				{
					String[] values = (String[])val;
					logger.debug( "##PARAM:" + key + "=[" + StringUtils.arrayToCommaDelimitedString( values ) + "]" );
				}
				else
				{
					logger.debug( "##PARAM:" + key + "=[" + val + "]" );
				}
			}
		} catch( Exception e )
		{
			e.printStackTrace();
		}
		
		
		RequestParamModel requestParam = requestParamProvider.get();
		
		String deviceid = request.getParameter( "deviceid" );

		//deviceid 파라미터 체크 - 모든 api 호출에 필수 파라미터
		if( deviceid == null )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "deviceid 가 전달되지 않았습니다" ) );

		DeviceDomain device = null;
		
		//단말 등록이 필요한 api 체크
		if( isDeviceRegisterRequiredRequest( request ) )
		{
			device = deviceService.getDeviceByDeviceid( deviceid );

			//디비에 없는 deviceid면 오류
			if( device == null )
				throw new ApiException( new ApiResultDomain( ApiResultCode.device_not_found, "등록된 단말이 아닙니다. deviceid=" + deviceid ) );

			requestParam.setDevice( device );			
		}
		
		//로그인이 필요한 api 체크
		if( isLoginRequiredRequest( request ) )
		{
			//단말정보에 로그인 정보가 없으면 오류
			if( device == null || 
				!StringUtils.hasText( device.getUserid() ) || 
				!StringUtils.hasText( device.getIdfg()   ) ||
				!StringUtils.hasText( device.getLoginyn() ) ||
				!device.getLoginyn().equals( "Y" ) 
			)
				throw new ApiException( new ApiResultDomain( ApiResultCode.login_required ) );

			OllehUserDomain ollehUser = memberService.getUser( device.getIdfg(), device.getUserid() );

			//디비에 유저가 없으면 오류
			if( ollehUser == null )
				throw new ApiException( new ApiResultDomain( ApiResultCode.user_not_found, String.format( "사용자를 찾을 수 없습니다. (%s)%s", device.getIdfg(),
						device.getUserid() ) ) );

			requestParam.setOllehUser( ollehUser );
			requestParam.setMember( memberService.convertUserToMember( ollehUser ) );
		}


		return true;
	}


	/**
	 * 컨트롤러에서 리턴한 model에 result 도메인이 없을경우 처리성공으로 간주하고 model에 result 도메인을 넣음
	 * 모든 api request의 view를 mappingJacksonJsonView로 설정
	 */
	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv ) throws Exception
	{
		if( mv == null )
			mv = new ModelAndView();

		if( mv.getModelMap().get( DomainNameCode.result ) == null )
		{
			mv.addObject( DomainNameCode.result, new ApiResultDomain( ApiResultCode.success ) );
		}
		
		mv.getModelMap().remove( "deviceDomain" );

		mv.setViewName( JSON_VIEW_NAME );
	}

	
	/**
	 * 등록된 단말이 필요한 요청인지 판단
	 * @param request
	 * @return
	 */
	private boolean isDeviceRegisterRequiredRequest( HttpServletRequest request )
	{
		if( isLoginRequiredRequest(request) ) return true;
		
		
		List<String> apiPathList = new ArrayList<String>();
		
		apiPathList.add( "/old/api/test/login.kt" );
		
		for( String path : apiPathList )
		{
			if( request.getRequestURI().indexOf( path ) > -1 )
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * 로그인이 필요 요청인지 판단
	 * @param request
	 * @return
	 */
	private boolean isLoginRequiredRequest( HttpServletRequest request )
	{
		List<String> apiPathList = new ArrayList<String>();
		
		apiPathList.add( "/old/api/pushConfModify.kt" 	);
		apiPathList.add( "/old/api/usetermsModify.kt" 	);
		apiPathList.add( "/old/api/memberInfo.kt" 		);
		apiPathList.add( "/old/api/logout.kt" 			);
		
		for( String path : apiPathList )
		{
			if( request.getRequestURI().indexOf( path ) > -1 )
				return true;
		}
		
		return false;
	}
}
