/*****************************************************************************
 * PROJECT NAME       
 * - olleh Market  Webtoon
 *  
 * FILE NAME
 * - CommentController.java
 * 
 * DESCRIPTION
 * - 댓글/대댓글 처리 Controller class.
 *****************************************************************************/

package com.olleh.webtoon.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.olleh.webtoon.common.dao.comment.domain.CommentDomain;
import com.olleh.webtoon.common.dao.comment.domain.DeclarationDomain;
import com.olleh.webtoon.common.dao.comment.domain.SubCommentDomain;
import com.olleh.webtoon.common.dao.comment.service.iface.CommentService;
import com.olleh.webtoon.common.dao.shop.domain.ShopDomain;
import com.olleh.webtoon.common.dao.shop.service.iface.ShopService;
import com.olleh.webtoon.common.dao.toon.domain.StickerIconDomain;
import com.olleh.webtoon.common.dao.user.domain.IconDomain;
import com.olleh.webtoon.common.dao.user.domain.OllehUserDomain;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.dao.user.service.iface.UserServiceIface;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.CommentUtil;
import com.olleh.webtoon.common.util.CookieUtil;
import com.olleh.webtoon.common.util.DateUtil;
import com.olleh.webtoon.common.util.MessageUtil;
import com.olleh.webtoon.common.util.SpamWordUtil;
import com.olleh.webtoon.common.util.StringUtil;

@Controller
public class CommentController 
{	
	protected static Log logger = LogFactory.getLog(CommentController.class);
	
	private final int pageSize = 10;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserServiceIface userServiceIface;
	
	@Autowired
	ShopService shopService;
	
