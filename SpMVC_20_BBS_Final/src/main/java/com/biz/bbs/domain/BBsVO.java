package com.biz.bbs.domain;

import org.apache.ibatis.type.Alias;

/*
 * VO(Value Object) = DTO(Data Transfer Object) 클래스
 * 메서드와 메서드 간에
 * web 브라우저와 컨트롤러(메서드) 간에
 * 컨트롤러와 view.jsp 간에 데이터를 수행
 * 		교환하는 매개체 역할을 수행
 * 
 * 일반적으로 select되는 table의 칼럼을 포함하고
 * web 브라우저에서 form tag를 사용하여
 * 		컨트롤러로 데이터를 보낼 때
 * 		form tag가 포함하는 input tag들의 name값들을 포함한다.
 * 
 * VO 클래스는
 * 객체지향 특징 중 추상화, 정보은닉, 캡슐화 특징을 포함하고 있다.
 * 추상화 : 어떤 필드변수들을 만들 것인가
 * 정보은닉 : 필드변수를 private으로 선언
 * 캡슐화 : getter, setter 메서드의 코드 정의
 * 		예) b_date_time 필드변수에는
 * 			2020-02-26 14:06:00 형태의 문자열만 저장하도록
 * 			setter() 메서드에 관련 코드를 정의 할 수 있다
 * 			또는
 * 			나이를 저장하는 필드변수 같으면 나이의 범위를 0 ~ 100까지
 * 			제한하는 등의 코드를 setter 메서드에 정의 할수 있다.
 * 
 * 		성적처리를 수행하다 라면
 * 			총점을 참조하는 getter 메서드를 정의하고
 * 			여기에 과목의 총합계를 계산하는 코드를 추가한 후
 * 			결과값을 return하도록 만들 수 있다.
 * 
 * 		캡슐화(투명성)
 * 			총점 getter 메서드의 내부에 어떤 코드가 있는지 몰라도
 * 			getter 메서드만 호출하면 총점을 알 수 있다 라는 개념
 */

/*
 * mybatis @Alias()
 * mybatis 초기 설정에서 typeAliasPackage를 설정하면
 * VO 클래스의 이름을 참조하여 Alias를 만드는데
 * Alias 이름을 명확하게 정의하고자 할때 사용한다.
 */
@Alias("bbsVO")
public class BBsVO {
	
	private long b_id; 				//	NUMBER
	private long b_p_id; 			//	NUMBER
	private String b_date_time; 	//	VARCHAR2(30)
	private String b_writer; 		//	VARCHAR2(30)
	private String b_subject; 		//	VARCHAR2(125)
	private String b_content; 		//	VARCHAR2(2000)
	private String b_file; 			//	VARCHAR2(125)
		
}
