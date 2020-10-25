<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link href="../css/report.css" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    	$('.r_view_btn').click(function(){
    		$('.r_td').html("<input type='button' value='기각'  class='r_view_btn_up'><input type='hidden' value='1' name='rp_feedback'>")
    	})
    	$('.r_view_btn01').click(function(){
    		$('.r_td').html("<input type='button' value='사기'  class='r_view_btn_up'><input type='hidden' value='2' name='rp_feedback'>")
    	})
    	$('.r_view_btn02').click(function(){
    		$('.r_td').html("<input type='button' value='비매너'  class='r_view_btn_up'><input type='hidden' value='3' name='rp_feedback'>")
    	})
    	$('.r_view_btn03').click(function(){
    		$('.r_td').html("<input type='button' value='기타'  class='r_view_btn_up'><input type='hidden' value='4' name='rp_feedback'>")
    	})
    	
    	
    });
    
	</script>
    
<jsp:include page="../main/head.jsp" />

<c:set var="v" value="${reportBean}"/>
<form action="reportStatsPro.re?rp_no=${v.rp_no}" method="post">
<div class="r_view_out">
<div class="r_view">
<a class="r_view_title"> 신고 처리 </a>
  <table class="r_view_table">
  
  		<tr><th>번호</th><td><c:out value="${v.rp_no}"/> </td></tr>
      	<tr><th>신고자</th><td><c:out value="${v.mb_id}"/> </td></tr>
      	<tr><th>신고 대상</th>
      	<td>
      	<c:choose>
      		<c:when test="${empty v.rp_suspect_mb_id}">
      			<c:out value="게시글 번호 : ${v.it_no}"/>
      		</c:when>
      		<c:otherwise>
      			<c:out value="사용자 ID : ${v.rp_suspect_mb_id}"/>
      		</c:otherwise>
      	</c:choose>
      	</td></tr>
      	<tr><th>신고 분류</th>
      	<td>
      	<c:choose>
      		<c:when test="${1 eq v.rp_flag}">
      			사용자 신고
      		</c:when>
      		<c:otherwise>
      			게시물 신고
      		</c:otherwise>
      	</c:choose>
      	</td></tr>
      	<tr><th>신고 내용</th><td>
      		<textarea rows="5" cols="2" class="r_view_content" readonly><c:out value="${v.rp_content}"/></textarea> 
      		</td></tr>
        <tr><th>처리 상태</th>
        <td class="r_td">
        	<c:choose>
        		<c:when test="${0 eq v.rp_feedback}">
        			<input type="button" value="기각"  class="r_view_btn">
        			<input type="button" value="사기" class="r_view_btn01">
        			<input type="button" value="비매너" class="r_view_btn02">
        			<input type="button" value="기타" class="r_view_btn03">
        		</c:when>
        		<c:when test="${1 eq v.rp_feedback}">
        			기각
        		</c:when>
        		<c:when test="${2 eq v.rp_feedback}">
					사기
        		</c:when>
        		<c:when test="${3 eq v.rp_feedback}">
					비매너
        		</c:when>
        		<c:when test="${4 eq v.rp_feedback}">
					기타
        		</c:when>
        		
        	</c:choose>
        </td>
        <tr><td colspan="2">
        <input type="button" value="검색"  class="r_v_btn" onclick="window.open('search.re','search','width=350,height=250')">
        <input type="button" value="목록"  class="r_v_btn" onclick="location.href='admin.re'">
        <input type="button" value="취소" class="r_v_btn" onclick="window.location.reload()"> 
        <input type="button" value="관리" class="r_v_btn" onclick="window.open('restrictSup.re?mb_id=${sessionScope.mb_id}','restrict','width=666,height=500')">
        <input type="submit" value="처리"  class="r_v_btn"></td></tr>
        
      
      </table>
</div>
</div>
      
</form>
			
<jsp:include page="../main/tail.jsp" />
