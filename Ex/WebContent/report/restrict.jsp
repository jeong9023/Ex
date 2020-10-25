<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link href="../css/report.css" rel="stylesheet">
<link rel="icon" type="image/png" sizes="32x32" href="../img/favicon.png">
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>

<div class="r_member_restrict">
	<div class="r_member_restrict_center">
<div class="r_stop_list">회원관리</div>
<table>
	<tr>
	<th class="r_restrict_01">회원ID</th>
	<th class="r_restrict_02">정지상태</th>
	<th class="r_restrict_03">처리</th>
	</tr>
<c:forEach var="call" items="${supList}">
	<tr>
	<c:choose>
		<c:when test="${!empty call.mb_id}">
			<td><c:out value="${call.mb_id}"/></td>
		</c:when>
	</c:choose>
		<c:choose>
		<c:when test="${null ne call.mb_ban_datetime}">
			<td><c:out value="정지 : ${call.mb_ban_datetime}"/></td>
		</c:when>
		</c:choose>
		<c:choose>
		<c:when test="${null eq call.mb_ban_datetime}">
			<td><c:out value=" - "/></td>
		</c:when>
		</c:choose>
			<td><input type="button" value="정지" class="r_start" onclick="location.href='restrict.re?mb_id=${call.mb_id}'">
				<input type="button" value="해제" class="r_stop" onclick="location.href='restrictClear.re?mb_id=${call.mb_id}'">
			</td>
			
	</tr>
</c:forEach>
</table>

<c:set var="list" value="${pageInfo}"  />
     <c:choose>
      <c:when test="${list.page>1}">
         <input type="button" value="이전" onclick="location.href='restrictSup.re?page=${list.page - 1}'" class="r_paging_btn">&nbsp;
      </c:when>
   </c:choose> 
   
   
         <c:forEach begin="${list.startPage}" end="${list.endPage}" var="i" step="1">
              <a href="restrictSup.re?page=${i}" class="r_paging01">[${i}]</a>
        </c:forEach>
        
          
          
   <c:choose>
      <c:when test="${list.page ne list.maxPage and list.maxPage>1}">
         &nbsp;<input type="button" value="다음" onclick="location.href='restrictSup.re?page=${list.page + 1}'" class="r_paging_btn">
      </c:when>
   </c:choose>
   	</div>
</div>
   

</body>
</html>