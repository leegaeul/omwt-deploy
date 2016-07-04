// Easing 추가
jQuery.easing.jswing=jQuery.easing.swing;
jQuery.extend(jQuery.easing,{def:"easeOutQuad",swing:function(e,a,c,b,d){return jQuery.easing[jQuery.easing.def](e,a,c,b,d)},easeInQuad:function(e,a,c,b,d){return b*(a/=d)*a+c},easeOutQuad:function(e,a,c,b,d){return-b*(a/=d)*(a-2)+c},easeInOutQuad:function(e,a,c,b,d){if((a/=d/2)<1)return b/2*a*a+c;return-b/2*(--a*(a-2)-1)+c},easeInCubic:function(e,a,c,b,d){return b*(a/=d)*a*a+c},easeOutCubic:function(e,a,c,b,d){return b*((a=a/d-1)*a*a+1)+c},easeInOutCubic:function(e,a,c,b,d){if((a/=d/2)<1)return b/
2*a*a*a+c;return b/2*((a-=2)*a*a+2)+c},easeInQuart:function(e,a,c,b,d){return b*(a/=d)*a*a*a+c},easeOutQuart:function(e,a,c,b,d){return-b*((a=a/d-1)*a*a*a-1)+c},easeInOutQuart:function(e,a,c,b,d){if((a/=d/2)<1)return b/2*a*a*a*a+c;return-b/2*((a-=2)*a*a*a-2)+c},easeInQuint:function(e,a,c,b,d){return b*(a/=d)*a*a*a*a+c},easeOutQuint:function(e,a,c,b,d){return b*((a=a/d-1)*a*a*a*a+1)+c},easeInOutQuint:function(e,a,c,b,d){if((a/=d/2)<1)return b/2*a*a*a*a*a+c;return b/2*((a-=2)*a*a*a*a+2)+c},easeInSine:function(e,
a,c,b,d){return-b*Math.cos(a/d*(Math.PI/2))+b+c},easeOutSine:function(e,a,c,b,d){return b*Math.sin(a/d*(Math.PI/2))+c},easeInOutSine:function(e,a,c,b,d){return-b/2*(Math.cos(Math.PI*a/d)-1)+c},easeInExpo:function(e,a,c,b,d){return a==0?c:b*Math.pow(2,10*(a/d-1))+c},easeOutExpo:function(e,a,c,b,d){return a==d?c+b:b*(-Math.pow(2,-10*a/d)+1)+c},easeInOutExpo:function(e,a,c,b,d){if(a==0)return c;if(a==d)return c+b;if((a/=d/2)<1)return b/2*Math.pow(2,10*(a-1))+c;return b/2*(-Math.pow(2,-10*--a)+2)+c},
easeInCirc:function(e,a,c,b,d){return-b*(Math.sqrt(1-(a/=d)*a)-1)+c},easeOutCirc:function(e,a,c,b,d){return b*Math.sqrt(1-(a=a/d-1)*a)+c},easeInOutCirc:function(e,a,c,b,d){if((a/=d/2)<1)return-b/2*(Math.sqrt(1-a*a)-1)+c;return b/2*(Math.sqrt(1-(a-=2)*a)+1)+c},easeInElastic:function(e,a,c,b,d){e=1.70158;var f=0,g=b;if(a==0)return c;if((a/=d)==1)return c+b;f||(f=d*0.3);if(g<Math.abs(b)){g=b;e=f/4}else e=f/(2*Math.PI)*Math.asin(b/g);return-(g*Math.pow(2,10*(a-=1))*Math.sin((a*d-e)*2*Math.PI/f))+c},easeOutElastic:function(e,
a,c,b,d){e=1.70158;var f=0,g=b;if(a==0)return c;if((a/=d)==1)return c+b;f||(f=d*0.3);if(g<Math.abs(b)){g=b;e=f/4}else e=f/(2*Math.PI)*Math.asin(b/g);return g*Math.pow(2,-10*a)*Math.sin((a*d-e)*2*Math.PI/f)+b+c},easeInOutElastic:function(e,a,c,b,d){e=1.70158;var f=0,g=b;if(a==0)return c;if((a/=d/2)==2)return c+b;f||(f=d*0.3*1.5);if(g<Math.abs(b)){g=b;e=f/4}else e=f/(2*Math.PI)*Math.asin(b/g);if(a<1)return-0.5*g*Math.pow(2,10*(a-=1))*Math.sin((a*d-e)*2*Math.PI/f)+c;return g*Math.pow(2,-10*(a-=1))*Math.sin((a*
d-e)*2*Math.PI/f)*0.5+b+c},easeInBack:function(e,a,c,b,d,f){if(f==undefined)f=1.70158;return b*(a/=d)*a*((f+1)*a-f)+c},easeOutBack:function(e,a,c,b,d,f){if(f==undefined)f=1.70158;return b*((a=a/d-1)*a*((f+1)*a+f)+1)+c},easeInOutBack:function(e,a,c,b,d,f){if(f==undefined)f=1.70158;if((a/=d/2)<1)return b/2*a*a*(((f*=1.525)+1)*a-f)+c;return b/2*((a-=2)*a*(((f*=1.525)+1)*a+f)+2)+c},easeInBounce:function(e,a,c,b,d){return b-jQuery.easing.easeOutBounce(e,d-a,0,b,d)+c},easeOutBounce:function(e,a,c,b,d){return(a/=
d)<1/2.75?b*7.5625*a*a+c:a<2/2.75?b*(7.5625*(a-=1.5/2.75)*a+0.75)+c:a<2.5/2.75?b*(7.5625*(a-=2.25/2.75)*a+0.9375)+c:b*(7.5625*(a-=2.625/2.75)*a+0.984375)+c},easeInOutBounce:function(e,a,c,b,d){if(a<d/2)return jQuery.easing.easeInBounce(e,a*2,0,b,d)*0.5+c;return jQuery.easing.easeOutBounce(e,a*2-d,0,b,d)*0.5+b*0.5+c}});

