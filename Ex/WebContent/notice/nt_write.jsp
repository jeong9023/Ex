<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<link href="../css/qna.css" rel="stylesheet" type="text/css">
<link href="../css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
</script>
<style type="text/css">
.ntwriteForm {	font-size: 17px;	padding: 70px 0 0 0;	width: 1200px;	margin: 0 auto; text-align: center;}
.ntwriteForm h1 {	font-size: 30px;	text-align: center; margin-bottom: 30px;}

.ntwriteForm .ntForm{width:1200px; }

.ntwriteForm .ntForm .ntdetail{width: 1000px;  margin: 0 auto}
.ntwriteForm .ntForm .ntdetail input[type="text"], .ntwriteForm .ntForm .ntdetail textarea{width: 1000px; margin: 10px 0 10px 0; padding:10px 0 10px 20px; border-radius: 5px;}

.ntwriteForm .ntForm .ntdetail textarea{min-height: 400px; margin-bottom: 30px;}

.ntwriteForm .ntForm .ntdetail #ntbtn{ width:300px; text-align: center; margin: 0 auto;}
.ntwriteForm .ntForm .ntdetail #ntbtn input[type="submit"],.ntwriteForm .ntForm .ntdetail #ntbtn input[type="reset"]
{height: 35px; width:100px; font-size: 18px; border-radius: 5px;
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset; margin:0px 20px 10px 20px}
.ntwriteForm .ntForm .ntdetail #ntbtn input[type="submit"]:hover
	{box-shadow: #fc5230 0 0px 0px 40px inset; color:white;}
.ntwriteForm .ntForm .ntdetail #ntbtn input[type="reset"]:hover
	{box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset; color:white;}
</style>
</head>
<body>
<jsp:include page="../main/head.jsp" />
	<!-- 게시판 등록 -->
	<div class="ntwriteForm">
	<h1> 공지사항 작성	</h1>
	<div class="ntForm">
		<form action="ntWritePro.nt" method="post" name="ntForm">
		<div class="ntdetail">
			<div class="ntsubjectarea">
			<input type="text" name="wr_subject" id="wr_subject" placeholder="공지 제목" required="required"/>
			</div>
			<div class="ntcontentarea">
			<textarea id="wr_content" name="wr_content" placeholder="공지 내용"  required="required" style="resize: none;"></textarea>
			</div>
			<div id="ntbtn">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="초기화">
			</div>
			</div>
		</form>
	</div>
	</div>
<jsp:include page="../main/tail.jsp" />
</body>
</html>