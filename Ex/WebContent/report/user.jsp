<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <link href="../css/report.css" rel="stylesheet">
   <script src="../js/jquery-3.4.1.min.js"></script>
    
    
<jsp:include page="../main/head.jsp" />

<form action="reportWriteUPro.re" method="post" >
<section class="r_sec_out">
<div class="r_out">
  <table>
      	<tr><th>신고자</th><td><input type="text" readonly class="r_content" name="mb_id" value="${sessionScope.memberID}"></td></tr>
      	<tr><th>신고 대상(ID)</th><td><input type="text" name="rp_suspect_mb_id" class="r_content" required="required"></td></tr>
      	<tr><th>신고 분류</th><td><select class="r_content" name="rp_content_div">
      		<option>기타</option>
      		<option>욕설</option>
      		<option>성희롱</option>
      		<option>사기</option>
      		<option>광고성 행위</option>
      		<option>불법 행위</option>
      	</select></td></tr>
      	<tr><th>신고 내용</th><td><textarea rows="20" cols="40" name="rp_content" required="required"></textarea> </td></tr>
        <tr><td colspan="2"><input type="submit" value="신고"  class="r_btn"></td></tr>
      </table>
</div>
</section>
</form>
			
<jsp:include page="../main/tail.jsp" />
