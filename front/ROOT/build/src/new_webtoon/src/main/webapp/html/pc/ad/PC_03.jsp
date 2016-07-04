<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<% 
    Random random = new Random();
    int pc03Idx = random.nextInt(2);

    if(pc03Idx == 1) {
%>
	    <a href="/support/noticeDetail.kt?noticeseq=67"><img src="/images/pc/ad/PC_03_1.png" alt="" /></a>
<% } else { %>
		<a href="http://webtoon.olleh.com/applay/eventDetail.kt?eventseq=6"><img src="/images/pc/ad/PC_03_2_1.png" alt="" /></a>
<% } %>