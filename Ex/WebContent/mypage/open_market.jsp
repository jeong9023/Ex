<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>땅땅땅 - 신개념 쇼핑의 시작</title>
		<link rel="icon" type="image/png" sizes="32x32" href="../img/favicon.png">
		<link href="../css/default.css" rel="stylesheet" type="text/css">
		<link href="../css/mypage.css" rel="stylesheet" type="text/css">
		<link href="../css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
		<script src="../js/jquery-3.4.1.min.js"></script>
	</head>
	<script type="text/javascript">
		$(function(){
			
			$('#btn_cancel').click(function(){
				window.open('','_self').close();
			});
			
			$('#btn_close').click(function(){
				window.opener.location.reload(); 
				window.open('','_self').close();
			});
			
		});

	</script>
<body>
			<div class="header_wrap_">
				<div class="logo"><img src="../img/logo.png"></div>
			</div>
<c:set var="pageInfo" value="${pageInfo }"/>
<c:set var="memberID" value="${memberID }"/>
<c:set var="isOpened" value="${isOpened }"/>
<c:set var="isSuccess" value="${isSuccess }"/>

<c:choose>
	<c:when test="${isOpened eq 0 }">
		<div class="open_market">
				<div class="market_title">상점 오픈  </div><br>
				<form action="OpenMarketPro.mk" method="post" enctype="multipart/form-data" name="OpenMarketForm" id="OpenMarketForm" >
					<input type="hidden" class="isupdate" name="isupdate" value="insert">
					<input type="hidden" class="mb_id" name="mb_id" value="${memberID}">
					<textarea rows="1" cols="70" class="mp_Introduce" name="mp_Introduce" placeholder="간단한 상점 소개를 입력하세요(75자 이내)" required="required" maxlength="76"></textarea>
					<div class="mkProfile">
						<span>상점 프로필 이미지 등록 </span>
						<input type="file" id="tn_name" name="tn_name" required="required">
						<input type="submit" value="등록">
						<input type="button" value="취소" name="btn_cancel" id="btn_cancel" >
					</div>
				</form>
			</div>
	</c:when>
	
	<c:when test="${isOpened eq 1 }">
		<div class="open_market">
				<div class="market_title">상점 프로필 수정</div><br>
				<form action="UpdateMarketPro.mk" method="post" enctype="multipart/form-data" name="OpenMarketForm">
					<input type="hidden" class="isupdate" name="isupdate" value="update">
					<input type="hidden" class="mb_id" name="mb_id" value="${memberID}">
					<textarea rows="1" cols="70" class="mp_Introduce" name="mp_Introduce" placeholder="간단한 상점 소개를 입력하세요(75자 이내)" required="required" maxlength="76"></textarea>
					<div class="mkProfile">
						<span>상점 프로필 이미지 등록</span><br>
						<input type="file" id="tn_name" name="tn_name" required="required">
						<input type="submit" value="등록" id="btn_ok">
						<input type="button" value="취소" name="btn_cancel" id="btn_cancel">
					</div>
				</form>
			</div>
	</c:when>

	<c:when test="${isSuccess eq 1 && empty isOpened}">
		<div class="open_market">
		<div class="open_market_inner">상점 오픈을 축하합니다.<br> <input type="button" value="확인" name="btn_close" id="btn_close"></div>
		</div>
	</c:when>
	<c:when test="${isSuccess eq 2 && empty isOpened}">
		<div class="open_market">
		<div class="open_market_inner">
		상점 프로필 수정이 완료되었습니다. 
		<br><input type="button" value="확인" name="btn_close" id="btn_close"></div>
		</div>
	</c:when>
	<c:when test="${isSuccess eq 3 && empty isOpened}">
		<div class="open_market">
		<div class="open_market_inner">처음부터 다시 시도해주세요.<input type="button" value="확인" name="btn_close" id="btn_close"></div>
		</div>
	</c:when>
</c:choose>

</body>
</html>