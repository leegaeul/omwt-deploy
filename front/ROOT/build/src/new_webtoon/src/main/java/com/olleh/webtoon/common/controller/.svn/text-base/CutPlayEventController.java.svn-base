/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : CutPlayEventController.java
 * DESCRIPTION    : Common CutPlayEvent Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        InJae Yeo      2015-05-15      init
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.constant.AppConstant;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayCommentDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayJoinDomain;
import com.olleh.webtoon.common.dao.cutplay.domain.CutPlayWinnerDomain;
import com.olleh.webtoon.common.dao.cutplay.service.iface.CutPlayEventService;
import com.olleh.webtoon.common.dao.cutplay.util.CutPlayEventUtil;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.FileUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.common.util.SpamWordUtil;
import com.olleh.webtoon.common.util.StringUtil;
import com.olleh.webtoon.olltoon.common.util.KeyUtil;

@Controller("CutPlayEventController")
public class CutPlayEventController {
			
	protected static Log logger = LogFactory.getLog(CutPlayEventController.class);
			
	@Autowired
	CutPlayEventService cutPlayEventService;
	
	/**
	 * 로그인 여부, 컷스프레 참여 여부 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCutPlayEventCnt.kt")
	public ModelAndView getCutPlayEventCnt(@ModelAttribute("cutPlayJoinDomain") CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("loginyn", "N");
			return modelAndView;
		}else {
			modelAndView.addObject("loginyn", "Y");
		}
		
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
		cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
		
		try {
			
			//약관동의 참여 횟수
			cutPlayJoinDomain.setEventfg("join");
			int cnt = cutPlayEventService.getCutPlayEventCnt(cutPlayJoinDomain);
			modelAndView.addObject("cutplay_cnt", cnt);
			
			//등록한 이미지 갯수
			cutPlayJoinDomain.setEventfg("image");
			int imagecnt = cutPlayEventService.getCutPlayEventCnt(cutPlayJoinDomain);
			modelAndView.addObject("cutplay_image_cnt", imagecnt);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	/**
	 * 컷스프레 이벤트 참여 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/common/cutPlayEventJoin.kt" )
	public ModelAndView cutPlayEventJoin(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("mappingJacksonJsonView");

		try {

			String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
			if(ot_token == null || "".equals(ot_token)) {
				modelAndView.addObject("erroryn", "Y");
				modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
				return modelAndView;
			}
			
			String eventfg = request.getParameter("eventfg");
			String phonefg = request.getParameter("phonefg");
			String phonenum = request.getParameter("phonenum");
			
			//유저정보 가져오기
			String tokenJson = KeyUtil.keyToString(ot_token);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
			
			CutPlayJoinDomain cutPlayJoinDomain = new CutPlayJoinDomain();
			cutPlayJoinDomain.setEventfg(eventfg);
			cutPlayJoinDomain.setPhonefg(phonefg);
			cutPlayJoinDomain.setPhonenum(phonenum);
			cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
			cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
			cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
			
			//default
			cutPlayJoinDomain.setImagepath("");
			cutPlayJoinDomain.setImagefilenm("");
			cutPlayJoinDomain.setMessage("");
			
			//중복 참여 체크
			if("join".equals(eventfg)) {
				int cnt = cutPlayEventService.getCutPlayEventCnt(cutPlayJoinDomain);
				if(cnt > 0) {
					modelAndView.addObject("erroryn", "Y");
					modelAndView.addObject("errormsg", "이미 참여하셨습니다.");
					return modelAndView;
				}
			}
			
			//이벤트 참여 처리
			cutPlayEventService.cutPlayEventRegistProc(cutPlayJoinDomain);

		} catch( Exception e ) {
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
			modelAndView.addObject("erroryn", "Y");
		}
		
		return modelAndView;
	}
	
	/**
	 * 컷스프레 이벤트 참여 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/common/cutPlayEventRegistProc.kt" )
	public ModelAndView cutPlayEventRegistProc(MultipartHttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		try {
			String eventfg = request.getParameter("eventfg");
			String message = request.getParameter("message");
			String cutplaytitle = request.getParameter("cutplaytitle");
			String basicimagepath = request.getParameter("basicimagepath");
			
			String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
			if(ot_token == null || "".equals(ot_token)) {
				throw new Exception();
			}
			
			//금칙어 체크
//			if(SpamWordUtil.isCommentContain(message) != null) {
//				modelAndView.addObject("erroryn", "Y");
//				modelAndView.addObject("errormsg", "금칙어가 포함되어 있습니다.");
//				return modelAndView;
//			}
			
			MultipartFile webtoonimage = null;
			MultipartFile image = null;
			
			CutPlayJoinDomain cutPlayJoinDomain = new CutPlayJoinDomain();
			
			if("webtooncut".equals(eventfg)) {
				image = request.getFileMap().get("imagepath");
			} else {
				webtoonimage = request.getFileMap().get("webtoonimagepath");
				image = request.getFileMap().get("freecutimagepath");
			}

			if("webtooncut".equals(eventfg)) {
				// 01. 프리컷 컷스프레 이미지 저장
				String originFileName = image.getOriginalFilename();  	  // 원본 파일명
				String fileExt = FileUtil.getExtension( originFileName ); // 확장자
	
				// exe, eml 파일처럼 업로드가 허용되지 않는 파일을 필터링한다.
				if( !FileUtil.isAllowedFile( fileExt ) )
				{
					throw new Exception();
				}
				
				// 파일을 저장할 경로를 가져온다. /YYYY/MM/DD
				String datePath  = FileUtil.getDatePath();
				String fileName  = String.valueOf( System.currentTimeMillis() ) + "." + fileExt;
				String imagePath = MessageUtil.getMessage("system.data.event.image.path");	//서버
				
				// 저장할 폴더가 있는지 확인한다.
				File saveFolder = new File( imagePath + datePath );
				if( !saveFolder.exists() )
				{
					saveFolder.mkdirs();
				}
				
				// 물리파일을 저장한다.
				File file = new File( imagePath + datePath + "/" + fileName );
				image.transferTo( file );
				
				cutPlayJoinDomain.setImagepath( datePath + "/" + fileName);
				cutPlayJoinDomain.setImagefilenm(originFileName);
				cutPlayJoinDomain.setWebtoonimagepath(basicimagepath);

			} else {
				// 01. 프리컷 컷스프레 이미지 저장
				String originFileName = image.getOriginalFilename();  	  // 원본 파일명
				String fileExt = FileUtil.getExtension( originFileName ); // 확장자
	
				// exe, eml 파일처럼 업로드가 허용되지 않는 파일을 필터링한다.
				if( !FileUtil.isAllowedFile( fileExt ) )
				{
					throw new Exception();
				}
	
				// 파일을 저장할 경로를 가져온다. /YYYY/MM/DD
				String datePath  = FileUtil.getDatePath();
				String fileName  = String.valueOf( System.currentTimeMillis() ) + "." + fileExt;
				String imagePath = MessageUtil.getMessage("system.data.event.image.path");	//서버
				
				// 저장할 폴더가 있는지 확인한다.
				File saveFolder = new File( imagePath + datePath );
				if( !saveFolder.exists() )
				{
					saveFolder.mkdirs();
				}
	
				// 물리파일을 저장한다.
				File file = new File( imagePath + datePath + "/" + fileName );
				image.transferTo( file );
				
				cutPlayJoinDomain.setImagepath( datePath + "/" + fileName);
				cutPlayJoinDomain.setImagefilenm(originFileName);
				
				// 02. 프리컷 웹툰 이미지 저장
				String webtoonOriginFileName = webtoonimage.getOriginalFilename();      // 원본 파일명
				String webtoonFileExt = FileUtil.getExtension( webtoonOriginFileName ); // 확장자
	
				// exe, eml 파일처럼 업로드가 허용되지 않는 파일을 필터링한다.
				if( !FileUtil.isAllowedFile( webtoonFileExt ) )
				{
					throw new Exception();
				}
				
				// 파일을 저장할 경로를 가져온다. /YYYY/MM/DD
				String webtoonDatePath = FileUtil.getDatePath();
				String webtoonFileName = String.valueOf( System.currentTimeMillis() ) + "." + webtoonFileExt;
				String webtoonTempPath = MessageUtil.getMessage("system.data.event.image.path");	//서버
				
				// 저장할 폴더가 있는지 확인한다.
				File webtoonSaveFolder = new File( webtoonTempPath + webtoonDatePath );
				if( !webtoonSaveFolder.exists() )
				{
					webtoonSaveFolder.mkdirs();
				}
	
				// 물리파일을 저장한다.
				File webtoonFile = new File( webtoonTempPath + webtoonDatePath + "/" + webtoonFileName );
				webtoonimage.transferTo( webtoonFile );
				
				cutPlayJoinDomain.setWebtoonimagepath( webtoonDatePath + "/" + webtoonFileName);
				cutPlayJoinDomain.setWebtoonimagefilenm(webtoonOriginFileName);
			}
			
			//유저정보 가져오기
			String tokenJson = KeyUtil.keyToString(ot_token);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
			
			cutPlayJoinDomain.setEventfg(eventfg);
			cutPlayJoinDomain.setCutplaytitle(cutplaytitle);
			cutPlayJoinDomain.setMessage(message);
			cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
			cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
			cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
			
			//이벤트 참여 처리
			cutPlayEventService.cutPlayEventRegistProc(cutPlayJoinDomain);
			
			modelAndView.setViewName("mappingJacksonJsonView");

		} catch( Exception e ) {
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
			modelAndView.addObject( "erroryn", "Y" );
		}
		
		return modelAndView;
	}
	
	/**
	 * 컷스프레 이벤트 참여 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/common/cutPlayEventMobileRegistProc.kt" )
	public ModelAndView cutPlayEventMobileRegistProc(@ModelAttribute("cutPlayJoinDomain") CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );

		try {
    		
			String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
			if(ot_token == null || "".equals(ot_token)) {
				throw new Exception();
			}
			
			
			// 금칙어 체크
			boolean isCommentCheck = false;
			
			if(SpamWordUtil.isCommentContain(cutPlayJoinDomain.getMessage()) != null)
			{
				isCommentCheck = true;
			}
			
			if(!isCommentCheck){

				if("webtooncut".equals(cutPlayJoinDomain.getEventfg())) {					
					FileUtil.tempFileCopy("event", StringUtil.defaultStr(cutPlayJoinDomain.getImagepath(), ""));
				} else {
					FileUtil.tempFileCopy("event", StringUtil.defaultStr(cutPlayJoinDomain.getImagepath(), ""));
					FileUtil.tempFileCopy("event", StringUtil.defaultStr(cutPlayJoinDomain.getWebtoonimagepath(), ""));
				}
				
				//유저정보 가져오기
				String tokenJson = KeyUtil.keyToString(ot_token);
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
				cutPlayJoinDomain.setPhonefg("");
				cutPlayJoinDomain.setPhonenum("");
				cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
				cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
				cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
				
				//이벤트 참여 처리
				cutPlayEventService.cutPlayEventRegistProc(cutPlayJoinDomain);
			}
			
			modelAndView.addObject("isCommentCheck", isCommentCheck);
			modelAndView.setViewName("mappingJacksonJsonView");

		} catch( Exception e ) {
			logger.error( e.getMessage() );
			logger.error( ExceptionUtils.getStackTrace( e ) );
			modelAndView.addObject( "erroryn", "Y" );
		}
		
		return modelAndView;
	}
	
	/**
	 * 포토 이벤트에 참여한 이미지 리스트롤 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCutPlayEventList.kt")
	public ModelAndView getPhotoEventImageList(@ModelAttribute("cutPlayJoinDomain") CutPlayJoinDomain cutPlayJoinDomain, 
			HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String eventfg = request.getParameter("typefg");
		
		int totalCnt = 0;
		List<CutPlayJoinDomain> imageList = null;
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token != null && !"".equals(ot_token)) {
			
			//유저정보 가져오기
			String tokenJson = KeyUtil.keyToString(ot_token);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
			
			cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
			cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
			cutPlayJoinDomain.setNickname((String)userMap.get("nickname"));
		
			//마이 리스트
			if("my".equals(eventfg)) {
				totalCnt = cutPlayEventService.getMyImageListCnt(cutPlayJoinDomain);
				imageList = cutPlayEventService.getMyImageList(cutPlayJoinDomain);
			}
		}
		
		//전체 리스트
		if("all".equals(eventfg)) {
			totalCnt = cutPlayEventService.getImageListCnt(cutPlayJoinDomain);
			imageList = cutPlayEventService.getImageList(cutPlayJoinDomain);
		}
		
		//이미지 도메인 설정
		CutPlayEventUtil.imageValidate(imageList);
		
		modelAndView.addObject("totalCnt", totalCnt);
		modelAndView.addObject("imageList", imageList);
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 댓글 등록 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/cutPlayCommentDeleteProc.kt")
	public ModelAndView cutPlayCommentDeleteProc(@ModelAttribute("cutPlayCommentDomain")CutPlayCommentDomain cutPlayCommentDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			throw new Exception();
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		cutPlayCommentDomain.setRegid((String)userMap.get("userid"));
		
		//본인 작성글 체크
		String commentRegId = cutPlayEventService.getCommentRegId(cutPlayCommentDomain); 
		
		//본인 작성 글
		if(commentRegId != null && commentRegId.equals(cutPlayCommentDomain.getRegid()))  { 
			cutPlayEventService.cutPlayCommentDeleteProc(cutPlayCommentDomain);
			modelAndView.addObject("erroryn", "N");
		}
		else {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 작성글이 아닙니다.");
		}
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 댓글 목록 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCutPlayCommentList.kt")
	public ModelAndView getCutPlayCommentList(@ModelAttribute("cutPlayCommentDomain")CutPlayCommentDomain cutPlayCommentDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token != null && !"".equals(ot_token)) {
			
			//유저정보 가져오기
			String tokenJson = KeyUtil.keyToString(ot_token);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
			
			cutPlayCommentDomain.setRegid((String)userMap.get("userid"));
			cutPlayCommentDomain.setIdfg((String)userMap.get("idfg"));
			cutPlayCommentDomain.setRegdt(DateUtil.getNowDate(1));
		}
		
		int totalCnt = cutPlayEventService.getCutPlayCommentTotalCnt(cutPlayCommentDomain);
		List<CutPlayCommentDomain> commentList = cutPlayEventService.getCutPlayCommentList(cutPlayCommentDomain);
		
		//이미지 도메인 설정
		CutPlayEventUtil.imageValidate(commentList);
				
		modelAndView.addObject("totalCnt", totalCnt);
		modelAndView.addObject("commentList", commentList);
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 상세 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCutPlayJoinDetail.kt")
	public ModelAndView getCutPlayJoinDetail(@ModelAttribute("cutPlayJoinDomain")CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token != null && !"".equals(ot_token)) {
			
			//유저정보 가져오기
			String tokenJson = KeyUtil.keyToString(ot_token);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
			
			cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
			cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
			cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
		}
		
		CutPlayJoinDomain detail = cutPlayEventService.getCutPlayJoinDetail(cutPlayJoinDomain);
		
		//이미지 도메인 설정
		CutPlayEventUtil.imageValidate(detail);
				
		modelAndView.addObject("joinDetail", detail);
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 삭제 처리
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/cutPlayDeleteProc.kt")
	public ModelAndView cutPlayDeleteProc(@ModelAttribute("cutPlayJoinDomain")CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
		cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
		
		//본인글 확인
		String regid = cutPlayEventService.getCutPlayRegid(cutPlayJoinDomain);
		if(!regid.equals((String)userMap.get("userid"))) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 게시물이 아닙니다.");
			return modelAndView;
		}
		
		cutPlayEventService.cutPlayDeleteProc(cutPlayJoinDomain);
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 신고 처리
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/cutPlayDeclarecntProc.kt")
	public ModelAndView cutPlayDeclarecntProc(@ModelAttribute("cutPlayJoinDomain")CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
				
		cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
		cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
		
		//중복 체크
		int goodcntHisCnt = cutPlayEventService.getDeclarecntHisCnt(cutPlayJoinDomain);
		if(goodcntHisCnt > 0) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "이미 신고하셨습니다.");
			return modelAndView;
		}
		
		//본인글 확인
		String regid = cutPlayEventService.getCutPlayRegid(cutPlayJoinDomain);
		if(regid.equals((String)userMap.get("userid"))) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 글은 참여하실수 없습니다.");
			return modelAndView;
		}
		
		cutPlayEventService.cutPlayDeclarecntProc(cutPlayJoinDomain);
		
		return modelAndView;
	}
	
	
	
	/**
	 * 공유 SNS 정보 저장
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/cutPlaySnsProc.kt")
	public ModelAndView cutPlaySnsProc(@ModelAttribute("cutPlayJoinDomain")CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
				
		cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
		cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
		
		cutPlayEventService.cutPlaySnsProc(cutPlayJoinDomain);
		
		//베리 지급 처리
		UserDomain user = new UserDomain();
		user.setEmail((String)userMap.get("userid"));
		user.setIdfg((String)userMap.get("idfg"));
		user.setNickname((String)userMap.get("nickname"));
		int freeBerryAmount = cutPlayEventService.getFreeBerryAmount(user);
		System.out.println("freeBerryAmount = " + freeBerryAmount);
		if(freeBerryAmount >= 20) {
			modelAndView.addObject("chargeyn", "N");
			modelAndView.addObject("chargemsg", "최대 20베리를 초과하셨습니다.");
			return modelAndView;
		}
		
		int todayFreeBerryAmount = cutPlayEventService.getTodayFreeBerryAmount(user);
		System.out.println("todayFreeBerryAmount = " + todayFreeBerryAmount);
		if(todayFreeBerryAmount >= 10) {
			modelAndView.addObject("chargeyn", "N");
			modelAndView.addObject("chargemsg", "일일 10베리를 초과하셨습니다.");
			return modelAndView;
		}
		
		cutPlayEventService.chargeFreeBerry(user, RequestUtil.isMobile(request));
		
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 좋아요 처리
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/cutPlayGoodcntProc.kt")
	public ModelAndView cutPlayGoodcntProc(@ModelAttribute("cutPlayJoinDomain")CutPlayJoinDomain cutPlayJoinDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
		
		cutPlayJoinDomain.setUserid((String)userMap.get("userid"));
		cutPlayJoinDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayJoinDomain.setRegdt(DateUtil.getNowDate(1));
		
		//중복 체크
		int goodcntHisCnt = cutPlayEventService.getGoodcntHisCnt(cutPlayJoinDomain);
		if(goodcntHisCnt > 0) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "이미 참여하셨습니다.");
			return modelAndView;
		}
		
		//본인글 확인
		String regid = cutPlayEventService.getCutPlayRegid(cutPlayJoinDomain);
		if(regid.equals((String)userMap.get("userid"))) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 글은 참여하실수 없습니다.");
			return modelAndView;
		}
		
		cutPlayEventService.cutPlayGoodcntProc(cutPlayJoinDomain);
		
		//베리 지급 처리
		UserDomain user = new UserDomain();
		user.setEmail((String)userMap.get("userid"));
		user.setIdfg((String)userMap.get("idfg"));
		user.setNickname((String)userMap.get("nickname"));
		int freeBerryAmount = cutPlayEventService.getFreeBerryAmount(user);
		System.out.println("freeBerryAmount = " + freeBerryAmount);
		if(freeBerryAmount >= 20) {
			modelAndView.addObject("chargeyn", "N");
			modelAndView.addObject("chargemsg", "최대 20베리를 초과하셨습니다.");
			return modelAndView;
		}
		
		int todayFreeBerryAmount = cutPlayEventService.getTodayFreeBerryAmount(user);
		System.out.println("todayFreeBerryAmount = " + todayFreeBerryAmount);
		if(todayFreeBerryAmount >= 10) {
			modelAndView.addObject("chargeyn", "N");
			modelAndView.addObject("chargemsg", "일일 10베리를 초과하셨습니다.");
			return modelAndView;
		}
		
		cutPlayEventService.chargeFreeBerry(user, RequestUtil.isMobile(request));
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 댓글 등록 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/cutPlayCommentProc.kt")
	public ModelAndView cutPlayCommentProc(@ModelAttribute("cutPlayCommentDomain")CutPlayCommentDomain cutPlayCommentDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
				
		cutPlayCommentDomain.setRegid((String)userMap.get("userid"));
		cutPlayCommentDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayCommentDomain.setNameconseq((Integer)userMap.get("nameconseq"));
		cutPlayCommentDomain.setNickname((String)userMap.get("nickname"));
		cutPlayCommentDomain.setRegdt(DateUtil.getNowDate(1));
		cutPlayCommentDomain.setComment(StringUtil.cleanXSS(cutPlayCommentDomain.getComment()));
		
		cutPlayEventService.cutPlayCommentProc(cutPlayCommentDomain);
		
		//베리 지급 처리
		UserDomain user = new UserDomain();
		user.setEmail((String)userMap.get("userid"));
		user.setIdfg((String)userMap.get("idfg"));
		user.setNickname((String)userMap.get("nickname"));
		int freeBerryAmount = cutPlayEventService.getFreeBerryAmount(user);
		System.out.println("freeBerryAmount = " + freeBerryAmount);
		if(freeBerryAmount >= 20) {
			modelAndView.addObject("chargeyn", "N");
			modelAndView.addObject("chargemsg", "최대 20베리를 초과하셨습니다.");
			return modelAndView;
		}
		
		int todayFreeBerryAmount = cutPlayEventService.getTodayFreeBerryAmount(user);
		System.out.println("todayFreeBerryAmount = " + todayFreeBerryAmount);
		if(todayFreeBerryAmount >= 10) {
			modelAndView.addObject("chargeyn", "N");
			modelAndView.addObject("chargemsg", "일일 10베리를 초과하셨습니다.");
			return modelAndView;
		}
		
		cutPlayEventService.chargeFreeBerry(user, RequestUtil.isMobile(request));
		
		return modelAndView;
	}
	
	/**
	 * 컷 이벤트 당첨자 조회
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/getCutPlayPrizeWinner.kt")
	public ModelAndView getCutPlayPrizeWinner(@ModelAttribute("cutPlayWinnerDomain")CutPlayWinnerDomain cutPlayWinnerDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName( AppConstant.JSON_VIEW_NAME );
		
		String ot_token = StringUtil.defaultStr(CookieUtil.getCookie(request, "ot_token"));
		if(ot_token == null || "".equals(ot_token)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "로그인 후 이용해주세요.");
			return modelAndView;
		}
		
		//유저정보 가져오기
		String tokenJson = KeyUtil.keyToString(ot_token);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> userMap = mapper.readValue(tokenJson, new TypeReference<HashMap<String, Object>>() {});
				
		cutPlayWinnerDomain.setUserid((String)userMap.get("userid"));
		cutPlayWinnerDomain.setIdfg((String)userMap.get("idfg"));
		cutPlayWinnerDomain.setRegdt(DateUtil.getNowDate(1));
		
		CutPlayWinnerDomain winner = cutPlayEventService.getCutPlayPrizeWinner(cutPlayWinnerDomain);
		if(winner == null) {
			modelAndView.addObject("winyn", "N");
			return modelAndView;
		}
		
		modelAndView.addObject("winyn", "Y");
		modelAndView.addObject("winner", winner);
		
		return modelAndView;
	}
}