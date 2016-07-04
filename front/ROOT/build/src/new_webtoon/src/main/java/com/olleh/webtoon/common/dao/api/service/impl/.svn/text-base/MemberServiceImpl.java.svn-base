package com.olleh.webtoon.common.dao.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.code.ApiResultCode;
import com.olleh.webtoon.common.code.DeviceIdfgCode;
import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.api.domain.MemberDomain;
import com.olleh.webtoon.common.dao.api.service.iface.MemberService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.exception.ApiException;

@Service
public class MemberServiceImpl implements MemberService
{
	@Autowired
	private UserServiceIface		userService;

	@Override
	@Transactional(readOnly=true)
	public OllehUserDomain getUser( String idfg, String userid )
	{
		OllehUserDomain user = null;
		
		// 오픈아이디
		if( idfg.equals( DeviceIdfgCode.open ) )
		{
			user = userService.getUserInfoByEmail( userid );
		}
		// 올레아이디
		else if( idfg.equals( DeviceIdfgCode.olleh ) )
		{
			user = userService.ollehUserDetail( userid );
		}
		else
			throw new ApiException( new ApiResultDomain( ApiResultCode.login_required, "사용자 구분코드가 잘못되었습니다:" + idfg ) );
		
		return user;
	}

	@Override
	public MemberDomain convertUserToMember( OllehUserDomain ollehUser )
	{
		MemberDomain member = new MemberDomain();
		
		if( ollehUser instanceof UserDomain )
		{
			UserDomain user  = (UserDomain) ollehUser;
			
			member.setIdfg( DeviceIdfgCode.open );
			member.setEmail( user.getEmail() );
		}
		else
		{
			member.setIdfg( DeviceIdfgCode.olleh );
			member.setEmail( ollehUser.getOllehid() );
		}
		
		member.setNickname( ollehUser.getNickname() );
		member.setUsetermsyn( ollehUser.getUsetermsyn() );
		member.setMnameconurl( ollehUser.getMnameconurl() );
		
		return member;
	}
}
