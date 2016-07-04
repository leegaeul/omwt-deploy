// Easing 추가
jQuery.easing.jswing=jQuery.easing.swing;
jQuery.extend(jQuery.easing,{def:"easeOutQuad",swing:function(e,a,c,b,d){return jQuery.easing[jQuery.easing.def](e,a,c,b,d)},easeInQuad:function(e,a,c,b,d){return b*(a/=d)*a+c},easeOutQuad:function(e,a,c,b,d){return-b*(a/=d)*(a-2)+c},easeInOutQuad:function(e,a,c,b,d){if((a/=d/2)<1)return b/2*a*a+c;return-b/2*(--a*(a-2)-1)+c},easeInCubic:function(e,a,c,b,d){return b*(a/=d)*a*a+c},easeOutCubic:function(e,a,c,b,d){return b*((a=a/d-1)*a*a+1)+c},easeInOutCubic:function(e,a,c,b,d){if((a/=d/2)<1)return b/
2*a*a*a+c;return b/2*((a-=2)*a*a+2)+c},easeInQuart:function(e,a,c,b,d){return b*(a/=d)*a*a*a+c},easeOutQuart:function(e,a,c,b,d){return-b*((a=a/d-1)*a*a*a-1)+c},easeInOutQuart:function(e,a,c,b,d){if((a/=d/2)<1)return b/2*a*a*a*a+c;return-b/2*((a-=2)*a*a*a-2)+c},easeInQuint:function(e,a,c,b,d){return b*(a/=d)*a*a*a*a+c},easeOutQuint:function(e,a,c,b,d){return b*((a=a/d-1)*a*a*a*a+1)+c},easeInOutQuint:function(e,a,c,b,d){if((a/=d/2)<1)return b/2*a*a*a*a*a+c;return b/2*((a-=2)*a*a*a*a+2)+c},easeInSine:function(e,
a,c,b,d){return-b*Math.cos(a/d*(Math.PI/2))+b+c},easeOutSine:function(e,a,c,b,d){return b*Math.sin(a/d*(Math.PI/2))+c},easeInOutSine:function(e,a,c,b,d){return-b/2*(Math.cos(Math.PI*a/d)-1)+c},easeInExpo:function(e,a,c,b,d){return a==0?c:b*Math.pow(2,10*(a/d-1))+c},easeOutExpo:function(e,a,c,b,d){return a==d?c+b:b*(-Math.pow(2,-10*a/d)+1)+c},easeInOutExpo:function(e,a,c,b,d){if(a==0)return c;if(a==d)return c+b;if((a/=d/2)<1)return b/2*Math.pow(2,10*(a-1))+c;return b/2*(-Math.pow(2,-10*--a)+2)+c},
easeInCirc:function(e,a,c,b,d){return-b*(Math.sqrt(1-(a/=d)*a)-1)+c},easeOutCirc:function(e,a,c,b,d){return b*Math.sqrt(1-(a=a/d-1)*a)+c},easeInOutCirc:function(e,a,c,b,d){if((a/=d/2)<1)return-b/2*(Math.sqrt(1-a*a)-1)+c;return b/2*(Math.sqrt(1-(a-=2)*a)+1)+c},easeInElastic:function(e,a,c,b,d){e=1.70158;var f=0,g=b;if(a==0)return c;if((a/=d)==1)return c+b;f||(f=d*0.3);if(g<Math.abs(b)){g=b;e=f/4}else e=f/(2*Math.PI)*Math.asin(b/g);return-(g*Math.pow(2,10*(a-=1))*Math.sin((a*d-e)*2*Math.PI/f))+c},easeOutElastic:function(e,
a,c,b,d){e=1.70158;var f=0,g=b;if(a==0)return c;if((a/=d)==1)return c+b;f||(f=d*0.3);if(g<Math.abs(b)){g=b;e=f/4}else e=f/(2*Math.PI)*Math.asin(b/g);return g*Math.pow(2,-10*a)*Math.sin((a*d-e)*2*Math.PI/f)+b+c},easeInOutElastic:function(e,a,c,b,d){e=1.70158;var f=0,g=b;if(a==0)return c;if((a/=d/2)==2)return c+b;f||(f=d*0.3*1.5);if(g<Math.abs(b)){g=b;e=f/4}else e=f/(2*Math.PI)*Math.asin(b/g);if(a<1)return-0.5*g*Math.pow(2,10*(a-=1))*Math.sin((a*d-e)*2*Math.PI/f)+c;return g*Math.pow(2,-10*(a-=1))*Math.sin((a*
d-e)*2*Math.PI/f)*0.5+b+c},easeInBack:function(e,a,c,b,d,f){if(f==undefined)f=1.70158;return b*(a/=d)*a*((f+1)*a-f)+c},easeOutBack:function(e,a,c,b,d,f){if(f==undefined)f=1.70158;return b*((a=a/d-1)*a*((f+1)*a+f)+1)+c},easeInOutBack:function(e,a,c,b,d,f){if(f==undefined)f=1.70158;if((a/=d/2)<1)return b/2*a*a*(((f*=1.525)+1)*a-f)+c;return b/2*((a-=2)*a*(((f*=1.525)+1)*a+f)+2)+c},easeInBounce:function(e,a,c,b,d){return b-jQuery.easing.easeOutBounce(e,d-a,0,b,d)+c},easeOutBounce:function(e,a,c,b,d){return(a/=
d)<1/2.75?b*7.5625*a*a+c:a<2/2.75?b*(7.5625*(a-=1.5/2.75)*a+0.75)+c:a<2.5/2.75?b*(7.5625*(a-=2.25/2.75)*a+0.9375)+c:b*(7.5625*(a-=2.625/2.75)*a+0.984375)+c},easeInOutBounce:function(e,a,c,b,d){if(a<d/2)return jQuery.easing.easeInBounce(e,a*2,0,b,d)*0.5+c;return jQuery.easing.easeOutBounce(e,a*2-d,0,b,d)*0.5+b*0.5+c}});

