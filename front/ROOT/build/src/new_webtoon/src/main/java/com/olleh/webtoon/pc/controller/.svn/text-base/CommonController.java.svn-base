/*****************************************************************************
 * PROJECT NAME   : 올레마켓 웹툰
 * SUBSYSTEM NAME : 사용자
 * FILE NAME      : CommonController.java
 * DESCRIPTION    : 공통 include 처리 관련 Controller class.
 * 
 * VERSION NO     author           date        content  -> info
 * ----------------------------------------------------------------------------
      1.0        Donghyun Kim      2014-04-21      init
 *****************************************************************************/

package com.olleh.webtoon.pc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.olleh.webtoon.common.dao.applay.domain.EventDomain;
import com.olleh.webtoon.common.dao.applay.domain.RecommendAppDomain;
import com.olleh.webtoon.common.dao.applay.service.iface.EventService;
import com.olleh.webtoon.common.dao.applay.service.iface.RecommendAppService;
import com.olleh.webtoon.common.dao.bluemembership.code.JoinStatusCode;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinDomain;
import com.olleh.webtoon.common.dao.bluemembership.domain.BMJoinTermDomain;
import com.olleh.webtoon.common.dao.bluemembership.service.BMOrderService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMService;
import com.olleh.webtoon.common.dao.bluemembership.service.BMTermService;
import com.olleh.webtoon.common.dao.ranking.service.iface.RankingService;
import com.olleh.webtoon.common.dao.support.domain.NoticeDomain;
import com.olleh.webtoon.common.dao.support.service.iface.NoticeService;
import com.olleh.webtoon.common.dao.toon.domain.ToonDomain;
import com.olleh.webtoon.common.dao.toon.service.iface.ToonService;
import com.olleh.webtoon.common.dao.user.domain.UserDomain;
import com.olleh.webtoon.common.util.AuthUtil;
import com.olleh.webtoon.common.util.RequestUtil;
import com.olleh.webtoon.pc.cache.RnbCache;

@Controller
public class CommonController
{

	private final int		NEWEST_SIZE	= 5;
	protected static Log	logger		= LogFactory.getLog( CommonController.class );

	@Autowired
	ToonService				toonService;

	@Autowired
	NoticeService			noticeService;

	@Autowired
	EventService			eventService;

	@Autowired
	RecommendAppService		recommendAppService;

	@Autowired
	RankingService			rankingService;

	@Autowired
	BMService				bmService;

	@Autowired
	BMTermService			bmTermService;;
	
	@Autowired
	BMOrderService			bmOrderService;
	
	@Value( "${system.pc.domain.ssl}" )
	private String				SYSTEM_PC_DOMAIN_SSL;

	@Value( "${system.pc.domain}" )
	private String				SYSTEM_PC_DOMAIN;


	/**
	 * 공통 : RNB 영역 include 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/common/rnb.kt" )
	public ModelAndView rnb( HttpServletRequest request ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "pc/include/rnb.inc" );

		// 오른쪽 화면의 ModelAndView객체를 캐시에서 가져오고 없으면 구성해서 넣는다.
		RnbCache rnbCache = RnbCache.getInstance();
		ModelAndView cachedModelAndView = rnbCache.getCachedModelAndView();

		if( cachedModelAndView != null )
		{
			modelAndView = cachedModelAndView;
		}
		else
		{

			// rnb notice 조회
			List<NoticeDomain> noticeList = noticeService.newestNoticeList( NEWEST_SIZE );
			modelAndView.addObject( "noticeList", noticeList );

			// rnb 이벤트 조회
			EventDomain eventDomain = eventService.rnbEvent();
			modelAndView.addObject( "eventDomain", eventDomain );

			// rnb 추천앱 조회
			RecommendAppDomain recommendAppDomain = recommendAppService.rnbRecommendApp();
			modelAndView.addObject( "recommendAppDomain", recommendAppDomain );

			// 월간 랭킹 목록 조회
			List<String> dateList = rankingService.rankingDate();
			modelAndView.addObject( "dateList", dateList );

			Map<String, Object> param = new HashMap<String, Object>();
			param.put( "rankingmt", dateList.get( 0 ) );

			List<ToonDomain> monthList = rankingService.monthList( param );
			modelAndView.addObject( "monthList", monthList );

			// 정보를 모두 취합한 후 캐시에 넣는다.
			rnbCache.putCache( modelAndView );
		}

		// My toon 정보
		// 로그인 여부 체크
		/*
		 * UserDomain userDomain = com.olleh.webtoon.common.util.AuthUtil.getCookieUserInfo(request);
		 * 
		 * if(userDomain != null && userDomain.getEmail() != null){
		 * Map<String,Object> param = new HashMap<String,Object>();
		 * param.put("regid", userDomain.getEmail());
		 * 
		 * String cookieName = "timesseqList"; //쿠키 변수명
		 * 
		 * //쿠키에서 최근 본 회차 목록 가져온다.
		 * String timesseqList = StringUtil.defaultStr(CookieUtil.getCookie(request, cookieName), "");
		 * 
		 * timesseqList = timesseqList.replaceAll("><", "','").replaceAll("<", "'").replaceAll(">", "'");
		 * 
		 * param.put("timesseqList", timesseqList);
		 * 
		 * List<Map<String,Object>> mytoon = toonService.mytoonDetail(param);
		 * 
		 * modelAndView.addObject("mytoon", mytoon);
		 * }
		 */

