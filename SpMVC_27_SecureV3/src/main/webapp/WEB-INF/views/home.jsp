<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 홈페이지</title>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<style>
	#body {
		height: 1000px;
	}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<div>
	<c:choose>
		<c:when test="${BODY == 'MYPAGE'}">
			<%@ include file="/WEB-INF/views/auth/mypage.jsp" %>
		</c:when>
		<c:otherwise>
			<section id="body">
	
			</section>
		</c:otherwise>
	</c:choose>
</div>

</body>
</html>