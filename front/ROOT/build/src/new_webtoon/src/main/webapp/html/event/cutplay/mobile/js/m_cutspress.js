$(document).ready(function(){
	// ***************************************** 베스트 슬라이딩 ***************************************** 
	/* 20150922-2 수정 */
	if($('#bestCutList').length > 0){
		_$bestSlide = setEGSlide('#bestCutList', function (index) { // index 는 현재 위치 : 1 부터시작 
			setBestSlideArr(index); // 20150922-2 수정
		}, true);
	};

	//베스트 인디케이터 등록
	if($('#bestCutList > article').length > 1){
		var blen = $('#bestCutList >article').length,
			_$homeIndi = $('.navi_box'),
			i = 0,
			appendHtml = '';
		for (i; i < blen; i++) {
			appendHtml += '<li';
			if (i == 0) {
				appendHtml += ' class="on"';
			}
			appendHtml += '></li>';
		}
		_$homeIndi.css({
			width : ((blen * 10) - 4) + 'px',
			opacity : 1
		}).html(appendHtml);
		$(".navi_box > li").eq(0).remove()
		$(".navi_box > li:last-child").remove()
		$(".navi_box > li").removeClass("on").eq(0).addClass("on");
	};

	//베스트 인디케이터 세팅 : 0 ~ n
	function setBestSlideArr(now) {
		var len = $('#bestCutList > article').length;
		$('.navi_box > li').removeClass('on').eq(now-1).addClass('on');
		weekShow();
	}

	function weekShow() {
		$('.week_date').show();
	};

	function weekHide() {
		$('.week_date').hide();
	};
	/* //20150922-2 수정 */

	// ***************************************** 참여자 사진 보기 ***************************************** 
	function invoPhotoViewSlide(){
		//사진보기 탭
		function enterTabMenu() {
			$tab = $('.view_slide_tab');
			$tabImg = $tab.find('em');
			$tabAnchor =$tab.find('a');
			$tabContent = $('.content_box');
			$tabImg1  = '<img src="./images/main/m_cutspress_view_tab01.png" alt="" />';
			$tabImg2  = '<img src="./images/main/m_cutspress_view_tab02.png" alt="" />';

			$tabContent.eq(0).addClass('content_on'); //탭 컨텐츠 초기화
			
			$tabAnchor.on('click', function(){
				tabIndex = $(this).parent().index();
				if(tabIndex == 0){
					$tabImg.find('img').remove();
					$tabImg.append($tabImg1);
					$tabContent.removeClass('content_on');
					$tabContent.eq(0).addClass('content_on');
				}else if(tabIndex == 1){
					$tabImg.find('img').remove();
					$tabImg.append($tabImg2);
					$tabContent.removeClass('content_on');
					$tabContent.eq(1).addClass('content_on');
				}
				return false;
			});
		}
		enterTabMenu();
		
		if($('#viewInvoList').length > 0){
			//사진보기 슬라이딩
			_$viewInvoSlide = setEGSlide('#viewInvoList', function (index) { // index 는 현재 위치 : 1 부터시작 
				setViewInfo(index);
			}, false); 

			//사진보기 인디케이터 등록
			var viewListCount = $('#viewInvoList >article').length,
				_$viewNaviWrap = $('#InvoPhoto .navi_count');

			if(viewListCount == 1){
				$('#InvoPhoto .navi_wrap').find('.prev').hide();
				$('#InvoPhoto .navi_wrap').find('.next').hide();
			};
			_$viewNaviWrap.find('.total').html(viewListCount);

			//사진보기 인디케이터 세팅 : 0 ~ n
			function setViewInfo(now) {
				_$viewNaviWrap.find('.now').html(now +1);
			}
		};
	};
	invoPhotoViewSlide();

	// ***************************************** 내 사진 보기 *****************************************
	function myPhotoViewSlide(){
		//사진보기 슬라이딩
		_$viewMySlide = setEGSlide('#viewMyList', function (index) { // index 는 현재 위치 : 1 부터시작 
			setMyViewInfo(index);
		}, false); 

		//사진보기 인디케이터 등록
		var viewMyListCount = $('#viewMyList >article').length,
			_$viewMyNaviWrap = $('#myPhoto .navi_count');

		if(viewMyListCount == 1){
			$('#myPhoto .navi_wrap').find('.prev').hide();
			$('#myPhoto .navi_wrap').find('.next').hide();
		};
		_$viewMyNaviWrap.find('.total').html(viewMyListCount);

		//사진보기 인디케이터 세팅 : 0 ~ n
		function setMyViewInfo(now) {
			_$viewMyNaviWrap.find('.now').html(now +1);
		}
	};
	if($('#viewMyList').length > 0){
		myPhotoViewSlide();
	};

	// *****************************************  이미지 조정 ***************************************** 
	/* 20150910 수정 */
	//이미지 조정
	function imageResize(){
		setTimeout(function(){
			/* 20150922-2 수정 */
			//베스트 슬라이딩 이미지 셋팅
			function bestImage(){
				$('.image_box').each(function(){
					$imageBox = $(this);
					$boxWidth =$imageBox.width();
					$boxCalHeight =$boxWidth*0.875; // 20150922 수정
					$image = $imageBox.find('.image');

					$imageBox.css({'height' : $boxCalHeight});
					$image.css({'width' : $boxWidth});

					function bestTopPos(){
					$imageHeight = $image.height();
					$imagePos = (($imageHeight - $boxCalHeight)/2);
						if($imageHeight >= $boxCalHeight){ // 이미지가 이미지 박스보다 클 경우에만 중간 셋팅
							$image.css({'top' : -$imagePos});
						}
					};
					bestTopPos();
				});
			};
			bestImage();
			/* //20150922-2 수정 */

			//사진보기 슬라이딩 이미지 셋팅
			function viewImage(){
				$('.image_view_box').each(function(){
					$viewImageBox = $(this);
					$boxWidth =$viewImageBox.width();
					$boxCalHeight =$boxWidth*0.81;
					$image = $viewImageBox.find('.image');

					$viewImageBox.css({'height' : $boxCalHeight});
					$image.css({'width' : $boxWidth});

					function viewTopPos(){
					$imageHeight = $image.height();
					$imagePos = (($imageHeight - $boxCalHeight)/2);
						if($imageHeight >= $boxCalHeight){ // 이미지가 이미지 박스보다 클 경우에만 중간 셋팅
							$image.css({'top' : -$imagePos});
						}
					};
					viewTopPos();
				});
			};
			viewImage();

		}, 100);
	};
	imageResize();
	$(window).on( {'resize': imageResize});
	/* //20150910 수정 */
	
	// *****************************************  레이어 ***************************************** 
	function layerImgView(){
		//터치 이벤트 막기
		hasTouch = 'ontouchstart' in window,
		MOVE_EV = hasTouch ? 'touchmove' : 'mousemove';
		var handler = function(e) {
			e.preventDefault();
		}

		var wrapScroll = 0;

		/* 20150910 추가 */
		// 포토리스트 사진크게보기 레이어 열기
		$('.btn_image_view').on('click', function(){
			
			/* 20150922 수정 */
			indexMax = $('#detailViewList > article').length - 1;
			Index = 0;
			/* //20150922 수정 */
			if($(this).children('span').children('img').hasClass('delete_image')){ //삭제된 이미지일 경우
				alert('삭제된 게시물입니다.')
			}else{
				wrapScroll = $(document).scrollTop();
				$('.m_cutspress_main_wrap').addClass('layer_on');
				$('.layer_image_view_wrap').addClass('layer_on');
				$(document).scrollTop(0);

				if($('.layer_image_view').length > 1){
					_$detailSlide = setEGSlide('#detailViewList', function (index) { // index 는 현재 위치 : 1 부터시작
					setSideArrow(index); //20150922 수정
					}, false); 
				}
				setSideArrow(0); //20150922 수정

				/* 20150922 수정 */
				if($('#detailViewList > article').length == 1){
					$('.prev').hide();
					$('.next').hide();
				}
				/* //20150922 수정 */
			};
			
			return false;
		});
		/* //20150910 추가 */

		// 포토리스트 사진크게보기 레이어 닫기
		$('.btn_layer_close').on('click', function(){
			$('.m_cutspress_main_wrap').removeClass('layer_on');
			$('.layer_image_view_wrap').removeClass('layer_on');
			$(document).scrollTop(wrapScroll);
			weekShow(); // 20150922-2 수정
			return false;
		});

		// 공유하기 레이어 열기
		$('.btn_share').on('click', function(){
			var winHeight = $(window).height();
			var pop_height = $('.layer_share_wrap').outerHeight();
			var pop_height_device = - (pop_height / 2);
			$('.layer_share_wrap').css({'top' : '50%', 'margin-top' : pop_height_device});

			$('.layer_share_wrap').addClass('layer_share_on');
			$('.bg_layer').addClass('layer_share_on');
			return false;
		});

		// 공유하기 레이어 닫기
		$('.btn_share_close').on('click', function(){
			$('.layer_share_wrap').removeClass('layer_share_on');
			$('.bg_layer').removeClass('layer_share_on');
			weekShow(); // 20150922-2 수정
			return false;
		});

		// 동의하기 열기
		$('.challenge').on('click', function(){
			wrapScroll = $(document).scrollTop();
			$('.layer_agree_wrap').addClass('layer_agree_on');
			$('.bg_layer').addClass('layer_share_on');
			pop_vertical();
			$( 'body' ).bind(MOVE_EV, handler);
			return false;
		});

		// 약관 동의 취소
		$('.btn_cancel').on('click', function(){
			$('.btn_agree_close').trigger('click');
			return false;
		});

		// 약관 동의 닫기
		$('.btn_agree_close').on('click', function(){
			$('.layer_agree_wrap').removeClass('layer_agree_on');
			$('.bg_layer').removeClass('layer_share_on');
			$( 'body' ).unbind(MOVE_EV, handler);
			weekShow(); // 20150922-2 수정
			return false;
		});

		// 약관 동의 iscroll
		if($('.iscroll1').length > 0){
			optionScroll = new IScroll('.iscroll1', { mouseWheel: true, click: true, scrollbars : true });
		};
		if($('.iscroll2').length > 0){
			optionScroll = new IScroll('.iscroll2', { mouseWheel: true, click: true, scrollbars : true });
		};

		/* 20150922 수정 */
		// STEP01 열기
		$('.btn_next_step01').on('click', function(){

			$('.layer_agree_wrap').removeClass('layer_agree_on');
			$('.bg_layer').removeClass('layer_share_on');
			$( 'body' ).unbind(MOVE_EV, handler);
			$('.m_cutspress_main_wrap').addClass('layer_on');
			$('.layer_step01_wrap').addClass('layer_on');
			$('body').css('background' , 'rgba(0, 0, 0, 0.8)');
			
			$('.list_box a').addClass('init_on'); // 20150910 추가
			$(document).scrollTop(0);
			return false;
		});
		/* //20150922 수정 */

		// STEP01 취소
		$('.btn_step01_cancel').on('click', function(){
			$('.btn_step01_close').trigger('click');
			return false;
		});

		// STEP01 닫기
		$('.btn_step01_close').on('click', function(){
			$('.m_cutspress_main_wrap').removeClass('layer_on');
			$('.layer_step01_wrap').removeClass('layer_on');
			$('body').css('background' , 'none');
			/* 20150911-2 추가 */
			$('.step02_mid').removeClass('freecut_on');
			$('.step02_mid').removeClass('basic_on');
			$('.list_box a').removeClass('select_on');
			/* //20150911-2 추가 */
			$(document).scrollTop(wrapScroll);
			weekShow(); // 20150922-2 수정
			return false;
		});

		// STEP01 선택 웹툰 활성화
		$('.list_box a').on('click', function(){
			$('.list_box a').removeClass('init_on'); // 20150910 추가
			$('.list_box a').removeClass('select_on');
			$(this).addClass('select_on');
			return false;
		});
		
		// STEP02 열기
		$('.btn_next_step02').on('click', function(){
			if($('.select_on').length > 0){
				if($('.select_on').hasClass('free_cut')){
					$('.step02_mid').addClass('freecut_on');
				}else{
					$('.step02_mid').addClass('basic_on');
					setTimeout(function(){
						oriWebtoonImage();
					},10);
				};
				$('.layer_step01_wrap').removeClass('layer_on');
				$('.bg_layer').removeClass('layer_share_on');
				$('.layer_step02_wrap').addClass('layer_on');
				$(document).scrollTop(0);
			}else{
				alert('웹툰 장면을 선택하세요');
			};
			return false;
		});

		// STEP02 닫기
		$('.btn_step02_close').on('click', function(){
			$('.m_cutspress_main_wrap').removeClass('layer_on');
			$('.layer_step02_wrap').removeClass('layer_on');
			$('body').css('background' , 'none');
			/* 20150911-2 추가 */
			$('.step02_mid').removeClass('freecut_on');
			$('.step02_mid').removeClass('basic_on');
			$('.list_box a').removeClass('select_on');
			/* //20150911-2 추가 */
			$(document).scrollTop(wrapScroll);
			weekShow(); // 20150922-2 수정
			return false;
		});

		// STEP02 이전
		$('.btn_prev').on('click', function(){
			var articleLen = $('#selectList').children('.list_box').length;

			$('.step02_mid').removeClass('basic_on');
			$('.step02_mid').removeClass('freecut_on');
			$('.layer_step02_wrap').removeClass('layer_on');
			$('.layer_step01_wrap').addClass('layer_on');
			if(articleLen > 1){
				_$selectPhotoSlide = setEGSlide('#selectList', function (index) { // index 는 현재 위치 : 1 부터시작 
				}, false); 
			};
			$(document).scrollTop(0);
			return false;
		});

		/* 20150922 수정 */
		// 상세 미리보기 레이어 열기
		$('.btn_preview').on('click', function(){
			introWrapScroll = $(document).scrollTop();

			$('body').addClass('layer_on');

			if($('.m_cutspress_intro_wrap').length > 0){
				$('.m_cutspress_intro_wrap').addClass('layer_on');
			}else if($('.m_cutspress_main_wrap').length > 0){
				$('.m_cutspress_main_wrap').addClass('layer_on');
			};

			$('.layer_detail_view').addClass('layer_on');
			$(document).scrollTop(0);
			return false;
		});
		// 상세 미리보기 레이어 닫기
		$('.btn_detail_close').on('click', function(){
			$('body').removeClass('layer_on');

			if($('.m_cutspress_intro_wrap').length > 0){
				$('.m_cutspress_intro_wrap').removeClass('layer_on');
			}else if($('.m_cutspress_main_wrap').length > 0){
				$('.m_cutspress_main_wrap').removeClass('layer_on');
			};
			$('.layer_detail_view').removeClass('layer_on');
			$(document).scrollTop(introWrapScroll);
			weekShow(); // 20150922-2 수정
			return false;
		});
		/* 20150922 수정 */

		/* 20150922 추가 */
		// 댓글달기 미리보기 레이어 열기
		$('.btn_reply').on('click', function(){
			previewWrapScroll = $(document).scrollTop();

			$('body').addClass('layer_on');
			$('.m_cutspress_main_wrap').addClass('layer_on');
			$('.layer_image_view_wrap').removeClass('layer_on');
			$('.layer_reply_wrap').addClass('layer_on');
			var iscrollLength = 1;
			$('.iscroll_box').each(function(){
				$iscrollMaxHeight = $(window).height() - 90;
				$(this).css({'max-height' : $iscrollMaxHeight});
				$(this).addClass('detail_iscroll'+ iscrollLength);
				new IScroll('.detail_iscroll'+ iscrollLength + '', { mouseWheel: true, click: true, scrollbars : true });
				iscrollLength = iscrollLength + 1;
			});
			return false;
		});

		// 댓글달기 미리보기 레이어 닫기
		$('.btn_reply_close').on('click', function(){
			$('body').removeClass('layer_on');
			$('.layer_image_view_wrap').addClass('layer_on');
			$('.layer_reply_wrap').removeClass('layer_on');
			$(document).scrollTop(previewWrapScroll);
			weekShow(); // 20150922-2 수정
			return false;
		});
		/* //20150922 추가 */

		//팝업 센터 정렬
		function pop_vertical(){
			var winHeight = $(window).height();
			var pop_height = $('.layer_agree_wrap').outerHeight();
			var pop_height_device = - (pop_height / 2);
			$('.layer_agree_wrap').css({'top' : '50%', 'margin-top' : pop_height_device});
		};

		//팝업사이즈 체크
		function popupResizing(){
			var winHeight = $(window).height();
			var popHeight = $('.layer_agree_wrap').height();

			if(popHeight > winHeight){
				$('.layer_agree_wrap').addClass('small_layer');
			}else{
				$('.layer_agree_wrap').removeClass('small_layer');
			}
		};
		popupResizing();

		//리사이즈
		function layerResizeHandler(){
			popupResizing();
			pop_vertical();
		};
		$(window).on( {'resize': layerResizeHandler} );

		//textarea 사이즈 자동조절
		autosize(document.querySelectorAll('#comment'));
	};
	layerImgView();
	$('.week_date').show(); //20150922-2 추가
});

