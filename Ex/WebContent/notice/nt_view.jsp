<%@page import="nt.vo.ntBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="nowPage" value="${param.page}" />
<c:set var="mb_id" value="${sessionScope.memberID }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 확인</title>
<style type="text/css">
.ntdetailForm{ width: 1200px; margin: 0 auto; padding:50px 0 0 0;}
.ntdetailForm h2 {font-size: 25px; }

.ntdetailForm .ntdetail{border-top:1px solid silver; border-bottom: 1px solid silver; font-size: 17px; padding: 50px 0 0 0}
.ntdetailForm .ntdetail .ntcontentarea {width: 1200px; word-break:break-all; white-space:pre-wrap;  overflow: visible; min-height: 100px; height: auto ; margin:40px 0 0 20px; line-height: 30px;}



.ntdetailForm  .ntbtn{float:right; clear: both;font-size: 16px; padding: 20px 0 0 0}

.ntdetailForm  .ntbtn input[type="button"]{width:70px; height:30px; font-size: 17px; border-radius: 5px;  margin: 0 5px 0 0; 
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;} 
.ntdetailForm  .ntbtn input[type="button"]:hover {color: rgba(255, 255, 255, 0.85); 
 		box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;} 

</style>
</head>
<body>
<jsp:include page="../main/head.jsp" />
	<!-- 게시판 상세내용 보기 -->
	<div class="ntdetailForm">
		<div class="ntdetail">
			<div class="ntsubjectarea">
				<h2>공지 )
				${article.wr_subject }</h2><br>
			</div>
			<div class="ntcontentarea">${article.wr_content}
			</div>
			<div style="clear: both;"></div>
		</div>
		<div class="ntbtn">
			<c:if test="${null ne mb_id }">
				<c:if test="${'admin' eq mb_id }">
				<input type="button" value="수정"
					onclick="location.href='ntModify.nt?wr_co_id=${article.wr_co_id}&wr_no=${article.wr_no}&page=${nowPage}'">
				<a href="<c:url value='ntDelete.nt?wr_co_id=${article.wr_co_id}&page=${nowPage}'/>"
						onclick="window.open(this.href,'_blank','width=500,height=150,top=200,left=500, toolbar=no, scrollbars=yes,resizable=yes');return false;">
					<input type="button" value="삭제"/></a>
				</c:if>
			</c:if>
				<input type="button" value="목록"
					onclick="location.href='ntList.nt?page=${nowPage}'">
					
		</div>
	</div>
<jsp:include page="../main/tail.jsp" />
</body>
</html>














