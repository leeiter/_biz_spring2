package com.biz.shop.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz.shop.domain.Authorities;
import com.biz.shop.domain.ProductVO;

/*
 * Hibernation에서 기본적으로 제공하는 CRUD를 사용하기 위해서
 * JpaRepository를 상속받은데
 * 		<사용할VO, PK의 Tpye>을 Generic으로 지정한다.
 * 
 * PK type에서 primitive 형식으로 지정하지 말고
 * wrapper class 형식으로 지정하자
 * 
 * ============================================
 * primitive		wrapper class
 * --------------------------------------------
 * int				Integer (타입형변수, 참조형변수)
 * float 			Float
 * long 			Long
 * double 			DoubleW
 * char 			Character
 * 문자열 			String
 * boolean 			Boolean
 * ============================================
 * int : 값으로 무언가를 처리
 * 참조형 변수 : 어떤 method에 매개변수로 보내고
 * 		값이 변동 되었을 때 원래 값, 변동 될 수 있는 class로 만든 변수
 * 		배열로 선언한 변수
 */
public interface ProductRepository extends JpaRepository<ProductVO, Long> {
	
	// C(R)UD 의 기본 method가 준비되어 있다.

}
