/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PaymentServiceIface.java
 * DESCRIPTION    : 결제
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        mslee      2014-05-23      init
 *****************************************************************************/

package com.olleh.webtoon.olltoon.mediapack.service.iface;

import java.util.Map;

public interface MediaPackServiceIface {
	
	public Map<String, Object> getMediaPackUser(Map<String, Object> params);
	
	public void registMediaPackUser(Map<String, Object> params);
}