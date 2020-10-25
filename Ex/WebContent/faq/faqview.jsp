<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/index.css" rel="stylesheet">
<style type="text/css">
.fqmenu {	width: 1200px;	margin: 0 auto;}
.fqmenu td {	border: 1px black solid;	font-size: 16px;	width: 200px;	text-align: center;}
</style>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#faqmember').click(function(){
			$.ajax('1.jsp', {
				success : function(rdata) {
					$(index_faq).html(rdata);
				}
			});
		});
/* 		$("#faqorder").on("click", function() {
			$.ajax('faqorder.jsp', {
				success : function(rdata) {
					$('.index_faq').html(rdata);
				}
			});
		});
		$("#faqtrade").on("click", function() {
			$.ajax('faqtrade.jsp', {
				success : function(rdata) {
					$('.index_faq').html(rdata);
				}
			});
		});
		$("#faqdelivery").on("click", function() {
			$.ajax('faqdelivery.jsp', {
				success : function(rdata) {
					$('.index_faq').html(rdata);
				}
			});
		});
		$("#faqcancel").on("click", function() {
			$.ajax('faqcancel.jsp', {
				success : function(rdata) {
					$('.index_faq').html(rdata);
				}
			});
		});
		$("#faqetc").on("click", function() {
			$.ajax('faqetc.jsp', {
				success : function(rdata) {
					$('.index_faq').html(rdata);
				}
			});
		}); */
});
</script>
<jsp:include page="../main/head.jsp" />
	<table class="fqmenu">
		<tr>
			<td id="faqmember">회원</td>
<!-- 			<td id="faqorder">주문</td>
			<td id="faqtrade">거래</td>
			<td id="faqdelivery">배송</td>
			<td id="faqcancel">취소</td>
			<td id="faqetc">기타</td> -->
		</tr>
	</table>
<div class="index_faq">
</div>


<jsp:include page="../main/tail.jsp" />
	<script type="text/javascript">
		var acc = document.getElementsByClassName("Q");
		var i;

		for (i = 0; i < acc.length; i++) {
			acc[i].addEventListener("click", function() {
				this.classList.toggle("faqactive");

				var A = this.nextElementSibling;

				if (A.style.maxHeight) {
					A.style.maxHeight = null;
				} else {
					A.style.maxHeight = A.scrollHeight + "px";
				}
			});
		}
	</script>
</body>
</html>