<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
div.p_detail_box {
	display: flex;
}
div.p_detail_label, div.p_detail_data {
	padding: 10px;
	margin: 5px;
}
div.p_detail_label {
	flex: 1;
	text-align: right;
	background-color: #FFE4C4;
	font-weight: bold;
}
div.p_detail_data {
	flex: 3;
	background-color: #FFF8DC;
}
</style>

<script>

</script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="container-fluid body m-5">
		<article class="container p-5 bg-light">
			<div class="p_detail_box">
				<div class="p_detail_label">상품코드</div>
				<div class="p_detail_data">${productVO.p_code}</div>
			</div>
			<div class="p_detail_box">
				<div class="p_detail_label">상품이름</div>
				<div class="p_detail_data">${productVO.p_name}</div>
			</div>
			<div class="p_detail_box">
				<div class="p_detail_label">판매가격</div>
				<div class="p_detail_data">${productVO.p_oprice}</div>
			</div>
			<div class="p_detail_box">
				<div class="p_detail_label">상품이미지</div>
				<div class="p_detail_data"><img width="300px" src="${rootPath}/upload/${productVO.p_file}"></div>
			</div>
		</article>
	</section>

<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>