<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	
	$(document).on("click", "button.btn-writer", function(){
		document.location.href = "${rootPath}/student/insert"		
	})
	
	$(".stdList tr").click(function() {
		let st_num = $(this).attr("data-id")
		document.location.href = "${rootPath}/student/view/" + st_num
	})
	
	$(document).on("click", "button.btn-score", function(){
		document.location.href = "${rootPath}/score/list"		
	})
	

})
</script>
</head>
<body>
	<section class="container-fluid">
		<article>
			<table class="table table-striped table-hover stdList">
				<tr>
					<th>학번</th>
					<th>이름</th>
					<th>학년</th>
					<th>반</th>
				</tr>
				<c:choose>
					<c:when test="${empty STUDENT_LIST}">
						<tr>
							<td colspan="4">학생정보가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${STUDENT_LIST}" var="STUDENT">
							<tr data-id="${STUDENT.st_num}">
								<td>${STUDENT.st_num}</td>
								<td>${STUDENT.st_name}</td>
								<td>${STUDENT.st_class}</td>
								<td>${STUDENT.st_group}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</table>
		</article>
		<article class="d-flex justify-content-end">
			<button class="btn btn-primary text-white btn-writer">학생등록</button>
			<button class="btn btn-primary text-white btn-score">점수보러가기</button>		
		</article>
	</section>
</body>
</html>