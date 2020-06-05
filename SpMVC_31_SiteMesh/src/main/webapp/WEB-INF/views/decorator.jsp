<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 홈페이지</title>
</head>
<body>
	<div>
		<%@ include file="/WEB-INF/views/nav.jsp" %>
	</div>
	<div>
		<sitemesh:write property="body" />
	</div>
	<div>
		<sitemesh:write property="footer" />
	</div>	
</body>
</html>