<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<style>
	body {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
		font-family: "TmonMonsori";
	}
	
	@font-face { font-family: 'TmonMonsori'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/TmonMonsori.woff') format('woff'); font-weight: normal; font-style: normal; }
	
	
	div#in_form {
		width: 20%;
		margin: 250px auto;
		border-radius: 10px; 
	}

	h2 {
		padding: 50px 0;
	}
	
	input {
		display: block;
		margin: 20px auto;
	}
	
	button {
		margin: 30px 0 60px 0;
	}
	
	button#btn-login {
		margin-right: 10px;
	}

	.message {
		color: red;
		font-weight: bold;
		font-size: 0.3rem;
	}
	
</style>

<script>
$(function() {
	
	$(document).on("click", "#btn-join", function() {
		// 유효성 검사
		// id, password가 입력되지 않았을 때 경고
		let username = $("#username")
		let password = $("#password")
		let re_password = $("#re_password")
		
		if(username.val() == "") {
			alert("ID를 입력해주세요")
			username.focus()
			return false
		}
		
		if(password.val() == "") {
			alert("비밀번호를 입력해주세요")
			password.focus()
			return false
		}
		
		if(re_password.val() == "") {
			alert("비밀번호 확인을 입력해주세요")
			re_password.focus()
			return false
		}
		
		if(password.val() != re_password.val()) {
			alert("비밀번호와 비밀번호 확인이 다릅니다.\n확인해주세요.")
			password.focus()
			return false
		}
		$("form").submit()
		
	})
	
	// 현재 입력박스에서 포커스가 벗어났을 때 발생하는 이벤트
	$(document).on("blur", "#username", function() {
		let username = $(this).val()
		if(username == "") {
			$("#m_username").text("아이디는 반드시 입력해야 합니다.")
			return false
		}
		
		$.ajax({
			url : "${rootPath}/user/idcheck",
			method : "GET",
			data : {username : username},
			success : function(result) {
				if(result == "USE") {
					$("#m_username").text("이미 가입된 사용자 이름입니다.")
					$("#m_username").css("color", "red")
					return false
				}
			},
			error : function() {
				// alert("서버와 통신 오류")
				
			}
		})
		
	})
	
})
</script>

</head>
<body class="bg-dark">
	<div id="in_form" class="form-group bg-light text-dark text-center">
		<form:form method="POST" action="${rootPath}/user/join">
			<h2>JOIN</h2>
			<!-- 
			<input type="text" name="${_csrf.parameterName}" value="${_csrf.token}">
			 -->
			<input id="username" name="username" class="form-control col-9" placeholder="User ID">
			<div class="message" id="m_username"></div>
			<input type="password" id="password" name="password" class="form-control col-9" placeholder="password">
			<input type="password" id="re_password" name="re_password" class="form-control col-9" placeholder="Re_password">
			<button type="button" id="btn-join" class="btn btn-primary">JOIN</button>
			<button type="button" id="btn-loss" class="btn btn-warning">ID/Password Search</button>
		</form:form>
	</div>
</body>
</html>