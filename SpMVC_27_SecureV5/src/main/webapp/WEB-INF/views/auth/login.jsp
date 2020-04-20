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
	
	$(document).on("click", "button.btn-join", function() {
		document.location.href = "${rootPath}/join"
	})
	
})
</script>

</head>
<body class="bg-dark" class="container-fluid">
    <div class="container" id="login">
        <div class="row justify-content-center">
        	<div class="col-lg-8">
          		<div class="login">
            		<h2>LOGIN</h2>
		            <form:form action="${rootPath}/login" method="POST">
		            	<div>
		            		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		            			<span>${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
		            		</c:if>
		            	</div>
		            	<br>
		            	<div class="form-group">
		                	<input class="form-control" id="username" name="username" placeholder="User ID">
						</div>
		                <div class="form-group">
		                	<input type="password" class="form-control" id="password" name="password" placeholder="password">
		                </div>
		                <br>
		                <button id="btn-login" class="btn btn-lg btn-block btn-success">LOGIN</button>
		                <br>
		                <button type="button" class="btn btn-lg btn-block btn-primary btn-join">JOIN</button>
					</form:form>
          		</div>
        	</div>
    	</div>
    </div>
</body>
</html>