/*
 * jQuery Selectbox plugin 0.1.2
 *
 * Copyright 2011, Dimitar Ivanov (http://www.bulgaria-web-developers.com/projects/javascript/selectbox/)
 * Licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) license.
 *
 * Date: Fri Mar 18 22:28:49 2011 +0200
 */
(function($, undefined) {
	var PROP_NAME = "selectbox",
		FALSE = false,
		TRUE = true;

	function Selectbox() {
		this._state = [];
		this._defaults = {
			classHolder: "sbHolder",
			classHolderDisabled: "sbHolderDisabled",
			classSelector: "sbSelector",
			classOptions: "sbOptions",
			classToggleOpen: "sbToggleOpen",
			classToggle: "sbToggle",
			effect: "fade",
			onChange: null,
			onOpen: null,
			onClose: null
		}
	}
	$.extend(Selectbox.prototype, {
		_isOpenSelectbox: function(target) {
			if (!target) {
				return FALSE
			}
			var inst = this._getInst(target);
			return inst.isOpen
		},
		_isDisabledSelectbox: function(target) {
			if (!target) {
				return FALSE
			}
			var inst = this._getInst(target);
			return inst.isDisabled
		},
		_attachSelectbox: function(target, settings) {
			if (this._getInst(target)) {
				return FALSE
			}
			var $target = $(target),
				self = this,
				inst = self._newInst($target),
				sbHolder, sbSelector, sbToggle, sbOptions, s = FALSE,
				opts = $target.find("option"),
				olen = opts.length;
			$target.attr("sb", inst.uid);
			$.extend(inst.settings, self._defaults, settings);
			self._state[inst.uid] = FALSE;
			$target.hide();

			function closeOthers() {
				var key, uid = this.attr("id").split("_")[1];
				for (key in self._state) {
					if (key !== uid) {
						if (self._state.hasOwnProperty(key)) {
							if ($(":input[sb='" + key + "']")[0]) {
								self._closeSelectbox($(":input[sb='" + key + "']")[0])
							}
						}
					}
				}
			}
			sbHolder = $("<div>", {
				id: "sbHolder_" + inst.uid,
				"class": inst.settings.classHolder
			});
			sbSelector = $("<a>", {
				id: "sbSelector_" + inst.uid,
				href: "#",
				"class": inst.settings.classSelector,
				click: function(e) {
					e.preventDefault();
					closeOthers.apply($(this), []);
					var uid = $(this).attr("id").split("_")[1];
					if (self._state[uid]) {
						self._closeSelectbox(target)
					} else {
						self._openSelectbox(target)
					}
				}
			});
			sbToggle = $("<a>", {
				id: "sbToggle_" + inst.uid,
				href: "#",
				"class": inst.settings.classToggle,
				click: function(e) {
					e.preventDefault();
					closeOthers.apply($(this), []);
					var uid = $(this).attr("id").split("_")[1];
					if (self._state[uid]) {
						self._closeSelectbox(target)
					} else {
						self._openSelectbox(target)
					}
				}
			});
			sbToggle.appendTo(sbHolder);
			sbOptions = $("<ul>", {
				id: "sbOptions_" + inst.uid,
				"class": inst.settings.classOptions,
				css: {
					display: "none"
				}
			});
			opts.each(function(i) {
				var that = $(this),
					li = $("<li>");
				if (that.is(":selected")) {
					sbSelector.text(that.text());
					s = TRUE
				}
				if (i === olen - 1) {
					li.addClass("last")
				}
				$("<a>", {
					href: "#" + that.val(),
					rel: that.val(),
					text: that.text(),
					click: function(e) {
						e.preventDefault();
						var t = sbToggle,
							uid = t.attr("id").split("_")[1];
						self._changeSelectbox(target, $(this).attr("rel"), $(this).text());
						self._closeSelectbox(target)
					}
				}).appendTo(li);
				li.appendTo(sbOptions)
			});
			if (!s) {
				sbSelector.text(opts.first().text())
			}
			$.data(target, PROP_NAME, inst);
			sbSelector.appendTo(sbHolder);
			sbOptions.appendTo(sbHolder);
			sbHolder.insertAfter($target)
		},
		_detachSelectbox: function(target) {
			var inst = this._getInst(target);
			if (!inst) {
				return FALSE
			}
			$("#sbHolder_" + inst.uid).remove();
			$.data(target, PROP_NAME, null);
			$(target).show()
		},
		_changeSelectbox: function(target, value, text) {
			var inst = this._getInst(target),
				onChange = this._get(inst, "onChange");
			$("#sbSelector_" + inst.uid).text(text);
			$(target).children("option[value='" + value + "']").attr("selected", TRUE);
			if (onChange) {
				onChange.apply((inst.input ? inst.input[0] : null), [value, inst])
			} else {
				if (inst.input) {
					inst.input.trigger("change")
				}
			}
		},
		_enableSelectbox: function(target) {
			var inst = this._getInst(target);
			if (!inst || !inst.isDisabled) {
				return FALSE
			}
			$("#sbHolder_" + inst.uid).removeClass(inst.settings.classHolderDisabled);
			inst.isDisabled = FALSE;
			$.data(target, PROP_NAME, inst)
		},
		_disableSelectbox: function(target) {
			var inst = this._getInst(target);
			if (!inst || inst.isDisabled) {
				return FALSE
			}
			$("#sbHolder_" + inst.uid).addClass(inst.settings.classHolderDisabled);
			inst.isDisabled = TRUE;
			$.data(target, PROP_NAME, inst)
		},
		_optionSelectbox: function(target, name, value) {
			var inst = this._getInst(target);
			if (!inst) {
				return FALSE
			}
			inst[name] = value;
			$.data(target, PROP_NAME, inst)
		},
		_openSelectbox: function(target) {
			var inst = this._getInst(target);
			if (!inst || inst.isOpen || inst.isDisabled) {
				return
			}
			var el = $("#sbOptions_" + inst.uid),
				viewportHeight = parseInt($(window).height(), 10),
				offset = $("#sbHolder_" + inst.uid).offset(),
				scrollTop = $(window).scrollTop(),
				height = el.prev().height(),
				diff = viewportHeight - (offset.top - scrollTop) - height / 2,
				onOpen = this._get(inst, "onOpen");
			el.css({
				top: height + "px",
				maxHeight: (diff - height) + "px"
			});
			inst.settings.effect === "fade" ? el.fadeIn() : el.slideDown();
			$("#sbToggle_" + inst.uid).addClass(inst.settings.classToggleOpen);
			this._state[inst.uid] = TRUE;
			inst.isOpen = TRUE;
			if (onOpen) {
				onOpen.apply((inst.input ? inst.input[0] : null), [inst])
			}
			$.data(target, PROP_NAME, inst)
		},
		_closeSelectbox: function(target) {
			var inst = this._getInst(target);
			if (!inst || !inst.isOpen) {
				return
			}
			var onClose = this._get(inst, "onClose");
			inst.settings.effect === "fade" ? $("#sbOptions_" + inst.uid).fadeOut() : $("#sbOptions_" + inst.uid).slideUp();
			$("#sbToggle_" + inst.uid).removeClass(inst.settings.classToggleOpen);
			this._state[inst.uid] = FALSE;
			inst.isOpen = FALSE;
			if (onClose) {
				onClose.apply((inst.input ? inst.input[0] : null), [inst])
			}
			$.data(target, PROP_NAME, inst)
		},
		_newInst: function(target) {
			var id = target[0].id.replace(/([^A-Za-z0-9_-])/g, "\\\\$1");
			return {
				id: id,
				input: target,
				uid: Math.floor(Math.random() * 99999999),
				isOpen: FALSE,
				isDisabled: FALSE,
				settings: {}
			}
		},
		_getInst: function(target) {
			try {
				return $.data(target, PROP_NAME)
			} catch (err) {
				throw "Missing instance data for this selectbox"
			}
		},
		_get: function(inst, name) {
			return inst.settings[name] !== undefined ? inst.settings[name] : this._defaults[name]
		}
	});
	$.fn.selectbox = function(options) {
		var otherArgs = Array.prototype.slice.call(arguments, 1);
		if (typeof options == "string" && options == "isDisabled") {
			return $.selectbox["_" + options + "Selectbox"].apply($.selectbox, [this[0]].concat(otherArgs))
		}
		if (options == "option" && arguments.length == 2 && typeof arguments[1] == "string") {
			return $.selectbox["_" + options + "Selectbox"].apply($.selectbox, [this[0]].concat(otherArgs))
		}
		return this.each(function() {
			typeof options == "string" ? $.selectbox["_" + options + "Selectbox"].apply($.selectbox, [this].concat(otherArgs)) : $.selectbox._attachSelectbox(this, options)
		})
	};
	$.selectbox = new Selectbox();
	$.selectbox.version = "0.1.2"
})(jQuery);

