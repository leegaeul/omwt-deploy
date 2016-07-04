package com.olleh.webtoon.common.dao.eventpromotion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.common.dao.eventpromotion.domain.EventContestDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventCouponDomain;
import com.olleh.webtoon.common.dao.eventpromotion.domain.EventPromotionDomain;
import com.olleh.webtoon.common.dao.eventpromotion.persistence.EventPromotionMapper;
import com.olleh.webtoon.common.dao.eventpromotion.service.iface.EventPromotionService;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.FileUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Service("eventPromotionService")
@Repository
public  class EventPromotionServiceImpl implements EventPromotionService
{
	@Autowired
	private EventPromotionMapper eventPromotionMapper;
	
	/**
	 * 아이스크림 이벤트 참여 여부 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 참여여부(Y) 
	 */
	@Transactional(readOnly=true)
	public String geIceEventDuplicateyn(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectIceEventDuplicateyn(eventPromotionDomain);
	}
	
	/**
	 * 아이스크림 이벤트 등록
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void iceEventRegistProc(EventPromotionDomain eventPromotionDomain){
		eventPromotionMapper.insertIceEvent(eventPromotionDomain);
	}
	
	/**
	 * 이벤트 유효성 체크
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 이벤트 유효성 여부(Y/N)
	 */
	@Transactional(readOnly=true)
	public String getEventJoinyn(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectEventJoinyn(eventPromotionDomain);
	}
	
	/**
	 * 아이스크림 이벤트1 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return String : 당첨 날짜
	 */
	@Transactional(readOnly=true)
	public String getIceMemberEventPrizeWinner(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectIceMemberEventPrizeWinner(eventPromotionDomain);
	}
	
	/**
	 * 아이스크림 이벤트2 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	@Transactional(readOnly=true)
	public List<String> getIceSnsEventPrizeWinner(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectIceSnsEventPrizeWinner(eventPromotionDomain);
	}
	
	/**
	 * SNS 응원글 목록 총 갯수 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 응원글 목록 총 갯수
	 */
	@Transactional(readOnly=true)
	public int getIceSnsCmtTotalCnt(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectIceSnsCmtTotalCnt(eventPromotionDomain);
	}

	/**
	 * SNS 응원글 목록 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<EventPromotionDomain> : 응원글 목록
	 */
	@Transactional(readOnly=true)
	public List<EventPromotionDomain> getIceSnsCmtList(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectIceSnsCmtList(eventPromotionDomain);
	}
	
	/**
	 * SNS 응원글 등록
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void iceSnsEventRegistProc(EventPromotionDomain eventPromotionDomain){
		eventPromotionMapper.insertIceSnsEvent(eventPromotionDomain);
	}
	
	/**
	 * SNS 이벤트 중복 참여 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 참여 횟수
	 */
	@Transactional(readOnly=true)
	public int getIceSnsEventHistory(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectIceSnsEventHistory(eventPromotionDomain);
	}

	/**
	 * 공모전 이벤트 필명 중복 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return int : 등록필명수
	 */
	@Transactional(readOnly=true)
	public int getContestPnmCheckCnt(EventContestDomain eventContestDomain) {
		return eventPromotionMapper.selectContestPnmCnt(eventContestDomain);
	}

	/**
	 * 공모전 이벤트 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void contestRegistProc(EventContestDomain eventContestDomain) {
		
		//임시 파일 복사 처리
		FileUtil.tempFileCopy("event", StringUtil.defaultStr(eventContestDomain.getFilepath(), ""));
		
		eventPromotionMapper.insertContestEvent(eventContestDomain);
		
	}

	/**
	 * 쿠폰 이벤트 등록
	 * 
	 * @param eventCouponDomain eventCouponDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void couponRegistProc(EventCouponDomain eventCouponDomain) {
		eventPromotionMapper.updateCouponEvent(eventCouponDomain);
		
	}

	/**
	 * 쿠폰번호로 쿠폰 이벤트 목록 조회
	 * 
	 * @param String couponnum
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	@Transactional(readOnly=true)
	public EventCouponDomain getCouponEventByCouponnum(String couponnum){
		return eventPromotionMapper.selectCouponEventByCouponnum(couponnum);
	}

	/**
	 * 아이디로 쿠폰 이벤트 목록 조회
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return EventCouponDomain : 쿠폰 이벤트
	 */
	@Transactional(readOnly=true)
	public EventCouponDomain getCouponEventById(EventCouponDomain eventCouponDomain){
		return eventPromotionMapper.selectCouponEventById(eventCouponDomain);
	}

