<%@page import="nt.vo.ntBean"%>
<%@page import="common.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="mb_id" value="${sessionScope.memberID}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 리스트</title>
<!-- <link href="../css/nt.css" rel="stylesheet" type="text/css"> -->
<link href="../css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-3.4.1.min.js"></script>
<style type="text/css">
.ntlist{font-size:16px;padding: 70px 0 0 0; }
.ntlist .ntcaution{width: 1200px; padding: 50px 50px 50px 50px; border: 1.5px groove #fc5230; margin: 0 auto;}
/* 위치 */
.ntlist .ntcontent{width:1200px; margin: 0 auto; padding:80px 0 0 0;}
.ntlist .nttitle{float:left; width:190px; font-size: 25px; text-align: center; clear: both;}
.ntlist .ntyes{float:right; width:990px;}
.ntyes table{ width: 990px; margin: 0 auto; text-align: center;}


.ntyes #tr_top{text-align: center; height: 40px;}
.ntyes #tr_top td{	border: 1px black solid; }
.ntyes table tr:last-child{ border-bottom: 2px solid black }

/* 글이 없을 때 */
.ntyes #ntnone{text-align: center; padding: 60px 0 80px 0; border-bottom: 1px solid black}

.ntlist .ntyes .ntpaging{text-align: center; margin-top: 30px;}
 .ntlist .ntyes .ntpaging> input[type="button"]{width:60px; height:15px auto; font-size: 16px; border-radius: 5px;  margin: 0 5px 0 5px; 
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;} 
 .ntlist .ntyes .ntpaging> input[type="button"]:hover {color: rgba(255, 255, 255, 0.85); 
 		box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;} 


.ntlist .ntcontent .ntyes .ntbtn input[type="button"]{height: 30px; width:90px; float:right; font-size: 16px; border-radius: 5px;
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset; margin:-30px 0 10px 0}
.ntlist .ntcontent .ntyes .ntbtn input[type="button"]:hover{box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset; color:white;}
</style>
</head>
<body>
<jsp:include page="../main/head.jsp" />
<div class="ntlist">
	<div>
		<div class="ntcaution">사이트의 하단에서 여러분에게 적용되는 약관 및 정책을 확인하실 수 있습니다.</div>
	</div>
	<div class="ntcontent">
	<div class="nttitle"><h1>땅땅땅 서비스 <br>공지사항</h1></div>
	<c:choose>
	
		<c:when test="${(null ne articleList) && (pageInfo.listCount > 0) }">
			<div class="ntyes">
			<div class="ntbtn">
				<c:if test="${'admin' eq mb_id }">
					<input type="button" value="글 작성"
						onclick="location.href='../notice/ntWrite.nt'">
				</c:if>
			</div>
				<table>
					<tr id="tr_top">
						<td>제목</td>
						<td width="150px">날짜</td>
					</tr>
					<c:forEach var="ab" items="${articleList}">
						<tr>
							<td style="text-align: left; height: 40px;">&nbsp;&nbsp;
							<a href="<c:url value='ntDetail.nt?wr_co_id=${ab.wr_co_id}&page=${pageInfo.page}'/>">
									${ab.wr_subject } </a>
							</td>
							<td align="center"><fmt:formatDate value="${ab.wr_datetime }" pattern="yyyy.MM.dd"/></td>
						</tr>
					</c:forEach>
				</table>
			<!-- 		페이징 -->
			<div class="ntpaging">
				<c:if test="${pageInfo.page > 1}">
					<input type="button" value="이전"
						onclick="location.href='ntList.nt?page=${pageInfo.page - 1}'">&nbsp;
				</c:if>
				<c:forEach var="i" begin="${pageInfo.startPage}"
					end="${pageInfo.endPage}" step="1">
					<c:choose>
						<c:when test="">
							[${i}]&nbsp;
						</c:when>
						<c:otherwise>
							<a href="ntList.nt?page=${i}">[${i}]</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${pageInfo.page < pageInfo.maxPage}">
					<input type="button" value="다음"
						onclick="location.href='ntList.nt?page=${pageInfo.page + 1}'">
				</c:if>
			</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="ntyes">
			<div class="ntbtn">
			<c:if test="${'admin' eq mb_id }">
			<input type="button" value="공지하기"
				onclick="location.href='../notice/ntWrite.nt'">
				</c:if>
			</div>
			<table>
				<tr id="tr_top" style="height: 40px;">
					<td style="width:800px;">제목</td>
					<td style="width:190px;">날짜</td>
				</tr>
				<tr><td colspan="2" id="ntnone">현재 글이 존재하지 않습니다.</td></tr>
			</table></div>
		</c:otherwise>
	</c:choose>
	</div>
</div>
<jsp:include page="../main/tail.jsp" />
</body>
</html>