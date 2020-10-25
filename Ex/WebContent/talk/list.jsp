<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../main/head.jsp" />
<link rel="stylesheet" href="../css/talk.css">

<c:set var="item_num" value="${param.item_num }" />
<c:set var="strangerID" value="${strangerID }" />
<c:set var="selectRecvRoom" value="${selectRecvRoom }" />
<c:set var="selectSendRoom" value="${selectSendRoom }" />
<c:set var="type" value="${type }" />

<div id="talk_wrap">
	<div class="group">
		<div class="switch_menu">
			<c:url value="../talk/list.ta" var="recvList">
				<c:if test="${not empty item_num }"><c:param name="item_num" value="${item_num }" /></c:if>
				<c:param name="type" value="recv" />
			</c:url>
			<c:url value="../talk/list.ta" var="sendList">
				<c:if test="${not empty item_num }"><c:param name="item_num" value="${item_num }" /></c:if>
				<c:param name="type" value="send" />
			</c:url>
			<a href="${recvList }" <c:if test="${type eq 'recv' }">class="on"</c:if>><span>받은대화</span></a>
			<a href="${sendList }" <c:if test="${type eq 'send' }">class="on"</c:if>><span>보낸대화</span></a>
		</div>
		<c:if test="${type == 'recv'}">
			<c:choose>
				<c:when test="${empty selectRecvRoom }">
					<div class="empty">
						<span>아무것도 없는 쓸쓸한 톡이군요 :(</span>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" items="${selectRecvRoom }">
						<c:url value="../talk/list.ta" var="talkList">
							<c:param name="item_num" value="${i.getItemNumber() }" />
							<c:param name="send_mb_id" value="${i.getSendMemberID() }" />
							<c:param name="type" value="recv" />
						</c:url>
						<a class="room" href="${talkList }">
							<c:choose>
								<c:when test="${i.getProfileImgName() == 'default_profile_img.png'}">
									<span class="profile_img"><img src="../img/default_profile_img.png"></span>
								</c:when>
								<c:otherwise>
									<span class="profile_img"><img src="../upload/${i.getProfileImgName() }"></span>
								</c:otherwise>
							</c:choose>
							<span class="nick"><c:out value="${i.getSendMemberNick() }" /></span>
							<span class="datetime">${i.getSendDatetimeToString() }</span>
							<span class="msg"><c:out value="${i.getContent() }" /></span>
						</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${type == 'send'}">
			<c:choose>
				<c:when test="${empty selectSendRoom }">
					<div class="empty">
						<span>실시간 경매의 상품 뷰 페이지에서 대화하기를 찾아 톡을 보내보세요 :)</span>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" items="${selectSendRoom }">
						<c:url value="../talk/list.ta" var="talkList">
							<c:param name="item_num" value="${i.getItemNumber() }" />
							<c:param name="recv_mb_id" value="${i.getRecvMemberID() }" />
							<c:param name="type" value="send" />
						</c:url>
						<a class="room" href="${talkList }">
							<c:choose>
								<c:when test="${i.getProfileImgName() == 'default_profile_img.png'}">
									<span class="profile_img"><img src="../img/default_profile_img.png"></span>
								</c:when>
								<c:otherwise>
									<span class="profile_img"><img src="../upload/${i.getProfileImgName() }"></span>
								</c:otherwise>
							</c:choose>
							<span class="nick"><c:out value="${i.getSendMemberNick() }" /></span>
							<span class="datetime">${i.getSendDatetimeToString() }</span>
							<span class="msg"><c:out value="${i.getContent() }" /></span>
						</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
	<div class="content">
		<c:choose>
			<c:when test="${empty item_num or empty param.recv_mb_id and empty param.send_mb_id }">
				<div class="empty">
					<img src="../img/talk_empty.png">
					<div>대화 상대를 선택하세요.</div>
				</div>
			</c:when>
			<c:otherwise>
				<jsp:include page="../talk/activation.jsp" />
			</c:otherwise>
		</c:choose>
	</div>
</div>

<jsp:include page="../main/tail.jsp" />