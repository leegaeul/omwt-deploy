package com.olleh.webtoon.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class TRMiscUtils
{
	public static void closeQuietly( InputStream s )
	{
		if( s == null )
			return;
		try
		{
			s.close();
		} catch( Exception e )
		{
		}
	}


	public static void closeQuietly( OutputStream s )
	{
		if( s == null )
			return;
		try
		{
			s.close();
		} catch( Exception e )
		{
		}
	}


	public static void closeQuietly( Reader s )
	{
		if( s == null )
			return;
		try
		{
			s.close();
		} catch( Exception e )
		{
		}
	}


	public static void closeQuietly( Writer s )
	{
		if( s == null )
			return;
		try
		{
			s.close();
		} catch( Exception e )
		{
		}
	}


	public static void randomSleep( int min, int max )
	{
		if( min < 0 || max < 1 || max < min )
		{
			System.err.println( "min=" + min + ",max=" + max );
			return;
		}
		min = min * 1000;
		max = max * 1000;

		long sleepMillis = 0;
		if( min == max )
			sleepMillis = min;
		else
			sleepMillis = min + (int)( Math.random() * ( max - min + 1 ) );

		// System.out.println("SLEEP="+ sleepDurationSec);
		try
		{
			if( sleepMillis > 0 )
				Thread.sleep( sleepMillis );
		} catch( InterruptedException e )
		{
			e.printStackTrace();
		}
	}


	private static String	serverToken;


	/**
	 * 서버의 맥주소 가져오기
	 * 
	 * @return
	 */
	public static String getServerTokenValue() throws Exception
	{
		if( serverToken != null )
			return serverToken;

		synchronized( TRMiscUtils.class )
		{
			InetAddress ip = InetAddress.getLocalHost();

			if( ip == null )
				throw new RuntimeException( "IP 주소를 가져올 수 없습니다" );

			return ip.getHostAddress();
		}
	}
}
