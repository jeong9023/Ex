<%@page import="qna.vo.qnaBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 해결(관리자)</title>
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
	<!-- 게시판 답변하기 -->
	<section id="qnareplyForm">
		<h2>글 답변하기</h2>
		<form action="qnaReplyPro.qa" method="post" name="reModifyForm">
			<!-- 답변 글 작성에 필요한 게시물 정보 중 입력받지 않는 정보도 함께 전달 -->
			<input type="hidden" name="page" value="${param.page}" />
			<input type="hidden" name="wr_no" value="${article.wr_no}" />
			<input type="hidden" name="wr_id" value="${article.wr_id}" />
			<input type="hidden" name="wr_co_id" value="${article.wr_co_id}" />
			<div class="qnadetailForm">
		<h2>${article.mb_id}님의 문의사항</h2>
		<div class="qnadetail">
			<div class="qasubjectarea" style="word-break: break-all;">
				<h2>문의사항 ) ${article.wr_subject }<br></h2>
			</div>
			<div class="qacontentarea" style="word-break: break-all;">${article.wr_content}</div>
			</div>
		</div>
			<table>
				<tr>
					<td class="td_left"><label for="re_subject">제목</label></td>
					<td class="td_right" colspan="3"><input type="text" name="re_subject" required="required" value="${article_re.wr_subject}"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="re_content">내용</label></td>
					<td class="td_right" colspan="3">
						<textarea id="re_content" name="re_content" cols="40" rows="15" required="required">${article_re.wr_content}</textarea>
					</td>
				</tr>
			</table>
			<section id="btn">
				<input type="submit" value="답변글등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">
			</section>
		</form>
	</section>
<jsp:include page="../main/tail.jsp" />
</body>
</html>


