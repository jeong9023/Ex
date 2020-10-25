<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <link href="../css/report.css" rel="stylesheet">
    <link href="../css/report_admin.css" rel="stylesheet">
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script src="../js/stats.js"></script>
<jsp:include page="../main/head.jsp" />

	
	<div class="r_admin_side">
	<div class="r_admin_wrap">
	<p class="r_admin_title">신고처리 진행 상태</p>
<c:set var="stats" value="${reportBean}" />
		<c:choose>
			<c:when test="${stats.statsCount<2}">
				<div class="r_stats_out">
				<div class="r_bar"><div data-width="20" >
					completed<span>20% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsCount<4}">
				<div class="r_stats_out">
				<div class="r_bar"><div data-width="40" >
					completed<span>40% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsCount<6}">
				<div class="r_stats_out">
				<div class="r_bar"><div data-width="60" >
					completed<span>60% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsCount<8}">
				<div class="r_stats_out">
				<div class="r_bar"><div data-width="80" >
					completed<span>80% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsCount>8}">
				<div class="r_stats_out">
				<div class="r_bar"><div data-width="98" >
					completed<span>90% 이상</span>
				</div>
				</div>
				</div>
			</c:when>
		</c:choose>
	</div>
	
	<div class="r_admin_wrap">
<p class="r_admin_title">사용자 신고 비율 </p>
		<c:choose>
			<c:when test="${stats.statsTwoCount<2}">
				<div class="r_stats_out">
				<div class="r_bar_01"><div data-width="20" >
					user<span>20% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsTwoCount<4}">
				<div class="r_stats_out">
				<div class="r_bar_01"><div data-width="40" >
					user<span>40% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsTwoCount<6}">
				<div class="r_stats_out">
				<div class="r_bar_01"><div data-width="60" >
					user<span>60% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsTwoCount<8}">
				<div class="r_stats_out">
				<div class="r_bar_01"><div data-width="80" >
					user<span>80% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsTwoCount>8}">
				<div class="r_stats_out">
				<div class="r_bar_01"><div data-width="98" >
					user<span>90% 이상</span>
				</div>
				</div>
				</div>
			</c:when>
		</c:choose>
	</div>	
	
			<div class="r_admin_wrap">
	<p class="r_admin_title">사기 및 비매너 비율</p>
		<c:choose>
			<c:when test="${stats.statsThreeCount<2}">
				<div class="r_stats_out">
				<div class="r_bar_02"><div data-width="20" >
					crime<span>20% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsThreeCount<4}">
				<div class="r_stats_out">
				<div class="r_bar_02"><div data-width="40" >
					crime<span>40% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsThreeCount<6}">
				<div class="r_stats_out">
				<div class="r_bar_02"><div data-width="60" >
					crime<span>60% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsThreeCount<8}">
				<div class="r_stats_out">
				<div class="r_bar_02"><div data-width="80" >
					crime<span>80% 미만</span>
				</div>
				</div>
				</div>
			</c:when>
			<c:when test="${stats.statsThreeCount>8}">
				<div class="r_stats_out">
				<div class="r_bar_02"><div data-width="98" >
					crime<span>90% 이상</span>
				</div>
				</div>
				</div>
			</c:when>
		</c:choose>
	</div>
			<div class="r_restrict_base">
				<input type="button" onclick="window.open('restrictSup.re?mb_id=${sessionScope.mb_id}','restrict','width=666,height=500')" class="r_admin_restrict" value="회원관리/정지">
			</div>
		
		</div> 
		
	<div class="r_admin_right">
<div class="r_in_content">
	<a class="r_table_title">신고 접수 상태</a>
	
	<table class="r_table">
		<tr>
		<th class="r_num">번호</th>
		<th class="r_div">대상분류</th>
		<th class="r_con">처리상태</th>
		</tr>
	<c:forEach var="admin" items="${reportList}">
		<tr onclick="location.href='view.re?rp_no=${admin.rp_no}'">
					<td><c:out value="${admin.rp_no}"/></td>
			<c:choose>
				<c:when test="${1 eq admin.rp_flag}">
					<td>게시글</td>
				</c:when>
				<c:when test="${1 ne admin.rp_flag}">
					<td>사용자</td>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${0 eq admin.rp_feedback}">
					<td>대기</td>
				</c:when>
				<c:when test="${0 ne admin.rp_feedback}">	
					<td>완료</td>
				</c:when>
			</c:choose>
		</tr>

	</c:forEach>
	</table>	
	
	<div class="r_paging">
	
<c:set var="list" value="${pageInfo}"  />

<div class="r_admin_list">
     <c:choose>
      <c:when test="${list.page>1}">
         <input type="button" value="이전" onclick="location.href='admin.re?page=${list.page - 1}'" class="r_admin_list_btn">&nbsp;
      </c:when>
   </c:choose> 
   
   
         <c:forEach begin="${list.startPage}" end="${list.endPage}" var="i" step="1">
              <a href="admin.re?page=${i}" class="r_paging01">[${i}]</a>
        </c:forEach>
        
          
          
   <c:choose>
      <c:when test="${list.page ne list.maxPage and list.maxPage>1}">
        &nbsp;<input type="button" value="다음" onclick="location.href='admin.re?page=${list.page + 1}'" class="r_admin_list_btn">
      </c:when>
   </c:choose>
</div>
	
	
	</div>
		
</div>
	</div>
	

		
			
<jsp:include page="../main/tail.jsp" />
