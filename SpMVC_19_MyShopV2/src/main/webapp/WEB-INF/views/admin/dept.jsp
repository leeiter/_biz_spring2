<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

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
				<form:errors path="d_ceo" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_sid" class="form-control" placeholder="거래처 사업자번호" />
				<form:errors path="d_sid" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_tel" class="form-control" placeholder="거래처 대표전화" />
				<form:errors path="d_tel" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_addr" class="form-control" placeholder="거래처 주소" />
				<form:errors path="d_addr" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_manager" class="form-control" placeholder="거래처 담당자" />
				<form:errors path="d_manager" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_mtel" class="form-control" placeholder="거래처 담당자 전화번호" />
				<form:errors path="d_mtel" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<form:input path="d_rem" class="form-control" placeholder="비고" />
				<form:errors path="d_rem" class="in-errors"/>
			</div>
			
			<div class="form-group">
				<button>저장</button>
			</div>
		</form:form>
	</article>
	
	<article class="col-4 col-md-4 col-12 bg-primary list-body">
		<%@ include file="/WEB-INF/views/admin/dept_list.jsp" %>
	</article>
	
</section>
