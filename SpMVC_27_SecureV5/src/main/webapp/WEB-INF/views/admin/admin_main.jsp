<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>

<script>
$(function() {
	
	$(document).on("click", "#user_list", function() {
		$.get("${rootPath}/admin/user_list", function(result) {
			$("#admin_content").html(result)
		})
	})
	
	$(document).on("click", "tr.tr_user", function() {
		let username = $(this).data("id")
		$.get("${rootPath}/admin/user_detail_view/" + username, function(result) {
			$("#admin_content").html(result)
		})
	})
	
	$(document).on("click", "button#btn_save", function() {
		let formdata = $("form").serialize()
		$.post("${rootPath}/admin/user_detail_view", formdata, function(result) {
			$("#admin_content").html(result)
			alert("업데이트 성공")
		})
		
		
	})
	
	$(document).on("click", "#auth_append", function() {
		
		let auth_input = $("<input/>", {class : "form-control auth", name : "auth"})
		
		// auth_input.append($("<p/>", {text : '제거', class : "auth_delete"}))
		
		$("div#auth_box").append(auth_input)
	})	

})
</script>

<style>
.body {
	width: 100%;
	display: flex;
	margin-bottom: 180px;
}

menu {
	width: 300px;
	margin-right: 150px;
}

menu h3 {
	font-size: 2.2rem;
	margin-left: 40px;
	margin-bottom: 15px;
}

menu ul {
	padding: 5px;
}

menu ul li {
	list-style: none;
}

menu ul li a {
	display: block;
	color: white;
	font-size: 1.3rem;
	padding: 20px;
	margin: 3px;
}

menu ul li:hover {
	background-color: white;
}

menu ul li a:hover {
	text-decoration: none;
	color: black;
	
}

.body article {
	flex: 3;
	margin-top: 65px;
	margin-right: 150px;
}

</style>

</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="body">
	<menu>
		<h3 class="w3-text-amber">관리자 페이지</h3>
		<ul class="w3-amber">
			<li><a href="javascript:void(0)" id="user_list">User List</a></li>
			<li><a href="#">메뉴 1</a></li>
			<li><a href="#">메뉴 2</a></li>
		</ul>	
	</menu>
	<article id="admin_content">
	
	</article>
</section>
<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>