/* 20150910 추가 */
//상세페이지 이전, 다음 화살표
function detailViewSet(dir) {
	var bwd = $('#detailViewList > .layer_image_view').width(),
		len = $('#detailViewList > .layer_image_view').length,
		tx = _$viewInvoSlide._x() + dir * bwd,
		bnow = Math.abs(tx / bwd);
	if(tx >= 0){
		tx = 0, bnow = 0;
	}else if(bnow >= len -1){
		tx = -bwd*(len-1);
		bnow = len-1;
	}    
	_$detailSlide.setMoveX(tx, 300, function () {
	});
};
/* //20150910 추가 */

//참여자 사진보기 이전, 다음 화살표
function viewInvoSet(dir) {
	var bwd = $('#viewInvoList > .view_box').width(),
		len = $('#viewInvoList > article').length,
		tx = _$viewInvoSlide._x() + dir * bwd,
		bnow = Math.abs(tx / bwd);
	
	if(tx >= 0){
		tx = 0, bnow = 0;
	}else if(bnow >= len -1){
		tx = -bwd*(len-1);
		bnow = len-1;
	}    
	_$viewInvoSlide.setMoveX(tx, 300, function () {
		viewInvoSetArr(bnow);
	});
};

// 메인 슬라이딩 카운트
function viewInvoSetArr(now) {
	var len = $('#viewInvoList > article').length,
		$homeBann = $('#viewInvoList > article');
	$('#InvoPhoto .navi_count').find('.now').html(now +1);
};

