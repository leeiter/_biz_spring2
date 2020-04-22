<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<style>
label {
	font-size: 1.3rem;
	margin-left: 20px;
}

.btn {
	margin-bottom: 5px;
}

.checkbox {
	margin-bottom: 20px;
}

.check {
	margin-right: 10px;
}

hr {
	width: 90%;
	margin: 120px auto;
}

.auth {
	margin-top: 5px;
}
</style>

<section>
	<form:form modelAttribute="userVO" action="${rootPath}/user/mypage">
		<div class="checkbox">
			<label for="enabled" class="w3-text-amber check">계정활성화</label>
			<form:checkbox path="enabled"/>
		</div>
		
		<div class="form-group">
			<label for="username" class="w3-text-amber">UserName</label>
			<form:input class="form-control" path="username" readonly="true"/>
		</div>
		
		<div class="form-group">
		<label for="email" class="w3-text-amber">Email</label>
			<form:input class="form-control" path="email"/>
		</div>
		
		<div class="form-group">
		<label for="phone" class="w3-text-amber">Phone</label>
			<form:input class="form-control" path="phone"/>
		</div>
		
		<div class="form-group">
		<label for="address" class="w3-text-amber">Address</label>
			<form:input class="form-control" path="address"/>
		</div>
	
		<hr class="w3-bottombar w3-border-amber"/>
		
		<div id="auth_box" class="form-group">
		<button id="auth_append" type="button" class="btn btn-warning">권한 정보 입력 추가</button>
			<c:if test="${not empty userVO.authorities}">
				<c:forEach items="${userVO.authorities}" var="auth">
					<input name="auth" value="${auth.authority}" class="form-control auth">
				</c:forEach>
			</c:if>
		</div>
		
		<div>
			<button type="button" id="btn_save" class="btn btn-primary">저장</button>
		</div>
	
	</form:form>
</section>
