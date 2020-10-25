<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	<link href="../css/point.css" rel="stylesheet">
    <script src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$.ajax('pointList.po',{
			success:function(rdata){
				$('.point').html(rdata);
			}
		});
		
		$.ajax('ticketList.po',{
			success:function(rdata){
				$('.ticket').html(rdata);
			}
		});
		
	});
	</script>
	<jsp:include page="../main/head.jsp" />

<section class="p_sec">
	<div class="point_title">
<img src="../img/charge.png" class="point_title_img">
<a class="point_title_font">땅땅포인트 충전소</a>
	</div>
	
	<div class="point_left">
		<div class="point_left_wrap">
			<div class="point_margin">
			<label class="point_list_id">ID</label> <a class="point_id">${sessionScope.memberID}</a><br>
			</div>
			<div class="point_margin">
			<label class="point_list">땅땅POINT</label> <a class="point"> 0 </a>P
			</div>
			<div class="point_margin">
			<label class="point_list">땅땅TICKET</label> <a class="ticket"> 0 </a>T
			</div>
			<div class="point_list_content">
			<a class="point_tip">땅땅TIP</a>땅땅 POINT로 경매장의 경매에<br>
			참여하여 물건에 입찰할 수 있으며 <br>
			교환 및 환불 문의는 문의하기 기능을<br>
			이용해 주세요.<br><br>
			땅땅 POINT 충전은 로그인이 되어있어야<br>
			합니다.<br><br>
			POINT 충전은 네이버페이만 가능합니다<br><br>
			POINT 충전시 전산상의 문제로 POINT 지급까지<br>
			5분 정도 소요될 수 있습니다. 이후에도 <br>
			문제가 지속된다면 카카오채널로 문의시<br>
			빠른 처리를 도와드리겠습니다.<br>
			(결제일시, 결제금액, 본인ID를 함께 기재하여<br>
			주시면 더욱더 빠른 처리가 가능합니다.)<br><br>
			</div>
				<a class="point_href_shop" onclick="window.open('../shop/shop.sp?mb_id=${sessionScope.memberID}','open','width=530,height=580')">땅땅샵</a>
				<a class="point_href" onclick="window.open('openFirst.jsp','open','width=530,height=500')">회원약관</a>
				<a class="point_href" onclick="window.open('openSecond.jsp','open2','width=530,height=500')">땅땅POINT 약관</a>
		</div>
		
	</div>

	<div class="point_right">

   	<input type="button" id="pay_one" class="pay_btn_one">
   	<input type="button" id="pay_two" class="pay_btn_two"><br>
   	<input type="button" id="pay_three" class="pay_btn_three">
   	<input type="button" id="pay_four" class="pay_btn_four"><br>
   	<input type="button" id="pay_five" class="pay_btn_five">
   	<input type="button" id="pay_six" class="pay_btn_six">
   	
<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"></script>
<script>
    var oPay = Naver.Pay.create({
          "mode" : "production", // development or production
          "clientId": "u86j4ripEt8LRfPGzQ8" // clientId
    });
    var elNaverPayBtn = document.getElementById("pay_one");
    elNaverPayBtn.addEventListener("click", function() {
        oPay.open({
          "merchantUserKey": "7",
          "merchantPayKey": "7",
          "productName": "땅땅포인트 충전",
          "totalPayAmount": "1000",
          "taxScopeAmount": "1000",
          "taxExScopeAmount": "0",
          "returnUrl": "payOne.po"
        });
    });
    var elNaverPayBtn = document.getElementById("pay_two");
    elNaverPayBtn.addEventListener("click", function() {
        oPay.open({
          "merchantUserKey": "7",
          "merchantPayKey": "7",
          "productName": "땅땅포인트 충전",
          "totalPayAmount": "5000",
          "taxScopeAmount": "5000",
          "taxExScopeAmount": "0",
          "returnUrl": "payTwo.po"
        });
    });
    var elNaverPayBtn = document.getElementById("pay_three");
    elNaverPayBtn.addEventListener("click", function() {
        oPay.open({
          "merchantUserKey": "7",
          "merchantPayKey": "7",
          "productName": "땅땅포인트 충전",
          "totalPayAmount": "10000",
          "taxScopeAmount": "10000",
          "taxExScopeAmount": "0",
          "returnUrl": "payThree.po"
        });
    });
    var elNaverPayBtn = document.getElementById("pay_four");
    elNaverPayBtn.addEventListener("click", function() {
        oPay.open({
          "merchantUserKey": "7",
          "merchantPayKey": "7",
          "productName": "땅땅포인트 충전",
          "totalPayAmount": "30000",
          "taxScopeAmount": "30000",
          "taxExScopeAmount": "0",
          "returnUrl": "payFour.po"
        });
    });
    var elNaverPayBtn = document.getElementById("pay_five");
    elNaverPayBtn.addEventListener("click", function() {
        oPay.open({
          "merchantUserKey": "7",
          "merchantPayKey": "7",
          "productName": "땅땅포인트 충전",
          "totalPayAmount": "50000",
          "taxScopeAmount": "50000",
          "taxExScopeAmount": "0",
          "returnUrl": "payFive.po"
        });
    });
    var elNaverPayBtn = document.getElementById("pay_six");
    elNaverPayBtn.addEventListener("click", function() {
        oPay.open({
          "merchantUserKey": "7",
          "merchantPayKey": "7",
          "productName": "땅땅포인트 충전",
          "totalPayAmount": "100000",
          "taxScopeAmount": "100000",
          "taxExScopeAmount": "0",
          "returnUrl": "paySix.po"
        });
    });

</script> 

	</div>
</section>
			
<jsp:include page="../main/tail.jsp" />