//내 사진보기 이전, 다음 화살표
function viewMySet(dir) {
	var bwd = $('#viewMyList > .view_box').width(),
		len = $('#viewMyList > article').length,
		tx = _$viewInvoSlide._x() + dir * bwd,
		bnow = Math.abs(tx / bwd);
	
	if(tx >= 0){
		tx = 0, bnow = 0;
	}else if(bnow >= len -1){
		tx = -bwd*(len-1);
		bnow = len-1;
	}    
	_$viewMySlide.setMoveX(tx, 300, function () {
		viewMySetArr(bnow);
	});
};

// 메인 슬라이딩 카운트
function viewMySetArr(now) {
	var len = $('#viewMyList > article').length,
		$homeBann = $('#viewMyList > article');
	$('#myPhoto .navi_count').find('.now').html(now +1);
};
	
// 파일 업로드(일반컷)
function readURLBasic(input) {
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			$('#fileUploadMycut').attr('src', e.target.result).load(function(){oriWebtoonMyImage();});
		}
		reader.readAsDataURL(input.files[0]);
	}
}

// 파일 업로드(웹툰)
function readURLWebtoon(input) {
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			$('#fileUploadWebtoon').attr('src', e.target.result).load(function(){webtoonImage();}); // 20150910 수정
		}
		reader.readAsDataURL(input.files[0]);
	}
}
	
