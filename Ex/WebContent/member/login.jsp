<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="../css/member.css" rel="stylesheet" type="text/css">
<script src="../js/js.cookie.js"></script>

<div id="login">
	<div class="wrap">		
		<form action="../member/loginPro.me" method="post" name="fl" onsubmit="return floginform_submit();">
			<p><input type="text" class="input" id="user_id" name="user_id" maxlength="20" placeholder="아이디" style="ime-mode:disabled" onkeyup="this.value=this.value.replace(/[^a-zA-Z-_0-9]/g,'');" required></p>
			<p><input type="password" class="input" name="user_password" maxlength="50" placeholder="비밀번호" required></p>
			<p><input type="submit" class="btn_submit" value="로그인"></p>
			
			<div class="link">
				<span class="save_id">
					<input type="checkbox" id="save_id" name="save_id" value="1"><label for="save_id">아이디저장</label>
				</span>
				<a href="../member/register.me">짧은회원가입</a>
			</div>
		</form>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.login_tab').fadeIn(400).addClass('show');
				fl.user_id.focus();
			});
			
			function floginform_submit() {
				if(!fl.user_id.value) {
					alert("아이디를 입력해주세요.");
					fl.user_id.focus();
					return false;
				} else if(!fl.user_password.value) {
					alert("비밀번호를 입력해주세요.");
					fl.user_password.focus();
					return false;
				}
				
				return true;
			}
			
			// 아이디 저장 시 7일간 쿠키에 저장
			$("#user_id").val(Cookies.get('key'));      
		    if($("#user_id").val() != "") {
		        $("#save_id").attr("checked", true);
		    }
		    
			$("#save_id").change(function(){
			    if($("#save_id").is(":checked")) {
			        Cookies.set('key', $("#user_id").val(), { expires: 7 });
			    } else {
			          Cookies.remove('key');
			    }
			});
			     
			$("#user_id").keyup(function(){
			    if($("#save_id").is(":checked")) {
			        Cookies.set('key', $("#user_id").val(), { expires: 7 });
			    }
			});
		</script>
	</div>
</div>