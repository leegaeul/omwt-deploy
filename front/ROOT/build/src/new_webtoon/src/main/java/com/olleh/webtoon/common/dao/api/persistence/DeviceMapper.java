package com.olleh.webtoon.common.dao.api.persistence;

import java.util.List;
import com.olleh.webtoon.common.dao.api.domain.DeviceDomain;

public interface DeviceMapper
{
	public DeviceDomain selectByDeviceid( DeviceDomain device );


	public List<DeviceDomain> selectByUser( DeviceDomain device );


	public DeviceDomain selectByPushKey( DeviceDomain device );


	public int updateByDeviceid( DeviceDomain device );


	public int insert( DeviceDomain device );


	public int updateByUser( DeviceDomain device );


	public int deleteByDeviceid( DeviceDomain device );
}
