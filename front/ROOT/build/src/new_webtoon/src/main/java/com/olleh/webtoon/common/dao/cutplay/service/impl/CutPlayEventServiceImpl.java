package com.olleh.webtoon.common.dao.cutplay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olleh.webtoon.api.sdp.SDPConstants;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayCommentDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayEventDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayJoinDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayWinnerDomain;
import com.olleh.webtoon.common.dao.cutplay.persistence.CutPlayEventMapper;
import com.olleh.webtoon.common.dao.cutplay.service.iface.CutPlayEventService;
import com.olleh.webtoon.common.dao.orderbuy.domain.OrderDomain;
import com.olleh.webtoon.common.dao.payment.domain.PaymentDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.DateUtil;

@Service("cutPlayEventService")
@Repository
public  class CutPlayEventServiceImpl implements CutPlayEventService {
	
	@Autowired
	private CutPlayEventMapper cutPlayEventMapper;	
	
	/**
	 * 컷스프레이벤트 현재 차수 조회 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 차수 
	 */
	public CutPlayEventDomain getCutPlayEventCurTimes(String date) {
		return cutPlayEventMapper.selectCutPlayEventCurTimes(date);
	}
	
	/**
	 * 컷스프레이벤트 차수 조회 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 차수 
	 */
	public CutPlayEventDomain getCutPlayEventTimes(int eventtimes) {
		return cutPlayEventMapper.selectCutPlayEventTimes(eventtimes);
	}
	
