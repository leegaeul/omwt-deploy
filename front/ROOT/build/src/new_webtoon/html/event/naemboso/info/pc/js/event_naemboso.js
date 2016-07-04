
$(document).ready(function(){	
	// 휴대폰번호 value 설정 */
	$(".phone_input").focusin(function(){
		var val = $(this).val();
		if(val == "‘-’를 제외한 숫자만 입력해주세요"){
			$(this).val("");
		}
	})
	$(".phone_input").focusout(function(){
		var val = $(this).val();
		if(val == ""){
			$(this).val("‘-’를 제외한 숫자만 입력해주세요");
		}
	})

	// 공유할 내용 입력 */
	$(".comment_txt").focusin(function(){
		$(this).html("");
	})
	
	// 디자인 라디오
	function designRadio(){
		var $radioWrapper = $('.radio_list'), //라디오 버튼 전체 영역
			$radioIist = $('.radio_list > li'), //라디오 버튼 리스트
			$radioInput = $('.radio_list > li > input'), //라디오 버튼 영역
			$radioLabel = $('.radio_list > li > label'), //레이블 버튼 영역
			$radioFake = $('.radio_list > li > .design_radio'), //페이크 버튼 영역
			$startIdx = 0, //라디오 버튼 초기화
			onIndex = -1 //현재활성화 라디오 버튼 초기화

		function init(){
			$radioInput.each(function (idx, entry){
				$(entry).data('input', idx);
			});
			$radioIist.each(function (idx, entry){
				$(entry).data('label', idx);
			});
			startIdx($startIdx); //탭 초기 활성화
			event(); //이벤트 실행
		};
		init(); //초기화

		//처음 이벤트 실행될 함수
		function startIdx(idx){
			$radioIist.eq(idx).addClass('select');
				$radioInput.css({position:'absolute', left:-9999});
			onIndex = idx;
		};

		//레이블 아웃라인 주기
		function labelOutline(idx){
			$radioIist.eq(onIndex).removeClass('select').children('.design_radio').css({outline:'none'});
			$radioIist.eq(idx).addClass('select').children('.design_radio').css({outline:'1px dotted'});

			onIndex = idx;
		}

		//레이블 클릭시 인풋 체크(ie 7,8대응)
		function inputCheck(idx){
			$radioIist.eq(onIndex).removeClass('select').children('.design_radio').css({outline:'none'});
			$radioIist.eq(idx).addClass('select').children('.design_radio').css({outline:'1px dotted'});
			$radioInput.eq(idx).focus();

			onIndex = idx;
		}

		//이벤트 실행
		function event(){
			//라디오버튼 클릭시
			$radioInput.bind({
				'focusin' : function(event){
					labelOutline($(this).data('input'));

				},
				'focusout' : function(event){
					$radioIist.children('.design_radio').css({outline:'none'});
				}
			});
			//레이블 클릭시
			$radioLabel.bind({
				'click' : function(event){
						$radioIist.removeClass('select').children('.design_button').css({outline:'none'});
						$radioIist.children("input").attr("checked", null);

						$(this).parents().addClass('select').children('.design_button').css({outline:'1px dotted'});
						$(this).parents().children("input").attr("checked", "checked");
				}
			});
		};
	};
	designRadio();

	// 디자인 체크박스
	function designCheckbox(){
		var $radioWrapper = $('.term_box1'), //라디오 버튼 전체 영역
			$radioIist = $('.term_box1 > dt'), //라디오 버튼 리스트
			$checkInputFake = $('.term_box1 > dt > .design_button'), //라디오 버튼 영역
			$checkInput = $('.term_box1 > dt > input'), //라디오 버튼 영역
			$radioLabel = $('.term_box1 > dt > label'), //레이블 버튼 영역
			$startIdx = 0, //라디오 버튼 초기화
			onIndex = -1 //현재활성화 라디오 버튼 초기화

		function init(){
			$checkInput.each(function (idx, entry){
				$(entry).data('input', idx);
			});
			$radioIist.each(function (idx, entry){
				$(entry).data('label', idx);
			});
			event(); //이벤트 실행
		};
		init(); //초기화

		//처음 이벤트 실행될 함수
		function startIdx(idx){
			$radioIist.eq(idx).addClass('select').children("input").attr("checked", "checked");
				$checkInput.css({position:'absolute', left:-9999});
			onIndex = idx;
		};

		//이벤트 실행
		function event(){
			//레이블 클릭시
			$radioLabel.bind({
				'click' : function(event){
					if($(this).parent().hasClass('select') == false){
						$(this).parents().addClass('select').children('.design_button').css({outline:'1px dotted'});
						$(this).parents().children("input").attr("checked", "checked");
					}else{
						$(this).parents().removeClass('select').children('.design_button').css({outline:'none'});
						$(this).parents().children("input").attr("checked", null);
					};
				}
			});
		};
	};
	designCheckbox();

	// 디자인 체크박스2
	function designCheckbox2(){
		var $radioWrapper = $('.term_box2'), //라디오 버튼 전체 영역
			$radioIist = $('.term_box2 > dt'), //라디오 버튼 리스트
			$checkInputFake = $('.term_box2 > dt > .design_button'), //라디오 버튼 영역
			$checkInput = $('.term_box2 > dt > input'), //라디오 버튼 영역
			$radioLabel = $('.term_box2 > dt > label'), //레이블 버튼 영역
			$startIdx = 0, //라디오 버튼 초기화
			onIndex = -1 //현재활성화 라디오 버튼 초기화

		function init(){
			$checkInput.each(function (idx, entry){
				$(entry).data('input', idx);
			});
			$radioIist.each(function (idx, entry){
				$(entry).data('label', idx);
			});
			event(); //이벤트 실행
		};
		init(); //초기화

		//처음 이벤트 실행될 함수
		function startIdx(idx){
			$radioIist.eq(idx).addClass('select').children("input").attr("checked", "checked");
				$checkInput.css({position:'absolute', left:-9999});
			onIndex = idx;
		};

		//이벤트 실행
		function event(){
			//레이블 클릭시
			$radioLabel.bind({
				'click' : function(event){
					if($(this).parent().hasClass('select') == false){
						$(this).parents().addClass('select').children('.design_button').css({outline:'1px dotted'});
						$(this).parents().children("input").attr("checked", "checked");
					}else{
						$(this).parents().removeClass('select').children('.design_button').css({outline:'none'});
						$(this).parents().children("input").attr("checked", null);
					};
				}
			});
		};
	};
	designCheckbox2();

	$.fn.layerPopup = function (options) {
		var opts= $.extend({
			evtType : "over", // over, click
			btnHideClass : ""
		}, options);
		
		return this.each(function () {
			var self = this,
				$self = $(this),
				$targetLayer = null,
				$btnHide = null,
				hiddenTimer = null;
			
			if ($self.attr("href") == "undefined")
				return true;
			
			$targetLayer = $($self.attr("href"));
			
			if ($targetLayer.length  != 1)
				return true;
			
			$btnHide = $targetLayer.find(opts.btnHideClass);
			
			/* 20150420 수정 */
			function layerShow() {
				if($self.hasClass('event03')){
					$targetLayer.addClass('layer_event03')
				}
				$targetLayer.css({visibility : "visible", display:"block"}).addClass("on");

				function pop_vertical(){//팝업 센터 정렬
					var pop_height = $('.layer_first').height();
					var pop_height_device = - (pop_height / 2); 
					$('.layer_first').css("top","50%");
					$('.layer_first').css("margin-top",pop_height_device);
				}
				pop_vertical();

				if($('.layer_photo_register ').hasClass('on')) {
					$('.layer_bg_wrap').css({'display' : 'block'});
				};
				layerScrollAuto();
			};
			
			function layerHidden() {
				if($self.hasClass('event03')){
					$targetLayer.removeClass('layer_event03')
				}

				if($('.layer_photo_register ').hasClass('on')) {
					$('.layer_bg_wrap').css({'display' : 'none'});
				};
				$targetLayer.css({visibility : "hidden"}).removeClass("on");

				var val = $('.photo_comment_txt').val();
				if(val !== "20자 이상~100자 이내로 입력해주세요."){
					$('.photo_comment_txt').val("20자 이상~100자 이내로 입력해주세요.");
				}
	
				/* 20150420 추가 */
				$("html").css({"overflow-y" : "auto"});
				$("#wrap").css({"overflow-y" : "auto"});
				/* //20150420 추가 */
			};
			/* //20150420 수정 */

			function addEvent() {

				$self.unbind(".layerPopup");
				$targetLayer.unbind(".layerPopup");
				$btnHide.unbind(".layerPopup");

				if (opts.evtType == "over") {
					$self.bind({
						"mouseenter.layerPopup focusin.layerPopup" : function (event) { clearTimeout(hiddenTimer); layerShow(); },
						"mouseleave.layerPopup focusout.layerPopup" : function (event) { clearTimeout(hiddenTimer); hiddenTimer = setTimeout(layerHidden, 100); },
						"click.layerPopup focusout.layerPopup" : function (event) { return false; }
					});
					$targetLayer.bind({
						"mouseenter.layerPopup focusin.layerPopup" : function (event) { clearTimeout(hiddenTimer); layerShow(); },
						"mouseleave.layerPopup focusout.layerPopup" : function (event) { clearTimeout(hiddenTimer); hiddenTimer = setTimeout(layerHidden, 100); }
					});
					if ($btnHide.length > 0)
						$btnHide.bind("click.layerPopup", function (event) { layerHidden(); return false; });
				} else if (opts.evtType == "click") {
					$self.bind("click.layerPopup", function (event) {
						if ($targetLayer.css("visibility") == "hidden" || $targetLayer.css("display") == "none")
							layerShow();
						else
							layerHidden();
						return false;
					});
					
					if ($btnHide.length > 0)
						$btnHide.bind("click.layerPopup", function (event) { layerHidden(); return false; });
				}
			};
			addEvent();
		});
	};
	$(".btn_click_prv").layerPopup({evtType : "click", btnHideClass : ".btn_layer_close"});

	/* 20150417 수정 */
	//탭
	function enterTabMenu() {
		$tabAnchor = $('.tab_box').find('a');
		$tabLi = $tabAnchor.parent('li');
		$tabContent = $('.content_box');

		$tabContent.eq(2).show(); //탭 컨텐츠 초기화
		$tabLi.eq(2).children('a').addClass('tab_on');
		
		$tabAnchor.on('click', function(){
			tabIndex = $(this).parent().index();
			if(tabIndex == 0){
				$tabLi.children('a').removeClass('tab_on');
				$tabLi.children('a').removeClass('tab_active');
				$tabLi.eq(1).children('a').addClass('tab_active');
				$(this).parent('li').children('a').addClass('tab_on');
				$tabContent.hide();
				$tabContent.eq(0).show();
				$('.layer_first').removeClass('on');
				$('.layer_first').removeClass('layer_event03');
				$('.layer_first').css('visibility' , 'hidden');
				$('.layer_photo_register').removeClass('on');
				$('.layer_photo_register').css('visibility' , 'hidden');
			}else if(tabIndex == 1){
				$tabLi.children('a').removeClass('tab_on');
				$tabLi.children('a').removeClass('tab_active');
				$tabLi.eq(0).children('a').addClass('tab_active');
				$tabLi.eq(2).children('a').addClass('tab_active');
				$(this).parent('li').children('a').addClass('tab_on');
				$tabContent.hide();
				$tabContent.eq(1).show();
				$('.layer_first').removeClass('on');
				$('.layer_first').css('visibility' , 'hidden');
				$('.layer_photo_register').removeClass('on');
				$('.layer_photo_register').css('visibility' , 'hidden');
			}else if(tabIndex == 2){
				$tabLi.children('a').removeClass('tab_on');
				$tabLi.children('a').removeClass('tab_active');
				$(this).parent('li').children('a').addClass('tab_on');
				$tabContent.hide();
				$tabContent.eq(2).show();
				$('.layer_first').removeClass('on');
				$('.layer_first').css('visibility' , 'hidden');
			}
			return false;
		});
	}
	enterTabMenu();
	/* //20150417 수정 */
	
	/* 20150417 추가 */
	// 이벤트 참여영역 휴대폰번호 value 설정 */
	$(".phone_join_input").focusin(function(){
		var val = $(this).val();
		if(val == "휴대폰 번호 ‘-’를 제외한 숫자만 입력"){
			$(this).val("");
		}
	})
	$(".phone_join_input").focusout(function(){
		var val = $(this).val();
		if(val == ""){
			$(this).val("휴대폰 번호 ‘-’를 제외한 숫자만 입력");
		}
	})

	// 디자인 체크박스3
	function designCheckbox3(){
		var $radioWrapper = $('.join_agree_box1'), //라디오 버튼 전체 영역
			$radioIist = $('.join_agree_box1 > dt'), //라디오 버튼 리스트
			$checkInputFake = $('.join_agree_box1 > dt > .design_button'), //라디오 버튼 영역
			$checkInput = $('.join_agree_box1 > dt > input'), //라디오 버튼 영역
			$radioLabel = $('.join_agree_box1 > dt > label'), //레이블 버튼 영역
			$startIdx = 0, //라디오 버튼 초기화
			onIndex = -1 //현재활성화 라디오 버튼 초기화

		function init(){
			$checkInput.each(function (idx, entry){
				$(entry).data('input', idx);
			});
			$radioIist.each(function (idx, entry){
				$(entry).data('label', idx);
			});
			event(); //이벤트 실행
		};
		init(); //초기화

		//처음 이벤트 실행될 함수
		function startIdx(idx){
			$radioIist.eq(idx).addClass('select').children("input").attr("checked", "checked");
				$checkInput.css({position:'absolute', left:-9999});
			onIndex = idx;
		};

		//이벤트 실행
		function event(){
			//레이블 클릭시
			$radioLabel.bind({
				'click' : function(event){
					if($(this).parent().hasClass('select') == false){
						$(this).parents().addClass('select').children('.design_button').css({outline:'1px dotted'});
						$(this).parents().children("input").attr("checked", "checked");
					}else{
						$(this).parents().removeClass('select').children('.design_button').css({outline:'none'});
						$(this).parents().children("input").attr("checked", null);
					};
				}
			});
		};
	};
	designCheckbox3();

	// 디자인 체크박스4
	function designCheckbox4(){
		var $radioWrapper = $('.join_agree_box2'), //라디오 버튼 전체 영역
			$radioIist = $('.join_agree_box2 > dt'), //라디오 버튼 리스트
			$checkInputFake = $('.join_agree_box2 > dt > .design_button'), //라디오 버튼 영역
			$checkInput = $('.join_agree_box2 > dt > input'), //라디오 버튼 영역
			$radioLabel = $('.join_agree_box2 > dt > label'), //레이블 버튼 영역
			$startIdx = 0, //라디오 버튼 초기화
			onIndex = -1 //현재활성화 라디오 버튼 초기화

		function init(){
			$checkInput.each(function (idx, entry){
				$(entry).data('input', idx);
			});
			$radioIist.each(function (idx, entry){
				$(entry).data('label', idx);
			});
			event(); //이벤트 실행
		};
		init(); //초기화

		//처음 이벤트 실행될 함수
		function startIdx(idx){
			$radioIist.eq(idx).addClass('select').children("input").attr("checked", "checked");
				$checkInput.css({position:'absolute', left:-9999});
			onIndex = idx;
		};

		//이벤트 실행
		function event(){
			//레이블 클릭시
			$radioLabel.bind({
				'click' : function(event){
					if($(this).parent().hasClass('select') == false){
						$(this).parents().addClass('select').children('.design_button').css({outline:'1px dotted'});
						$(this).parents().children("input").attr("checked", "checked");
					}else{
						$(this).parents().removeClass('select').children('.design_button').css({outline:'none'});
						$(this).parents().children("input").attr("checked", null);
					};
				}
			});
		};
	};
	designCheckbox4();

	//탭
	function photoTabMenu() {
		$photoTabAnchor = $('.photo_list_tab').find('a');
		$photoTabLi = $photoTabAnchor.parent('li');
		$photoTabContent = $('.list_box');

		$photoTabContent.eq(0).show(); //탭 컨텐츠 초기화
		$photoTabLi.eq(0).addClass('tab_on');
		
		$photoTabAnchor.on('click', function(){
			tabIndex = $(this).parent().index();
		
			$photoTabLi.removeClass('tab_on');
			$photoTabLi.eq(tabIndex).addClass('tab_on');
			$photoTabContent.hide();
			$photoTabContent.eq(tabIndex).show();
			return false;
		});
	}
	photoTabMenu();

	// 사진 사연 내용 입력 */
	$(".photo_comment_txt").focusin(function(){
		var val = $(this).val();
		if(val == "20자 이상~100자 이내로 입력해주세요."){
			$(this).val("");
		}
	})
	$(".photo_comment_txt").focusout(function(){
		var val = $(this).val();
		if(val == ""){
			$(this).val("20자 이상~100자 이내로 입력해주세요.");
		}
	})

	/* 20150420 수정 */
	// 사진 크게보기
	$('.photo_info > a').bind({
		'click' : function(event){
			var wrapWidth = 980;
			var layerBoxWidth = $('#layerBigView').width();
			var layerLeftPost = (wrapWidth - layerBoxWidth)/2;

			if($(this).parent().parent().parent().parent().hasClass('pohto_list')){
				$('#layerBigView').find('.layer_content').addClass('photo_list_content');
			}else{
				$('#layerBigView').find('.layer_content').addClass('my_photo_content');
			}
			$('#layerBigView').addClass('on').css({'display' : 'block'});
			$('.layer_bg_wrap').css({'display' : 'block'});
			layerScrollAuto();
			return false;
		}
	});
	$('.btn_big_view_close').bind({
		'click' : function(event){
			$('#layerBigView').find('.layer_content').removeClass('photo_list_content');
			$('#layerBigView').find('.layer_content').removeClass('my_photo_content');
			$('#layerBigView').removeClass('on').css('display', 'none');
			$('.layer_bg_wrap').css({'display' : 'none'});

			$("html").css({"overflow-y" : "auto"});
			$("#wrap").css({"overflow-y" : "auto"});
			return false;
		}
	});
	
	function layerScrollAuto(){
		if($('#photoRegister').hasClass('on')){
			var layerHeight = $("#photoRegister .layer_box").height();
			var layerTopPost = 80 // 20141023 수정
			var layerTopHeight = $("#photoRegister .layer_box > .layer_top").height();
			var windowsHeight = $(window).height();
			$("#photoRegister .layer_box > .layer_content").css({"overflow-y" : "auto", "height" : windowsHeight - layerTopPost - layerTopHeight -90});
			$("html").css({"overflow-y" : "hidden"});
			$("#wrap").css({"overflow-y" : "hidden"});
		}else if($('#layerBigView').hasClass('on')){
			var layerImgHeight = $("#layerBigView .layer_box > .layer_content > .big_photo_img_box > img").height();
			var layerTxtHeight = $("#layerBigView .layer_box > .layer_content > dl").outerHeight();
			var layerTopPost = 22 // 20141023 수정
			var layerTopHeight = $("#layerBigView .layer_box > .layer_top").height();
			var windowsHeight = $(window).height();
			var insertLayerHeight = windowsHeight - layerTopPost - layerTopHeight;
			var reallyLayerHeight = layerImgHeight + layerTxtHeight;

			if(insertLayerHeight <= reallyLayerHeight){
				$("#layerBigView .layer_box > .layer_content").css({"overflow-y" : "auto", "height" : insertLayerHeight});
				$('#layerBigView').css({"top" : 0, "margin-top" : 0});
			}else{
				var pop_height = $('#layerBigView').height();
				var pop_height_device = - (pop_height / 2); 
				$('#layerBigView').css("top","50%");
				$('#layerBigView').css("margin-top",pop_height_device);
				$("#layerBigView .layer_box > .layer_content").css({"height" : reallyLayerHeight});
			};

			$("html").css({"overflow-y" : "hidden"});
			$("#wrap").css({"overflow-y" : "hidden"});
		}
	};
    $(window).resize(function() { layerScrollAuto();});
	

	/* 사진 파일등록 */
	$(".button_file_search").click(function(){
		fineUrl = $("")
		$(".file_real").click();
	});
	/* //20150420 수정 */
	/* //20150417 추가 */
});