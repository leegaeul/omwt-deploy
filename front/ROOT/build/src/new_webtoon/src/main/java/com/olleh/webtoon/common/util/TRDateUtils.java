package com.olleh.webtoon.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang3.StringUtils;

public class TRDateUtils
{
	private static final int	MILLSECS_PER_DAY	= 1000 * 60 * 60 * 24;


	public static String yyyymmddFromDate( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int yyyy = cal.get( Calendar.YEAR );
		int mm = cal.get( Calendar.MONTH ) + 1;
		int dd = cal.get( Calendar.DAY_OF_MONTH );
		return String.format( "%04d%02d%02d", yyyy, mm, dd );
	}


	public static String yyyymmddhhmissFromDate( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int yyyy = cal.get( Calendar.YEAR );
		int mm = cal.get( Calendar.MONTH ) + 1;
		int dd = cal.get( Calendar.DAY_OF_MONTH );
		int hh = cal.get( Calendar.HOUR_OF_DAY );
		int mi = cal.get( Calendar.MINUTE );
		int ss = cal.get( Calendar.SECOND );
		return String.format( "%04d%02d%02d%02d%02d%02d", yyyy, mm, dd, hh, mi, ss );
	}
	
	public static String yyyymmddhhmiFromDate( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int yyyy = cal.get( Calendar.YEAR );
		int mm = cal.get( Calendar.MONTH ) + 1;
		int dd = cal.get( Calendar.DAY_OF_MONTH );
		int hh = cal.get( Calendar.HOUR_OF_DAY );
		int mi = cal.get( Calendar.MINUTE );
		return String.format( "%04d%02d%02d%02d%02d", yyyy, mm, dd, hh, mi );
	}


	public static String yyyyFromDate( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int yyyy = cal.get( Calendar.YEAR );
		return String.format( "%04d", yyyy );
	}


	public static String yyyymmFromDate( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int yyyy = cal.get( Calendar.YEAR );
		int mm = cal.get( Calendar.MONTH ) + 1;
		return String.format( "%04d%02d", yyyy, mm );
	}


