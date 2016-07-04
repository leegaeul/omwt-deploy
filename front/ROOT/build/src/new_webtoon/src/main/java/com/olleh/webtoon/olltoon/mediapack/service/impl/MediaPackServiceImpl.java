/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PaymentServiceImpl.java
 * DESCRIPTION    : 결제
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.mediapack.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.olltoon.mediapack.persistence.MediaPackMapper;
import com.olleh.webtoon.olltoon.mediapack.service.iface.MediaPackServiceIface;

@Service("mediaPackService")
@Repository
public class MediaPackServiceImpl implements MediaPackServiceIface {
	
	@Autowired
	private MediaPackMapper mediaPackMapper;
	
	@Transactional(readOnly=true)
	public Map<String, Object> getMediaPackUser(Map<String, Object> params) {
		return mediaPackMapper.getMediaPackUser(params);
	}
	
	@Transactional(readOnly=false)
	public void registMediaPackUser(Map<String, Object> params) {
		mediaPackMapper.registMediaPackUser(params);
	}
}