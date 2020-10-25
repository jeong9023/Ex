<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link href="../css/report.css" rel="stylesheet">
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
    
    $(document).ready(function(){
    	
    $.ajax('inquiry_ajax_all.re',{
    	success:function(rdata){
    	$('.r_iq_plus01').html(rdata);
    	}
    });
    $.ajax('inquiry_ajax_def.re',{
    	success:function(rdata){
    	$('.r_iq_plus02').html(rdata);
    	}
    });
    $.ajax('inquiry_ajax_risk.re',{
    	success:function(rdata){
    	$('.r_iq_plus03').html(rdata);
    	}
    });
    	
    	
    	$('.r_iq_btn').click(function(){
    		$.ajax('inquiry_ajax.re',{
    			data: {inquiry:$('.r_inquiry').val()},
    		success:function(rdata){
    			$('.r_iq_result').html(rdata);
    				}
    		});
    	});
    });
	</script>
<jsp:include page="../main/head.jsp" />

	

<!-- 사기 피해사례 조회 -->

<section class="r_sec_iq">
<p class="r_iq_font">사기 피해사례 검색</p>

         <div class="r_iq_base01">
   <a class="r_back">
   		<input type="text" name="inquiry"  placeholder="사용자 아이디로 검색해보세요." class="r_inquiry">
   		<input type="button" value="조회" class="r_iq_btn">
     </a>
         </div>

<div class="r_iq_base02">
         <p class="r_iq_result"> 조회 결과</p>
        
</div>
	<div class="r_iq_info">
		<a>누적 신고 수 <span class="r_iq_plus01"> ???</span></a>
		<a>피해 예방 수 <span class="r_iq_plus02"> ???</span></a>
		<a>피해 사례 수 <span class="r_iq_plus03"> ???</span></a>
	</div>
	
		<div class="r_link">
			<a onclick="window.open('https://www.nuricops.org','link','width=800,height=500')"><img src="../img/report_link01.png" class="r_link01"></a>
			<a onclick="window.open('http://www.police.go.kr/www/security/cyber.jsp','link','width=800,height=500')"><img src="../img/report_link02.png" class="r_link02"></a>
			<a onclick="window.open('https://www.kisa.or.kr','link','width=800,height=500')"><img src="../img/report_link03.png" class="r_link03"></a>
			<a onclick="window.open('https://www.gov.kr/portal/service/serviceInfo/PTR000050727','link','width=800,height=500')" ><img src="../img/report_link04.png" class="r_link04"></a>
			<a onclick="window.open('http://www.fss.or.kr/fss/kr/main.html','link','width=800,height=500')" ><img src="../img/report_link05.png" class="r_link05"></a>
		</div>
</section>
		
			
			
<jsp:include page="../main/tail.jsp" />





