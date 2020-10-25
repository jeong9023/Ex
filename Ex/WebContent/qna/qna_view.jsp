<%@page import="qna.vo.qnaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nowPage" value="${param.page}" />
<c:set var="mb_id" value="${sessionScope.memberID }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 사항 확인</title>
<style type="text/css">
.qnadetailForm{ width: 1200px; margin: 0 auto; padding:50px 0 0 0; font-size: 17px;}
.qnadetailForm h2 {font-size: 25px; margin: 0px 0 40px 0;padding:5px 5px 0 5px;}

.qnadetailForm .qnadetail{border-top:1px solid silver; border-bottom: 1px solid silver; padding: 10px 0 0 0;border-radius: 5px}
.qnadetailForm .qnadetail .qacontentarea { width: 1150px; word-break:break-all; white-space:pre-line;  overflow: visible; min-height: 30px; height: auto ; margin:0 0 0 20px; line-height: 30px;}


.qnadetailForm  .qare{border-top: 1px dotted gray; padding:15px 0 15px 0}
.qnadetailForm  .qabtn{float:right; clear: both; padding: 20px 0 0 0; }

.qnadetailForm  .qabtn input[type="button"]{width:70px; height:30px; font-size: 17px; border-radius: 5px;  margin: 0 5px 0 0; 
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset;} 
.qnadetailForm  .qabtn input[type="button"]:hover {color: rgba(255, 255, 255, 0.85); 
 		box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;} 

</style>
</head>
<body>
	<jsp:include page="../main/head.jsp" />
	<!-- 게시판 상세내용 보기 -->
	<div class="qnadetailForm">
		<h2>${article.mb_id}님의 문의사항</h2>
		<div class="qnadetail">
			<div class="qasubjectarea" style="word-break: break-all;">
				<h2>문의사항 ) ${article.wr_subject }<br></h2>
			</div>
			<div class="qacontentarea" style="word-break: break-all;">${article.wr_content}</div>
			<!-- ---------------- 답변 ---------------- -->
			<c:choose>
				<c:when test="${null ne article_re}">
					<div class="qacontentarea">
						<div class="qare">${article_re.wr_content }</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="qabtn">
						<c:if test="${null ne mb_id }">
							<c:if test="${'admin' eq mb_id }">
								<input type="button" value="답변"
									onclick="location.href='qnaReply.qa?wr_id=${article_re.wr_id}&page=${nowPage}'">
							</c:if>
						</c:if>
					</div>
				</c:otherwise>
			</c:choose>
			<div class="qabtn">
				<c:if test="${null ne mb_id}">
					<c:if test="${'admin' eq mb_id}">
						<input type="button" value="답변"
							onclick="location.href='qnaReply.qa?wr_id=${article_re.wr_id}&page=${nowPage}'">
					</c:if>
				</c:if>
				<c:if test="${null ne mb_id}">
					<c:if test="${'admin' ne mb_id}">
						<input type="button" value="수정"
							onclick="location.href='qnaModify.qa?wr_id=${article.wr_id}&page=${nowPage}'">
					</c:if>
				</c:if>
				<a
					href="<c:url value='qnaDelete.qa?wr_id=${article.wr_id}&page=${nowPage}'/>"
					onclick="window.open(this.href,'_blank','width=500,height=150,top=200,left=500, toolbar=no, scrollbars=yes,resizable=yes');return false;">
					<input type="button" value="삭제" />
				</a> <input type="button" value="목록"
					onclick="location.href='qnaList.qa?page=${nowPage}'">
			<div style="clear: both"></div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/tail.jsp" />
</body>
</html>














