<%@page import="market.vo.MarketBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../main/head.jsp" />
<link href="../css/mypage.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-3.4.1.min.js"></script>
<c:set var="isOpened" value="${isOpened}"/>
<c:set var="profile" value="${profile }"/>
<c:set var="counts" value="${counts }"/>
<c:set var="Marketgrade" value="${Marketgrade }"/>
<c:set var="cookiesCount" value="${cookiesCount }"/>
<div class="mypage_wrap">
	<div class="outer">
		<div class="whose">
			<span class="market_profile">
			<img src="../upload/${profile.tn_source_name}">
			</span>
			<!-- 세션값으로 변경하기 -->
			<span class="id">${profile.mb_id}</span>
			<span class="myInfo"><a href='<c:url value="/mypage/UpdateMemberForm.mk"/>'>회원정보수정</a></span>
			<span class="myInfo"><a href='<c:url value="/point/main.po"/>'>포인트 : 
			<fmt:formatNumber value="${counts.point }" pattern="#,###" />
			</a></span>
			<!-- 상점 오픈 시 : 상점관리  -->
			<c:choose>
			<c:when test="${isOpened eq 1}"><span class="myInfo">상점관리<img class="update_market" src="../img/open-store.png"></span></c:when>
			<c:otherwise><span class="myInfo">상점오픈<img class="open_market" src="../img/open-store.png"></span></c:otherwise>
			</c:choose>
		</div>
		<div class="menuBox">
		<ul class="mymenuBox">
			<li>
			<span class="menu">출품</span>
			<c:choose>
			<c:when test="${isOpened eq 1 }"><span class="count01">${counts.itemcount }</span></c:when>
			<c:otherwise><span class="count_none">-</span></c:otherwise>
			</c:choose>
			</li>
			<li>
			<span class="menu">낙찰</span>
			<span class="count02">${counts.tradecount }</span>
			</li>
			<li>
			<span class="menu">입찰</span>
			<span class="count03">${counts.biddingcount }</span>
			</li>
			<li>
			<span class="menu">최근 본 상품</span>
			<span class="count04">${cookiesCount }</span>
			</li>
		</ul>
		</div>
	</div>
		<c:if test="${isOpened eq 1}">
			<div class="store_info">
					<ul>
					<li><img src="../img/store_icon.png" style="width:20px; height: 18px; margin-bottom: 3px">
					<span class="store_intro">${profile.mp_introduce }</span></li>
					</ul>
			</div>
		</c:if>
	
	<div class="show_page">
	
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){		
		$('.open_market').click(function(){
			window.open('OpenMarketForm.mk?isOpened=0','','width=500,height=250,scrollbars=no,resizable=no');
		});
		
		$('.update_market').click(function(){
			window.open('OpenMarketForm.mk?isOpened=1','','width=500,height=250,resizable=no, scrollbars=no');
		});
		
		$('.show_page').load("MyTradeList.mk");
		
		$('.count01').click(function(){
			$('.show_page').load("MyItemList.mk");
		});
		
		$('.count02').click(function(){
			$('.show_page').load("MyTradeList.mk");
		});
		
		$('.count03').click(function(){
			$('.show_page').load("MyBiddingList.mk");
		});
		
		$('.count04').click(function(){
			$('.show_page').load("MyRecentView.mk");
		});
	});
</script>
<jsp:include page="../main/tail.jsp" />