<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<section class="container-fluid mt-5 row">
	<article class="col-7 col-md-7 col-12 bg-light pro-input">
		<c:choose>
			<c:when test="${PRO_BODY == 'DETAIL'}">
				<%@ include file="/WEB-INF/views/admin/product_detail.jsp" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/WEB-INF/views/admin/product_input.jsp" %>
			</c:otherwise>
		</c:choose>
	</article>
	
	<article class="col-4 col-md-4 col-12 bg-primary list-body">
		<%@ include file="/WEB-INF/views/admin/product_list.jsp" %>
	</article>
</section>