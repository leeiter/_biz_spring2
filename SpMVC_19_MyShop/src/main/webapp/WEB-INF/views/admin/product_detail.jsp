<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form action="${rootPath}/admin/product/input" modelAttribute="productVO">

	<div class="form-group">
		<form:textarea path="p_detail" class="form-control" rows="" cols="" placeholder="상세정보" />
	</div>
			
	<div class="form-group">
		<button>저장</button>
	</div>

</form:form>
