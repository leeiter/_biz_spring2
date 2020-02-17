<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
	<ul class="navbar-nav">
		<li class="navbar-item"><a class="nav-link" href="${rootPath}/">HOME</a></li>
		<li class="navbar-item"><a class="nav-link" href="${rootPath}/admin/product/">상품정보</a></li>
		<li class="navbar-item"><a class="nav-link" href="#">품목정보</a></li>
		<li class="navbar-item"><a class="nav-link" href="${rootPath}/admin/dept/">거래처정보</a></li>
		<li class="navbar-item">
			<form:form action="${rootPath}/logout" name="logout_form">
				<a class="nav-link" onclick="document.logout_form.submit()" href="javascript:void(0)">로그아웃</a>
			</form:form>
		</li>				
	</ul>
</nav>

<section>
	<c:choose>
		<c:when test="${BODY == 'PRODUCT'}">
			<%@ include file="/WEB-INF/views/admin/product.jsp" %>
		</c:when>
		<c:when test="${BODY == 'DEPT'}">
			<%@ include file="/WEB-INF/views/admin/dept.jsp" %>
		</c:when>
	</c:choose>
</section>

</body>
</html>