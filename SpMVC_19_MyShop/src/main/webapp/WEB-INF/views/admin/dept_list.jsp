<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
$(function() {
	
	/*
		클래스 pro_tr은 본문의 tr tag에 설정이 되어 있고
		본문의 tr tag를 클릭하면 이벤트가 발생하고
		함수가 실행된다.
		
		이때 tr tag가 갖는 모든 정보는
		this라는 내장 객체에 담겨 함수내부로 전달된다.
	*/
	
	/*
		tr tag가 클릭되면 id 값을 추출하고
		update method로 전달하기
	*/
	$(".dept_tr").click(function() {
		let id = $(this).data("id") // attr("data-id")
		let c = $(this).attr("class")
		
		// document.location.href = "${rootPath}/admin/product/update?id=" + id
		document.location.href = "${rootPath}/admin/dept/update/" + id
		
	
	})
	
})

</script>

<table class="col-md-4 col-12">
	<tr>
		<th>ID</th>
		<th>거래처코드</th>
		<th>거래처명</th>
		<th>대표자명</th>
		<th>사업자번호</th>
		<th>대표전화</th>
		<th>주소</th>
		<th>담당자</th>
		<th>담당자 전화번호</th>
		<th>비고</th>	
	</tr>
	<c:choose>
		<c:when test="${empty DEPT_LIST}">
			<tr>
				<td colspan="10">상품정보가 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="DEPT" items="${DEPT_LIST}" varStatus="i">
				<tr class="dept_tr" data-id="${DEPT.id}">
					<td>${DEPT.d_code}</td>
					<td>${DEPT.d_name}</td>
					<td>${DEPT.d_ceo}</td>
					<td>${DEPT.d_sid}</td>
					<td>${DEPT.d_tel}</td>
					<td>${DEPT.d_addr}</td>
					<td>${DEPT.d_manager}</td>
					<td>${DEPT.d_mtel}</td>
					<td>${DEPT.d_rem}</td>	
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>