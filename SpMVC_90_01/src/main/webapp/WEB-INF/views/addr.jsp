<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 홈페이지</title>
<style>
body {
	display: flex;
	flex-wrap: wrap;
}

.a_list {
	width: 100px;
	border: 1px solid yellow;
	margin: 5px;
	padding: 10px;
}
</style>
</head>
<body>
<c:forEach items="${ADDR_LIST}" var="ADDR">
<div class="a_list">
	<h3>이름 : ${ADDR.name}</h3>
	<p>전화번호 : ${ADDR.tel}</p>
	<p>우편번호 : ${ADDR.post}</p>
	<p>시도 : ${ADDR.city}</p>
	<p>주소 : ${ADDR.addr}</p>
</div>
</c:forEach>

</body>
</html>