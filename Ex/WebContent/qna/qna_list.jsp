<%@page import="qna.vo.qnaBean"%>
<%@page import="common.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="mb_id" value="${sessionScope.memberID}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 리스트</title>
<!-- <link href="../css/qna.css" rel="stylesheet" type="text/css"> -->
<link href="../css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-3.4.1.min.js"></script>
<style type="text/css">
.qnalist .qnatop {	width: 88%;	padding: 10px 5px 10px 5px; font-size: 16px; margin: 0 auto;}
.qnalist .qnatop h1 {	font-size: 30px;	text-align: center; margin-top: 50px;}
.qnalist .qnatop .qnacaution {margin: 30px auto;	padding: 20px 10px 10px 30px;	border: 1.5px groove #fc5230;}

.qnalist .qnalistyes {	width: 1180px; font-size: 16px;	margin:0 auto;	padding: 10px 20px 10px 30px;}

.qnalist #qnatbtitle{text-align: center;}
.qnalist #qnatbtitle td{	border: 1px black solid;}
.qnalist .qnanone{text-align: center; padding: 30px 0 50px 0; border-bottom: 1px solid black}

.qnalist .qnapaging{text-align: center; margin-top: 30px;}
.qnalist .qnapaging input[type="button"]{width:50px; height:10px auto; font-size: 15px; border-radius: 5px;  margin: 0 10px 0 10px;
		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;}
.qnalist .qnapaging input[type="button"]:hover {color: rgba(255, 255, 255, 0.85);
		box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;}

.qnabtn input[type="button"]{height: 30px; width:90px; float:right;	margin: 0 80px 0 0; font-size: 16px; border-radius: 5px;
		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;}

.qnabtn input[type="button"]:hover{box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset; color:white}

</style>
</head>
<body>
<jsp:include page="../main/head.jsp" />
<div class="qnalist">
	<div class="qnatop">
		<h1>INQUIRY 문의 사항</h1>
		<div class="qnacaution">고객센터 운영시간은 오전 9:00~오후 05:50까지이며 상황에 따라 달라질 수 있으니 양해부탁드립니다.
		<ol>
			<li>질문에 대한 답변은 주말, 공휴일을 제외하고 다음 날까지 완료되며, 상황에 따라 조금 늦어질 수 있습니다.</li>
			<li>욕설, 타인에 대한 명예 훼손, 불건전한 내용, 기타 게시판의 성격에 맞지 않는 내용의 경우 예고 없이 삭제될 수 있습니다.</li>
			<li>자주 묻는 질문은 <strong><a href="../main/index">메인 페이지</a></strong>의 하단에 위치한 FAQ을(를) 이용하시면 더 쉽고 빠르게 답변을 확인하실 수 있습니다.</li>
			<li>시스템에 대한 내용의 경우 때에 따라 입력된 고객님의 정보를 통해 연락을 취할 수 있으니 확인 부탁드립니다.</li>
		</ol></div>
		<div style="clear: both;"></div>
	</div>
	<div class="qnabtn">
		<input type="button" value="문의하기"
			onclick="window.open('../qna/qnaWrite.qa','_blank','width=810,height=550,top=200,left=500, toolbar=no, scrollbars=yes,resizable=yes');return false;">
	<div style="clear: both;"></div>
	</div>
	<c:choose>
		<c:when test="${(null ne articleList) && (pageInfo.listCount > 0) }">
			<div class="qnalistyes">
					<table>
						<tr id="qnatbtitle" style="height: 40px;">
							<td width="800px">제목</td>
							<td width="150px">작성자</td>
							<td width="200px">날짜</td>
						</tr>
						<c:forEach var="ab" items="${articleList}">
							<tr>
								<td style="text-align: left; height: 35px;">&nbsp;&nbsp;
								<a href="<c:url value='qnaDetail.qa?wr_id=${ab.wr_id}&page=${pageInfo.page}'/>">
										${fn:substring(ab.wr_subject, 0, 40)}
										<c:if test="${fn:length(ab.wr_subject) > 41}">...</c:if>
									</a></td>
								<td align="center">${fn:substring(ab.mb_id, 0, 8)}
									<c:if test="${fn:length(ab.mb_id) > 9}">...</c:if></td>
								<td align="center"><fmt:formatDate value="${ab.wr_datetime }" pattern="yyyy.MM.dd"/></td>
							</tr>
						</c:forEach>
					</table>
				<div style="clear: both;"></div>
				<!-- 		페이징 -->
				<div class="qnapaging">
					<c:if test="${pageInfo.page <= 1}">
						<input type="button" value="이전">
					</c:if>
					<c:if test="${pageInfo.page > 1}">
						<input type="button" value="이전"
							onclick="location.href='qnaList.qa?page=${pageInfo.page - 1}'">&nbsp;
					</c:if>
					<c:forEach var="i" begin="${pageInfo.startPage}"
						end="${pageInfo.endPage}" step="1">
						<c:choose>
							<c:when test="">
					[${i}]&nbsp;
				</c:when>
				<c:otherwise>
					<a href="qnaList.qa?page=${i}">[${i}]</a>&nbsp;
				</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pageInfo.page >= pageInfo.maxPage}">
						<input type="button" value="다음">
					</c:if>
					<c:if test="${pageInfo.page < pageInfo.maxPage}">
						<input type="button" value="다음"
							onclick="location.href='qnaList.qa?page=${pageInfo.page + 1}'">
					</c:if>
				</div>
			</div>
			<div style="clear: both;"></div>
		</c:when>
		<c:otherwise>
			<div class="qnalistyes">
				<table>
					<tr id="qnatbtitle" style="height: 40px;">
						<td width="800px">제목</td>
						<td width="150px">작성자</td>
						<td width="200px">날짜</td>
					</tr>
					<tr>
					<td colspan="3" class="qnanone">접수된 내용이 없습니다. <br>
						문의하실 내용이 있으시다면 오른쪽 하단의 문의하기 버튼을 눌러주세요.</td>
					</tr>
				</table>
			</div>
			<div style="clear: both;"></div>
		</c:otherwise>
	</c:choose>
	</div>
	<jsp:include page="../main/tail.jsp" />
</body>
</html>