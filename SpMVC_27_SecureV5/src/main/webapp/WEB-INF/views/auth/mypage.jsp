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
<script>
$(function() {
	
	$("input").prop("readonly", true)
		
	$(document).on("click", "#btn_update", function() {
		
		let pass = $("#password").val()
		
		if(pass == "") {
			alert("수정하려면 비밀번호를 입력하세요\n그리고 다시 수정버튼을 누르세요")
			$("div.password").css("display", "block")
			$("#password").prop("readonly", false)
			$("#password").focus()
			return false
		}
		
		
		if(pass != "") {
			
			$.ajax({
				url : "${rootPath}/user/password",
				method : "POST",
				data : {
					password : pass,
					"${_csrf.parameterName}" : "${_csrf.token}"
				},
				success : function(result) {
					if(result == "PASS_OK") {
						$("input").prop("readonly", false)
						$("input").css("color", "blue")
						$("button#btn_save").prop("disabled", false)
						$("button#btn_update").prop("disabled", true)
						
					} else {
						alert("비밀번호가 불일치")
					}
					
				},
				error : function() {
					alert("서버 통신 오류")
					
				}
			})
			
		}
		
	})
		
})
</script>
<style>
.body {
	width: 50%;
	margin: 0 auto;
}

h2 {
	font-size: 4.5rem;
	margin-top: 70px;
	margin-bottom: 25px;
}

form div.password {
	display: none;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="body w3-container">
	<h2 class="w3-center w3-text-amber">MY PAGE</h2>
	<form:form modelAttribute="userVO">
		<div class="form-group">
			<form:input class="form-control" path="username"/>
		</div>
		<div class="password form-group">
			<input class="form-control" type="password" id="password" placeholder="비밀번호를 입력!!">
		</div>
		<div class="form-group">
			<form:input class="form-control" path="email" placeholder="이메일 입력!!"/>
		</div>
		<div class="form-group">
			<form:input class="form-control" path="phone" placeholder="전화번호 입력!!"/>
		</div>
		<div class="form-group">
			<form:input class="form-control" path="address" placeholder="주소 입력!!"/>
		</div>
		<section id="btns">
			<button type="button" id="btn_update" class="btn btn-danger">수정</button>
			<button type="submit" id="btn_save" disabled="disabled" class="btn btn-primary">저장</button>
			<button type="button" id="btn_loss_pass" class="btn btn-warning">비밀번호찾기</button>
		</section>
	</form:form>
</section>
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>