	/**
	 * 댓글 조회 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentJsonList.kt")
	public ModelAndView commentJsonList(@ModelAttribute("commentDomain") CommentDomain commentDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		if(user != null && !"".equals(user.getEmail()))
			commentDomain.setRegid(user.getEmail());
		
		//모니터링 유저 설정
		commentDomain.setMusers(CommentUtil.getMoniteringUsers());
		
		//베스트 댓글 건수 조회
		int bestTotalCnt = commentService.bestCommentListCnt(commentDomain);
		modelAndView.addObject("bestTotalCnt", bestTotalCnt);
		
		//베스트 댓글 리스트 조회
		List<CommentDomain> bestCommentList = commentService.bestCommentList(commentDomain);
		modelAndView.addObject("bestCommentList", bestCommentList);
				
		if(commentDomain.getStartRowNo() == 0)	
			commentDomain.setPageSize(commentDomain.getPageSize() - bestTotalCnt);
		else
			commentDomain.setStartRowNo(commentDomain.getStartRowNo() - bestTotalCnt);
		
		//댓글 전체 건수 조회(댓글 + 베스트 댓글)
		int totalCnt = commentService.commentListCnt(commentDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//댓글 리스트 조회
		List<CommentDomain> list = commentService.commentList(commentDomain);
		modelAndView.addObject("commentList", list);		

		modelAndView.addObject("commentDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");		
		
		return modelAndView;
	}
	
	
	/**
	 * 댓글 등록 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentRegistProc.kt")
	public ModelAndView commentRegistProc(@ModelAttribute("commentDomain") CommentDomain commentDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();		
		
		modelAndView.setViewName("mappingJacksonJsonView");
				
		//비정상적인 접속시 에러 처리
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);		
		commentDomain.setRegid(user.getEmail());
		commentDomain.setIdfg(user.getIdfg());
		commentDomain.setNickname(user.getNickname());
		
		//댓글 도배 체크
		String duplicateyn = commentService.commentDuplicateyn(commentDomain);
		
		if("Y".equals(duplicateyn))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.duplicate.comment"));
			return modelAndView;
		}
		
		// 금칙어 체크
		if(CommentUtil.isEqual(commentDomain.getComment()) == null && SpamWordUtil.isCommentContain(commentDomain.getComment()) != null)
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.contain.spamword"));
			
			return modelAndView;
		}
		
		//5회 이상 신고접수된 내용 체크
		commentDomain.setMusers(CommentUtil.getMoniteringUsers());
		String declareComment = (commentService.chkDeclareComment(commentDomain));
		
		if(CommentUtil.isEqual(commentDomain.getComment()) == null && declareComment != null && !"".equals(declareComment))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.declare.comment"));
			
			return modelAndView;
		}
		
		modelAndView.addObject("erroryn", "N");
			
		//작가여부 체크
		if("Y".equals(commentDomain.getAuthoryn())) 
		{
			//본인 작품 여부 체크
			String authoryn = commentService.selectMyWebtoon(commentDomain);
			if(authoryn != null && "Y".equals(authoryn)) 
			{
				commentDomain.setNameconseq(user.getNameconseq2());
			} 
			else 
			{
				commentDomain.setAuthoryn("N");
				commentDomain.setNameconseq(user.getNameconseq());
			}
		} 
		else 
		{
			commentDomain.setNameconseq(user.getNameconseq());
		}
		
		commentDomain.setRegdate(DateUtil.getNowDate(1));
		commentDomain.setComment(StringUtil.cleanXSS(commentDomain.getComment()));
		
		//댓글 저장
		commentService.commentRegistProc(commentDomain);

		modelAndView.addObject("commentDomain", null);
		
		return modelAndView;	
	}
		
	
	/**
	 * 댓글 삭제 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentDeleteProc.kt")
	public ModelAndView commentDeleteProc(@ModelAttribute("commentDomain") CommentDomain commentDomain, HttpServletRequest request) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//비정상적인 접속시 에러 처리
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		commentDomain.setRegid(user.getEmail());
		
		//본인 작성글 체크
		String commentRegId = commentService.commentRegId(commentDomain); 
		
		//본인 작성 글
		if(commentRegId != null && commentRegId.equals(commentDomain.getRegid())) 
		{ 
			//댓글 삭제(대댓글도 같이 삭제)	
			commentService.commentDeleteProc(commentDomain);
			
			modelAndView.addObject("erroryn", "N");
		}
		else
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 작성글이 아닙니다.");
		}
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		modelAndView.addObject("commentDomain", null);
				
		return modelAndView;		
	}
	
	
	/** 
	 * 대댓글 리스트 화면(모바일)으로 이동 처리하는  RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/subCommentList.kt")
	public ModelAndView subCommentList(@ModelAttribute("commentDomain") CommentDomain commentDomain, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		boolean isLogin = AuthUtil.isLogin(request); 
				
		// 유저정보
		UserDomain userDomain = AuthUtil.getDecryptUserInfo(request);
		
		// 로그인일 경우 쿠키에 저장되어있는 네임콘 정보와 DB에서 회원이 보유한 네임콘 정보가 다를경우 쿠키값에 다시 셋팅
		if(isLogin){
			
			OllehUserDomain newUserDomain = null;
			if("open".equals(userDomain.getIdfg()))
				 newUserDomain = (UserDomain)userServiceIface.getUserInfoByEmail(userDomain.getEmail());
			if("olleh".equals(userDomain.getIdfg()))
				newUserDomain = userServiceIface.ollehUserDetail(userDomain.getEmail());
			
			if(userDomain.getNameconseq() != newUserDomain.getNameconseq()){
				String cookieAge = MessageUtil.getSystemMessage("system.cookie.age");        //쿠키 유효시간 (초단위), -1 이면 브라우져 종료시 만료됨.
				String cookiePath = MessageUtil.getSystemMessage("system.cookie.path");      //쿠키경로
				String cookieDomain = MessageUtil.getSystemMessage("system.cookie.domain");  //쿠키 도메인
				int intCookieAge = Integer.parseInt(cookieAge);
				
				//쿠키정보 변경		
				CookieUtil.setCookie(response, "u_nc_seq", String.valueOf(newUserDomain.getNameconseq()), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_nc_url", newUserDomain.getNameconurl(), intCookieAge, cookiePath, cookieDomain);
				CookieUtil.setCookie(response, "u_mnc_url", newUserDomain.getMnameconurl(), intCookieAge, cookiePath, cookieDomain);
			}
		}
		
		modelAndView.setViewName("mobile/comment/commentReply");
		
		return modelAndView;
	}
	
	
	/** 
	 * 대댓글 상세 정보를 가져오는 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/subCommentJsonDetail.kt")
	public ModelAndView subCommentJsonDetail(@ModelAttribute("commentDomain") CommentDomain comment) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		
		//댓글 상세정보를 가져옴
		CommentDomain commentDomain = commentService.commentDetail(comment); 
		
		modelAndView.addObject( "commentDomain",commentDomain);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}

	
	/**
	 * 대댓글 조회 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/subCommentJsonList.kt")
	public ModelAndView subCommentJsonList(@ModelAttribute("subCommentDomain") SubCommentDomain subCommentDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		//페이지 사이즈 기본값
		if(subCommentDomain.getPageSize() == 0) subCommentDomain.setPageSize(pageSize);
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		if(user != null && !"".equals(user.getEmail()))
			subCommentDomain.setRegid(user.getEmail());
		
		//모니터링 유저 설정
		subCommentDomain.setMusers(CommentUtil.getMoniteringUsers());
		
		//대댓글 전체 건수 조회
		int totalCnt = commentService.subCommentListCnt(subCommentDomain);
		modelAndView.addObject("totalCnt", totalCnt);
		
		//대댓글 리스트 조회
		List<SubCommentDomain> list = commentService.subCommentList(subCommentDomain);
		modelAndView.addObject("subCommentList", list);
		
		modelAndView.addObject("subCommentDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	
	/**
	 * 대댓글 등록 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/subCommentRegistProc.kt")
	public ModelAndView subCommentRegistProc(@ModelAttribute("subCommentDomain") SubCommentDomain subCommentDomain, HttpServletRequest request) throws Exception 
	{	
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		//비정상적인 접속시 에러 처리
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		subCommentDomain.setRegid(user.getEmail());
		subCommentDomain.setIdfg(user.getIdfg());
		subCommentDomain.setNickname(user.getNickname());
		
		//댓글 도배 체크
		String duplicateyn = commentService.subCommentDuplicateyn(subCommentDomain);
		
		if("Y".equals(duplicateyn))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.duplicate.comment"));
			return modelAndView;
		}
		
		// 금칙어 체크
		if(CommentUtil.isEqual(subCommentDomain.getSubcomment()) == null 
				&& SpamWordUtil.isCommentContain(subCommentDomain.getSubcomment()) != null)
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.contain.spamword"));
			
			return modelAndView;
		}
		
		//5회 이상 신고접수된 내용 체크 comment? subcommnet?
		subCommentDomain.setMusers(CommentUtil.getMoniteringUsers());
		String declareComment = (commentService.chkDeclareSubComment(subCommentDomain));

		if(CommentUtil.isEqual(subCommentDomain.getSubcomment()) == null 
				&& declareComment != null && !"".equals(declareComment))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.declare.comment"));
			
			return modelAndView;
		}
		
		modelAndView.addObject("erroryn", "N");
		
		//작가 여부 체크
		if("Y".equals(subCommentDomain.getAuthoryn())) 
		{
			//본인 작품 여부 체크
			String authoryn = commentService.selectMyWebtoonByCommentseq(subCommentDomain);
			if(authoryn != null && "Y".equals(authoryn)) 
			{
				subCommentDomain.setNameconseq(user.getNameconseq2());
			} 
			else 
			{
				subCommentDomain.setAuthoryn("N");
				subCommentDomain.setNameconseq(user.getNameconseq());
			}
		}
		else 
		{
			subCommentDomain.setNameconseq(user.getNameconseq());
		}
		
		subCommentDomain.setRegdate(DateUtil.getNowDate(1));
		subCommentDomain.setSubcomment(StringUtil.cleanXSS(subCommentDomain.getSubcomment()));
				
		//대댓글 저장
		commentService.subCommentRegistProc(subCommentDomain);
		
		modelAndView.addObject("subCommentDomain", null);

		return modelAndView;	
	}
	
	
	/**
	 * 대댓글 삭제 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/subCommentDeleteProc.kt")
	public ModelAndView subCommentDeleteProc(@ModelAttribute("subCommentDomain") SubCommentDomain subCommentDomain, HttpServletRequest request) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		subCommentDomain.setRegid(user.getEmail());
		
		//본인 작성글 체크
		String subCommentRegId = commentService.subCommentRegId(subCommentDomain); 
		
		if(subCommentRegId != null && subCommentRegId.equals(subCommentDomain.getRegid())){//본인 작성글 
			//대댓글 삭제	
			commentService.subCommentDeleteProc(subCommentDomain);
			
			modelAndView.addObject("erroryn", "N");
		}
		else
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "본인 작성글이 아닙니다.");
		}
		
		modelAndView.addObject("subCommentDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
				
		return modelAndView;		
	}
	
	
	/**
	 * 댓글 좋아요/싫어요 처리하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentLikeProc.kt")
	public ModelAndView commentLikeProc(@ModelAttribute("commentDomain") CommentDomain commentDomain, HttpServletRequest request) throws Exception 
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		//유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		commentDomain.setRegid(user.getEmail());
		commentDomain.setIdfg(user.getIdfg());
		commentDomain.setRegdate(DateUtil.getNowDate(1));
		
		//추천 중복 체크
		String recommseq = commentService.commentRecommendSeq(commentDomain);
		
		//본인 작성글 체크
		String commentRegId = commentService.commentRegId(commentDomain); 
		
		if(commentRegId != null && commentDomain.getRegid().equals(commentRegId)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.comment.my"));
		} else if(recommseq != null && !"".equals(recommseq)) {
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", MessageUtil.getSystemMessage("message.error.comment.dup"));
			
		} else {
		
			modelAndView.addObject("erroryn", "N");
			
			//업데이트
			commentService.commentRecommend(commentDomain);	
		}		

		modelAndView.addObject("commentDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");   
		
		return modelAndView;
	}

	
	/**
	 * 스티커콘 리스트를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/stickerconJsonList.kt")
	public ModelAndView stickerconJsonList(@ModelAttribute("stickerIconDomain") StickerIconDomain stickerIconDomain) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		//스티커콘 총 갯수 조회
		int totalCnt = commentService.stickerconListCnt();
		modelAndView.addObject("totalCnt", totalCnt);
		
		//스티커콘 아이템 리스트 조회
		List<StickerIconDomain> list = commentService.stickerconList(stickerIconDomain);
		modelAndView.addObject("stickerconList", list);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	

	/**
	 * 신고 처리 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentDeclation.kt")
	public ModelAndView commentDeclation(@ModelAttribute("declarationDomain") DeclarationDomain declarationDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		// 유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		declarationDomain.setRegid(user.getEmail());
		declarationDomain.setIdfg(user.getIdfg());
		declarationDomain.setRegdt(DateUtil.getNowDate(1));
					
		//신고 접수
		commentService.commentDeclation(declarationDomain);
		
		//블라인드 여부 조회 -> 신고 5회 이상일 경우 refresh
		declarationDomain.setMusers(CommentUtil.getMoniteringUsers());
		String blindyn = commentService.commentBlind(declarationDomain);
		
		if("Y".equals(blindyn))
		{
			modelAndView.addObject("blindyn", "Y");
		}
		else
		{
			modelAndView.addObject("blindyn", "N");
		}

		modelAndView.addObject("declarationDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	
	/**
	 * 신고여부를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/checkDeclare.kt")
	public ModelAndView checkDeclare(@ModelAttribute("declarationDomain") DeclarationDomain declarationDomain, HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		if(user != null && !"".equals(user.getEmail()))
			declarationDomain.setRegid(user.getEmail());		
		
		// 신고여부 확인
		String declarationseq = commentService.declarationSeq(declarationDomain);
		
		if(declarationseq != null && !"".equals(declarationseq))
		{
			modelAndView.addObject("erroryn", "Y");
			modelAndView.addObject("errormsg", "이미 신고가 접수되었습니다.");
		}
		else
		{
			modelAndView.addObject("erroryn", "N");
		}		

		modelAndView.addObject("declarationDomain", null);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	 
	/**
	 * 보유한 스티콘 리스트를 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/commentconJsonList.kt")
	public ModelAndView commentconJsonList(HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
//		if(!AuthUtil.isLogin(request))
//		{
//			throw new Exception();
//		}
		
		// 유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		
		//보유한 스티콘 목록
		List<ShopDomain> commentconList = shopService.commentconList(user.getEmail());
		modelAndView.addObject("commentconList", commentconList);
		
		List<List<IconDomain>> iconImageList = new ArrayList<List<IconDomain>>();
		
		//각 상품의 이미지 리스트
		for(ShopDomain shopDomain : commentconList)
		{
			iconImageList.add(shopService.iconImageList(shopDomain.getPrdhistoryseq()));
		}
		
		modelAndView.addObject("iconImageList", iconImageList);
		
		//최근 사용한 스티콘 목록
		List<IconDomain> recentconList = commentService.recentconList(user.getEmail());
		modelAndView.addObject("recentconList", recentconList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
	
	/**
	 * 최근사용한 스티콘 목록을 조회하는 RequestMapping 메소드
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping(value = "/common/recentconJsonList.kt")
	public ModelAndView recentconJsonList(HttpServletRequest request) throws Exception
	{
		ModelAndView modelAndView = new ModelAndView();
		
		if(!AuthUtil.isLogin(request))
		{
			throw new Exception();
		}
		
		// 유저정보 가져오기
		UserDomain user = com.olleh.webtoon.common.util.AuthUtil.getDecryptUserInfo(request);
		
		//최근 사용한 스티콘 목록
		List<IconDomain> recentconList = commentService.recentconList(user.getEmail());
		modelAndView.addObject("recentconList", recentconList);
		
		modelAndView.setViewName("mappingJacksonJsonView");
		
		return modelAndView;
	}
}