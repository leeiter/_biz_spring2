<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<script>
$(function() {
	
	$(document).on("click", "button#btn-join", function() {
		document.location.href = "${rootPath}/join"
	})
	
})
</script>

<style>
.body {
	width: 23%;
	margin: 0 auto;
}

h2 {
	font-size: 4.5rem;
	margin-top: 70px;
	margin-bottom: 15px;
}

span {
	display: block;
	margin-bottom: 4px;
}

button.btn {
	display: block;
	width: 100%;
	margin-bottom: 10px;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<section class="body w3-container">
	<h2 class="w3-center w3-text-amber">LOGIN</h2>
	<br/>
	<form:form action="${rootPath}/login" method="POST">
		<div>
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<span class="w3-text-pink w3-center">${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
			</c:if>
		</div>
	    <div class="form-group">
	    	<input class="form-control" id="username" name="username" placeholder="User ID">
		</div>
	    <div class="form-group">
	    	<input class="form-control" type="password" id="password" name="password" placeholder="password">
	    </div>
	    <section id="btns"> 
	    	<button class="btn btn-success">LOGIN</button>
	    	<button type="button" id="btn-join" class="btn btn-primary">JOIN</button>
	    </section>
	</form:form>
</section>
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>