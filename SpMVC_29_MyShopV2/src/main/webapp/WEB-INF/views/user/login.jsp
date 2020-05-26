<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 홈페이지</title>
</head>
<body>
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
</body>
</html>