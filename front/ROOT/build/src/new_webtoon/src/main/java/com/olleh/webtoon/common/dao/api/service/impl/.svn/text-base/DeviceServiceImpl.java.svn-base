package com.olleh.webtoon.common.dao.api.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.olleh.webtoon.common.code.ApiResultCode;
import com.olleh.webtoon.common.dao.api.domain.ApiResultDomain;
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;
import com.olleh.webtoon.common.dao.api.persistence.DeviceMapper;
import com.olleh.webtoon.common.dao.api.service.iface.DeviceService;
import com.olleh.webtoon.common.dao.api.service.iface.MemberService;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.util.TRDateUtils;
import com.olleh.webtoon.exception.ApiException;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService
{
	@Autowired
	private DeviceMapper	deviceMapper;

	@Autowired
	private MemberService	memberService;

	@Transactional(readOnly=true)
	public DeviceDomain getDeviceByDeviceid( String deviceid )
	{
		return deviceMapper.selectByDeviceid( new DeviceDomain( deviceid ) );
	}
	
	@Transactional(readOnly=true)
	public List<DeviceDomain> getDeviceListByUser( String idfg, String userid )
	{
		DeviceDomain device = new DeviceDomain();
		device.setIdfg( idfg );
		device.setUserid( userid );
		
		return deviceMapper.selectByUser( device );
	}
	
	@Transactional(readOnly=false)
	public int addDevice( DeviceDomain device )
	{
		//푸시키가 중복된 단말을 삭제(앱 삭제 후 삭제되지 않은 단말 데이터)
		if( StringUtils.hasText( device.getPushkey() ) )
		{
			DeviceDomain duplicated = deviceMapper.selectByPushKey( device );
			
			if( duplicated != null )
			{
				removeDeviceByDeviceid( duplicated.getDeviceid() );
			}	
		}
		
		
		device.setPushyn( "N" );
		device.setRegdt( TRDateUtils.yyyyMMddhhmiss() );
		return deviceMapper.insert( device );
	}

	@Transactional(readOnly=false)
	public int modifyDeviceByDeviceid( DeviceDomain device )
	{
		return deviceMapper.updateByDeviceid( device );
	}

	@Transactional(readOnly=false)
	public int modifyDeviceByUser( DeviceDomain device )
	{
		return deviceMapper.updateByUser( device );
	}
	
	@Transactional(readOnly=false)
	public int modifyPushYn( String idfg, String userid, String pushyn )
	{
		if( !StringUtils.hasText( pushyn ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "pushyn 가 전달되지 않았습니다" ) );

		pushyn = pushyn.toUpperCase();

		if( !pushyn.equals( "Y" ) && !pushyn.equals( "N" ) )
			throw new ApiException( new ApiResultDomain( ApiResultCode.parameter_error, "pushyn 파라미터는 Y 또는 N 이어야 합니다" ) );

		DeviceDomain device = new DeviceDomain( idfg, userid );
		
		device.setPushyn( pushyn.toUpperCase() );

		return modifyDeviceByUser( device );
	}

	@Transactional(readOnly=false)
	public int saveDevice( DeviceDomain device )
	{
		DeviceDomain registeredDevice = getDeviceByDeviceid( device.getDeviceid() );
		
		if( registeredDevice != null )
		{
			//새로운 푸시키가 들어왔으면 삭제 상태를 초기화 한다
			if(  StringUtils.hasText( device.getPushkey() ) &&
				(
					!StringUtils.hasText( registeredDevice.getPushkey() ) ||
					!device.getPushkey().equals( registeredDevice.getPushkey()) 
				))
			{
				device.setDelyn( "N" );
				device.setDelreason( "" );
				device.setDeldt( "" );
			}
			
			return modifyDeviceByDeviceid( device );
		}
		else
			return addDevice( device );
	}

	@Transactional(readOnly=false)
	public int loginDevice( String deviceid, String idfg, String userid )
	{
		OllehUserDomain ollehUser = memberService.getUser( idfg, userid );

		// 디비에 유저가 없으면 오류
		if( ollehUser == null )
			throw new ApiException( new ApiResultDomain( ApiResultCode.user_not_found, String.format( "사용자를 찾을 수 없습니다. (%s)%s", idfg, userid ) ) );

		DeviceDomain device = new DeviceDomain( deviceid );

		device.setIdfg( idfg );
		device.setUserid( userid );
		device.setLoginyn( "Y" );

		return modifyDeviceByDeviceid( device );
	}

	@Transactional(readOnly=false)
	public int logoutDevice( String deviceid )
	{
		DeviceDomain device = new DeviceDomain( deviceid );

//		device.setIdfg( "" );
//		device.setUserid( "" );
		device.setLoginyn( "N" );

		return modifyDeviceByDeviceid( device );
	}
	
	@Transactional(readOnly=false)
	public int clearPushKey( String devicefg, String pushkey, String delreason )
	{
		DeviceDomain device = new DeviceDomain();

		device.setDevicefg( devicefg );
		device.setPushkey( pushkey );
		
		device = deviceMapper.selectByPushKey( device );
		
		if( device == null )
			return 0;
		
		device.setPushkey( "" );
		device.setDelyn( "Y" );
		device.setDelreason( delreason );
		device.setDeldt( TRDateUtils.yyyyMMddhhmiss() );

		return deviceMapper.updateByDeviceid( device );
	}
	
	@Transactional(readOnly=false)
	public int removeDeviceByDeviceid( String deviceid )
	{
		DeviceDomain device = new DeviceDomain();
		device.setDeviceid( deviceid );
		
		return deviceMapper.deleteByDeviceid( device );
	}
}
