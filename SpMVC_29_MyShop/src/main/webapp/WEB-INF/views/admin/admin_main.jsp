<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<style>
section.body {
	display: flex;
}
article {
	border: 1px solid; blue;
	height: 500px;
}

article.menu {
	flex-basis: 300px;
}

</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="container-fluid body p-2">
	<article class="container menu m-2">
		<%@ include file="/WEB-INF/views/admin/admin_nav.jspf" %>
	</article>
	<article class="container-fluid content m-2"></article>
</section>

<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>