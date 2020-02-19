<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="col-md-4 col-12">
	<tr>
		<th>상품코드</th><td>${productVO.p_code}</td>
		<th>상품이름</th><td>${productVO.p_name}</td>

		<th>품목</th><td>${productVO.p_dcode}</td>

		<th>판매가격</th><td>${productVO.p_oprice}</td>	
	</tr>
	
	<button>장바구니 담기</button>
</table>