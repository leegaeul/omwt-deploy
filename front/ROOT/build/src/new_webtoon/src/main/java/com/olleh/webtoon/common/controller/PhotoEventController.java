/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : PhotoEventController.java
 * DESCRIPTION    : Common PhotoEvent Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2015-05-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.photoevent.domain.ImageDeclarationDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.ImageRecommendDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventDomain;
import com.olleh.webtoon.common.dao.photoevent.domain.PhotoEventImageDomain;
import com.olleh.webtoon.common.dao.photoevent.service.iface.PhotoEventService;
import com.olleh.webtoon.common.dao.photoevent.util.PhotoEventUtil;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.SpamWordUtil;

@Controller("CommonPhotoEventController")
public class PhotoEventController {
			
	protected static Log logger = LogFactory.getLog(PhotoEventController.class);
			
	@Autowired
	PhotoEventService photoEventService;
	
	/**
	 * 포토 이벤트 참여 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/photoEventRegistProc.kt")
	public ModelAndView photoEventRegistProc(@ModelAttribute("photoEventDomain") PhotoEventDomain photoEventDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request)) {
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		photoEventDomain.setUserid(user.getEmail());
		photoEventDomain.setIdfg(user.getIdfg());
		photoEventDomain.setRegdt(DateUtil.getNowDate(1));
		
		//이벤트 참여 처리
		photoEventService.photoEventRegistProc(photoEventDomain);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}	

	
	/**
	 * 댓글 이벤트 참여 약관 동의 여부를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getPhotoEventJoinyn.kt")
	public ModelAndView getPhotoEventJoinyn(@ModelAttribute("photoEventDomain") PhotoEventDomain photoEventDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		photoEventDomain.setUserid(user.getEmail());
		photoEventDomain.setIdfg(user.getIdfg());
		
		photoEventDomain = photoEventService.getPhotoEventRegist(photoEventDomain);
		
		if(photoEventDomain == null){
			modelAndView.addObject("photo_joinyn", "N");
		}else{
			modelAndView.addObject("photo_joinyn", "Y");
			modelAndView.addObject("photo_eventseq", photoEventDomain.getEventseq());
			
			
			//이미지 등록 갯수
			PhotoEventImageDomain photoEventImageDomain = new PhotoEventImageDomain();
			photoEventImageDomain.setRegid(user.getEmail());
			photoEventImageDomain.setIdfg(user.getIdfg());
			photoEventImageDomain.setRegdt(DateUtil.getNowDate(0));

			int totalCnt = photoEventService.getMyImageListCnt(photoEventImageDomain);
			
			modelAndView.addObject("photo_totalCnt", totalCnt);
		}
		modelAndView.addObject("photoEventDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 포토 이벤트 참여 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/photoEventImageRegistProc.kt")
	public ModelAndView photoEventImageRegistProc(@ModelAttribute("photoEventImageDomain") PhotoEventImageDomain photoEventImageDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request)) {
			throw new Exception();
		}
		
		// 금칙어 체크
		boolean isCommentCheck = false;
		
		if(SpamWordUtil.isCommentContain(photoEventImageDomain.getContent()) != null)
		{
			isCommentCheck = true;
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		photoEventImageDomain.setRegid(user.getEmail());
		photoEventImageDomain.setIdfg(user.getIdfg());
		photoEventImageDomain.setNickname(user.getNickname());
		photoEventImageDomain.setRegdt(DateUtil.getNowDate(1));
		
		//이벤트 이미지 등록
		if(!isCommentCheck){
			photoEventService.photoEventImageRegistProc(photoEventImageDomain);
		}
		
		modelAndView.addObject("isCommentCheck", isCommentCheck);
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 포토 이벤트에 참여한 이미지 리스트롤 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getPhotoEventImageList.kt")
	public ModelAndView getPhotoEventImageList(@ModelAttribute("photoEventImageDomain") PhotoEventImageDomain photoEventImageDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//모니터링유저 셋팅
		photoEventImageDomain.setMusers(PhotoEventUtil.getMoniteringUsers());
		
		int totalCnt = 0;
		List<PhotoEventImageDomain> imageList = null;

		if(AuthUtil.isLogin(request)) {
			UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
			photoEventImageDomain.setRegid(user.getEmail());
			photoEventImageDomain.setIdfg(user.getIdfg());
		
			//마이 리스트
			if("my".equals(photoEventImageDomain.getTypefg())) {
				totalCnt = photoEventService.getMyImageListCnt(photoEventImageDomain);
				imageList = photoEventService.getMyImageList(photoEventImageDomain);
			}
		}
		
		//전체 리스트
		if("all".equals(photoEventImageDomain.getTypefg())) {
			totalCnt = photoEventService.getImageListCnt(photoEventImageDomain);
			imageList = photoEventService.getImageList(photoEventImageDomain);
		}
		
		//이미지 도메인 설정
		PhotoEventUtil.imageValidate(imageList);
		
		modelAndView.addObject("totalCnt", totalCnt);
		modelAndView.addObject("imageList", imageList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 포토이벤트 이미지 신고 처리 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/imageDeclationProc.kt")
	public ModelAndView imageDeclationProc(@ModelAttribute("imageDeclarationDomain") ImageDeclarationDomain imageDeclarationDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request)) {
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		imageDeclarationDomain.setRegid(user.getEmail());
		imageDeclarationDomain.setIdfg(user.getIdfg());
		imageDeclarationDomain.setRegdt(DateUtil.getNowDate(1));
		
		//신고접수 여부 조회
		int myDeclareHisCnt = photoEventService.getDeclareCntById(imageDeclarationDomain);
		
		//게시물 등록자 조회
		String regId = photoEventService.getImageRegId(imageDeclarationDomain.getImageseq()); 
		if(regId != null && imageDeclarationDomain.getRegid().equals(regId)){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 게시물은 신고할 수 없습니다.");
			
		} else if(myDeclareHisCnt > 0) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "이미 신고 하였습니다.");
			
		} else{
			
			modelAndView.addObject("erroryn", "N");
			
			//모니터링 유저 셋팅
			imageDeclarationDomain.setMusers(PhotoEventUtil.getMoniteringUsers());
			
			//신고 접수
			photoEventService.imageDeclationProc(imageDeclarationDomain);
			
			//블라인드 처리 여부 조회(5회 이상)
			int declareHisCnt = photoEventService.getDeclareCntByImgseq(imageDeclarationDomain);
			
			if(declareHisCnt >= 5) {
				modelAndView.addObject("blindyn", "Y");
			} else{
				modelAndView.addObject("blindyn", "N");
				modelAndView.addObject("declareHisCnt", declareHisCnt);
			}
		}
		
		modelAndView.addObject("imageDeclarationDomain", null);		
		
		return modelAndView;
	}
	
	/**
	 * 포토이벤트 이미지 좋아요/싫어요 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/imageRecommendProc.kt")
	public ModelAndView imageRecommendProc(@ModelAttribute("imageRecommendDomain") ImageRecommendDomain imageRecommendDomain, HttpServletRequest request) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request)) {
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		imageRecommendDomain.setRegid(user.getEmail());
		imageRecommendDomain.setIdfg(user.getIdfg());
		imageRecommendDomain.setRegdt(DateUtil.getNowDate(1));
		
		//추천 이력 조회
		int recomHisCnt = photoEventService.getRecomCntById(imageRecommendDomain);
		
		//게시물 등록자 조회
		String regId = photoEventService.getImageRegId(imageRecommendDomain.getImageseq()); 
		
		if(regId != null && imageRecommendDomain.getRegid().equals(regId)){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.image.my"));
		} else if(recomHisCnt > 0) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.image.recommend.dup"));
		} else{
			modelAndView.addObject("erroryn", "N");
			photoEventService.imageRecommendProc(imageRecommendDomain); //추천하기
			photoEventService.modifyImageInfo(imageRecommendDomain);    //totalgoodCnt 수정하기
			modelAndView.addObject("recomCnt", photoEventService.getRecomCntByImgseq(imageRecommendDomain));
		}
		
		modelAndView.addObject("imageRecommendDomain", null);
		modelAndView.setViewName("mappingJacksonJsonView");   
		
		return modelAndView;
	}
	
	/**
	 * 포토 이벤트 참여한 이미지를 삭제하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/imageDeleteProc.kt")
	public ModelAndView photoEventImageDeleteProc(@ModelAttribute("photoEventImageDomain") PhotoEventImageDomain photoEventImageDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		if(!AuthUtil.isLogin(request)) {
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		photoEventImageDomain.setRegid(user.getEmail());
		photoEventImageDomain.setIdfg(user.getIdfg());
		photoEventImageDomain.setModdt(DateUtil.getNowDate(1));
		photoEventImageDomain.setStatusfg("urdelete");
		
		//게시물 등록자 조회
		String regId = photoEventService.getImageRegId(photoEventImageDomain.getImageseq()); 
		if(!photoEventImageDomain.getRegid().equals(regId)){
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 게시물이 아닙니다.");
			return modelAndView;
		}
		
		modelAndView.addObject("erroryn", "N");
		
		//이벤트 이미지 상태값 업데이트
		photoEventService.photoEventImageDeleteProc(photoEventImageDomain);
		
		modelAndView.addObject("photoEventImageDomain", null);
		
		return modelAndView;
	}
	
	
	
}