	/**
	 * 쿠폰 플러스 이벤트 등록
	 * 
	 * @param eventCouponDomain eventCouponDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void couponPlusEventRegistProc(EventCouponDomain eventCouponDomain) {
		eventPromotionMapper.insertPlusCouponEvent(eventCouponDomain);
		
	}

	/**
	 * 아이디로 쿠폰 플러스 이벤트
	 * 
	 * @param EventCouponDomain eventCouponDomain
	 * @return EventCouponDomain : 쿠폰 플러스 이벤트
	 */
	@Transactional(readOnly=true)
	public EventCouponDomain getCouponPlusEventById(EventCouponDomain eventCouponDomain){
		return eventPromotionMapper.selectPlusCouponEventById(eventCouponDomain);
	}

	/**
	 * 공모전 접수현황 조회
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return int : 접명수
	 */	
	@Transactional(readOnly=true)
	public int getContestCnt(EventContestDomain eventContestDomain) {
		return eventPromotionMapper.selectContestCnt(eventContestDomain);
	}
	
	/**
	 * 이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	@Transactional(readOnly=true)
	public List<EventPromotionDomain> getCouponEventPrizeWinner(EventPromotionDomain eventPromotionDomain){
		return eventPromotionMapper.selectCouponEventPrizeWinner(eventPromotionDomain);
	}
	
	/**
	 * 공모전 이벤트(댓글, 스티커) 등록
	 * 
	 * @param EventContestDomain eventContestDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void contestEventRegistProc(EventContestDomain eventContestDomain){
		eventContestDomain.setRegdt(DateUtil.getNowDate(1));

		int hiscnt = eventPromotionMapper.selectContestEventHisCnt(eventContestDomain);
		if(hiscnt > 0){
			eventContestDomain.setPhonenum(eventPromotionMapper.selectContestPhoneNumber(eventContestDomain));
		}
		
		if("comment".equals(eventContestDomain.getEventfg())){
			
			//최근 댓글 순번 가져오기
			eventContestDomain.setCommentseq(eventPromotionMapper.selectRecentCommentseq(eventContestDomain));
			
			eventPromotionMapper.insertContestComment(eventContestDomain);
			
		}else if("sticker".equals(eventContestDomain.getEventfg())){
			eventPromotionMapper.insertContestSticker(eventContestDomain);
		}else{
			eventPromotionMapper.insertContestDefault(eventContestDomain);
		}
	}
	
	/**
	 * 공모전 이벤트 준비완료 여부 조회
	 * 
	 * @param userid, idfg
	 * @return void
	 */
	@Transactional(readOnly=true)
	public int getContestEventHisCnt(String userid, String idfg){
		
		EventContestDomain eventContestDomain = new EventContestDomain();
		eventContestDomain.setRegid(userid);
		eventContestDomain.setIdfg(idfg);
		
		return eventPromotionMapper.selectContestEventHisCnt(eventContestDomain);
	}

