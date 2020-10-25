<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <link href="../css/report.css" rel="stylesheet">
    
<jsp:include page="../main/head.jsp" />
<form action="reportWriteIPro.re" method="post" >
<section class="r_sec_out">
<div class="r_out">
  <table>
      	<tr><th>신고자</th><td><input type="text" name="mb_id" readonly class="r_content" value="${sessionScope.memberID}"></td></tr>
      	<tr><th>신고 게시글(번호)</th><td><input type="text" name="it_no" class="r_content" required="required">
      									  <input type="button" value="방법" class="r_info_btn" onclick="window.open('btnInfo.jsp','','width=435,height=180,top=200,left=1200')"></td></tr>
      	<tr><th>신고 분류</th><td><select class="r_content" name="rp_content_div">
      		<option>기타</option>
      		<option>허위 매물</option>
      		<option>사기</option>
      		<option>불법 행위</option>
      		<option>욕설 및 비방글</option>
      		<option>광고성 게시물</option>
      		<option>도배 게시물</option>
      	</select></td></tr>
      	<tr><th>신고 내용</th><td><textarea rows="20" cols="40" name="rp_content" required="required"></textarea> </td></tr>
       <tr><td colspan="2"><input type="submit" value="신고"  class="r_btn"></td></tr>
      </table>
	</div>
</section>

</form>	
			
<jsp:include page="../main/tail.jsp" />