		// 공통 : 주간인기 웹툰 정보 가져오기(Cache 처리)

		// 공통 : 추천 앱 정보 가져오기(Cache 처리)

		// 공통 : 프리미엄 목록 가져오기(Cache 처리)

		// 공통 : 신규 아이템 목록 가져오기(Cache 처리)

		// 공통 : Hot 이벤트 정보 가져오기(Cache 처리)

		// 공통 : 공지사항 목록 가져오기(Cache 처리)

		return modelAndView;

	}


	/**
	 * 헤더 인클루드
	 * 블루멤버십 관련 데이터 전달
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "/common/header.inc.kt" )
	public ModelAndView headerInc( HttpServletRequest request ) throws Exception
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName( "pc/include/header.inc" );
		
		mv.addObject( "SYSTEM_PC_DOMAIN_SSL", SYSTEM_PC_DOMAIN_SSL 	);
		mv.addObject( "SYSTEM_PC_DOMAIN"	, SYSTEM_PC_DOMAIN 		);

		if( !AuthUtil.isLogin( request ) ) return mv;

		//로그인한 사용자 정보
		UserDomain user = AuthUtil.getDecryptUserInfo( request );
		mv.addObject( "user", user );
		
		//블루멤버십 가입 정보
		BMJoinDomain bmJoin = bmService.getActiveJoinForUser( user.getIdfg(), user.getEmail() );
		if( bmJoin == null ) return mv;
		mv.addObject( "bmJoin", bmJoin );
		
		//이용 가능한 블루베리 갯수
		Integer ownBlueBerry = bmOrderService.getOwnBlueBerryForUser( user.getIdfg(), user.getEmail() );
		mv.addObject( "ownBlueBerry", ownBlueBerry );
		
		
		BMJoinTermDomain term = bmTermService.getCurrentTermForUser( user.getIdfg(), user.getEmail() );
		
		if( term == null && bmJoin.getJoinstatusfg() == JoinStatusCode.active )
			term = bmTermService.getLastJoinTermByJoinseq( bmJoin.getJoinseq() );
		
		//다음 충전일
		mv.addObject( "nextTerm", bmTermService.getNextTermForUserAndPrd( bmJoin.getIdfg(), bmJoin.getUserid(), bmJoin.getPrdcode() ) );

		return mv;
	}


	/**
	 * toon 목록, 작화보기 : 올레마켓 추천 서비스 include 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/common/recommendService.kt" )
	public ModelAndView recommendService() throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "pc/include/recommendService.inc" );

		// 올레마켓 추천 서비스 목록 가져오기

		return modelAndView;

	}


	/**
	 * 랭킹 목록 : 장르별 베스트 include 처리하는 RequestMapping 메소드
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = "/common/genreBest.kt" )
	public ModelAndView genreBest() throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName( "pc/include/genreBest.inc" );

		// 장르별 베스트 정보 가져오기

		return modelAndView;

	}


	/**
	 * 기존 URL Recirect 처리 : 요요진 상세, Toon 회차 목록, Toon 회차 상세
	 * 
	 * @return ModelAndView modelAndView : ModelAndView 객체
	 */
	@RequestMapping( value = { "/subcontents/yoyozine_list.kt", "/subcontents/yoyozine_detail.kt", "/main/times_list.kt", "/main/times_detail.kt",
			"/main/week_list.kt" } )
	public ModelAndView oldUrlRedirect( HttpServletRequest request, HttpServletResponse response ) throws Exception
	{

		ModelAndView modelAndView = new ModelAndView();

		String mobileRedirectUrl = "/m/main.kt";
		String pcRedirectUrl = "/main.kt";

		if( request.getRequestURI().indexOf( "/subcontents/yoyozine_list.kt" ) > -1 )
		{
			// 요요진 목록URL

			mobileRedirectUrl = "/m/yoyozine/list.kt";
			pcRedirectUrl = "/yoyozine/list.kt";

		}
		else if( request.getRequestURI().indexOf( "/subcontents/yoyozine_detail.kt" ) > -1 )
		{
			// 요요진 상세URL

			mobileRedirectUrl = "/m/yoyozine/yoyozineDetail.kt?yoyozineseq=" + RequestUtil.getParameter( request, "yoyozineseq", true );
			pcRedirectUrl = "/yoyozine/detail.kt?yoyozineseq=" + RequestUtil.getParameter( request, "yoyozineseq", true );

		}
		else if( request.getRequestURI().indexOf( "/main/times_detail.kt" ) > -1 )
		{
			// 웹툰 회차목록 URL

			mobileRedirectUrl = "/m/toon/timesDetail.kt?timesseq=" + RequestUtil.getParameter( request, "timesseq", true ) + "&webtoonseq="
					+ RequestUtil.getParameter( request, "webtoonseq", true );
			pcRedirectUrl = "/toon/timesDetail.kt?timesseq=" + RequestUtil.getParameter( request, "timesseq", true ) + "&webtoonseq="
					+ RequestUtil.getParameter( request, "webtoonseq", true );

		}
		else if( request.getRequestURI().indexOf( "/main/times_list.kt" ) > -1 )
		{
			// 웹툰 회차상세 URL

			mobileRedirectUrl = "/m/toon/timesList.kt?webtoonseq=" + RequestUtil.getParameter( request, "webtoonSeq", true );
			pcRedirectUrl = "/toon/timesList.kt?webtoonseq=" + RequestUtil.getParameter( request, "webtoonSeq", true );

		}
		else if( request.getRequestURI().indexOf( "/main/week_list.kt" ) > -1 )
		{
			mobileRedirectUrl = "/m/toon/weekList.kt?type=list&week=" + RequestUtil.getParameter( request, "week", true );
			pcRedirectUrl = "/toon/weekList.kt?type=list&week=" + RequestUtil.getParameter( request, "week", true );
		}

		// 모바일 체크
		boolean isMobile = false;
		String userAgent = request.getHeader( "User-Agent" ).toLowerCase();
		if( userAgent
				.matches( ".*(android|avantgo|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\\/|plucker|pocket|psp|symbian|treo|up\\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino).*" )
				|| userAgent
						.substring( 0, 4 )
						.matches(
								"1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\\-(n|u)|c55\\/|capi|ccwa|cdm\\-|cell|chtm|cldc|cmd\\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\\-s|devi|dica|dmob|do(c|p)o|ds(12|\\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\\-|_)|g1 u|g560|gene|gf\\-5|g\\-mo|go(\\.w|od)|gr(ad|un)|haie|hcit|hd\\-(m|p|t)|hei\\-|hi(pt|ta)|hp( i|ip)|hs\\-c|ht(c(\\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\\-(20|go|ma)|i230|iac( |\\-|\\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\\/)|klon|kpt |kwc\\-|kyo(c|k)|le(no|xi)|lg( g|\\/(k|l|u)|50|54|e\\-|e\\/|\\-[a-w])|libw|lynx|m1\\-w|m3ga|m50\\/|ma(te|ui|xo)|mc(01|21|ca)|m\\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\\-2|po(ck|rt|se)|prox|psio|pt\\-g|qa\\-a|qc(07|12|21|32|60|\\-[2-7]|i\\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\\-|oo|p\\-)|sdk\\/|se(c(\\-|0|1)|47|mc|nd|ri)|sgh\\-|shar|sie(\\-|m)|sk\\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\\-|v\\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\\-|tdg\\-|tel(i|m)|tim\\-|t\\-mo|to(pl|sh)|ts(70|m\\-|m3|m5)|tx\\-9|up(\\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\\-|2|g)|yas\\-|your|zeto|zte\\-" ) ) isMobile = true;

		// 모바일 체크 후 redirect
		if( isMobile ) modelAndView.setViewName( "redirect:" + mobileRedirectUrl );
		else modelAndView.setViewName( "redirect:" + pcRedirectUrl );

		return modelAndView;

	}

}