// 파일 업로드(마이컷)
function readURLMy(input) {
	if(input.files && input.files[0]){
		var reader = new FileReader();
		reader.onload = function (e) {
			$('#fileUploadMytoon').attr('src', e.target.result).load(function(){myImage();});
		}
		reader.readAsDataURL(input.files[0]);
	}
}

/* 20150910 수정 */
//올레마켓툰 이미지 셋팅
function oriWebtoonImage(){
	$('.ori_webtoon_preview').each(function(){
		$viewImageBox = $(this);
		$boxWidth =$viewImageBox.width();
		$boxCalHeight =$boxWidth*1.22;
		$image = $viewImageBox.find('.image3');

		$viewImageBox.css({'height' : $boxCalHeight});
		$image.css({'width' : $boxWidth});
		
		function viewTopPos(){
			$imageHeight = $image.height();
			$imagePos = (($imageHeight - $boxCalHeight)/2);
			if($imageHeight >= $boxCalHeight){ // 이미지가 이미지 박스보다 클 경우에만 중간 셋팅
				$image.css({'top' : -$imagePos, 'position' : 'absolute'});
			};
		};
		viewTopPos();
	});
};

//올레마켓툰 마이이미지 셋팅
function oriWebtoonMyImage(){
	$('.ori_webtoon_preview').each(function(){
		$viewImageBox = $(this);
		$boxWidth =$viewImageBox.width();
		$boxCalHeight =$boxWidth*1.22;
		$image = $viewImageBox.find('.image4');

		$viewImageBox.css({'height' : $boxCalHeight});
		$image.css({'width' : $boxWidth});
		
		function viewTopPos(){
			$imageHeight = $image.height();
			$imagePos = (($imageHeight - $boxCalHeight)/2);
			if($imageHeight >= $boxCalHeight){ // 이미지가 이미지 박스보다 클 경우에만 중간 셋팅
				$image.css({'top' : -$imagePos, 'position' : 'absolute'});
			}
		};
		viewTopPos();
	});
};

