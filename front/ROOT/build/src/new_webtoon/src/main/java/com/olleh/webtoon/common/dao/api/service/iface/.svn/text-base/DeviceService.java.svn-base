package com.olleh.webtoon.common.dao.api.service.iface;

import java.util.List;
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;

public interface DeviceService
{
	public DeviceDomain getDeviceByDeviceid( String deviceId );


	//특정 유저로 로그인된 단말 가져오기
	public List<DeviceDomain> getDeviceListByUser( String idfg, String userid );


	public int addDevice( DeviceDomain device );


	public int modifyDeviceByDeviceid( DeviceDomain device );


	public int saveDevice( DeviceDomain device );


	// pushyn 업데이트
	public int modifyPushYn( String idfg, String userid, String pushyn );


	// 단말 로그인 처리
	public int loginDevice( String deviceid, String idfg, String userid );


	// 단말 로그아웃 처리
	public int logoutDevice( String deviceid );
	
	
	// 푸시 실패한 단말 토큰 지우기
	public int clearPushKey( String devicefg, String pushkey, String delreason );
	
	
	// 단말 삭제
	public int removeDeviceByDeviceid( String deviceid );
}
