package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.service.UserService;
import com.biz.sec.utils.PbeEncryptor;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security 기반
 * 회원가입 및 Email 인증 프로젝트
 * 메인 컨트롤러
 * 
 * @since 2020-04-20
 * @author leeiter
 */

@RequiredArgsConstructor
@SessionAttributes("userVO")
@RequestMapping(value = "/join")
@Controller
public class JoinController {
	
	private final UserService userService;
	
	/*
	 * @SessionAttributes(서버 가상머신에 저장) 사용 목적
	 * 일반적으로 사용할 때에는
	 * 첫화면에 담은 form 값을 두번째 화면에서도 hidden을 사용해서 계속해서 form의 값을 가져가야 한다.
	 * 결국 마지막 화면에서는 각 페이지의 값들이 통합되어 있는 상태로 되어 있다.
	 * 
	 * 그런데
	 * @SessionAttributes와 @ModelAttribute 그리고 form:form 을 사용한다면
	 * 각 페이지마다 hidden을 사용하지 않고 페이지마다 보여줄는 화면만 보여주게 되고
	 * @SessionAttributes와 @ModelAttribute가 서로 융합되어 값을 저장해놓고 담아주기 때문에
	 * 조금 더 간단한 코드를 진행할 수 있다. 
	 * 
	 * 하지만 문제는 중간에 뒤로가기를 한다면 input box에 값이 남아있는 경우가 생길 수도 있다.
	 */

	// 빈공간(Null Point)을 채울수 있는 생성자가 있어야 한다.
	@ModelAttribute("userVO") // sessionAttributes 와 같은 이름으로 만들어져야 한다.
	public UserDetailsVO newUser() {
		return new UserDetailsVO(); // 초기화
	}
	
	/**
	 * 회원가입하면 보여주기
	 * sessionAttributes 활용
	 * localhost:8080/sec/join : 회원가입 화면 보이기
	 * join/user : 회원가입 버튼 클릭 후
	 * join/email_ok : email 인증화면에서 email 보내기 후
	 *
	 * @since 2020-04-20
	 * 
	 * UPDATE : 2020-04-21
	 * localhost:8080/sec/join : 회원가입 화면 보이기
	 * join/join_next : 회원가입 버튼 클릭 후
	 * 		DB에 회원정보를 보여준 후 email 인증 화면 보이기
	 * join/join_last : email 인증 후 이후 처리
	 * 
	 * @param userVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String join(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		return "join/join"; // 첫 화면, 아무런 조치를 하지 않고 sessionA과 modelA만 지정
		// 대신 jsp 화면에는 form:input 이 꼭 있어야 한다.
	}
	
	/**
	 * @since 2020-04-21
	 * 최초 회원가입 화면에서 username과 password를 입력한 후
	 * 회원가입 버튼을 클릭하면
	 * userVO에 데이터를 받아서
	 * sessionAttributes에 설정된 저장소에 저장해 두고
	 * email 인증 화면 보여주기
	 * 
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value = "/join_next", method = RequestMethod.POST)
	public String join_next(@ModelAttribute("userVO") UserDetailsVO userVO) {
		return "join/join_email";
	}
	
	/**
	 * @since 2020-04-21
	 * Email 인증form에서 Email 보내기 버튼을 클릭했을 때
	 * userVO에 데이터를 받아서(email만)
	 * 
	 * sessionAttributes에 저장된 데이터와 통합(merge)하고
	 * DB에 저장한 후
	 * 인증 정보를 Email로 보내고
	 * 인증코드를 입력받는 화면을 다시 보여주기
	 * 이땐 JOIN변수에 EMAIL_OK 문자열을 실어서 보내고
	 * 화면에는 인증 코드 입력하는 란이 보이도록 설정
	 * 
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value = "/join_last", method = RequestMethod.POST)
	public String join_last(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		// String email_token = userService.insert_getToken(userVO);
		
		model.addAttribute("username", PbeEncryptor.getEncrypt(userVO.getUsername()));
		// model.addAttribute("My_Email_Secret", email_token);
		model.addAttribute("JOIN", "EMAIL_OK");
		return "join/join_email";
	}
	
	/**
	 * @since 2020-04-21
	 * 이메일 인증폼에서 인증키와 인증값을 받아서
	 * 인증처리
	 * 
	 * @param secret_key
	 * @param secret_value
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/email_token_check", method = RequestMethod.POST)
	public String email_token_check(
			@RequestParam("secret_id") String username, @RequestParam("secret_key") String secret_key, @RequestParam("secret_value") String secret_value) {		
		boolean bKey = userService.email_token_ok(username, secret_key, secret_value);
		if(bKey) return "OK";
		return "FAIL";
	}

	/**
	 * @since 2020-04-20
	 * 회원가입 화면에서 username, password 입력후
	 * 회원가입 버튼 클릭했을 때 호출
	 * 
	 * @param userVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		return "join/join_email"; // UserDeatailVO에 담긴 데이터가 담긴다.
	}
	
	/**
	 * @since 2020-04-20
	 * 회원가입에서 username, password 입력후
	 * email 보내기 화면으로 이동하기
	 *  
	 * @param userVO
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/joinok", method = RequestMethod.POST)
	public String joinOk(@ModelAttribute("userVO") UserDetailsVO userVO, SessionStatus session, Model model) {
		int ret = userService.insert(userVO);
		model.addAttribute("JOIN", "EMAIL_OK");
		
		// sessionAttribute에 저장된 session값을 clear 시키기
		// session.setComplete();
		return "join/join_email"; // 여기서는 userVO가 받는데 이전 두 화면에서 받은 데이터가 들어있다.
	}
		
	/**
	 * @since 2020-04-20
	 * 회원가입 중 email 보내기 화면
	 * email 보내기 후 재 전송화면으로
	 * 
	 * @param userVO
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/emailok", method = RequestMethod.GET)
	public String emailOk(@ModelAttribute("userVO") UserDetailsVO userVO, SessionStatus session, Model model) {
		boolean ret = userService.emailOk(userVO.getUsername(), userVO.getEmail());
		
		session.setComplete();
		if(ret) {
			return "redirect:/user/login";
		} else {
			// 2020-04-21 추가
			return "join/join_email_fail";
		}		
	}	
	
}
