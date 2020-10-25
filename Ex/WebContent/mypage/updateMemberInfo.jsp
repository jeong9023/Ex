<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../main/head.jsp" />

<link href="../css/member.css" rel="stylesheet" type="text/css">
<link href="../css/mypage.css" rel="stylesheet" type="text/css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<c:set var="mb" value="${mb}"/>
<div id="register">
	<div class="form update_form">
		<form action="UpdateMember.mk" name="rf" method="post" onsubmit="return register_form_submit(this);">
			<p>
				<span class="title">회원 정보 수정</span>
			</p>
			<p>
				<input type="password" name="password" maxlength="50" placeholder="현재 비밀번호" tabindex="2" required="required">
			</p>
			<p>
				<input type="password" name="register_password" maxlength="50" placeholder="새 비밀번호" tabindex="2" required="required">
			</p>
			<p>
				<input type="password" name="register_re_password" maxlength="50" placeholder="새 비밀번호 확인" tabindex="3" required="required">
			</p>
			<p>
				<input type="text" id="register_nick" name="register_nick" class="inp_small" value="${mb.nick}" maxlength="10" placeholder="닉네임 (한글/영문/숫자 2~10자)" tabindex="4" required="required">
				<input type="button" class="btn_check" onclick="duplicationCheck('nick')" value="중복검사">
				<span id="result_nick"></span>
			</p>
			<p>
				<input type="text" name="register_email" style="ime-mode:disabled;" readonly="readonly" value="${mb.email }" maxlength="100" placeholder="이메일" tabindex="5" required="required" >
			</p>
			<p>
				<span class="title">선택 정보 수정</span>
			</p>
			<p>
				<input type="text" style="cursor:pointer;" value="${mb.zip }" id="zip" class="inp_small" name="register_zip" maxlength="7" placeholder="우편번호" onclick="zipSearch()" tabindex="6" readonly="readonly">
				<input type="button" class="btn_search" value="검색" onclick="zipSearch()">
			</p>
			<p>
				<input type="text" style="cursor:pointer;" id="addr1" name="register_address1" value="${mb.address1 }" maxlength="100" placeholder="주소" onclick="zipSearch()" tabindex="7" readonly="readonly">
			</p>
			<p>
				<input type="text" id="addr2" name="register_address2" value="${mb.address2 }" maxlength="100" placeholder="상세주소" tabindex="8">
			</p>
			<p>
				<input type="text" name="register_phone" value="${mb.phone }" maxlength="15" placeholder="연락처 번호" tabindex="9">
			</p>
			<p>
				<input type="submit" class="btn_submit" value="회원정보를 수정합니다.">
			</p>
			
		</form>
		
		<script type="text/javascript">
			var checkNick = false;
		
			function duplicationCheck() {
					// 특수문자 제외 한글/영문자/숫자 조합 2~10자
					var regex = /^[A-Za-z0-9ㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/;
					
					if(!rf.register_nick.value) {
						document.getElementById("result_nick").innerHTML = "닉네임을 입력해주세요.";
						document.getElementById("result_nick").style.color = '#fc5230';
						rf.register_nick.focus();
						return false;
					} else if(!regex.exec(rf.register_nick.value)) {
						document.getElementById("result_nick").innerHTML = "닉네임은 한글/영문자/숫자로만 2~10자 까지 사용 가능합니다.";
						document.getElementById("result_nick").style.color = '#fc5230';
						rf.register_nick.focus();
						return false;
					} else {
						var register_nick = $('#register_nick').val();
						
						$.ajax({
							url: '../mypage/duplicationCheckPro.mk?register_nick=' + register_nick,
							type: 'get',
							success: function(data) {
								if(data == 2) {
									document.getElementById("result_nick").innerHTML = "누군가 이미 사용중인 닉네임입니다 :(";
									document.getElementById("result_nick").style.color = '#fc5230';
								} else if(data == 0){
									document.getElementById("result_nick").innerHTML = "잘 어울리는 닉네임이네요 :)";
									document.getElementById("result_nick").style.color = '#3883c9';
									checkNick = true;
								} else if(data == 1){
									document.getElementById("result_nick").innerHTML = "닉네임은 그대로 사용할래요 :)";
									document.getElementById("result_nick").style.color = '#3883c9';
									checkNick = true;
								}
							}, error: function() {
								alert("서버가 불안정하거나 연결되어 있지 않아 통신을 할 수 없습니다.");
							}
						});
					
				}
			}
			
			function register_form_submit() {
				var regex_phone = /^\d{3}-\d{3,4}-\d{4}$/;
				
				if(!rf.password.value) {
					alert("비밀번호를 입력해주세요.");
					rf.password.focus();
					return false;
				}else if(!rf.register_password.value) {
					alert("비밀번호를 입력해주세요.");
					rf.register_password.focus();
					return false;
				} else if(rf.register_password.value.length < 6) {
					alert("비밀번호는 최소 6자 이상 입력해주세요.");
					rf.register_password.focus();
					return false;
				} else if(rf.register_password.value !== rf.register_re_password.value) {
					alert("비밀번호가 일치하지 않습니다.");
					rf.register_re_password.focus();
					return false;
				} else if(!checkNick) {
					alert("닉네임 중복검사를 통해 사용 가능한 닉네임을 입력해주세요.");
					rf.register_nick.focus();
					return false;
				} else if(rf.register_phone.value.length > 0 && !regex_phone.exec(rf.register_phone.value)) {
					alert("연락처 형식이 올바르지 않습니다.");
					rf.register_phone.focus();
					return false;
				}
	
				return true;
			}
		
			function zipSearch() {
				new daum.Postcode({
			        oncomplete: function(data) {
		                var addr = ''; // 주소
		                var extraAddr = ''; // 참고항목
	
		                if (data.userSelectedType === 'R') { // 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
	
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가 (법정리 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝남
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
	
		                    extraAddr = extraAddr;
		                } else {
		                	extraAddr = '';
		                }
	
		                document.getElementById('zip').value = data.zonecode;
		                document.getElementById("addr1").value = addr + extraAddr;
		                document.getElementById("addr2").focus();
	 		        }
	 		    }).open();
			}
		</script>
	</div>
</div>

<jsp:include page="../main/tail.jsp" />
