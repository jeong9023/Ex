<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>
<link href="../css/qna.css" rel="stylesheet" type="text/css">
<link href="../css/font/NotoSansKR.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
</script>
<style type="text/css">
.qnaWriteForm{	width: 800px;margin: 0 auto;}
.qnaWriteForm h1{text-align: center; font-size: 30px;}
.qnaWriteForm form table{ margin:auto; width:700px; border-radius: 5px;}
.qnaWriteForm form table input[type=text]{width:700px; min-height: 25px; border:2px solid black; border-radius: 5px; padding: 5px 10px 5px 10px; margin: 10px 5px 3px 5px;font-size: 15px;}
.qnaWriteForm form table textarea {width:700px; height:250px; margin: 5px 6px 5px 6px; border:2px solid black; border-radius: 5px; padding: 10px 10px 5px 10px; font-size: 15px;}

.qnaWriteForm form .btn{margin-top:10px; width: 500x;text-align: center; margin-top: 30px;}
.qnaWriteForm form .btn>input[type=submit],.qnaWriteForm form .btn>input[type=reset]
{height: 35px; width:100px; font-size: 18px; border-radius: 5px;
 		color: rgba(30, 22, 54, 0.6); box-shadow: rgba(30, 22, 54, 0.4) 0 0px 0px 2px inset; margin:0px 20px 10px 20px}
.qnaWriteForm form .btn>input[type=submit]:hover
{box-shadow: #fc5230 0 0px 0px 40px inset; color:white;}
.qnaWriteForm form .btn>input[type=reset]:hover
{box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset; color:white;}
</style>
</head>
<body>
	<!-- 게시판 등록 -->
	<div class="qnaWriteForm">
		<h1>문의사항 등록</h1>
		<form action="qnaWritePro.qa" method="post" name="qnaForm">
			<table>
				<tr id="subject">
					<td><input type="text" name="wr_subject" id="wr_subject" placeholder="문의 제목" required="required"/></td>
				</tr>
				<tr id="content">
					<td><textarea id="wr_content" name="wr_content" placeholder="문의 내용"  required="required" style="resize: none;"></textarea></td>
				</tr>
			</table>
			<div class="btn">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="초기화">
			</div>
		</form>
	</div>
</body>
</html>