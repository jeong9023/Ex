<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="item_num" value="${param.item_num }" />
<c:set var="strangerID" value="${strangerID }" />
<c:set var="selectNowItemDetail" value="${selectNowItemDetail }" />
<c:set var="selectMarketProfile" value="${selectMarketProfile }" />
<c:set var="selectItemExp" value="${selectItemExp }" />
<c:set var="selectItemExhibiting" value="${selectItemExhibiting }" />
<c:set var="selectRecvContent" value="${selectRecvContent }" />

<span class="substance">
	<span class="notice">땅땅땅은 거래 사고를 책임지지 않으며 사고를 최소화하기 위하여 거래 전 사기조회 혹은 타 플랫폼의 안전거래 에스크로 서비스를 이용하시기 바랍니다.</span>
	<a class="now_product" href="../item/view.it?no=${item_num }">
		<span class="thumbnail"><img src="../upload/${selectNowItemDetail.getItemImgName() }"></span>
		<span class="name"><strong><c:out value="${selectNowItemDetail.getItemName() }" /></strong><br>현재 대화중인 상품입니다.</span>
	</a>
	<c:if test="${not empty selectSendContent }">
		<c:forEach var="i" items="${selectSendContent }">
			<span class="my_datetime"><c:out value="${i.getSendDatetimeToString() }" /></span>
			<span class="my_talk">
				<span class="message"><c:out value="${i.getContent() }" /></span>
			</span>
		</c:forEach>
	</c:if>
	<c:if test="${not empty selectRecvContent }">
		<c:forEach var="i" items="${selectRecvContent }">
			<span class="he_datetime"><c:out value="${i.getSendDatetimeToString() }" /></span>
			<span class="he_talk">
				<span class="message"><c:out value="${i.getContent() }" /></span>
			</span>
		</c:forEach>
	</c:if>
</span>
<span class="profile">
	<c:choose>
		<c:when test="${selectMarketProfile.getProfileImgName() == 'default_profile_img.png'}">
			<span class="profile_img"><img src="../img/default_profile_img.png"></span>
		</c:when>
		<c:otherwise>
			<span class="profile_img"><img src="../upload/${selectMarketProfile.getProfileImgName() }"></span>
		</c:otherwise>
	</c:choose>
	<span class="nick"><c:out value="${selectMarketProfile.getSendMemberNick() }" /></span>
	<span class="introduce"><c:out value="${selectMarketProfile.getMarketIntroduce() }" /></span>
	<span class="info">
		<span class="subject">출품횟수</span>
		<span class="description"><c:out value="${selectItemExp.getEnterCount() }" />회</span>
	</span>
	<span class="info">
		<span class="subject">낙찰횟수</span>
		<span class="description"><c:out value="${selectItemExp.getSuccessfulCount() }" />회</span>
	</span>
	<span class="sale">
		<span class="title">출품중인 상품</span>
		<c:choose>
			<c:when test="${empty selectItemExhibiting }">
				<span>없어요 :(</span>
			</c:when>
			<c:otherwise>
				<c:forEach var="i" items="${selectItemExhibiting }">
					<c:url value="../item/view.it" var="itemExhibiting">
						<c:param name="no" value="${i.getItemNumber() }" />
					</c:url>
					<a href="${itemExhibiting }" class="item_list">
						<span class="thumbnail"><img src="../upload/${i.getItemImgName() }"></span>
						<span class="name"><c:out value="${i.getItemName() }" /></span>
						<span class="price_start">시작가 <c:out value="${i.getItemPriceStart() }" />원</span>
					</a>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</span>
</span>
<div class="message_footer">
	<form action="../talk/messageSendPro.ta" name="msf" method="post" onsubmit="return message_submit();">
		<input type="hidden" name="item_num" value="${item_num }">
		<c:if test="${not empty param.send_mb_id }">
			<input type="hidden" name="stranger_id" value="${param.send_mb_id }">
		</c:if>
		<c:if test="${not empty param.recv_mb_id }">
			<input type="hidden" name="stranger_id" value="${param.recv_mb_id }">
		</c:if>
		<input type="hidden" name="recv_mb_id" value="${param.recv_mb_id }">
		<input type="hidden" name="send_mb_id" value="${param.send_mb_id }">
		<input type="hidden" name="type" value="${param.type }">
		<input type="text" name="message" class="inp_message" placeholder="메시지를 입력하세요.">
		<input type="submit" value="메시지 전송 (Ctrl + Enter)" class="btn_submit">
	</form>
</div>

<script type="text/javascript">
var isCtrl = false;

document.onkeyup = function(e) {
	if(e.which == 17) isCtrl = false;
	return false;
}
document.onkeydown = function(e) {
	if(e.which == 17) isCtrl = true;
	
	// 메시지 전송 (Ctrl + Enter)
	if(e.which == 13 && isCtrl) {
		if(msf.message.value) {
			alert("메시지 전송 완료!\n보낸 톡은 좌측의 보낸 톡 리스트에서 확인 가능합니다.");
			document.msf.submit();
		}
	}
}

function message_submit() {
	if(!msf.message.value) {
		alert("메시지 내용을 입력해주세요.");
		msf.message.focus();
		return false;
	}
	
	alert("메시지 전송 완료!\n보낸 톡은 좌측의 보낸 톡 리스트에서 확인 가능합니다.");
	
	return true;
}
</script>