	/**
	 * 냄보소 SNS이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	@Transactional(readOnly=true)
	public List<EventPromotionDomain> getNaembosoSnsEventPrizeWinner(EventPromotionDomain eventPromotionDomain) {
		return eventPromotionMapper.selectNaembosoEventPrizeWinner(eventPromotionDomain);
	}

	/**
	 * 냄보소 Comment이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	@Transactional(readOnly=true)
	public List<EventPromotionDomain> getNaembosoCommentEventPrizeWinner(EventPromotionDomain eventPromotionDomain) {
		return eventPromotionMapper.selectNaembosoCommentEventPrizeWinner(eventPromotionDomain);
	}
	
	/**
	 * 냄보소 Photo이벤트 당첨자 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return List<String> : 당첨 날짜 목록
	 */
	@Transactional(readOnly=true)
	public List<EventPromotionDomain> getNaembosoPhotoEventPrizeWinner(EventPromotionDomain eventPromotionDomain) {
		return eventPromotionMapper.selectNaembosoPhotoEventPrizeWinner(eventPromotionDomain);
	}
	
	
	
	
	/**
	 * (공통)sns이벤트 참여 여부 조회
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	@Transactional(readOnly=true)
	public int getSnsEventJoinHisCnt(EventPromotionDomain eventPromotionDomain) {
		return eventPromotionMapper.selectSnsEventJoinHisCnt(eventPromotionDomain);
	}
	
	/**
	 * (공통)sns 이벤트 참여
	 * 
	 * @param EventPromotionDomain eventPromotionDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void snsEventRegistProc(EventPromotionDomain eventPromotionDomain) {
		if("Y".equals(eventPromotionDomain.getPreview())) {
			eventPromotionDomain.setSnsfg("");
			eventPromotionDomain.setContent("");
		}
		
		eventPromotionMapper.insertIceSnsEvent(eventPromotionDomain);
	}
	
	
	
	
//	/**
//	 * 2주년 이벤트 참여 날짜 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public String getMissionEventRegdt(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectMissionEventRegdt(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 참여
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=false)
//	public void missionEventRegistProc(EventMissionDomain eventMissionDomain) {
//		eventPromotionMapper.insertMissionEvent(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 참여 내역 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public List<EventMissionDomain> getMissionEventList(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectMissionEventList(eventMissionDomain); 
//	}
//	
//	/**
//	 * 2주년 이벤트 미션 참여
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=false)
//	public void missionRegistProc(EventMissionDomain eventMissionDomain) {
//		eventPromotionMapper.insertMission(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 로그인 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public int getLoginTotalCnt(EventMissionDomain eventMissionDomain) {
//		eventMissionDomain.setMissionfg("login");
//		return eventPromotionMapper.selectMissionCnt(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 소문내기 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public int getRumorTotalCnt(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectRumorTotalCnt(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 스티커 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public int getStickerTotalCnt(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectStickerTotalCnt(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 댓글 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public int getCommentTotalCnt(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectCommentTotalCnt(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 이벤트 공유하기 총 갯수 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public int getSnsTotalCnt(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectSnsTotalCnt(eventMissionDomain);
//	}
//	
//	/**
//	 * 2주년 미션 업데이트 여부 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	public String getUpdateyn(EventMissionDomain eventMissionDomain) {
//		
//		eventMissionDomain.setMissionfg(null);
//		int missionTotalCnt = eventPromotionMapper.selectMissionCnt(eventMissionDomain);
//		int totalCnt = eventMissionDomain.getLogincnt() + 
//				       eventMissionDomain.getRumorcnt() + 
//				       eventMissionDomain.getStickercnt() + 
//				       eventMissionDomain.getCommentcnt() + 
//				       eventMissionDomain.getSnscnt();
//		
//		return (missionTotalCnt < totalCnt || eventMissionDomain.getLogincnt() < 1) ? "Y" : "N";
//	}
//	
//	/**
//	 * 2주년 이벤트 금일 출석체크 조회
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=true)
//	public int getMissionTodayHisCnt(EventMissionDomain eventMissionDomain) {
//		return eventPromotionMapper.selectMissionTodayHisCnt(eventMissionDomain);
//	}
//	
//	
//	/**
//	 * 2주년 이벤트 미션처리
//	 * 
//	 * @param EventMissionDomain eventMissionDomain
//	 * @return void
//	 */
//	@Transactional(readOnly=false)
//	public void insertMissionList(EventMissionDomain eventMissionDomain) {
//		
//		//1.소문내기
//		eventMissionDomain.setMissionfg("rumor");
//		eventMissionDomain.setMissionseq(eventPromotionMapper.selectRecentMissionseq(eventMissionDomain));
//		List<EventMissionDomain> rumorList = eventPromotionMapper.selectRumorList(eventMissionDomain);
//		if(rumorList != null && rumorList.size() > 0) {
//			for(int i = 0; i < rumorList.size(); i++) {
//				eventPromotionMapper.insertMission(rumorList.get(i));
//			}
//		}
//		
//		//2.스티커
//		eventMissionDomain.setMissionfg("sticker");
//		List<String> seqList = eventPromotionMapper.selectMissionseqList(eventMissionDomain);
//		String missionseqList = "";
//		if(seqList != null && seqList.size() > 0) {
//			for(int i = 0; i < seqList.size(); i++) {
//				if(i > 0) {
//					missionseqList += ",";
//				}
//				missionseqList += "'" + seqList.get(i) + "'";
//			}
//			eventMissionDomain.setMissionseqList(missionseqList);
//		}
//		
//		List<EventMissionDomain> stickerList = eventPromotionMapper.selectStickerList(eventMissionDomain);
//		if(stickerList != null && stickerList.size() > 0) {
//			for(int i = 0; i < stickerList.size(); i++) {
//				eventPromotionMapper.insertMission(stickerList.get(i));
//			}
//		}
//		
//		//3.댓글
//		eventMissionDomain.setMissionfg("comment");
//		eventMissionDomain.setMissionseq(eventPromotionMapper.selectRecentMissionseq(eventMissionDomain));
//		List<EventMissionDomain> commentList = eventPromotionMapper.selectCommentList(eventMissionDomain);
//		if(commentList != null && commentList.size() > 0) {
//			for(int i = 0; i < commentList.size(); i++) {
//				eventPromotionMapper.insertMission(commentList.get(i));
//			}
//		}
//		
//		//4.공유하기
//		eventMissionDomain.setMissionfg("sns");
//		eventMissionDomain.setMissionseq(eventPromotionMapper.selectRecentMissionseq(eventMissionDomain));
//		List<EventMissionDomain> snsList = eventPromotionMapper.selectSnsList(eventMissionDomain);
//		if(rumorList != null && snsList.size() > 0) {
//			for(int i = 0; i < snsList.size(); i++) {
//				eventPromotionMapper.insertMission(snsList.get(i));
//			}
//		}
//	}

}