	/**
	 * 컷스프레이벤트 참여횟수 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 참여 횟수 
	 */
	public int getCutPlayEventCnt(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectCutPlayEventCnt(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 베스트 
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return CutPlayEventDomain : 컷스프레이벤트 베스트 
	 */
	public List<CutPlayJoinDomain> getCutPlayEventBest(int eventtimes, String userid) {
		CutPlayJoinDomain cutPlayJoinDomain = new CutPlayJoinDomain();
		cutPlayJoinDomain.setUserid(userid);
		cutPlayJoinDomain.setEventtimes(eventtimes);
		return cutPlayEventMapper.selectCutPlayEventBest(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 참여
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return void
	 */
	@Transactional(readOnly=false)
	public void cutPlayEventRegistProc(CutPlayJoinDomain cutPlayJoinDomain) {
		
		String phonenum = cutPlayJoinDomain.getPhonenum();
		if(phonenum == null || phonenum.length() < 1) {
			CutPlayJoinDomain join = cutPlayEventMapper.selectRegUser(cutPlayJoinDomain);
			if(join != null) {
				cutPlayJoinDomain.setPhonefg(join.getPhonefg());
				cutPlayJoinDomain.setPhonenum(join.getPhonenum());
			}
		}
		
		cutPlayEventMapper.insertCutPlayEvent(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 목록 전체 수
	 */
	public int getImageListCnt(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectImageListCnt(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public List<CutPlayJoinDomain> getImageList(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectImageList(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return int : 컷스프레이벤트 목록 전체 수
	 */
	public int getMyImageListCnt(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectMyImageListCnt(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public List<CutPlayJoinDomain> getMyImageList(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectMyImageList(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레이벤트 내 목록 조회
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 목록
	 */
	public CutPlayJoinDomain getCutPlayJoinDetail(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectCutPlayJoinDetail(cutPlayJoinDomain);
	}
	
	/**
	 * 컷스프레 도전하기 step01
	 * @param CutPlayEventDomain cutPlayEventDomain
	 * @return List<CutPlayJoinDomain> : 컷스프레이벤트 WebToonImagePath,webtoonseq
	 */
	public List<CutPlayJoinDomain> getCutPlayWebToonImagePath() {
		return cutPlayEventMapper.selectWebToonImagePath();
	}
	
	/**
	 * 컷 이벤트 댓글 등록
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public void cutPlayCommentProc(CutPlayCommentDomain cutPlayCommentDomain) {
		cutPlayEventMapper.insertCutPlayComment(cutPlayCommentDomain);
	}
	
	/**
	 * 컷 이벤트 댓글 등록 아이디 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public String getCommentRegId(CutPlayCommentDomain cutPlayCommentDomain) {
		return cutPlayEventMapper.selectCommentRegId(cutPlayCommentDomain);
	}
	
	/**
	 * 컷 이벤트 댓글 삭제
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return 
	 */
	public void cutPlayCommentDeleteProc(CutPlayCommentDomain cutPlayCommentDomain) {
		cutPlayEventMapper.deleteCutPlayComment(cutPlayCommentDomain);
	}
	
	/**
	 * 컷이벤트 댓글 목록 갯수 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return int
	 */
	public int getCutPlayCommentTotalCnt(CutPlayCommentDomain cutPlayCommentDomain) {
		return cutPlayEventMapper.selectCutPlayCommentTotalCnt(cutPlayCommentDomain);
	}
	
	/**
	 * 컷이벤트 댓글 목록 조회
	 * @param CutPlayCommentDomain cutPlayCommentDomain
	 * @return List<CutPlayCommentDomain> 
	 */
	public List<CutPlayCommentDomain> getCutPlayCommentList(CutPlayCommentDomain cutPlayCommentDomain) {
		return cutPlayEventMapper.selectCutPlayCommentList(cutPlayCommentDomain);
	}
	
	/**
	 * 좋아요 이력 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public int getGoodcntHisCnt(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectGoodcntHisCnt(cutPlayJoinDomain);
	}
	
	/**
	 * 작성자 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public String getCutPlayRegid(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectCutPlayRegid(cutPlayJoinDomain);
	}
	
	/**
	 * 삭제 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void cutPlayDeleteProc(CutPlayJoinDomain cutPlayJoinDomain) {
		cutPlayEventMapper.cutPlayDeleteProc(cutPlayJoinDomain);
	}
	
	/**
	 * 좋아요 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void cutPlayGoodcntProc(CutPlayJoinDomain cutPlayJoinDomain) {
		cutPlayEventMapper.insertCutPlayGoodcnt(cutPlayJoinDomain);
		cutPlayEventMapper.updateCutPlayGoodcnt(cutPlayJoinDomain);
	}

	/**
	 * 신고 이력 조회
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public int getDeclarecntHisCnt(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectDeclarecntHisCnt(cutPlayJoinDomain);
	}

	/**
	 * 신고 처리
	 * @param CutPlayJoinDomain cutPlayJoinDomain
	 * @return 
	 */
	public void cutPlayDeclarecntProc(CutPlayJoinDomain cutPlayJoinDomain) {
		cutPlayEventMapper.insertCutPlayDeclarecnt(cutPlayJoinDomain);
		cutPlayEventMapper.updateCutPlayDeclarecnt(cutPlayJoinDomain);
	}

	/**
	 * 공유 SNS 정보 저장 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public void cutPlaySnsProc(CutPlayJoinDomain cutPlayJoinDomain) {
		cutPlayEventMapper.insertCutPlaySnsInfo(cutPlayJoinDomain);
	}
	
	
	
	/**
	 * 총 누적 베리 조회 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public int getFreeBerryAmount(UserDomain user) {
		return cutPlayEventMapper.selectFreeBerryAmount(user);
	}
	
	/**
	 * 일일 누적 베리 조회 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public int getTodayFreeBerryAmount(UserDomain user) {
		return cutPlayEventMapper.selectTodayFreeBerryAmount(user);
	}
	
	/**
	 * 베리 지급 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@Transactional(readOnly=false)
	public void chargeFreeBerry(UserDomain user, boolean isMobile) {
		
		String orderno = DateUtil.getNowDate(1) + Long.toString(System.nanoTime()).substring(5, 11);
		
		// 결제정보를 등록한다.					
		PaymentDomain paymentDomain = new PaymentDomain();
		paymentDomain.setResultcode(SDPConstants.RESULT_SUCCESS);
		paymentDomain.setResultmsg("Success");
		paymentDomain.setPaymentseq(0);
		paymentDomain.setPaymentstep("complete");
		paymentDomain.setPayname("이벤트 무료 충전");
		paymentDomain.setPayno("");
		paymentDomain.setOrderno(orderno);
		paymentDomain.setFreeyn("Y");
		paymentDomain.setPartialyn("N");
		paymentDomain.setPaymentfg("order");
		paymentDomain.setPaymethod("EV");
		paymentDomain.setOrderamount(2);
		paymentDomain.setPaymentid(user.getEmail());
		paymentDomain.setIdfg(user.getIdfg());
		paymentDomain.setOpcode("");
		paymentDomain.setClienttype(isMobile ? "mobile" : "pc");
		paymentDomain.setPaymentdt(DateUtil.getNowDate(1));
		paymentDomain.setRegid(user.getEmail());
		paymentDomain.setRegdt(DateUtil.getNowDate(1));
		
		cutPlayEventMapper.registFreePayment(paymentDomain);
		
		//주문정보를 등록한다.
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setPaymentseq(paymentDomain.getPaymentseq());
		orderDomain.setOrderno(paymentDomain.getOrderno());
		orderDomain.setBuyseq(0);
		orderDomain.setUsefg("charge");
		orderDomain.setOrderfg("order");
		orderDomain.setOrderamount(paymentDomain.getOrderamount());
		orderDomain.setOrderid(paymentDomain.getPaymentid());
		orderDomain.setOrderdt(DateUtil.getNowDate(1));
		orderDomain.setIdfg(paymentDomain.getIdfg());
		orderDomain.setRegid(user.getEmail());
		orderDomain.setRegdt(DateUtil.getNowDate(1));
		
		cutPlayEventMapper.registFreeOrder(orderDomain);
	}
	
	//모파일 페이지 이동시 초기 리스트 Cnt
	public int getMobileImageListCnt(CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectMobileImageListCnt(cutPlayJoinDomain);
	}

	//모파일 페이지 이동시 초기 리스트 	
	public List<CutPlayJoinDomain> getMobileImageList(
			CutPlayJoinDomain cutPlayJoinDomain) {
		return cutPlayEventMapper.selectMobileImageList(cutPlayJoinDomain);
	}
		
	/**
	 * 당첨자 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public CutPlayWinnerDomain getCutPlayPrizeWinner(CutPlayWinnerDomain cutPlayWinnerDomain) {
		return cutPlayEventMapper.getCutPlayPrizeWinner(cutPlayWinnerDomain);
	}
	
	/**
	 * 당첨자 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	public List<CutPlayWinnerDomain> getCutPlayPrizeList(CutPlayWinnerDomain cutPlayWinnerDomain) {
		return cutPlayEventMapper.getCutPlayPrizeList(cutPlayWinnerDomain);
	}
}