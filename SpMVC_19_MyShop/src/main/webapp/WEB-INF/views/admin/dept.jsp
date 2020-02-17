<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<style>
.in-errors {
	color: red;
	font-size: 8px;
}

/*
	col-md-7 col-12
	해상도가 768보다 크면 7칸만 차지하고
	그 이하이면 12칸을 차지하여 풀 width로 보여라
*/

tr, th, td {
	white-space: nowrap;
}

.dept-list {
	overflow: auto;
}

td.p_name {
	width: 150px;
	display: inline-block;
	padding: 0 5px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>

<section class="container-fluid mt-5 row">
	<article class="col-7 col-md-7 col-12 bg-light dept-input">
		<form:form action="${rootPath}/admin/dept/input" modelAttribute="deptVO">			
			<div class="form-group">
				<form:input path="d_code" class="form-control" placeholder="거래처코드" />
				<form:errors path="d_code" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_name" class="form-control" placeholder="거래처명" />
				<form:errors path="d_name" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_ceo" class="form-control" placeholder="거래처 대표명" />
			</div>
			
			<div class="form-group">
				<form:input path="d_sid" class="form-control" placeholder="거래처 사업자번호" />
			</div>
			
			<div class="form-group">
				<form:input path="d_tel" class="form-control" placeholder="거래처 대표전화" />
			</div>
			
			<div class="form-group">
				<form:input path="d_addr" class="form-control" placeholder="거래처 주소" />
			</div>
			
			<div class="form-group">
				<form:input path="d_manager" class="form-control" placeholder="거래처 담당자" />
			</div>
			
			<div class="form-group">
				<form:input path="d_mtel" class="form-control" placeholder="거래처 담당자 전화번호" />
			</div>
			
			<div class="form-group">
				<form:textarea path="d_rem" class="form-control" rows="" cols="" placeholder="비고" />
			</div>
			
			<div class="form-group">
				<button>저장</button>
			</div>
		</form:form>
	</article>
	
	<article class="col-4 col-md-4 col-12 bg-primary dept-list">
		<%@ include file="/WEB-INF/views/admin/dept_list.jsp" %>
	</article>
	
</section>
