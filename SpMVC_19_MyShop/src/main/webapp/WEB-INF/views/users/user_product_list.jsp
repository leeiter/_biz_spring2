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

<table class="col-md-4 col-12">
	<tr>
		<th>상품코드</th>
		<th>상품이름</th>
		<th>거래처</th>
		<th>품목</th>
		<th>매입가격</th>
		<th>판매가격</th>	
	</tr>
	<c:choose>
		<c:when test="${empty B2C_LIST}">
			<tr>
				<td colspan="6">상품정보가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="B2C" items="${B2C_LIST}" varStatus="i">
				<tr class="b2c_tr context-menu-one btn btn-naertal" data-id="${B2C.id}">
					<td>${B2C.p_code}</td>
					<td class="p_name">${B2C.p_name}</td>
					<td>${B2C.p_bcode}</td>
					<td>${B2C.p_dcode}</td>
					<td>${B2C.p_iprice}</td>
					<td>${B2C.p_oprice}</td>	
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>