package com.olleh.webtoon.app;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;
import com.olleh.webtoon.common.dao.api.domain.MemberDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;

/**
 * 인터셉터에서 쿼리한 데이터를 다른 component에서 사용할 수 있도록 함
 * @author bigmandoo
 *
 */
@Component
@Scope( "request" )
public class RequestParamModel
{
	private DeviceDomain	device;
	private OllehUserDomain	ollehUser;
	private MemberDomain	member;


	public DeviceDomain getDevice()
	{
		return device;
	}


	public void setDevice( DeviceDomain device )
	{
		this.device = device;
	}


	public OllehUserDomain getOllehUser()
	{
		return ollehUser;
	}


	public void setOllehUser( OllehUserDomain ollehUser )
	{
		this.ollehUser = ollehUser;
	}


	public MemberDomain getMember()
	{
		return member;
	}


	public void setMember( MemberDomain member )
	{
		this.member = member;
	}

}
