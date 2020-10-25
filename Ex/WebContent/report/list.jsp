<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고내역 조회</title>
<link rel="icon" type="image/png" sizes="32x32" href="../img/favicon.png">
<link href="../css/report.css" rel="stylesheet">

</head>
<body>


<div class="r_list_wrap">
   <div class="r_list_out">
   <a>신고내역</a><hr>
   </div>
   
     <table class="r_list_table">
        <tr>
           <th class="r_list">신고 대상</th>
           <th class="r_list">신고 분류</th>
           <th class="r_list_status">처리 상태</th>
        </tr>
      <c:forEach var="call" items="${reportList}">
           <tr>
          <c:choose>
             <c:when test="${!empty call.rp_suspect_mb_id }">
             	<c:choose>
             		<c:when test="${fn:length(call.rp_suspect_mb_id) gt 7}">
            		  <td><c:out value="사용자 - ${fn:substring(call.rp_suspect_mb_id, 0, 6)}" />...</td>
             		</c:when>
             		<c:otherwise>
             			<td><c:out value="사용자 - ${call.rp_suspect_mb_id}" /></td>
             		</c:otherwise>
             	</c:choose>
             </c:when>
             
             <c:otherwise>
               <td><c:out value="게시글 - ${call.it_no}" /></td>
             </c:otherwise>
          </c:choose>
              <td><c:out value="${call.rp_content}" /></td>
             <c:choose>
                <c:when test="${0 eq call.rp_feedback }">
              <td><c:out value="대기중" /><input type="button" value="삭제" class="r_list_delete" onclick="if(confirm('삭제 하시겠습니까?')){location.href='reportDelete.re?rp_no=${call.rp_no}';}else{return false;}"></td>
                </c:when>
                <c:otherwise>
              <td><c:out value="처리완료" /></td>
                </c:otherwise>
             </c:choose>
           </tr>
        </c:forEach>
      </table>
      
<c:set var="list" value="${pageInfo}"  />

     <c:choose>
      <c:when test="${list.page>1}">
         <input type="button" value="이전" onclick="location.href='list.re?page=${list.page - 1}'" class="r_paging_btn">&nbsp;
      </c:when>
   </c:choose> 
   
   
         <c:forEach begin="${list.startPage}" end="${list.endPage}" var="i" step="1">
              <a href="list.re?page=${i}" class="r_paging01">[${i}]</a>
        </c:forEach>
        
          
          
   <c:choose>
      <c:when test="${list.page ne list.maxPage and list.maxPage>1}">
         &nbsp;<input type="button" value="다음" onclick="location.href='list.re?page=${list.page + 1}'" class="r_paging_btn">
      </c:when>
   </c:choose>
   
</div>

</body>
</html>