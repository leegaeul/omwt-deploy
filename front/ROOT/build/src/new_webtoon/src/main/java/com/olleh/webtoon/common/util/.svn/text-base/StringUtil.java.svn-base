/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : StringUtil.java
 * DESCRIPTION    : 문자열 관련 Util class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	
	protected static Log logger = LogFactory.getLog(StringUtil.class);
	
	/**
	 * 문자열을 넘겨받아 null이거나 공백 문자일 경우 true를 반환하는 메소드.
	 * 
	 * @param String src : 체크할 문자열
	 * @return boolean   :  null이거나 공백 문자인지 여부
	 */
    public static boolean isEmptyOrWhitespace(String src) {
    	    	
    	if(src==null || src.trim().length()==0) {
    		return true;
    	} else {
    		return false;
    	}
    	    	
    }
	 
	/**
	 * 문자열을 넘겨받아 null일 경우 빈 문자열을 반환하는 메소드.
	 * 
	 * @param String src : null 체크할 문자열
	 * @return String    : 변환한 문자열
	 */
    public static String changeNullToEmpty(String src) {
    	
    	if(src == null) {
    		return "";
    	} else {
    		return src;
    	}
    	
    }
        
	/**
     * 입력문자열의 특정 문자열을 다른 문자열로 바꾸어 반환한다.
     * 
     * @param String src    : 수정할 문자열
     * @param String oldStr : 찾을 문자열
     * @param String newStr : 바꿀 문자열
     * @return String       : 변환한 문자열
    */
    public static String replaceAll(String src, String oldStr, String newStr) {
        
    	if(src==null || src.length()==0) {
    		return "";
    	}
    	
    	if(oldStr==null || oldStr.length()==0) {
    		return src;
    	}
    	
        int idx = src.indexOf(oldStr);
        StringBuilder result = new StringBuilder();
        
        if(idx == -1) {
        	return src;
        }
        
        result.append(src.substring(0, idx) + newStr);
            
        if(idx + oldStr.length() < src.length()) {
        	result.append(replaceAll(src.substring(idx + oldStr.length(), src.length()),oldStr, newStr));
        }
        
        return result.toString();
        
    }

    /**
	 * 문자열과 구분자를 넘겨받아 구분자로 분리한 문자배열을 반환하는 메소드.
	 * 
	 * @param String src       : 배열로 분리할 문자열
	 * @param String delim     : 구분자
	 * @return String[] result : 구분자로 분리한 배열
	 */
    public static String[] split(String src, String delim) {
		
		if(src==null || src.length()==0) {
			return null;
		}

		String[] result = null;
		String sourceStr = src;
		int idx = 0;

		ArrayList<String> list = new ArrayList<String>();

		while(true) {
			idx = sourceStr.indexOf(delim);

			if(idx == -1) {
				list.add(sourceStr);
				break;
			} else {
				list.add(sourceStr.substring(0, idx));
				sourceStr = sourceStr.substring(idx + delim.length());
			}
		}

		result = new String[list.size()];

		for(int i=0; i<list.size(); i++) {
			result[i] = list.get(i);
		}

		return result;
		
	}
    
    /**
     * 일정 Byte 이상의 문자열 이후를 잘라내고 주어진 문자열을 추가한다.
     * 예)제목이 길 경우 뒷부분을 잘라내고 "..."을 붙인다.
     * 
     * @param1 String str    : 수정을 원하는 문자열
     * @param2 int size      : Byte 길이
     * @param3 String append : 추가하고자 하는 문자열
     * @return String retStr : 변환한 문자열
    */
    public static String cutString(String str, int size, String append) {
        
        String retStr = "";
        int strSize = 0;
        char[] charArray = str.toCharArray();
        byte[] byteArray = str.getBytes();

        // Byte 길이를 먼저 검사하여 입력된 길이보다 긴 경우만 처리
        if(byteArray.length > size) {
            for(int i=0; i < str.length(); i++) {
                // 2 Byte가 하나의 char인 경우
                if(charArray[i] > '\u00FF') {
                	strSize += 2;
                } else {
                	strSize++;
                }

                if(strSize > size) {
                    break;
                } else {
                	retStr += charArray[i];
                }
            }
            // append를 뒷 부분에 추가
            retStr += append;

            return retStr;
        }

        return str;
        
    }
    
    /**
     * 개행문자를 <br>로, " "을 "&nbsp;"로 치환한다.
     * @param String src     : 변환할 문자열
     * @return String result : 변환한 문자열
    */
    public static String changeEnter(String src) {
        
        String result = replaceAll(src, "\n", "<br>");
        result = replaceAll(result, "  ", "&nbsp;&nbsp;");
        
        return result;
        
    }
    
    /**
     * XSS 공격을 차단한다.
     * @param String src : 변환할 문자열
     * @return String    : 변환한 문자열
    */
    public static String chkXss(String src) {
        
    	if(src==null || src.equals("")) {
    		return "";
    	}
        
        StringBuffer buffer = new StringBuffer();

		String[] otoken = split(src, "<");
		String[] ttoken = split(src, "<");
		
        int cnt = 0;

        for(int i=0; i<otoken.length; i++) {
        	cnt++;
			String oStr = otoken[i];
			String tStr = ttoken[i].toLowerCase();
			
			boolean isXss = false;

			if(tStr.length()>5 && tStr.substring(0,6).equals("script")) isXss = true;
			else if(tStr.length()>5 && tStr.substring(0,6).equals("object")) isXss = true;
			else if(tStr.length()>0 && tStr.substring(0,1).equals("a")) isXss = true;
			else if(tStr.length()>2 && tStr.substring(0,3).equals("xmp")) isXss = true;
			else if(tStr.length()>4 && tStr.substring(0,5).equals("param")) isXss = true;

			if(cnt==1 && src.charAt(0)!='<') ;
			else if(!isXss && cnt ==1) buffer.append("<");
			else if(isXss) buffer.append("&lt;");
			else if(cnt > 1) buffer.append("<");
			
			buffer.append(oStr);
		}

        return buffer.toString();
        
    }
    
	/**
     * XSS 문제가 되는 태그 치환
     * @param String src : 변환할 문자열
     * @return String        : 변환한 문자열
     */
    public static String cleanXSS(String src) {
    	if(src == null) return src;
    	
    	src = src.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    	src = src.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
    	src = src.replaceAll("'", "&#39;");
    	src = src.replaceAll("\"", "&#34;");
    	src = src.replaceAll("eval\\((.*)\\)", "");
    	src = src.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
    	src = src.replaceAll("script", "");
    	return src;
    }
        	
	/**
     * 특수문자가 있을 경우 javascript에서 json 파일의 특수문자를 인식하도록 치환한다.
     * (\' : \\\'), (\" : \\\"), (\\ : \\\\), (\n : \\n), (\r\n : \\n)
     * @param String pm_sSrc : 변환할 문자열
     * @return String        : 변환한 문자열
     */
    public static String changeJson(String pm_sSrc) {
    	if(pm_sSrc == null) return "";
    	
    	StringBuffer lm_sBuffer = new StringBuffer();
        char[] charArray = pm_sSrc.toCharArray();
        for(int i=0; i < charArray.length; i++) {

            if(charArray[i] == '\'') {
            	lm_sBuffer.append("\\\'");
            }else if(charArray[i] == '\"') {
            	lm_sBuffer.append("\\\"");
            }else if(charArray[i] == '\\') {
            	lm_sBuffer.append("\\\\");
            }else if(charArray[i] == '\n') {
            	lm_sBuffer.append("\\n");
            }else if(charArray[i] == '\r') {
            	lm_sBuffer.append("");
            }else if(new Character(charArray[i]).hashCode()==0) {
            	lm_sBuffer.append("");
            }else{
            	lm_sBuffer.append(charArray[i]);
            }
            
        }
        
        return lm_sBuffer.toString();
    }
    
    /**
	 * @param param
	 * @param defaultVal
	 * @return
	 */
	public static int defaultInt(String param, int defaultVal){
		if ( (null == param) || "".equals(param) ) {
			return defaultVal;
		}
		param = param.trim();

		return Integer.valueOf(param);
	}
	
	/**
	 * @param param
	 * @return
	 */
	public static int defaultInt(String param){
		return defaultInt(param, 0);
	}
    
   /**
	 * 문자열을 넘겨받아 null일 경우 입력받은 값으로 변환
	 * @param param
	 * @param defaultStr
	 * @return
	 */
	public static String defaultStr(String param, String defaultVal){
		if ( (null == param) || "".equals(param) ) {
			return defaultVal;
		}
		return param.trim();
	}

	/**
	 * 문자열을 넘겨받아 null일 "" 반환
	 * @param param
	 * @return
	 */
	public static String defaultStr(String param){
		return defaultStr(param, "");
	}
	
	/**
	 * 문자열을 넘겨받아 10글자 이상일 경우 10글자까지 반환
	 * @param param
	 * @return
	 */
	public static String subStr(String param){
		return subStr(param, 10);
	}
	
	/**
	 * 문자열을 넘겨받아 글자수가 length 이상일 경우 length까지 반환
	 * @param param
	 * @return
	 */
	public static String subStr(String param, int length){
		if(param != null &&! "".equals(param))
			return param.length() > length ? param.substring(0, length) : param;
			
		return "";
	}
	
	public static String utf8ToEuckr( String str )
	{
		if( str == null || StringUtils.isEmpty( str ) ) return str;
		
		try
		{
			str = new String( URLDecoder.decode( str, "utf-8" ).getBytes( "euc-kr" ) );
		} catch( UnsupportedEncodingException e )
		{
			return "encoding conversion failed";
		}
		
		return str;
	}
	
	public static String euckrToUtf8( String str )
	{
		if( str == null || StringUtils.isEmpty( str ) ) return str;
		
		try
		{
			str = new String( URLDecoder.decode( str, "euc-kr" ).getBytes( "utf-8" ) );
		} catch( UnsupportedEncodingException e )
		{
			return "encoding conversion failed";
		}
		
		return str;
	}
	
	/**
	  * 숫자에 천단위마다 콤마 넣기
	  * @param int
	  * @return String
	  * */
	 public static String toNumFormat(int num) {
	  DecimalFormat df = new DecimalFormat("#,###");
	  return df.format(num);
	 }
}