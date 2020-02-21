<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	height: 100%;
}
.detail-li {
	list-style: none;
}
.product-container-box {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}
.img-box {
	border: 1px solid black;
	width: 300px;
	height: 270px;
	margin: 3rem;
}
.btn-box {
	display: flex;
	justify-content: center;
	align-items: center;
}
#p-button{
	margin: 3px;
	width: 200px;
}
.producthtag{
	font-weight: bold;
}

p{
	white-space: pre-line;
}
</style>

	<div class="product-container-box">

		<div class="img-box">
			<img>
		</div>

		<div class="product-item-box">
			<div class="product-name">
				<h2 class="producthtag">${pVO.p_name}</h2>
				<hr />
			</div>
			<table>
				<cation>
				<tbody>
					<tr>
						<th>판매가</th>
						<td>${pVO.p_oprice}원</td>
					</tr>
					<tr>
						<th>상품코드</th>
						<td>${pVO.p_code}</td>
					</tr>
					<tr>
						<th>제조사/공급사</th>
						<td>${pVO.p_d_code}</td>
					</tr>
					<tr>
						<th>구매수량</th>
						<td><input type="number"> <a href="#a">증가</a> <a
							href="#a">감소</a></td>
					</tr>
				</tbody>
				</cation>
			</table>
			<hr/>
			<div class="btn-box">
				<button class="btn btn-primary" id="p-button">장바구니</button>
				<button class="btn btn-primary" id="p-button">바로구매</button>
			</div>
		</div>
	</div>
	
	
	
	<div class="container">
		<hr/>
		<p>
		일반사면을 명하려면 국회의 동의를 얻어야 한다. 대통령은 헌법과 법률이 정하는 바에 의하여 공무원을 임면한다. 모든 국민은 법률이 정하는 바에 의하여 선거권을 가진다.

제2항과 제3항의 처분에 대하여는 법원에 제소할 수 없다. 대통령은 조약을 체결·비준하고, 외교사절을 신임·접수 또는 파견하며, 선전포고와 강화를 한다.

제2항의 재판관중 3인은 국회에서 선출하는 자를, 3인은 대법원장이 지명하는 자를 임명한다. 국가는 국민 모두의 생산 및 생활의 기반이 되는 국토의 효율적이고 
균형있는 이용·개발과 보전을 위하여 법률이 정하는 바에 의하여 그에 관한 필요한 제한과 의무를 과할 수 있다.

국방상 또는 국민경제상 긴절한 필요로 인하여 법률이 정하는 경우를 제외하고는, 사영기업을 국유 또는 공유로 이전하거나 그 경영을 통제 또는 관리할 수 없다.

국교는 인정되지 아니하며, 종교와 정치는 분리된다. 모든 국민의 재산권은 보장된다. 그 내용과 한계는 법률로 정한다. 근로조건의 기준은 인간의 존엄성을 
보장하도록 법률로 정한다.


제안된 헌법개정안은 대통령이 20일 이상의 기간 이를 공고하여야 한다. 근로자는 근로조건의 향상을 위하여 자주적인 단결권·단체교섭권 및 단체행동권을 가진다.

모든 국민은 법률이 정하는 바에 의하여 납세의 의무를 진다. 법원은 최고법원인 대법원과 각급법원으로 조직된다. 모든 국민은 양심의 자유를 가진다.

국회의원은 법률이 정하는 직을 겸할 수 없다. 국무총리는 국무위원의 해임을 대통령에게 건의할 수 있다. 정당은 그 목적·조직과 활동이 민주적이어야 하며, 
국민의 정치적 의사형성에 참여하는데 필요한 조직을 가져야 한다.

국가는 농지에 관하여 경자유전의 원칙이 달성될 수 있도록 노력하여야 하며, 농지의 소작제도는 금지된다. 국회의원과 정부는 법률안을 제출할 수 있다.

대통령은 제3항과 제4항의 사유를 지체없이 공포하여야 한다. 탄핵결정은 공직으로부터 파면함에 그친다. 그러나, 이에 의하여 민사상이나 형사상의 책임이 면제되지는 아니한다.
		</p>
	</div>