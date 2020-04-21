<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
	table {
		width: 100%;
	}
	
	tr.tr_user {
		cursor: pointer;
	}
	th {
		color: white;
	}
	
	th, td {
		white-space: nowrap;
		padding: 5px;
	}
</style>

<table class="w3-table-all w3-centered">
	<tr class="w3-amber">
		<th>NO</th>
		<th>USERNAME</th>
		<th>EMAIL</th>
		<th>PHONE</th>
		<th>ADDRESS</th>
	</tr>
	<c:choose>
		<c:when test="${empty userList}">
			<tr><td colspan="5">USER 정보가 없습니다.</td></tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${userList}" var="user" varStatus="i">
				<tr data-id="${user.username}" class="tr_user">
					<td>${i.count}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
					<td>${user.address}</td>
				</tr>				 
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>