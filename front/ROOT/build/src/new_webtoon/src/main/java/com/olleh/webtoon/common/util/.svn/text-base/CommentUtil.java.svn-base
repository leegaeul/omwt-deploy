package com.olleh.webtoon.common.util;


public class CommentUtil {
	
	protected static final String ADMIN_ID = "ollehmarketwebtoon.cs@gmail.com";
	
	protected static String[] COMMENT_DEFAULT_WORDS = new String[]{
												"허윽~ 다음 내용이 궁금궁금! 벌써부터 기다려져욧!"   
												,"넘치는 재미 파워! 웹툰계를 평정 할 불꽃같은 작품"
												,"키햐~ 작가님 센스에 무릎을 탁! 감동이 팍!"
												,"요런 스토리를 상상하고 그려내는 작가님의 능력에 치얼쓰☆"   
												,"역시 올레마켓웹툰과 내 취향은! 찹쌀떡~♪"   
												,"이런 작품이 숨어있었다니, 홍보 요정이 되어볼게요! 뾰로롱"   
												,"손에 땀을 쥐게 하는 찰진 대사! 임팩트 최고 b"   
												,"스크롤아 멈추지 마라! 나는 더 달리고 싶단다~*"
												,"오늘 연재 업데이트만을 손꼽아 기다리고 계셨던 분들~ 손!"
												,"웹소설의 세계에 흠뻑 빠져들게 한 작품입니다. 추천합니다!"
												,"단행본으로 소장하여 영~원토록 함께하고픈 웹소설!"
												,"처음부터 끝까지 숨 죽이고 읽게되는 작품 @_@"
												,"고품격 웹소설 감상으로 일상의 품격도 업그레이드!"
												,"한 눈에 쏙쏙 들어오는 요점 정리! 참~ 잘했어요!!!" 
												,"요런 저런 궁금했던 이야기들... 앞으로도 많이 부탁 드려요~" 
												,"영!차! 나만 모르고 있던 힛트작 새로이 발굴해내기" 
												,"레어템 득템하는 기분! 올레마켓웹툰 다시봤어요~" 
												,"맛보기 코너~ 본격 감상하러 떠나렵니다! 휘릭!"
												,"한 눈에 쏙쏙 들어오는 요점 정리! 참~ 잘했어요!!!" 
												,"요런 저런 궁금했던 이야기들... 앞으로도 많이 부탁 드려요~" 
												,"영!차! 나만 모르고 있던 힛트작 새로이 발굴해내기" 
												,"레어템 득템하는 기분! 올레마켓웹툰 다시봤어요~" 
												,"맛보기 코너~ 본격 감상하러 떠나렵니다! 휘릭!"
	};
	
	protected static String MONITORING_USERS = "nadiazzang@naver.com"
										     + ",mynaviz@paran.com"
											 + ",kim.youngsik@kt.com"
											 + ",connectdb@naver.com"
											 + ",ujung626@gmail.com"
											 + ",Patarilo78@naver.com"
											 + ",ujung626@gmail.com"
											 + ",triea@naver.com"
											 + ",ggimgi@daum.net"
											 + ",romis@naver.com";
	
	/**
     * 랜덤 기본문구 
     * 
     * @return String : 랜덤 기본문구
     */
	public static String getRandomPlaceHolder(){
		
		return COMMENT_DEFAULT_WORDS[(int)Math.round(Math.random() * (COMMENT_DEFAULT_WORDS.length - 1))];
	}
	
    /**
     * 기본문구와 일치 여부
     * @param String str : 체크 할 문자열
     * @return String    : 일치 기본문구
     */
    public static String isEqual(String str) {
    	for(int i=0 ; i < COMMENT_DEFAULT_WORDS.length ; i++){
    		if((COMMENT_DEFAULT_WORDS[i].toUpperCase()).equals(str.toUpperCase())){
    			return COMMENT_DEFAULT_WORDS[i];
    		}
    	}
    	return null;
    }
    
    /**
     * 모니터링 유저아이디 정보 
     * 
     * @return String : 유저아이디 정보
     */
    public static String getMoniteringUsers(){
    	
    	return "'" + MONITORING_USERS.replaceAll(",", "','") + "'";
    }
    
    /**
     * 운영자 여부 조회 
     * 
     * @return String : 유저아이디 정보
     */
    public static String getAdminyn(String userid){
    	
    	return ADMIN_ID.equals(userid) ? "Y" : "N";
    }
    
    
}