<%@page import="qna.vo.qnaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nowPage" value="${param.page}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<style type="text/css">
.ntmodifyForm {	font-size: 17px;	padding: 70px 0 0 0;	width: 1200px;	margin: 0 auto; text-align: center;}
.ntmodifyForm h1 {	font-size: 30px;	text-align: center; margin-bottom: 30px;}


.ntwriteForm .ntForm{width:1200px; }

.ntwriteForm .ntForm .ntdetail{width: 1000px;  margin: 0 auto}
.ntmodifyForm .ntForm .ntdetail input[type="text"], .ntmodifyForm .ntForm .ntdetail textarea{width: 1000px; margin: 10px 0 10px 0; padding:10px 0 10px 20px; border-radius: 5px;}

.ntmodifyForm .ntForm .ntdetail textarea{min-height: 400px; margin-bottom: 30px;}

.ntmodifyForm .ntForm .ntdetail #ntbtn{ width:300px; text-align: center; margin: 0 auto;}
.ntmodifyForm .ntForm .ntdetail #ntbtn input[type="submit"],.ntmodifyForm .ntForm .ntdetail #ntbtn input[type="button"]
{height: 35px; width:100px; font-size: 18px; border-radius: 5px;
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset; margin:0px 20px 10px 20px}
.ntmodifyForm .ntForm .ntdetail #ntbtn input[type="submit"]:hover
{box-shadow: #fc5230 0 0px 0px 40px inset; color:white;}
.ntmodifyForm .ntForm .ntdetail #ntbtn input[type="button"]:hover
{box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset; color:white;}

</style>
</head>
<body>
	<jsp:include page="../main/head.jsp" />
	<!-- 게시판 수정하기 -->
	<div class="ntmodifyForm">
		<h1> 공지사항 수정 </h1>
		<div class="ntForm">
		<form action="ntModifyPro.nt" method="post" name="modifyForm">
			<input type="hidden" name="wr_no" value="${article.wr_no}" />
			<input type="hidden" name="wr_co_id" value="${article.wr_co_id}" />
			<input type="hidden" name="page" value="${nowPage}" />
			<div class="ntdetail">
				<div class="ntsubjectarea">
					<input type="text" name="wr_subject" required="required"
						value="${article.wr_subject}" />
				</div>
				<div class="ntcontentarea">
					<textarea id="wr_content" name="wr_content" required="required">${article.wr_content}</textarea>
				</div>
				<div id="ntbtn">
					<input type="submit" value="완료">&nbsp;&nbsp;
					<input type="button" value="취소" onclick="history.back()">
				</div>
			</div>
		</form>
		</div>
	</div>
	<jsp:include page="../main/tail.jsp" />
</body>
</html>















