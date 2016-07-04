$(document).ready(function(){

	$('.comInfo').toggle( //20140522 수정
		function(){
			$('.comInfo_txt').css("display","block");
			$(this).css("border-bottom","0");
			$('.comInfo img').attr("src", "img/bl_cominfo2.png"); 
		},
		function(){
			$('.comInfo_txt').css("display","none");				
			$(this).css("border-bottom","1px solid #696969");
			$('.comInfo img').attr("src", "img/bl_cominfo.png"); 
		}
	);

});

$(window).load(function(){

});


$(window).resize(function () {// resize event bind

});


//********* 셀렉트박스 디자인 *********//
jQuery.fn.styledSelect = function(options) {
	var isFF2 = jQuery.browser.mozilla && jQuery.browser.version.indexOf('1.8.')==0;
	var prefs = {
		coverClass : 'select-replace-cover',
		innerClass : 'select-replace',
		adjustPosition : { top:0, left:0 },
		selectOpacity : 0
		}
	if (options) jQuery.extend(prefs,options);
	return this.each( function() {
		if (isFF2) return false;
		var selElm = jQuery(this);
		selElm.wrap('<span><'+'/span>');
		selElm.after('<span><'+'/span>');
		var selReplace = selElm.next();
		var selCover = selElm.parent();
		selElm.css({
			'opacity':prefs.selectOpacity,
			'visibility':'visible',
			'position':'absolute',
			'top':0,
			'left':0,
			'display':'inline',
			'z-index':1
			});
		selCover.addClass(prefs.coverClass).css({
			'display':'inline-block',
			'position':'relative',
			'top':prefs.adjustPosition.top,
			'left':prefs.adjustPosition.left,
			'z-index':0,
			'vertical-align':'middle',
			'text-align':'left'
			});
		selReplace.addClass(prefs.innerClass).css({
			'display':'block',
			'white-space':'nowrap'
			});

		selElm.bind('change',function() {
			jQuery(this).next().text(this.options[this.selectedIndex].text);
			}).bind('resize',function() {
				//jQuery(this).parent().width( jQuery(this).width()+'px' );
				jQuery(this).parent().width( 100+'%' );
			});
		selElm.trigger('change').trigger('resize');
		});
	}