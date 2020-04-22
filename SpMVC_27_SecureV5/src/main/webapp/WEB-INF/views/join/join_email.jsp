<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<script>
$(function() {
	
	$(document).on("click", "#btn_email_ok", function() {
		let secret_key =  $("span#secret").text()
		let secret_value = $("input#email_ok").val()
		if(secret_value == "") {
			alert("인증코드를 입력한 후 인증버튼을 클릭하세요")
			$("input#email_ok").focus()
			return false
		}
	
	$.ajax({
		url : "${rootPath}/join/email_token_check",
		method : "POST",
		data : {
			"${_csrf.parameterName}" : "${_csrf.token}",
			secret_id : "${username}",
			secret_key : secret_key,
			secret_value : secret_value
		},
		success : function(result) {
			// alert(result)
			document.location.replace("${rootPath}/user/login")
			
		},
		error : function() {
			alert("서버통신오류")
			
		}
	})
	
	})
	
})
</script>

<style>
.body {
	width: 23%;
	margin: 0 auto;
	display: flex;
	flex-flow: column;
}

h2 {
	font-size: 4.5rem;
	margin-top: 70px;
	
}

.for-email {
	margin-bottom: 30px;
}

p {
	margin-top: 30px;
}

span#secret {
	display: none;
}

button.btn {
	display: block;
	width: 100%;
	margin-top: 10px;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="body w3-container email_body">
	<h2 class="w3-center w3-text-amber">Email 인증</h2>
	<div class="for-email w3-center">회원가입을 완료하려면 Email 인증을 수행해야 합니다.</div>
	<form:form action="${rootPath}/join/join_last" modelAttribute="userVO">
		<div class="form-group">
			<form:input class="form-control" type="email" path="email" placeholder="email"/>
			<button class="btn btn-info">인증 Email 보내기</button>
		</div>
		<c:choose>
			<c:when test="${JOIN == 'EMAIL_OK'}">
				<p class="w3-center w3-text-pink">E-mail을 열어서 인증코드를 확인한 후 아래 입력란에<br/>입력한 후 인증 버튼을 클릭하세요</p>
				<div class="form-group">
				<span id="secret">${My_Email_Secret}</span>
					<input class="form-control" id="email_ok">
					<button type="button" id="btn_email_ok" class="btn btn-dark">인증하기</button>
				</div>
			</c:when>
		</c:choose>
	</form:form>
</section>
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>