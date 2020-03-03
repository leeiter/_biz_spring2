<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.container {
	margin: 50px auto;
}

.b2c_list {
	position:relative;
	padding: 40px;
	border-bottom: 1px solid gray;
}

.b2c_item {
	position: absolute;
	top: 40px;
	right: 50px;
	width: 480px;
}
.btn_list {
	position: absolute;
	bottom: 30px;
	right: 80px;
}

strong {
	font-size: 1.4rem;
	display: block;
	padding: 20px 5px;
	border-bottom: 1px solid gray;
}

span {
	display: block;
	font-size: 0.9rem;
	color: gray;
	padding: 20px 5px;
	border-bottom: 1px solid gray;
}

p {
	display: block;
	font-size: 1.1rem;
	font-weight: bold;
	padding: 20px 5px;
	border-bottom: 1px solid gray;
}

.btn_buy {
	margin-left: 20px; 
}

.btn {
	width: 200px;
}

.p_dis {
	font-weight: normal;
	font-size: 0.9rem;
}
</style>

<div class="container">
	<div class="b2c_list">
		<img alt="상품" src="${rootPath}/resources/image/img_01.jpg" width="500px" height="300px">
		<div class="b2c_item">
			<strong>${productVO.p_name}</strong><br>
			<span>현재 상품이미지 준비중입니다.</span><br>
			<p>${productVO.p_oprice} 원</p>
		</div>
		<div class="btn_list">
			<button type="button" class="btn btn-primary">장바구니 담기</button>
			<button type="button" class="btn btn-success btn_buy">바로 구매</button>
		</div>
	</div>
	<div>
		<p class="p_dis">이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		 이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		  이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		   이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		    이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		     이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		      이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....
		       이 물건은 1990년도 광주광역시 북구 한국경영원에서 시작이 되었으며 ....</p>
	</div>
</div>