$(document).ready(function(){

	// 공유할 내용 입력 */
	$(".comment_txt").focusin(function(){
		$(this).html("");
	})

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

	// 전문보기 슬라이딩-1
	$("a.button_term1_all").bind({
		"click" : function() {
			var txtMaxHeight = $(".term_txt1 > .txt_area").height();
			var txtMinHeight = 50;

			if($(this).parent().siblings(".term_box").length > 0){
				if($(this).parent().siblings(".term_box").hasClass("on")){
					$(this).parent().siblings(".term_box").removeClass("on");
				   $(this).parent().siblings(".term_box").stop().animate({height:txtMinHeight});
					return false;
				}else{
					$(this).parent().siblings(".term_box").addClass("on");
					$(this).parent().siblings(".term_box").stop().animate({height:txtMaxHeight});
					return false;
				};
			}else{
				return false;
			};
		}
	});

	// 전문보기 슬라이딩-2
	$("a.button_term2_all").bind({
		"click" : function() {
			var txtMaxHeight = $(".term_txt2 > .txt_area").height();
			var txtMinHeight = 50;

			if($(this).parent().siblings(".term_box").length > 0){
				if($(this).parent().siblings(".term_box").hasClass("on")){
					$(this).parent().siblings(".term_box").removeClass("on");
				   $(this).parent().siblings(".term_box").stop().animate({height:txtMinHeight});
					return false;
				}else{
					$(this).parent().siblings(".term_box").addClass("on");
					$(this).parent().siblings(".term_box").stop().animate({height:txtMaxHeight});
					return false;
				};
			}else{
				return false;
			};
		}
	});
	
	/* 20150419 수정 */
	//탭
	function enterTabMenu() {
		$tab = $('.tab_box');
		$tabImg  = $tab.find('img');
		$tabSrc = $tabImg.attr('src');
		$tabAnchor =$tab.find('a');
		$tabContent = $('.content_box');
		$tabImg1  = '<img src="./images/m_event_naemboso_tab_01.png" alt="" />';
		$tabImg2  = '<img src="./images/m_event_naemboso_tab_02.png" alt="" />';
		$tabImg3  = '<img src="./images/m_event_naemboso_tab_03.png" alt="" />';

		$tabContent.eq(2).show(); //탭 컨텐츠 초기화
		
		$tabAnchor.on('click', function(){
			tabIndex = $(this).parent().index();
			if(tabIndex == 0){
				$tab.find('img').remove();
				$tab.append($tabImg1);
				$tabContent.hide();
				$tabContent.eq(0).show();
			}else if(tabIndex == 1){
				$tab.find('img').remove();
				$tab.append($tabImg2);
				$tabContent.hide();
				$tabContent.eq(1).show();
			}else if(tabIndex == 2){
				$tab.find('img').remove();
				$tab.append($tabImg3);
				$tabContent.hide();
				$tabContent.eq(2).show();
			}
			return false;
		});
	}
	enterTabMenu();
	/* //20150419 수정 */

	/* 20150419 추가 */
	/* 20150420 수정 */
	// 이벤트 댓글 영역 휴대폰번호 value 설정
	$(".phone_input").focusin(function(){
		var val = $(this).val();
		if(val == "휴대폰번호 ‘-’를 제외한 숫자만 입력"){
			$(this).val("");
		}

	})
	$(".phone_input").focusout(function(){
		var val = $(this).val();
		if(val == ""){
			$(this).val("휴대폰번호 ‘-’를 제외한 숫자만 입력");
		}
	})
	/* //20150420 수정 */
		
	// 전문보기 레이어 팝업
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
			
				//터치 이벤트 막기
				hasTouch = 'ontouchstart' in window,
				MOVE_EV = hasTouch ? 'touchmove' : 'mousemove';
				var handler = function(e) {
					e.preventDefault();
				}

			if ($self.attr("href") == "undefined")
				return true;
			
			$targetLayer = $($self.attr("href"));
			
			if ($targetLayer.length  != 1)
				return true;
			
			$btnHide = $targetLayer.find(opts.btnHideClass);
			
			function layerShow() {
				$targetLayer.css({visibility : "visible", display:"block"}).addClass("on");
				$( 'body' ).bind(MOVE_EV, handler);
			};
			
			function layerHidden() {
				$targetLayer.css({visibility : "hidden"}).removeClass("on");
				$( 'body' ).unbind(MOVE_EV, handler);
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

	function pop_vertical(){//팝업 센터 정렬
		var pop_height = $('.layer_popup .popup').height();
		var pop_height_device = - (pop_height / 2); 
		$('.layer_popup .popup').css("top","50%");
		$('.layer_popup .popup').css("margin-top",pop_height_device);		
	}
	pop_vertical();
	// 디자인 체크박스3
	function designCheckbox3(){
		var $radioWrapper = $('.term_box3'), //라디오 버튼 전체 영역
			$radioIist = $('.term_box3 > dt'), //라디오 버튼 리스트
			$checkInputFake = $('.term_box3 > dt > .design_button'), //라디오 버튼 영역
			$checkInput = $('.term_box3 > dt > input'), //라디오 버튼 영역
			$radioLabel = $('.term_box3 > dt > label'), //레이블 버튼 영역
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
		var $radioWrapper = $('.term_box4'), //라디오 버튼 전체 영역
			$radioIist = $('.term_box4 > dt'), //라디오 버튼 리스트
			$checkInputFake = $('.term_box4 > dt > .design_button'), //라디오 버튼 영역
			$checkInput = $('.term_box2 4 dt > input'), //라디오 버튼 영역
			$radioLabel = $('.term_box4 > dt > label'), //레이블 버튼 영역
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
	$('.photo_comment_txt').focusin(function(){
		$(this).html("");
	})
	
	// 사진 취소
	$('.regesiter_cancel').on('click', function(){
		$('.layer_photo_regesiter').removeClass('layer_on');
		if(agent.indexOf('iphone') > -1 || agent.indexOf('android') > -1) {		
		    $('body').unbind('touchmove');
	    }
	});
	
	// 사진 등록
	$('.photo_regesiter').on('click', function(){
		var photoComment = $('.photo_comment_txt').val();
		if(photoComment == ''){
			//alert('사연 미등록시 등록 불가');
		};
	});
	
	// 터치 이벤트 막기
	var handler = function(e) {
		e.preventDefault();
	}
	
	/* 20150420 수정 */
	// 포토리스트 사진크게보기 레이어
	$('.list_box ul li a').on('click', function(){
		var commentTxtHeight = $('.comment_txt > p').height();

		if(commentTxtHeight > 30){
			$('.btn_more_wrap').addClass('btn_more_on');
		}
		// 포토리스트, 마이포토 분기처리
		if($(this).parent().parent().parent().parent().hasClass('pohto_list')){
			$('.layer_thumbnail').find('.thumbnail_box_bottom').addClass('photo_list_content');
		}else{
			$('.layer_thumbnail').find('.thumbnail_box_bottom').addClass('my_photo_content');
		}
		$('.layer_thumbnail').addClass('layer_on');
		$( 'body' ).bind(MOVE_EV, handler);
		photoBigViewCal();
	});

	function photoBigViewCal() {
		MOVE_EV = hasTouch ? 'touchmove' : 'mousemove';
		windowHeight = $(window).height();
		$('.layer_thumbnail').css({'height' : '100%'});
		$('.thumbnail_box_image').css({'height' : windowHeight + 'px'});
		$('.thumbnail_box_image').css({'line-height' : windowHeight + 'px', 'height' : windowHeight + 'px'});
	};
	
	// 포토리스트 사진크게보기 레이어 닫기
	$('.photo_view_close').on('click', function(){
		if($('.thumbnail_box_image').hasClass('more_on')){
			$('.thumbnail_box_image').removeClass('more_on');
			$('.btn_more_wrap').addClass('btn_more_on');
			$('.comment_txt').animate({height:33});
		}else{
			MOVE_EV = hasTouch ? 'touchmove' : 'mousemove';
			$('.layer_thumbnail').removeClass('layer_on');
			$('.thumbnail_box_bottom').removeClass('photo_list_content')
			$('.thumbnail_box_bottom').removeClass('my_photo_content')
			$( 'body' ).unbind(MOVE_EV, handler);
		}
	});
	/* //20150420 수정 */

	// 포토리스트 사진크게보기 더 보기
	function photoTxtMore(){
		var commentTxtHeight = $('.comment_txt > p').height();
		if($('.thumbnail_box_image').hasClass('more_on')){
			$('.comment_txt').animate({height:commentTxtHeight});
		}
	};

	$('.comment_txt_more').on('click', function(){
		$('.thumbnail_box_image').addClass('more_on');
		$('.btn_more_wrap').removeClass('btn_more_on');
		photoTxtMore();
	});

	// 사진 리스트 높이 계산
	function photoListCal() {
		var photoWidth = $(window).width()/2-17.5;
	
		$('.list_box > ul > li >img').css('height', photoWidth);
	};
	photoListCal();
	
	//리사이징
	function winResizeHandler(){
		photoTxtMore();
		photoBigViewCal();
		photoListCal();
		
	};
	$(window).on( {'resize': winResizeHandler} );
	/* //20150419 추가 */
});

/* 파일 업로드 */
function readURL(input) {
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			$('#fileUploadView').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
	$('.layer_photo_regesiter').addClass('layer_on');
}