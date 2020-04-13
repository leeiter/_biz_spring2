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
$(function(){
	$("button").click(function(){
		
		let btn_id = $(this).attr("id")
		let url = "${rootPath}/student/"
		
		if(btn_id == "btn-update") {
			url += "update/${STUDNET.st_num}"
		} else if(btn_id == "btn-delete") {
			if(!confirm("학생정보를 삭제할까요?")) {
				return false
			}
			url += "delete/${STUDNET.st_num}"
		} else if(btn_id == "btn-list") {
			url += "list"
		}
		document.location.href = url
	})
	
})
</script>
</head>
<body>
<article>
	<div class="item-box">
		<div class="title-box rb-view">학번</div>
		<div class="rb-view">${STUDNET.st_num}</div>
	</div>
	
		<div class="item-box">
		<div class="title-box rb-view">이름</div>
		<div class="rb-view">${STUDNET.st_name}</div>
	</div>
	
		<div class="item-box">
		<div class="title-box rb-view">학년</div>
		<div class="rb-view">${STUDNET.st_class}</div>
	</div>
	
		<div class="item-box">
		<div class="title-box rb-view">반</div>
		<div class="rb-view">${STUDNET.st_group}</div>
	</div>

</article>

<article class="text-center item-btn">
	<button id="btn-update" type="button" class="btn btn-success">수정</button>
	<button id="btn-delete" type="button" class="btn btn-danger">삭제</button>
	<button id="btn-list" type="button" class="btn btn-info">전체리스트</button>
</article>

</body>
</html>