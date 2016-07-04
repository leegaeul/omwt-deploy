package com.olleh.webtoon.common.dao.api.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

public class DeviceDomain
{
	private String	deviceid;		// 단말아이디(UUID)
	private String	idfg;
	private String	userid;
	private String	devicefg;		// 단말구분 IOS, android
	private String  marketfg;		// 마켓구분(google:구글마켓 olleh:올레마켓)
	private String	pushkey;
	private String	pushyn;			// 푸시수신여부
	private String	delyn;
	private String	delreason;
	private String	deldt;
	private String	regdt;
	private String	devicemodelno;
	private String	deviceosversion;
	private String	appversion;
	private String	loginyn;


	public DeviceDomain()
	{

	}


	public DeviceDomain( String deviceid )
	{
		setDeviceid( deviceid );
	}


	public DeviceDomain( String idfg, String userid )
	{
		setIdfg( idfg );
		setUserid( userid );
	}


	public String getDeviceid()
	{
		return deviceid;
	}


	public void setDeviceid( String deviceid )
	{
		this.deviceid = deviceid;
	}


	@JsonIgnore
	public String getIdfg()
	{
		return idfg;
	}


	public void setIdfg( String idfg )
	{
		this.idfg = idfg;
	}


	@JsonIgnore
	public String getUserid()
	{
		return userid;
	}


	public void setUserid( String userid )
	{
		this.userid = userid;
	}


	@JsonIgnore
	public String getDevicefg()
	{
		return devicefg;
	}


	public void setDevicefg( String devicefg )
	{
		if( devicefg != null )
			devicefg = devicefg.toLowerCase();

		this.devicefg = devicefg;
	}


	public String getPushkey()
	{
		return pushkey;
	}


	public void setPushkey( String pushkey )
	{
		this.pushkey = pushkey;
	}


	@JsonIgnore
	public String getPushyn()
	{
		return pushyn;
	}


	public void setPushyn( String pushyn )
	{
		this.pushyn = pushyn;
	}


	@JsonIgnore
	public String getDelyn()
	{
		return delyn;
	}


	public void setDelyn( String delyn )
	{
		this.delyn = delyn;
	}


	@JsonIgnore
	public String getDelreason()
	{
		return delreason;
	}


	public void setDelreason( String delreason )
	{
		this.delreason = delreason;
	}


	@JsonIgnore
	public String getDeldt()
	{
		return deldt;
	}


	public void setDeldt( String deldt )
	{
		this.deldt = deldt;
	}


	@JsonIgnore
	public String getRegdt()
	{
		return regdt;
	}


	public void setRegdt( String regdt )
	{
		this.regdt = regdt;
	}


	@JsonIgnore
	public String getDevicemodelno()
	{
		return devicemodelno;
	}


	public void setDevicemodelno( String devicemodelno )
	{
		this.devicemodelno = devicemodelno;
	}


	@JsonIgnore
	public String getDeviceosversion()
	{
		return deviceosversion;
	}


	public void setDeviceosversion( String deviceosversion )
	{
		this.deviceosversion = deviceosversion;
	}


	@JsonIgnore
	public String getAppversion()
	{
		return appversion;
	}


	public void setAppversion( String appversion )
	{
		this.appversion = appversion;
	}


	public String getLoginyn()
	{
		return loginyn;
	}


	public void setLoginyn( String loginyn )
	{
		this.loginyn = loginyn;
	}


	@JsonIgnore
	public String getMarketfg() 
	{
		return marketfg;
	}


	public void setMarketfg(String marketfg) 
	{
		this.marketfg = marketfg;
	}
}
