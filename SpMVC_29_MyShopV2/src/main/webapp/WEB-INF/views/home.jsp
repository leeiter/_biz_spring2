<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<style>
h2 {
	font-size: 5.5rem;
	margin: 300px 0;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="body">
	<h2 class="w3-center w3-text-amber">My Security Project</h2>
</section>

<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>