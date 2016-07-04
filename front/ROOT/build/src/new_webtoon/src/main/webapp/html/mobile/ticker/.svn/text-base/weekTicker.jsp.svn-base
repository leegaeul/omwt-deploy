<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.olleh.webtoon.common.dao.ticker.domain.TickerDomain" %>
<%
	TickerDomain tickerDomain = (TickerDomain)request.getAttribute("TickerDomain");
%>

<script type="text/javascript" >

$(document).ready(function(){
	
	//ajax 방식으로 ticker 조회
   	$.ajax({
   		type : 'POST',
        url: '/m/ticker/tickerJsonList.kt',
        beforeSend: function(xmlHttpRequest) {
          },
        error: function(xhr, textStatus, errorThrown) {
        	//에러 메시지 처리
        	if(xhr.status != 0)
        		cfPrintMsg("ok","요청 중 서버에서 에러가 발생하였습니다.",null);
        },
        success: function(json, textStatus) {
        	
        	if(json.tickerList.length > 0) 
        	{	
        		var html = "";
        		html += '<marquee scrolldelay="100" direction="left">';
        		html += '<a href="'+json.tickerList[0].linkurl;
        		if(json.tickerList[0].newwindowyn == 'Y'){
        			html += '" target="_blank">';
        		}else{
        			html += '">';
        		}
        		html += json.tickerList[0].contents+'</a>';
        		html += '</marquee>';
        		
        		$('.noticeArea').append(html);
        	}
        }
    });
});
</script>

<div class="noticeArea"></div>