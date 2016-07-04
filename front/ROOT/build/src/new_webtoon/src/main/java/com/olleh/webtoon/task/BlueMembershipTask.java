package com.olleh.webtoon.task;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;

@Component
public class BlueMembershipTask
{
	protected Log		logger	= LogFactory.getLog( BlueMembershipTask.class );

	@Autowired
	private BMService	bmService;
	
	@Value( "${system.batch.enabled}" )
	private boolean batchEnabled;
	
	private boolean batchInProgress = false;
	

/*	@Scheduled( cron = "1 * * * * *" )
	public void batchEnabled()
	{
		logger.error("batchEnabled : " + batchEnabled);
	}*/

	/**
	 * 매일 0시 1분에 자동결제를 태스크를 시작
	 * 모든 front-end was 에서 동시다발적으로 처리
	 */
	//테스트를 위해 10분 간격으로 실행
//	@Scheduled( fixedDelay = 600000 )
//	@Scheduled( cron = "1 1 0 * * *" )
	@Scheduled( fixedDelay = 59000 )	//59초마다 실행
	public void dailyTask()
	{
		if( !batchEnabled )
		{
			//logger.error("batch not enabled");
			return;
		}
		
		// 서버별 태스크 시작시간을 가급적 분산시키기 위해 랜덤슬립
		// TRMiscUtils.randomSleep(0, 10);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime( new Date() );
		
		//현재시간이 0시 0분 xx초가 아니라면 종료
		if( !(cal.get( Calendar.HOUR_OF_DAY ) == 0 && cal.get(  Calendar.MINUTE ) == 0 ) )
			return;
			
		
		//배치가 이미 돌고 있으면 종료
		if( batchInProgress ) return;
		batchInProgress = true;
		
		logger.error("dailyTask");
		
		while( true )
		{
			BMJoinDomain join = null;
			
			try
			{
				// 토큰을 마킹함, 마킹되지 않으면 태스크 종료
				if( bmService.markUpdatetokenForRenewal() == 0 )
				{
					logger.error("nothing marked");
					break;
				}

				// 토큰이 마킹된 가입이력 가져옴
				join = bmService.getLastJoinByUpdatetoken();

				// 마킹된 가입이력이 없으면 태스크 종료
				if( join == null )
				{
					logger.error("no marked join data");
					break;
				}
				
				logger.error("processing join data:joinseq=" + join.getJoinseq());
				
				// 마킹된 가입이력에 대해 연장, 해지, 변경처리
				bmService.membershipDailyProcess( join );
				
				logger.error("processing join data end:joinseq=" + join.getJoinseq());
			}
			catch( Exception e )
			{
				logger.error( e.getMessage() );
				logger.error( ExceptionUtils.getStackTrace( e ) );
				
				// 오류가 발생하면 실패처리
				if( join != null)
				{
					logger.error("mark join data as fail:joinseq=" + join.getJoinseq());
					bmService.processMembershipFailure( join, null, e );
					logger.error("mark join data as fail end:joinseq=" + join.getJoinseq());
				}
			}
			finally
			{
				// 토큰 마킹 해제
				if( bmService.unMarkUpdatetoken() > 1 )
					logger.error( "토큰 중복 마킹된 가입이력이 있었음" );
			}
		}
		
		batchInProgress = false;
	}
}
