package com.olleh.webtoon.common.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MapperLoggerUtil {
	private static Logger logger = Logger.getLogger(MapperLoggerUtil.class);

	/**
	 * 메소드 이름, args type, args name 을 메소드 시작시에 로그에 남긴다
	 *
	 * @param joinPoint
	 */
	@Before("execution(* com.olleh.webtoon.common.dao..persistence.*Mapper.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().toLongString();
		StringBuffer logMessage = new StringBuffer(methodName.substring(methodName.indexOf("persistence.")+12));
		logger.debug(logMessage);
	}	

	/**
	 * 메소드의 return 값을 로그에 남긴다.
	 *
	 * @param staticPart
	 * @param result
	 */
	//@After("execution(* com.olleh.webtoon.common.dao..persistence.*Mapper.*(..))")
	public void afterMethod(StaticPart staticPart, Object result) {
		String methodName = staticPart.getSignature().toLongString();
		logger.info(methodName + " returning:[ " + result + " ]");
	}

	/**
	 * 실행되는 메소드가 exception을 발생시키고 종료했을시, exception을 로그에 남긴다.
	 *
	 * @param ex
	 */
	//@AfterThrowing("execution(* com.olleh.webtoon.common.dao..persistence.*Mapper.*(..))")
	public void afterThrowing(Exception ex) {
		logger.debug(ex);
	}

}