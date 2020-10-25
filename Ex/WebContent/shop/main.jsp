<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/shop.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="../img/favicon.png">
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$(".shop_point_btn").click(function(){
		$.ajax('point.sp',{
			success:function(rdata){
				$('.shop_point_inquiry').html(rdata);
			}
		});
	});
	
	$(".shop_ticket_btn").click(function(){
		$.ajax('ticketIq.sp',{
			success:function(rdata){
				$('.shop_ticket_inquiry').html(rdata);
			}
		});
	});
	
});
</script>


<title>땅땅땅 샵</title>
</head>
<body>
<div class="shop_main">
<div class="shop_title">
<img src="../img/shop.png" class="shop_title_img"><br>땅땅땅 샵
</div><br>

	<img src="../img/ticket.png" onclick="confirm('구매하시겠습니까?') ? location.href='ticket.sp' : false;" class="shop_img"><br>
	
	<div>
	<a class="shop_price">9,900￦</a><br>
	티 켓 을 구 매 하 세 요 <br>
	사 면 좋 아 요 티 켓 으 <br>
	로 자 신 의 상 품 을 남 <br>
	들 과 다 르 게 개 성 있 <br>
	게 표 현 하 여 일 분 만 <br>
	에 낙 찰 받 아 서 치 킨 <br>
	을 사 먹 어 요 이 글 은 <br>
	티 켓 을 판 매 하 기 위 <br>
	하 여 구 매 권 장 하 는 <br>
	글 이 맞 습 니 다 하 하 <br>
	
	</div>
	
	<div class="shop_point">
		<input type="button" value="포인트조회" class="shop_point_btn">
		<a class="shop_point_inquiry"> - </a> 
		<br>
		<input type="button" value="티켓조회" class="shop_ticket_btn">
		<a class="shop_ticket_inquiry"> - </a>  
	</div>
	
</div>



</body>
</html>