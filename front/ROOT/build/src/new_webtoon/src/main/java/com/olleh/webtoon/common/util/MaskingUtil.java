/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : MaskingUtil.java
 * DESCRIPTION    : 문자열 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

public class MaskingUtil {

	/**
	 * 아이디 마스킹 처리
	 * 
	 * @param String
	 *            str : 아이디
	 * @return String : 마스킹 처리된 아이디
	 */
	public static String getMaskingId(String str) {

		if (str.indexOf("@") > -1) {

			return str.indexOf("@") > 3 ? str.substring(0, 3)
					+ addChar(str.indexOf("@") - 3, "*")
					+ str.substring(str.indexOf("@")) : str;
		}

		return str.length() > 3 ? str.substring(0, 3)
				+ addChar(str.length() - 3, "*") : str;
	}

	public static String addChar(int cnt, String ch) {

		return cnt == 1 ? ch : ch + addChar(cnt - 1, ch);
	}

	/** 
	 * 아이디 마스킹 처리 
	 * @param String str : 아이디
	 * @return String : 마스킹 처리된 아이디 
	*/
	public static String getMaskingUserId(String userid) {
		int maskingLength = 3;
		if (userid.indexOf("@") > -1) {
			String id = userid.substring(0, userid.indexOf("@"));
			String email = userid.substring(userid.indexOf("@"));
			if (id.length() > maskingLength) {
				return id.substring(0, id.length() - maskingLength) + "***"
						+ email;
			} else {
				return userid;
			}
		} else {
			if (userid.length() > maskingLength) {
				return userid.substring(0, userid.length() - maskingLength)
						+ "***";
			} else {
				return userid;
			}
		}
	}
}