<%@page import="qna.vo.qnaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="nowPage" value="${param.page}" />
<fmt:parseNumber value="${param.wr_co_id}"   var="wr_co_id"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 삭제</title>
<style>
	.ntdeleteForm { width: 300px;	margin: 0 auto;	text-align: center;}
	
	.ntdeleteForm h2 {	text-align: center;	}
	
	.ntdeleteForm form  #ntbtn {	width: 300px;	margin: 0 auto;}

	.ntdeleteForm form  #ntbtn input[type="submit"], .deleteForm form  #ntbtn input[type="button"]{height: 30px; width:90px;font-size: 16px; border-radius: 5px;
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset; margin:5px 10px 5px 10px}
	.ntdeleteForm form  #ntbtn input[type="submit"]:hover{box-shadow: #fc5230 0 0px 0px 40px inset; color:white;}
	.ntdeleteForm form  #ntbtn input[type="button"]:hover{box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset; color:white;}
	
	
</style>
</head>
<body>
	<!-- 게시판 글 삭제 -->
	<div class="ntdeleteForm">
	<h2>공지사항 삭제</h2>
		<form action="ntDeletePro.nt" name="deleteForm" method="post">
			<input type="hidden" name="wr_co_id" value="${wr_co_id}" />
			<input type="hidden" name="page" value="${nowPage}" />
			<table id="ntbtn">
				<tr>
					<td colspan="2" >
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>





