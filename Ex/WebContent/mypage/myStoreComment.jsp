<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="../css/mypage.css" rel="stylesheet" type="text/css">
		<link href="../css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
		<script src="../js/jquery-3.4.1.min.js"></script>
		<script type="text/javascript"></script>
		</head>
<body>
<c:choose>
<c:when test="${!(pageInfo.listCount eq 0)}">
<div class="comment_container">
	<c:forEach var="CommentList" items="${CommentList }" begin="0" end="${CommentList.size() }">
	<fmt:parseNumber var="lk_grade" integerOnly= "true" value= "${CommentList.lk_grade}" />
	<div class="comment_inner_container">
		<span class="grade">
		<span class="grade_1">
		<c:forEach var="i" begin="1" end="${lk_grade}">★</c:forEach>
		<c:forEach var="i" begin="1" end="${5-lk_grade}">☆</c:forEach></span>
		<span class="grade_2">&nbsp;${CommentList.lk_grade }</span>
		</span>
		<span class="comment_content">${CommentList.lk_comment}</span>
		<span class="member_info">${CommentList.mb_id}&nbsp;&nbsp;|&nbsp;
		<fmt:formatDate value="${CommentList.lk_datetime}" pattern="yyyy.MM.dd HH:mm"/></span>
	</div>
	</c:forEach>
</div>
<c:set var="pageInfo" value="${pageInfo }"/>
		<input type="hidden" value="${pageInfo.page }" class="nowPage">
		<div class="page_wrap">
			<ul class="pagination modal">
			<c:if test="${pageInfo.startPage>1 }">
				<li class="Prev">[이전]</li>
			</c:if>
			<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }">
				<c:choose>
					<c:when test="${i eq pageInfo.page}">
						<li><b>${i}</b></li>
					</c:when>
					<c:otherwise>
						<li class="page" value="${i}">${i}</li>
					</c:otherwise>				
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageInfo.page >= pageInfo.maxPage }">
					<li>[다음]</li>
				</c:when>
				<c:otherwise>
					<li class="Next">[다음]</li>
				</c:otherwise>
			</c:choose>
			</ul>
		</div>
</c:when>
	<c:otherwise>
		<div class="empty">  아직 나의 상점 후기가 없습니다 :( 
		</div> 
	</c:otherwise>
</c:choose>
</body>
<script type="text/javascript">
	$(document).ready(function(){	
		var nowPage = $('.nowPage').val();
		
		$('.page').click(function(){
			var page = $(this).val();
			$('.show_page').load("MyStoreComment.mk?page="+page);
		});
		$('.Prev').click(function(){
			var prevPage = parseInt(nowPage)-1;
			$('.show_page').load("MyStoreComment.mk?page="+prevPage);
		});
		$('.Next').click(function(){
			var nextPage = parseInt(nowPage)+1;
			$('.show_page').load("MyStoreComment.mk?page="+nextPage);
		});
});
</script>
</html>