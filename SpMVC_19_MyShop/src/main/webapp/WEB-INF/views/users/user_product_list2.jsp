<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function(){
	
	$(".b2c_tr").click(function(){
		let id = $(this).data("id")
		document.location.href = "${rootPath}/user/product/detail/" + id
	})
		
})
</script>

<style>
.container {
	margin: 50px auto;
}

.b2c_list {
	position:relative;
	padding: 20px;
	border-bottom: 1px solid gray;
}

.b2c_item {
	position: absolute;
	top: 55px;
	left: 200px;
}

strong {
	font-size: 1rem;
	font-weight: normal;
}

span {
	font-size: 0.9rem;
	color: gray;
	margin-left: 50px;
}

p {
	font-size: 1.1rem;
	font-weight: bold;
	margin-top: 5px;
	margin-left: 5px;
}

.form-control {
	margin-bottom: 20px;
}
</style>

<div class="container">
	<c:choose>
		<c:when test="${empty B2C_LIST}">
			<div>
				<p>상품정보가 없습니다.</p>
			</div>
		</c:when>
		<c:otherwise>
			<form>
				<input type="search" name="search" value="${search}" class="form-control" 
						placeholder="검색어를 입력한 후 Enter...">
			</form>
			<c:forEach var="B2C" items="${B2C_LIST}" varStatus="i">
				<div class="b2c_tr b2c_list" data-id="${B2C.id}">
					<img alt="상품" src="${rootPath}/resources/image/img_01.jpg" width="130px" height="130px">
					<div class="b2c_item">
						<strong>${B2C.p_name}</strong>
						<span>현재 상품이미지 준비중입니다.</span>
						<p>${B2C.p_oprice} 원</p>
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>