	public static String mmddFromDate( Date date )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		int mm = cal.get( Calendar.MONTH ) + 1;
		int dd = cal.get( Calendar.DAY_OF_MONTH );
		return String.format( "%02d%02d", mm, dd );
	}


	private static final String	DATE_FORMAT_YYMMDD			= "yyMMdd";
	private static final String	DATE_FORMAT_YYMM			= "yyMM";
	private static final String	DATE_FORMAT_YYYY			= "yyyy";
	private static final String	DATE_FORMAT_YYYYMM			= "yyyyMM";
	private static final String	DATE_FORMAT_YYYYMMDD		= "yyyyMMdd";
	private static final String	DATE_FORMAT_HHMISS			= "hhmiss";
	private static final String	DATE_FORMAT_HH				= "hh";
	private static final String	DATE_FORMAT_YYYYMMDDHHMISS	= "yyyyMMddhhmiss";


	public static String yyMM()
	{
		return TRDateUtils.dateString( DATE_FORMAT_YYMM );
	}


	public static String yyMMdd()
	{
		return TRDateUtils.dateString( DATE_FORMAT_YYMMDD );
	}


	public static String yyyyMMdd()
	{
		return TRDateUtils.dateString( DATE_FORMAT_YYYYMMDD );
	}


	public static String yyyyMM()
	{
		return TRDateUtils.dateString( DATE_FORMAT_YYYYMM );
	}


	public static String yyyy()
	{
		return TRDateUtils.dateString( DATE_FORMAT_YYYY );
	}


	public static String yyyyMMddhhmiss()
	{
		return TRDateUtils.dateString( DATE_FORMAT_YYYYMMDDHHMISS );
	}


	public static String hhmiss()
	{
		return TRDateUtils.dateString( DATE_FORMAT_HHMISS );
	}


	public static String hh()
	{
		return TRDateUtils.dateString( DATE_FORMAT_HH );
	}


	private static String dateString( String formatStr )
	{
		Calendar cal = Calendar.getInstance();
		return _dateString( cal, formatStr );
	}


	public static String yyyyMMdd( long milli )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( milli );
		return _dateString( cal, DATE_FORMAT_YYYYMMDD );
	}


	public static String hhmiss( long milli )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( milli );
		return _dateString( cal, DATE_FORMAT_HHMISS );
	}


	public static String hh( long milli )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( milli );
		return _dateString( cal, DATE_FORMAT_HH );
	}


	public static String yyMM( long milli )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( milli );
		return _dateString( cal, DATE_FORMAT_YYMM );
	}


	/**
	 * 이 메소드는 외부에서 직접 호출하지 않고 내부에서만 사용하겠음
	 * 
	 * @param formatStr
	 * @return
	 */
	private static String _dateString( Calendar cal, String formatStr )
	{

		// TODO DateFormat 객체는 너무 무거운 객체이므로 간단하게 구현하자
		// 매우 간단한 구현
		if( formatStr.equals( DATE_FORMAT_YYYYMMDD ) )
		{
			return String.format( "%04d%02d%02d", cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ) + 1, cal.get( Calendar.DAY_OF_MONTH ) );
		}
		else if( formatStr.equals( DATE_FORMAT_YYYYMM ) )
		{
			return String.format( "%04d%02d", cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ) + 1 );
		}
		else if( formatStr.equals( DATE_FORMAT_YYMMDD ) )
		{
			return String.format( "%02d%02d%02d", cal.get( Calendar.YEAR ) % 100, cal.get( Calendar.MONTH ) + 1, cal.get( Calendar.DAY_OF_MONTH ) );
		}
		else if( formatStr.equals( DATE_FORMAT_YYYY ) )
		{
			return String.format( "%04d", cal.get( Calendar.YEAR ) );
		}
		else if( formatStr.equals( DATE_FORMAT_YYMM ) )
		{
			return String.format( "%02d%02d", cal.get( Calendar.YEAR ) % 100, cal.get( Calendar.MONTH ) + 1 );
		}

		else if( formatStr.equals( DATE_FORMAT_HHMISS ) )
		{
			return String.format( "%02d%02d%02d", cal.get( Calendar.HOUR_OF_DAY ), cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		}
		else if( formatStr.equals( DATE_FORMAT_HH ) )
		{
			return String.format( "%02d", cal.get( Calendar.HOUR_OF_DAY ) );
		}
		else if( formatStr.equals( DATE_FORMAT_YYYYMMDDHHMISS ) )
		{
			return String.format( "%04d%02d%02d%02d%02d%02d", cal.get( Calendar.YEAR ), cal.get( Calendar.MONTH ) + 1,
					cal.get( Calendar.DAY_OF_MONTH ), cal.get( Calendar.HOUR_OF_DAY ), cal.get( Calendar.MINUTE ), cal.get( Calendar.SECOND ) );
		}
		throw new IllegalArgumentException( "Unsupported format:" + formatStr );
	}


	public static Date dateFromYyyymmdd( String yyyymmdd )
	{
		int yyyy = Integer.parseInt( yyyymmdd.substring( 0, 4 ) );
		int mm = Integer.parseInt( yyyymmdd.substring( 4, 6 ) );
		int dd = Integer.parseInt( yyyymmdd.substring( 6, 8 ) );
		Calendar cal = Calendar.getInstance();
		cal.set( yyyy, mm - 1, dd );
		return cal.getTime();
	}


	public static Date dateFromYyyymmddhhmiss( String yyyymmddhhmiss )
	{
		if( StringUtils.isEmpty(yyyymmddhhmiss) || yyyymmddhhmiss.length() < 8 ) return null;
		int hh = 0, mi = 0, ss = 0;
		int yyyy = Integer.parseInt( yyyymmddhhmiss.substring( 0, 4 ) );
		int mm = Integer.parseInt( yyyymmddhhmiss.substring( 4, 6 ) );
		int dd = Integer.parseInt( yyyymmddhhmiss.substring( 6, 8 ) );
		if( yyyymmddhhmiss.length() >= 10 ) hh = Integer.parseInt( yyyymmddhhmiss.substring( 8, 10 ) );
		if( yyyymmddhhmiss.length() >= 12 ) mi = Integer.parseInt( yyyymmddhhmiss.substring( 10, 12 ) );
		if( yyyymmddhhmiss.length() >= 14 ) ss = Integer.parseInt( yyyymmddhhmiss.substring( 12, 14 ) );
		Calendar cal = Calendar.getInstance();
		cal.set( yyyy, mm - 1, dd, hh, mi, ss );
		return cal.getTime();
	}


	public static int countDaysBetween( String startYmd, String endYmd )
	{
		return countDaysBetween( dateFromYyyymmdd( startYmd ), dateFromYyyymmdd( endYmd ) );
	}


	public static int countDaysBetween( Date start, Date end )
	{
		long startTime = start.getTime();
		long endTime = end.getTime();

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( startTime );
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MILLISECOND, 0 );
		startTime = cal.getTimeInMillis();

		cal.setTimeInMillis( endTime );
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MILLISECOND, 0 );
		endTime = cal.getTimeInMillis();

		long day = (long)( ( endTime - startTime ) / MILLSECS_PER_DAY );
		// System.out.println(String.format("end:%d - start:%d = %d(%d days)",
		// endTime, startTime, endTime - startTime, day));

		return (int)day;
	}


	public static Date addMonth( Date now, int monthCount )
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( now );
		gc.add( GregorianCalendar.MONTH, monthCount );
		return gc.getTime();
	}


	public static Date addDate( Date now, int dateCount )
	{
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime( now );
		gc.add( GregorianCalendar.DATE, dateCount );
		return gc.getTime();
	}


	public static int computeAgeByBirthDay( Date birthDay )
	{
		Date today = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy" );

		return Integer.parseInt( dateFormat.format( today ) ) - Integer.parseInt( dateFormat.format( birthDay ) ) + 1;
	}


	/**
	 * 월의 마지막 날짜를 반환
	 * 
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth( Date month )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( month );
		cal.add( Calendar.MONTH, 1 );
		cal.set( Calendar.DAY_OF_MONTH, 1 );
		cal.add( Calendar.DATE, -1 );

		return cal.getTime();
	}
}
