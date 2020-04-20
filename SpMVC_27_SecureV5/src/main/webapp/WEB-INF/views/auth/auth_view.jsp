<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<style>
body {
	position: fixed;
	top: 60px;
	left: 0;
	width: 100%;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>
<section id="body">
	<h1>로그인 동적 정보 보기</h1>
	<sec:authorize access="isAnonymous()">
		<h3>로그인 하지 않았을 때 보이는 정보</h3>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<h3>로그인 했을 때 보이는 정보</h3>
		<p>
			Principal : 
			<sec:authentication property="principal"/>
		</p>
		
		<p>
			로그인 UserName : 
			<sec:authentication property="principal.username"/>
		</p>
		
		<p>
			로그인 Password : 
			<sec:authentication property="principal.password"/>
		</p>
		
		<p>
			로그인 E-mail : 
			<sec:authentication property="principal.email"/>
		</p>
		
		<p>
			로그인 Phone : 
			<sec:authentication property="principal.phone"/>
		</p>
		
		<p>
			로그인 Address : 
			<sec:authentication property="principal.address"/>
		</p>
		
		<p>
			권한 리스트 : 
			<sec:authentication property="principal.authorities"/>
		</p>
		
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h3>ADMIN으로 로그인 했을 때 보이는 정보</h3>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ROLE_USER')">
		<h3>USER으로 로그인 했을 때 보이는 정보</h3>
	</sec:authorize>
	
	
	<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
		<h3>ADMIN이나 USER으로 로그인 했을 때 보이는 정보</h3>
	</sec:authorize>

</section>
</body>
</html>