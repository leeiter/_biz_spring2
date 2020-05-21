<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/include-head.jspf" %>
<script>
$(function() {
	
	// ajax를 호출하여 가져온 form에서는 사용하면 안되는 event
	// $("button.save").click(function() { })을 사용해야 한다.
	$(document).on("click", "button.save", function() {
		
		let p_code = $("#p_code").val()
		if(p_code == "") {
			$("div#p_code_error").html("<b>* 상품코드는 반드시 입력하세요.</b>")
			$("#p_code").focus()
			return false
		}
		$("form").submit()
		
	})
		
	$(document).on("blur", "#p_code", function() {
		
		// 상품코드는 중복되면 안되기 때문에
		// 중복된 상품코드를 미리 검사해 보자
		let p_code = $("#p_code").val()
		if(p_code == "") {
			$("div#p_code_error").html("<b>* 상품코드는 반드시 입력하세요.</b>")
			$("#p_code").focus()
			return false
		}
		
		$.ajax({
			url : "${rootPath}/product/code_check",
			data : {p_code : p_code},
			type : "GET",
			method : "GET",
			success : function(result) {
				if(result == "EXISTS") {
					$("div#p_code_error").html("이미 사용중인 코드 입니다.")
					$("#p_code").focus()
					return false
				} else if(result == "NONE") {
					$("div#p_code_error").html("<font color='blue'>사용 가능한 코드입니다.</font>")
				}
				$("form").submit()
			},
			error : function(x, error) {
				$("div#p_code_error").html("서버 통신 오류")
				
			}
		})
		/*
			상품코드 중복검사를 ajax로 실행한 후
			데이터를 서버로 submint을 수행할 떄는
			ajax가 끝난다음에 success 함수 내에서
			>>>>>
			
		*/
		// $("form").submit()
		
		
		
	})
	
})
</script>
</head>
<body>
<%@ include file="/WEB-INF/views/include/include-nav.jspf" %>

<section class="container body">
	<form:form modelAttribute="productVO">
		<fieldset>
			<legend>상품정보 등록</legend>
						<div id="p_code_error" class="text-danger">
						
			</div>
			
			<div class="form-group">
				<form:input path="p_code" class="form-control" placeholder="상품코드" />
			</div>

			<div class="form-group">
				<form:input path="p_name" class="form-control" placeholder="상품이름" />
			</div>
			<div class="form-group">
				<form:input path="p_oprice" class="form-control" placeholder="판매가격" />
			</div>
			<div class="button-group text-right">
				<button type="button" class="btn btn-primary save">저장</button>
				<button type="button" class="btn btn-success">상품정보 목록</button>
			</div>
		</fieldset>
	</form:form>
</section>

<%@ include file="/WEB-INF/views/include/include-footer.jspf" %>
</body>
</html>