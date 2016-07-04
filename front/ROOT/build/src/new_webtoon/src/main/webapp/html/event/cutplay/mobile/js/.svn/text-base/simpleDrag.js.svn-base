//common ----------------------------------------------------
var isAndroid = (/android/gi).test(navigator.appVersion),
    isIDevice = (/iphone|ipad/gi).test(navigator.appVersion),
    isPlaybook = (/playbook/gi).test(navigator.appVersion),
    isTouchPad = (/hp-tablet/gi).test(navigator.appVersion),
    hasTouch = 'ontouchstart' in window && !isTouchPad,	
    DOWN_EV = hasTouch ? 'touchstart' : 'mousedown',
    MOVE_EV = hasTouch ? 'touchmove' : 'mousemove',
    UP_EV = hasTouch ? 'touchend' : 'mouseup',
    android2 = false;
if(navigator.userAgent.indexOf("Android 2") > 0){android2 = true;}

(function($){
	$.fn.simpleDrag = function(upFnc){
		var prvx = -1,
            DOWNPOS,
            OPOS = 0,
            target = document.getElementById($(this).attr("id")),
            DOWNY,
            DOWNX,
            dragDist = 0,
            dragDir = 2, //드래그방향 : 0 - 왼쪽 , 1 - 오른쪽, 2 - 제자리	
            dragFlag = true, //드래그 기능 작동 여부
            tx = 0,
            downTime;
        if(arguments[1] != null){var moveFnc = arguments[1];
		}else{function moveFnc(){};}		
		$(document.body).attr("ondragstart","return false");
		$(document.body).attr("onselectstart","return false"); 				
		var mUp = function(e){
            if(Math.abs(dragDist) > 20){
				if(dragDist > 0){
					dragDir = 1;
				}else{
					dragDir = 0;
				}
			}else{
				dragDir = 2;
			}

			upFnc();

			document.body.removeEventListener(MOVE_EV, mMove);
			document.body.removeEventListener(UP_EV, mUp);
		}		
		function mDown(e){
            //debug(dragDist + " down:" + dragFlag);
			if(dragFlag){                
				downTime = new Date().getMilliseconds();
                dragDist = 0;
				var point = hasTouch ? e.touches[0] : e;		
				OPOS = tx;
                DOWNPOS = point.clientX - OPOS;
				DOWNY = point.clientY;
				DOWNX = point.clientX;
				prvx = 0;
				document.body.addEventListener(MOVE_EV, mMove);
				document.body.addEventListener(UP_EV, mUp);		
			}
		}
		function mMove(e){            
            if(dragFlag){
                var point = hasTouch ? e.touches[0] : e;
                if(Math.abs(point.clientY - DOWNY) < Math.abs(point.clientX - DOWNX)){
                    e.preventDefault();	
                    dragDist = point.clientX - DOWNX;
                    prvx = OPOS - tx;
                    tx = point.clientX - DOWNPOS;
                    setTx(tx);
                    moveFnc();
                }else{
                    mUp(null);
                }
            }
		}
        function setTx(val){
            if(android2){
                $(target).css("margin-left", val + "px");                
            }else{
                $(target).css("-webkit-transform","translate3d(" + val + "px, 0, 0)");
                $(target).css("transform","translate3d(" + val + "px, 0, 0)");
            }
			$('.week_date').hide(); // 20150922-2 추가
            
            tx = val;
        }
		target.addEventListener(DOWN_EV, mDown);
        if(!android2){
            $(target).css("-webkit-transition-property","transform");
            $(target).css("transition-property","transform");                        
        }           
		return {
			setX:function(value){
                setTx(value);
			},
			setMoveX:function(value, time, endFnc){                
                if(android2){
                    $(target).animate({"margin-left":value + "px"}, time, "linear", endFnc);
                }else{
                    //#20150306 pointer-events 속성 적용
                    if(dragDir < 2){
                    	$(document.body).css("pointer-events", "none");   
                    }                    
                    
                    $(target).css("-webkit-transition-duration", time + "ms");
                    $(target).css("transition-duration", time + "ms");
                    setTx(value);
                    setTimeout(function(){                        
                        //#20150306 pointer-events 속성 적용
                        $(document.body).css("pointer-events", "auto");            

                        $(target).css("-webkit-transition-duration", "0ms");
                        $(target).css("transition-duration", "0ms");
                        endFnc();
                    }, time);
                }                
			},
			setDragFlag:function(flag){
				dragFlag = flag;
			},
			_x:function(){
				return tx;
    		},
			_width:function(){
				return parseInt($(target).width());
			},
			_dir:function(){
				return dragDir;
			},
            _dragDist:function(){ //20150316 드래그한 거리 
                return Math.abs(dragDist);    
            },			
            getIndex:function(totalNum, partWidth){
                var xvalue = parseInt(-parseInt(tx)/partWidth);
                if(dragDir == 0){
                    xvalue += 1;  
                }
                if(xvalue<0){
                    xvalue = 0;
                }else if(xvalue >= totalNum){
                    xvalue = totalNum-1;
                }
                return xvalue;                
            },
            off:function(){
                target.removeEventListener(DOWN_EV, mDown);
            },
            getFlickDist:function(){
                //플리킹처리
                var timeGap = new Date().getMilliseconds() - downTime,
                    tmpx = 0;
                //20150108 80 -> 40 수치조정
                if(timeGap > 0 && timeGap < 200 && Math.abs(dragDist) > 40){
                    tmpx = dragDist*2 + tx;
                }
                return tmpx;
            },
            getFlickDist2:function(min, max, dist, flickArea, timeArea){ 
                //20150520 플리킹 영역 및 감도 설정, 
                //인자 : 최소값(0), 최고값, 플리킹하여움직이는거리비율(default:2), 플리킹판단거리(default:40), 플리킹판단 시간(default:200) 
                var timeGap = new Date().getMilliseconds() - downTime,
                    tmpx = tx;
                if(dist == undefined) dist = 2;
                if(flickArea == undefined) flickArea = 40;
                if(timeArea == undefined) timeArea = 200;                
                if(timeGap > 0 && timeGap < 200 && Math.abs(dragDist) > flickArea)tmpx = dragDist*dist + tx;
                if(tmpx > min || max > 0){
                    tmpx = min;
                }else if(tmpx < max){
                    tmpx = max;
                }                 
                return tmpx;
            }            
		};		
	}
})(jQuery); 

