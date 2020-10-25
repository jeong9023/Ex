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
<fmt:requestEncoding value="utf-8"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy.MM.dd" var="today"/>
<c:choose>
<c:when test="${!(pageInfo.listCount eq 0)}">
		<div class="itemList_container">
		<c:forEach var="itemList" items="${item }" begin="0" end="${item.size() }">
			<div class="item">
			<a href="<c:url value='../item/view.it?no=${itemList.no }'/>">
			<img src="../upload/${itemList.thumbnail }" width="230" height="220">
			${itemList.name }</a><br>
			<fmt:formatNumber value="${itemList.nowPrice}" pattern="#,###원" /><br>
			<fmt:formatDate value="${itemList.writeTime}" pattern="yyyy.MM.dd" var="writeDate"/>
			<fmt:formatDate value="${itemList.writeTime}" pattern="HH:mm" var="writeTime"/>
			<c:choose>
				<c:when test="${today eq writeDate }">
					${writeTime }
				</c:when>
				<c:otherwise>
					${writeDate }
				</c:otherwise>
			</c:choose>
			</div>
		</c:forEach>
		</div>
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
		<div class="empty"> 출품 내역이 없습니다. 판매를 시작해보세요 :)<br>
		<span class="gotoLink">
		<a href="<c:url value='../item/write.it'/>">[출품 하러 가기]</a>
		</span>
		</div> 
	</c:otherwise>
</c:choose>
</body>
<script type="text/javascript">
	$(document).ready(function(){	
		var nowPage = $('.nowPage').val();
		
		$('.page').click(function(){
			var page = $(this).val();
			$('.show_page').load("MyItemList.mk?page="+page);
		});
		$('.Prev').click(function(){
			var prevPage = parseInt(nowPage)-1;
			$('.show_page').load("MyItemList.mk?page="+prevPage);
		});
		$('.Next').click(function(){
			var nextPage = parseInt(nowPage)+1;
			$('.show_page').load("MyItemList.mk?page="+nextPage);
		});
});
</script>
</html>