$(document).ready(function(){
	//헬프바 열기/닫기
	$(".help_bar > ul > li > .help_button").bind(
		"click", function() {
			if($(".help_bar").hasClass("active") == false){
				$(".help_bar").addClass("active").animate({"margin-left":248, "width":242},{duration:400, easing:"easeOutQuad"});
				return false;
			}else{
				$(".help_bar").removeClass("active").animate({"margin-left":425, "width":65},{duration:400, easing:"easeOutQuad"});
			}
	});

	//네비 이동하기
	$(function(){
		$(".navigation > ul > li > a").click(function(event){
			event.preventDefault();
			$('html, body').stop().animate({scrollTop:$(this.hash).offset().top - 65}, 500);
			$(".navigation > ul > li > a").removeClass("active");
			$(this).addClass("active");
		});
	});

	//작가 코멘트 열기/닫기
	$(".audit_wrap > ul > li > a").bind(
		"click", function() {
			if($(this).parent().hasClass("active") == false){
				$(".audit_wrap > ul > li").removeClass("active");
				$(this).parent().addClass("active").find(".audit_comment");
				return false;
			}else{
				$(this).parent().removeClass("active").find(".audit_comment");
			}
	});
	
	// 휴대폰번호 value 설정 */
	$(".phone_input").focusin(function(){
		var val = $(this).val();
		if(val == "'-' 제외한 숫자만 입력"){
			$(this).val("");
		}

	})
	$(".phone_input").focusout(function(){
		var val = $(this).val();
		if(val == ""){
			$(this).val("'-' 제외한 숫자만 입력");
		}
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
					inputCheck($(this).data('label'));

				},
				'focusout' : function(event){
					$radioIist.children('.design_radio').css({outline:'none'});
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
			
			function layerShow() {
				$targetLayer.css({visibility : "visible", display:"block"}).addClass("on");
			};
			
			function layerHidden() {
				$targetLayer.css({visibility : "hidden"}).removeClass("on");
			};
			
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
});