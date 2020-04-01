<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
	span {
		color: blue;
		font-weight: bold;
		font-style: italic;
	}
</style>

<h3>사용자 ID : <span>${userVO.userId}</span></h3>
<h3>사용자 PW : <span>${userVO.password}</span></h3>
<h3>사용자 Name : <span>${userVO.userName}</span></h3>
<h3>사용자 Rolle : <span>${userVO.rolle}</span></h3>
