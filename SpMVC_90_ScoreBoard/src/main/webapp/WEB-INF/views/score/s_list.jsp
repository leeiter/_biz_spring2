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
		document.location.href = "${rootPath}/score/insert"		
	})
	
	$(".scList tr").click(function() {
		let s_subject = $(this).attr("data-id")
		document.location.href = "${rootPath}/score/view/" + s_subject
	})

})
</script>
</head>
<body>
	<section class="container-fluid">
		<article>
			<table class="table table-striped table-hover scList">
				<tr>
					<th>학번</th>
					<th>과목</th>
					<th>점수</th>
				</tr>
				<c:choose>
					<c:when test="${empty SCORE_LIST}">
						<tr>
							<td colspan="4">점수 정보가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${SCORE_LIST}" var="SCORE">
							<tr data-id="${SCORE.s_subject}">
								<td>${SCORE.s_num}</td>
								<td>${SCORE.s_subject}</td>
								<td>${SCORE.s_score}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>

			</table>
		</article>
		<article class="d-flex justify-content-end">
			<button class="btn btn-primary text-white btn-writer">학생점수등록</button>		
		</article>
	</section>
</body>
</html>