//웹툰 등록하기 이미지 셋팅
function webtoonImage(){
	$('.webtoon_preview').each(function(){
		$viewImageBox =$(this);
		$boxWidth =$viewImageBox.width();
		$boxCalHeight =$boxWidth*1.23;
		$image = $viewImageBox.find('.image1');

		$viewImageBox.css({'height' : $boxCalHeight});
		$image.css({'width' : $boxWidth});
		
		function viewTopPos(){
			$imageHeight = $image.height();
			$imagePos = (($imageHeight - $boxCalHeight)/2);
			if($imageHeight >= $boxCalHeight){ // 이미지가 이미지 박스보다 클 경우에만 중간 셋팅
				$image.css({'top' : -$imagePos, 'position' : 'absolute'});
			}
		};
		viewTopPos();
	});
};

//마이툰 등록하기 이미지 셋팅
function myImage(){
	$('.webtoon_preview').each(function(){
		$viewImageBox = $(this);
		$boxWidth =$viewImageBox.width();
		$boxCalHeight =$boxWidth*1.23;
		$image = $viewImageBox.find('.image2');

		$viewImageBox.css({'height' : $boxCalHeight});
		$image.css({'width' : $boxWidth});
		
		function viewTopPos(){
			$imageHeight = $image.height();
			$imagePos = (($imageHeight - $boxCalHeight)/2);
			if($imageHeight >= $boxCalHeight){ // 이미지가 이미지 박스보다 클 경우에만 중간 셋팅
				$image.css({'top' : -$imagePos, 'position' : 'absolute'});
			}
		};
		viewTopPos();
	});
};
/* //20150910 수정 */

/* 20150922 추가 */
var indexMax;
function setSideArrow(Index){
    $('.prev').show();
    $('.next').show();
    if(Index == 0){
        $('.prev').hide();
    }else if(Index >= indexMax){
        $('.next').hide();
    }
}
/* //20150922 추가 */