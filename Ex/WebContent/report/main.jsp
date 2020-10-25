<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <link href="../css/report.css" rel="stylesheet">
    <script src="../js/jquery-3.4.1.min.js"></script>
    
<jsp:include page="../main/head.jsp" />

<section class="r_sec">
   	<img src="../img/user.png" class="r_img_u" onclick="location.href='user.re'">
   	<img src="../img/product.png" class="r_img_p" onclick="location.href='product.re'">
   	<img src="../img/list.png" class="r_img_l" onclick="window.open('list.re?memberID=${sessionScope.memberID}','list','width=500, height=600')"><br>
   	
<c:if test="${memberID eq 'admin'}">
	<div class="r_admin_out">
	<input type="button" value="관리자 전용" class="r_admin_btn" onclick="location.href='admin.re'">
	</div>
</c:if>


</section>
			
<jsp:include page="../main/tail.jsp" />



