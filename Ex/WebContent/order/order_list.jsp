<%@page import="order.vo.orderBean"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.print.Printable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/order_list.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../main/head.jsp" />
    <h1 id="title">참여 내역</h1>
    <br><br><br>


 <c:forEach var="i" items="${articleList}" > <%-- begin=0 end="${articleList.size}" step=1 --%>
 <div id="list" >
  <div> 
    <a href="../item/view.it?no=${i.it_no }">
      <span id="cate">
 
      
      </span>${i.it_category }<span id="date">${i.tr_datetime2 } </span>
      <p id="flag">${i.tr_flag } / ${i.it_successful_price }원</p><div id="pic"><img src="../upload/${i.it_thumbnail }" id="pic2"></div>
      <p id="sub"><b>${i.it_name }</b></p>
      <p id="selname">출품자: ${i.mb_id2 }</p>
    </a><br>
      <p>
        <a href="../report/user.re?mb_id=${i.mb_id2 }" id="other">신고</a>
      </p>
      </div>
      
 </div><br><br><br><br><br><br><br><br><br>
 </c:forEach>
</body>
</html>