/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : DateUtil.java
 * DESCRIPTION    : 날짜 관련 util class
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0              InJae Yeo      2014-04-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
	
	public static final int MONDAY    = 1;
	public static final int TUESDAY   = 2;
	public static final int WEDNESDAY = 3;
	public static final int THURSDAY  = 4;
	public static final int FRIDAY    = 5;
	public static final int SATURDAY  = 6;
	public static final int SUNDAY    = 7;
	
	//날짜(시간) 구분자 상수정의
	private static final String[][] div = {
		{".","."," ",".","."},
		{"/","/","",":",":"},
		{"년 ","월 ","일","시 ","분 "},
		{"-","-","",":",":"}
	};

	/**
	 * yyyymmddHHMMDD : 년월일시분초에 대한 String을 구분자로 표기하여 가져온다.
	 * @param String dt          : yyyymmddHHMMDD(년월일시분초) 
	 * @param int divNo          : 표기 구분값(0,1,2)
	 * @param boolean isViewTime : 시간 표시여부
	 * @return String            : 구분자를 추가한 날짜
	 */
	public static String getDivDate(String dt, int divNo, boolean isViewTime) {

		if(dt == null) {
			return "";
		}

		StringBuilder result = new StringBuilder();

		String year = "";     //년
		String month = "";    //월
		String day = "";      //일
		String hour = "";     //시
		String minute = "";   //분
		String second = "";   //초

		year = dt.substring(0, 4);
		month = dt.substring(4, 6);

		if(dt.length() != 8) {
			day = dt.substring(6, 8);
		} else {
			day = dt.substring(6);
		}

		if(dt.length() == 14) {
			hour = dt.substring(8, 10);
			minute = dt.substring(10, 12);
			second = dt.substring(13);
		}

		result.append(year + div[divNo][0]);
		result.append(month + div[divNo][1]);
		result.append(day + div[divNo][2]);

		if(isViewTime) {
			result.append(" " + hour + div[divNo][3]);
			result.append(minute);
		}

		return result.toString();
	}

	/**
	 * 현재 날짜를 문자열로 가져온다.
	 * 구분자에 따라 날짜를 8자리, 14자리로 가져온다. 
	 *
	 * @param int flag     : 날짜 구분자 (0:8자리 날짜, 1:14자리 날짜+시간)
	 * @return String date : 날짜 문자열
	 */
	public static String getNowDate(int flag) {

		String date = "";       //반환할 날짜

		//현재 날짜를 세팅한다.
		Date lm_oDate = new Date();
		GregorianCalendar lm_oGCal = new GregorianCalendar();
		lm_oGCal.setTime(lm_oDate);

		String year = Integer.toString(lm_oGCal.get(Calendar.YEAR));        //년
		String month = Integer.toString(lm_oGCal.get(Calendar.MONTH) + 1);  //월
		String day = Integer.toString(lm_oGCal.get(Calendar.DATE));         //일
		String hour = Integer.toString(lm_oGCal.get(Calendar.HOUR_OF_DAY)); //시
		String minute = Integer.toString(lm_oGCal.get(Calendar.MINUTE));    //분
		String second = Integer.toString(lm_oGCal.get(Calendar.SECOND));    //초

		//월일시분초가 1자리로 나올 경우 2자리로 맞춘다.
		if(month.length() == 1) {
			month = "0" + month;
		}

		if(day.length() == 1) {
			day = "0" + day;
		}

		if(hour.length() == 1) {
			hour = "0" + hour;
		}

		if(minute.length() == 1) {
			minute = "0" + minute;
		}

		if(second.length() == 1) {
			second = "0" + second;
		}

		//Flag 값에 맞는 날짜 조합을 만든다.
		if(flag == 0) {
			date = year + month + day;
		} else if(flag == 1) {
			date = year + month + day + hour + minute + second;
		}

		return date;

	}
	
	/**
	 * 현재 날짜를 기준으로 해당요일을 가져온다.
	 * 구분자에 따라 날짜를 8자리, 14자리로 가져온다. 
	 *
	 * @param int flag     : 요일 구분자 (1:월요일 ~ 7:일요일)
	 * @return String date : 날짜 문자열(YYYYMMDDHHMMSS)
	 */
	public static String getDate(int flag) {
		
		int dayOfWeek = getDayOfWeek(); //1:월요일 ~ 7:일요일
		String date   = getDateAfterDay(dayOfWeek >= flag ? flag - dayOfWeek : (flag - dayOfWeek) - 7);
		
		return date;

	}
	
	/**
	 * 현재 날짜를 기준으로 월~금 날짜 정보를 Map에 담아 반환 한다. 
	 *
	 * @return Map<String, Object> dayOfWeekMap : 날짜 문자열(YYYYMMDDHHMMSS)
	 */
	public static Map<String, Object> getDayOfWeekList() {
		
		Map<String, Object> dayOfWeekMap = new HashMap<String, Object>();
		
		dayOfWeekMap.put("monday",    getDate(DateUtil.MONDAY).toString());
		dayOfWeekMap.put("tuesday",   getDate(DateUtil.TUESDAY).toString());
		dayOfWeekMap.put("wednesday", getDate(DateUtil.WEDNESDAY).toString());
		dayOfWeekMap.put("thursday",  getDate(DateUtil.THURSDAY).toString());
		dayOfWeekMap.put("friday",    getDate(DateUtil.FRIDAY).toString());
		dayOfWeekMap.put("saturday",  getDate(DateUtil.SATURDAY).toString());
		dayOfWeekMap.put("sunday",    getDate(DateUtil.SUNDAY).toString());

		return dayOfWeekMap;

	}

	/**
	 * 파라미터로 넘어온 초만큼 이전 시간을 YYYYMMDDHHMMSS 형태의 문자열로 가져온다.
	 *
	 * @param int sec      : 계산할 초
	 * @return String date : 날짜 문자열
	 */
	public static String getDateBeforeSecond(int sec) {

		String date = "";       //반환할 날짜

		//현재 날짜를 세팅한다.
		Date lm_oDate = new Date(System.currentTimeMillis() - (sec * 1000));
		GregorianCalendar lm_oGCal = new GregorianCalendar();
		lm_oGCal.setTime(lm_oDate);

		String year = Integer.toString(lm_oGCal.get(Calendar.YEAR));        //년
		String month = Integer.toString(lm_oGCal.get(Calendar.MONTH) + 1);  //월
		String day = Integer.toString(lm_oGCal.get(Calendar.DATE));         //일
		String hour = Integer.toString(lm_oGCal.get(Calendar.HOUR_OF_DAY)); //시
		String minute = Integer.toString(lm_oGCal.get(Calendar.MINUTE));    //분
		String second = Integer.toString(lm_oGCal.get(Calendar.SECOND));    //초

		//월일시분초가 1자리로 나올 경우 2자리로 맞춘다.
		if(month.length() == 1) month = "0" + month;
		if(day.length() == 1) day = "0" + day;
		if(hour.length() == 1) hour = "0" + hour;
		if(minute.length() == 1) minute = "0" + minute;
		if(second.length() == 1) second = "0" + second;

		date = year + month + day + hour + minute + second;

		return date;

	}

	/**
	 * 파라미터로 넘어온 일만큼 추가하여 YYYYMMDDHHMMSS 형태의 문자열로 가져온다.
	 *
	 * @param int day      : 추가 일
	 * @return String date : 날짜 문자열
	 */
	public static String getDateAfterDay(int amount) {

		String date = "";       //반환할 날짜
		
		//현재 날짜를 세팅한다.
		Date lm_oDate = new Date(System.currentTimeMillis());
		GregorianCalendar lm_oGCal = new GregorianCalendar();
		lm_oGCal.setTime(lm_oDate);
		lm_oGCal.add(GregorianCalendar.DAY_OF_MONTH, amount);

		String year = Integer.toString(lm_oGCal.get(Calendar.YEAR));        //년
		String month = Integer.toString(lm_oGCal.get(Calendar.MONTH) + 1);  //월
		String day = Integer.toString(lm_oGCal.get(Calendar.DATE));         //일
		String hour = Integer.toString(lm_oGCal.get(Calendar.HOUR_OF_DAY)); //시
		String minute = Integer.toString(lm_oGCal.get(Calendar.MINUTE));    //분
		String second = Integer.toString(lm_oGCal.get(Calendar.SECOND));    //초

		//월일시분초가 1자리로 나올 경우 2자리로 맞춘다.
		if(month.length() == 1) month = "0" + month;
		if(day.length() == 1) day = "0" + day;
		if(hour.length() == 1) hour = "0" + hour;
		if(minute.length() == 1) minute = "0" + minute;
		if(second.length() == 1) second = "0" + second;

		date = year + month + day + hour + minute + second;

		return date;

	}

	/**
	 * 오늘기준으로 만 나이를 계산함
	 * @param birthday 대상의 생년월일
	 * @return 만 나이
	 */
	public static int getManAge(String birthday) {
		if (("" + birthday).length() != 8)
			return -1;
		
		//현재 날짜를 세팅한다.
		Date lm_oDate = new Date(System.currentTimeMillis());
		GregorianCalendar lm_oGCal = new GregorianCalendar();
		lm_oGCal.setTime(lm_oDate);

		// 오늘날짜
		int year = lm_oGCal.get(Calendar.YEAR);        //년
		int month = lm_oGCal.get(Calendar.MONTH);  //월
		int day = lm_oGCal.get(Calendar.DATE);         //일

		// 대상생일
		int bYear = Integer.parseInt(birthday.substring(0, 4));
		int bMonth = Integer.parseInt(birthday.substring(4, 6)) - 1;
		int bDay = Integer.parseInt(birthday.substring(6, 8));

		// 만나이
		int manAge = year - bYear - 1;

		// 생일이 지난경우 +1
		if ((month > bMonth) || (month == bMonth && day >= bDay))
			manAge++;

		return manAge;
	}

	/**
	 * 현재의 요일을 구한다.(1: 월 2: 화 3: 수 4: 목 5:금 )
	 * @return 요일 
	 */
	public static int getDayOfWeek(){
		Calendar rightNow = Calendar.getInstance();
		int day_of_week = rightNow.get(Calendar.DAY_OF_WEEK) -1;
		if( day_of_week == 0) {
			day_of_week = 7;
		}
		return day_of_week;
	}
	
	/**
	 * 만 나이를 리턴한다.
	 * @return 나이 
	 */
	public static int getAge(String birthDt) {
		String nowDt = DateUtil.getNowDate(0);
		
		int birthYear = Integer.parseInt(birthDt.substring(0, 4));
		int nowYear = Integer.parseInt(nowDt.substring(0, 4));
		int birthMMDD = Integer.parseInt(birthDt.substring(4, 8));
		int nowMMDD = Integer.parseInt(nowDt.substring(4, 8));
		
		int age = nowYear - birthYear + 1;
		
		if(nowMMDD < birthMMDD) { // 생일이 지나지 않은 경우
			return age - 2;
		} else { // 생일이 지난 경우
			return age - 1;
		}
	}

}