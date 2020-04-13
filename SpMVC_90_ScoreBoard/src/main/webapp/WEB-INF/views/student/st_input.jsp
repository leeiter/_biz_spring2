<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 홈페이지</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function() {
	
	$(document).on("click", "#btn-insert", function(){
		let num = $("#st_num")
		let name = $("#st_name")
		let grade = $("#st_class")
		let group = $("#st_group")
		if(num.val() == "") {
			alert("학번을 입력하세요.")
			num.focus()
			return false;
		}
		if(name.val() == "") {
			alert("이름을 입력하세요")
			name.focus()
			return false;
		}
		if(grade.val() == "") {
			alert("학년을 입력하세요.")
			grade.focus()
			return false;
		}
		if(group.val() == "") {
			alert("반을 입력하세요.")
			group.focus()
			return false;
		}
		$("form").submit()
	})
	
	$(document).on("blur", "#st_num", function() {
		
		let num = $(this).val()
		if(num == "") {
			$("#m_stnum").text("학번은 반드시 입력해야 합니다.")
			return false
			
		}
		
		
		$.ajax({
			beforeSend: function(){
				console.log("ajax")
			},
			url : "${rootPath}/student/numcheck",
			method : "GET",
			data : {st_num : num},
			success : function(result) {
				if(result == "EXISTS") {
					$("#m_stnum").text("이미 등록된 학번입니다.")
					$("#m_stnum").css("color","red")
					$("#st_num").focus()
					return false
				} else {
					$("#m_stnum").text("사용 가능한 학번 입니다.")
				}
				
			},

			
		})
		
		
	})
	
	
	
	
})


</script>
</head>
<body>
<form:form action="${rootPath}/student/insert" method="POST">

	<input id="st_num" name="st_num" value="${STUDENT.st_num}" placeholder="학번">
	<div class="message" id="m_stnum"></div>
	<input id="st_name" name="st_name" value="${STUDENT.st_name}" placeholder="이름">
	<input id="st_class" name="st_class" value="${STUDENT.st_class}" placeholder="학년">
	<input id="st_group" name="st_group" value="${STUDENT.st_group}" placeholder="반">
					
		<button type="button" id="btn-insert">등록하기</button>


</form:form>
</body>
</html>