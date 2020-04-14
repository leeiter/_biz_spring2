<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 홈페이지</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>



  
            <h2>${userVO.username}</h2>


          <p><i class="fa fa-home fa-fw w3-large w3-text-teal"></i>${userVO.address}</p>
          <p><i class="fa fa-envelope fa-fw  w3-large w3-text-teal"></i>${userVO.email}</p>
          <p><i class="fa fa-phone fa-fw w3-large w3-text-teal"></i>${userVO.phone}</p>
          <hr>





</body>
</html>