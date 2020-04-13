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
		let num = $("#s_num")
		let subject = $("#s_subject")
		let score = $("#s_score")
		if(num.val() == "") {
			alert("학번을 입력하세요.")
			num.focus()
			return false;
		}
		if(subject.val() == "") {
			alert("과목을 입력하세요")
			subject.focus()
			return false;
		}
		if(score.val() == "") {
			alert("점수을 입력하세요.")
			score.focus()
			return false;
		}
		$("form").submit()
	})
	
	/*
	$(document).on("blur", "#s_num", function() {
		
		let num = $(this).val()
		if(num == "") {
			$("#m_snum").text("학번은 반드시 입력해야 합니다.")
			return false
			
		}
		
		
		$.ajax({
			beforeSend: function(){
				console.log("ajax")
			},
			url : "${rootPath}/score/numsubcheck",
			method : "GET",
			data : {s_num : num},
			success : function(result) {
				if(result == "EXISTS") {
					$("#m_snum").text("이미 등록된 학번과 과목입니다.")
					$("#m_snum").css("color","red")
					$("#s_num").focus()
					return false
				} else {
					$("#m_snum").text("사용 가능한 학번입니다.")
				}
				
			},

			
		})
		
		
	})
	*/
	
	
	
})


</script>
</head>
<body>
<form:form action="${rootPath}/score/insert" method="POST">

	<input id="s_num" name="s_num" value="${SCORE.s_num}" placeholder="학번">
	<input id="s_subject" name="s_subject" value="${SCORE.s_subject}" placeholder="과목">
	<div class="message" id="m_snum"></div>
	<input id="s_score" name="s_score" value="${SCORE.s_score}" placeholder="점수">
					
		<button type="button" id="btn-insert">등록하기</button>


</form:form>
</body>
</html>