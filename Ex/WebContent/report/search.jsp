<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 조회</title>
  	<link href="../css/report.css" rel="stylesheet">
    <script src="../js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$('.r_search_btn').click(function(){
			$.ajax('reportSearch.re',{
				data:{id:$('#searchId').val()},
				success:function(rdata){
					$('.r_search_result').html(rdata);
				}
			});
		});
	});
	</script>
</head>
<body>
<div class="r_search_wrap">
	<h1>신고 내역 조회</h1>
		<input type="text" placeholder="ID 를 입력하세요" class="r_admin_search_id" id="searchId" name="searchId">
		<input type="button" value="검색" class="r_search_btn">
	<div class="r_search_result">결과</div>
</div>
	
	
</body>
</html>