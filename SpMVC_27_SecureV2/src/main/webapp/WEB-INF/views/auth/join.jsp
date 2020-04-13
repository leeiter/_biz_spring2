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
	// blur를 사용할 때는 alert가 제대로 작동하지 않는다.
	$(document).on("blur", "#username", function() {
		let username = $(this).val()
		if(username == "") {
			$("#m_username").text("아이디는 반드시 입력해야 합니다.")
			$("#username").foucs()
			return false
		}
		
		$.ajax({
			url : "${rootPath}/user/idcheck",
			method : "GET",
			data : {username : username},
			success : function(result) {
				if(result == "EXISTS") {
					$("#m_username").text("이미 가입된 사용자 이름입니다.")
					$("#m_username").css("color", "red")
					$("#username").focus()
					return false
				} else {
					$("m_username").text("사용가능한 ID 입니다.")
				}
			},
			error : function() {
				$("m_username").text("서버와 통신 오류")
				// alert("서버와 통신 오류")
			}
		})
		
	})
	
})
</script>

</head>
<body class="bg-dark" class="container-fluid">
    <div class="container" id="join">
        <div class="row justify-content-center">
        	<div class="col-lg-8">
          		<div class="join">
            		<h2>JOIN</h2>
		            <form:form method="POST" action="${rootPath}/user/join">
		            	<div class="form-group">
		                	<input class="form-control" id="username" name="username" placeholder="User ID">
						</div>
						<div class="message" id="m_username"></div>
		                <div class="form-group">
		                	<input type="password" class="form-control" id="password" name="password" placeholder="password">
		                </div>
		               	<div class="form-group">
		                	<input type="password" class="form-control" id="re_assword" name="re_password" placeholder="re_password">
		                </div>
		                <br>
		                <button type="button" id="btn-join" class="btn btn-lg btn-block btn-primary">JOIN</button>
		                <br>
		                <button type="button" id="btn-loss" class="btn btn-lg btn-block btn-warning">ID/Password Search</button>
		        	</form:form>
          		</div>
        	</div>
    	</div>
    </div>
</body>
</html>