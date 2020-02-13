<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<li class="navbar-item"><a class="nav-link" href="${rootPath}/admin/product">상품정보</a></li>
		<li class="navbar-item"><a class="nav-link" href="#">품목정보</a></li>
		<li class="navbar-item"><a class="nav-link" href="#">거래처정보</a></li>		
	</ul>
</nav>

<section>
	<c:choose>
		<c:when test="${BODY == 'PRODUCT'}">
			<%@ include file="/WEB-INF/views/admin/product.jsp" %>
		</c:when>
	</c:choose>
</section>

</body>
</html>