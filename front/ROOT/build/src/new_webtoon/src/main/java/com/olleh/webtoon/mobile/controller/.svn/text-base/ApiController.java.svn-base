/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : AuthorController.java
 * DESCRIPTION    : 작가 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.mobile.controller;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olleh.webtoon.app.RequestParamModel;
import com.olleh.webtoon.common.code.ApiResultCode;
import com.olleh.webtoon.common.code.DevicefgCode;
import com.olleh.webtoon.common.code.DomainNameCode;
import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;
import com.olleh.webtoon.common.dao.api.domain.MemberDomain;
import com.olleh.webtoon.common.dao.api.service.iface.AppLatestVersionService;
import com.olleh.webtoon.common.dao.api.service.iface.DeviceService;
import com.olleh.webtoon.common.dao.api.service.iface.MemberService;
import com.olleh.webtoon.common.dao.api.service.impl.AppService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.MaskingUtil;
import com.olleh.webtoon.exception.ApiException;

@Controller( "MobileApiController" )
public class ApiController
{

	protected static Log			logger	= LogFactory.getLog( ApiController.class );
	
	@Inject
	private Provider<RequestParamModel>	requestParamProvider;

	@Autowired
	private HttpServletRequest		request;

	@Autowired
	private AppLatestVersionService	versionService;
	
	@Autowired
	private AppService				appService;

	@Autowired
	private DeviceService			deviceService;

	@Autowired
	private UserServiceIface		userService;

	@Autowired
	private MemberService			memberService;

	/**
	 * 단말 등록/업데이트 JSON 메소드
	 * 
	 */
	@RequestMapping( value = "/old/api/deviceRegist.kt" )
	public void deviceRegist( Model model, @ModelAttribute DeviceDomain device ) throws Exception
	{
		if( !StringUtils.hasText( device.getDevicefg() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "devicefg 가 전달되지 않았습니다" ) );

		if( !StringUtils.hasText( device.getDevicemodelno() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "devicemodelno 가 전달되지 않았습니다" ) );

		if( !StringUtils.hasText( device.getDeviceosversion() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "deviceosversion 가 전달되지 않았습니다" ) );

		if( !StringUtils.hasText( device.getAppversion() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "appversion 가 전달되지 않았습니다" ) );
		
		if( device.getDevicefg().equals( DevicefgCode.android ) && !StringUtils.hasText( device.getPushkey() ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "pushkey 가 전달되지 않았습니다" ) );
		
		if( device.getPushkey().trim().equals( "" ) )
			device.setPushkey( null );
		
		// appMeta정보 조회 파라미터
		String devicefg = device.getDevicefg();
		String marketfg = device.getMarketfg();
		String appversion = device.getAppversion();
		
		// 단말 등록
		deviceService.saveDevice( device );
		
		device = deviceService.getDeviceByDeviceid( device.getDeviceid() );
		
		if( 	device.getDevicefg().equals( DevicefgCode.android ) && 
				StringUtils.hasText( device.getDelyn() ) && 
				device.getDelyn().equals( "Y" ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.invalid_pushkey, "유효하지 않은 pushkey가 등록되어있습니다" ) );
		
		// 앱 최신 버전 정보(deprecated)
		model.addAttribute( "applatestversion", versionService.getAppLatestVersion() );
		
		// 앱 정보
		model.addAttribute( "appmeta", appService.getAppMeta( devicefg, marketfg, appversion ));

		// 등록된 단말 정보
		model.addAttribute( "device", deviceService.getDeviceByDeviceid( device.getDeviceid() ) );
	}


	/**
	 * 푸시설정변경 JSON 메소드
	 * 
	 */
	@RequestMapping( value = "/old/api/pushConfModify.kt" )
	public void pushConfModify( Model model, String deviceid, String pushyn ) throws Exception
	{
		if( true )
			throw new ApiException( new ApiResultDomain( ApiResultCode.system_error, "v0.7에서 삭제된 api 입니다. /old/api/usetermsModify.kt를 사용하세요" ) ); 
	}


	/**
	 * 정보/광고 수신동의 JSON 메소드
	 * 
	 */
	@RequestMapping( value = "/old/api/usetermsModify.kt" )
	public void usetermsModify( Model model, String deviceid, String usetermsyn ) throws Exception
	{
		OllehUserDomain ollehUser = getOllehUserParam();
		ollehUser.setUsetermsyn( usetermsyn );
		
		//정보/광고 수신동의 변경
		userService.usetermsynUpdate( ollehUser );
		
		// 푸시설정 변경 : 꼭 하지 않아도 되지만 일단 필드가 있으니 처리함
		deviceService.modifyPushYn( getDeviceParam().getIdfg(), getDeviceParam().getUserid(), usetermsyn );
		

		// 회원정보 가져오기
		MemberDomain member = memberService.convertUserToMember( ollehUser );
		//이메일 마스킹 처리
		member.setEmail(MaskingUtil.getMaskingId(member.getEmail()));

		model.addAttribute( DomainNameCode.member,  member);
	}


	/**
	 * 회원정보 수신 JSON 메소드
	 * 
	 */
	@RequestMapping( value = "/old/api/memberInfo.kt" )
	public void memberInfo( Model model, String deviceid ) throws Exception
	{
		// 회원정보 가져오기
		MemberDomain member = getMemberParam();
		//이메일 마스킹 처리
		member.setEmail(MaskingUtil.getMaskingId(member.getEmail()));
		
		model.addAttribute( "member",  member);
	}


	/**
	 * 로그아웃 JSON 메소드
	 * 
	 */
	@RequestMapping( value = "/old/api/logout.kt" )
	public void logout( Model model, String deviceid ) throws Exception
	{
		deviceService.logoutDevice( deviceid );
		
		model.addAttribute( DomainNameCode.result, new ApiResultDomain( ApiResultCode.success, "", "로그아웃 되었습니다" ) );
	}
	
	/**
	 * 로그인 테스트 JSON 메소드
	 * api를 테스트할 수 있도록 임의로 단말에 로그인 처리를 함
	 * 
	 */
	@RequestMapping( value = "/old/api/test/login.kt" )
	public void logout( Model model, @ModelAttribute DeviceDomain device ) throws Exception
	{
		deviceService.loginDevice( device.getDeviceid(), device.getIdfg(), device.getUserid() );
	}
	
	private DeviceDomain getDeviceParam()
	{
		return requestParamProvider.get().getDevice();
	}
	
	private OllehUserDomain getOllehUserParam()
	{
		return requestParamProvider.get().getOllehUser();
	}
	
	private MemberDomain getMemberParam()
	{
		return requestParamProvider.get().getMember();
	}
	
}