/*resize 이벤트용*/
function rotateWindow(fnc){    
    var winW = $(window).width();
	$(window).resize(function(){        
		if(winW != $(window).width()){
            winW = $(window).width();
            fnc();
        }
	});
}
//EG Slider 확장 기본 슬라이딩 (회전형) 
/*
인자 1 : 움직일 리스트 객체의 아이디값
인자 2 : 애니메이션이 끝난 후 동작할 함수
인자 3 : 배너를 회전시킬지 여부 true, false
//이지슬라이더 배너 샘플 : 배너객체, 애니메이션이 끝난 후 호출할 함수, 배너의 회전여부
//index 는 현재 위치 : rolling이 true이면(배너회전시) 1 부터시작하고  false 이면 0부터 시작함. 
var $bannerSlide = setEGSlide("#deptBann", function(index){  
    //alert(index);
}, true);
*/    
function setEGSlide(banner, endFnc, rolling){
    
    var $banner = $(banner),
        $wrapper = $banner.parent(),    
        $bannerChild = $banner.children(),    
        wd = $wrapper.width(),
        len = $bannerChild.length,    
        index = 0,    
        rollFlag = true, //회전형 배너를 쓸경우
        startIndex = 0,
        lastIndex = len - 1;
    if(rolling != undefined){
        rollFlag = rolling;
    }
    if(rollFlag){/*회전용*/        
        var $lbann = $bannerChild.eq(lastIndex).clone();
        $banner.append($bannerChild.eq(startIndex).clone());
        $bannerChild.eq(startIndex).before($lbann);
        $banner = $(banner);
        $bannerChild = $banner.children();
        len += 2;
        lastIndex += 2;
        //20150211 수정 
        index = startIndex = 1;
    }    
    $banner.width(wd*len + "px");
    $bannerChild.width(wd + "px");
    
    //###20150312 스크립트 개선 
    var $deptdrag = $(banner).simpleDrag(function(){
        var dir = $deptdrag._dir();
        //20150311 클릭시에는 처리하지 않도록 예외처리 추가 
        if(dir < 2){
            //#20150312 인덱스를 구함 (외부에서 이동시킬 경우 대비)
            index = $deptdrag.getIndex(len, wd);                        
            wd = $wrapper.width(); //20150210 추가         
            $deptdrag.setMoveX(-index*wd, 300, function(){
                /*회전용*/
                if(rollFlag){
                    if(index == 0){ 
                        $deptdrag.setX(-(lastIndex-1)*wd);
                        index = lastIndex - 1;
                    }else if(index == lastIndex){
                        $deptdrag.setX(-startIndex*wd);
                        index = startIndex;
                    }
                }
                if(endFnc != undefined){
                    endFnc(index); //움직임이 끝난 후 
                }
            });
        }
    });
    $deptdrag.setX(-startIndex*wd);
    //20150309 수정 
    rotateWindow(function(){
        setTimeout(function(){
			wd = $wrapper.width();     
			$banner.width(wd*len + "px");
			$bannerChild.width(wd + "px");
			$deptdrag.setX(-startIndex*wd);
			index = startIndex;
        },50);        
    });
    return $deptdrag;
}
