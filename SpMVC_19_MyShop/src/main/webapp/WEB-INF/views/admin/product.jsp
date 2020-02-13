<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="container-fluid row">
	<article class="col-7 bg-light pro-input">
		<form>
			<div class=" container-fluid form-group row">
				<select class="custom-select-sm col-6">
					<option>공산품</option>
					<option>농산물</option>
					<option>수산물</option>
				</select>
				<select class="custom-select-sm col-6">
					<option>대덕물산</option>
					<option>삼성농산</option>
					<option>목포수산</option>
				</select>
			</div>
			
			<div class="form-group">
				<input class="form-control" placeholder="상품코드">
			</div>
			
			<div class="form-group">
				<input class="form-control" placeholder="상품이름">
			</div>
			
			<div class=" container-fluid form-group row">
				<input class="form-control col-6" placeholder="매입단가">
				<input class="form-control col-6" placeholder="판매단가">
			</div>
			
			<div class="form-group">
				<textarea class="form-control" rows="" cols="" placeholder="상세정보"></textarea>
			</div>
		</form>
	</article>
	
	<article class="col-3 bg-primary pro-list">
	
	</article>
</section>