package com.olleh.webtoon.common.dao.api.domain;

import com.olleh.webtoon.common.util.MessageUtil;

public class MemberDomain
{
	private String	idfg;			// 아이디구분: open, olleh
	private String	email;			// 이메일
	private String	nickname;		// 닉네임
	private String	usetermsyn;		// 정보활용약관동의여부
	private String	mnameconurl;	// 모바일용네임콘URL


	public String getEmail()
	{
		return email;
	}


	public void setEmail( String email )
	{
		this.email = email;
	}


	public String getNickname()
	{
		return nickname;
	}


	public void setNickname( String nickname )
	{
		this.nickname = nickname;
	}


	public String getUsetermsyn()
	{
		return usetermsyn;
	}


	public void setUsetermsyn( String usetermsyn )
	{
		this.usetermsyn = usetermsyn;
	}


	public String getMnameconurl()
	{
		if(mnameconurl == null || "".equals(mnameconurl)) 
			mnameconurl = "/images/mobile/namecon/pc_namecon_05.png";
		
		if(mnameconurl != null && mnameconurl.indexOf("/images/mobile/") > -1){
			mnameconurl = MessageUtil.getSystemMessage("system.mobile.domain") + mnameconurl;
		}else if(mnameconurl.indexOf(MessageUtil.getSystemMessage("system.image.server.domain")) == -1){
			mnameconurl = MessageUtil.getSystemMessage("system.image.server.domain") +mnameconurl;
		}
		
		return mnameconurl;
	}


	public void setMnameconurl( String mnameconurl )
	{
		this.mnameconurl = mnameconurl;
	}


	public String getIdfg()
	{
		return idfg;
	}


	public void setIdfg( String idfg )
	{
		this.idfg = idfg;
	}
}
