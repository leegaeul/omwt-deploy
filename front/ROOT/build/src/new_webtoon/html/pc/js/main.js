$(document).ready(function(){
	//인기앱, HOT 아이템 탭 20140512 2차 오픈때 쓰임
	/* 
	$('.m_issue_app .tab a').click(function(){
		$('.m_issue_app .tab a').removeClass('on');
		$('.m_issue_app .tabEtc').removeClass('tabEtcOn');
		$(this).addClass('on');
		$(this).parent().next().addClass('tabEtcOn');
		return false;
	});
	*/

	//오늘의 웹툰, HOT 앱
	$('.m_main_tab .tab a').click(function(){
		$('.m_main_tab .tab a').removeClass('on');
		$('.m_main_tab .tabEtc').removeClass('tabEtcOn');
		$(this).addClass('on');
		$(this).parent().next().addClass('tabEtcOn');
		return false;
	});

	//퀵바
	var currentPosition = parseInt($("#quick_right .quick").css("top"));
	$(window).scroll(function() {
		var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환		
		if (position < 0) position = 0; 
		$("#quick_right .quick").stop().animate({"top": position + currentPosition + "px"}, 800);
	});
	var currentPosition2 = parseInt($("#quick_left .quick").css("top"));
	$(window).scroll(function() {
		var position2 = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환		
		if (position2 < 0) position2 = 0; 
		$("#quick_left .quick").stop().animate({"top": position2 + currentPosition2 + "px"}, 800);
	});
});