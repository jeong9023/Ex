<%@page import="qna.vo.qnaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nowPage" value="${param.page}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 수정</title>
<style type="text/css">
td_right{width:1000px; height:500px; border: red 3px solid;}
td_right>textarea {width:1000px; height:500px; border: blue 3px solid;}
</style>
</head>
<body>
	<!-- 게시판 수정하기 -->
	<section id="modifyForm">
		<h2>문의사항 수정하기asdasd</h2>
		<form action="qnaModifyPro.qa" method="post" name="modifyForm">
			<input type="hidden" name="wr_no" value="${article.wr_no}" />
			<input type="hidden" name="wr_id" value="${article.wr_id}" />
			<input type="hidden" name="page" value="${nowPage}" />
			<table>
				<tr>
					<td class="td_left"><label for="wr_subjct">제목</label></td>
					<td class="td_right" style="border: blue solid 3px; width: 300px;">
						<input type="text" name="wr_subject" required="required" value="${article.wr_subject}" style="width: 300px;"/>
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="wr_content">내용</label></td>
					<td class="td_right">
						<textarea id="wr_content" name="wr_content" cols="40" rows="15" 
									required="required">${article.wr_content}</textarea>
					</td>
				</tr>
			</table>
			<section id="btn">
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="button" value="뒤로" onclick="history.back()">
			</section>
		</form>
	</section>
</body>
</html>















