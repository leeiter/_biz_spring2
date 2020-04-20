package com.biz.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String join(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		return "join/join"; // 첫 화면, 아무런 조치를 하지 않고 sessionA과 modelA만 지정
		// 대신 jsp 화면에는 form:input 이 꼭 있어야 한다.
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@ModelAttribute("userVO") UserDetailsVO userVO, Model model) {
		return "join/join_email"; // UserDeatailVO에 담긴 데이터가 담긴다.
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinok", method = RequestMethod.POST)
	public String joinOk(@ModelAttribute("userVO") UserDetailsVO userVO, SessionStatus session, Model model) {
		
		int ret = userService.insert(userVO);
		
//		String ret = userService.insert(userVO);
		
		model.addAttribute("JOIN", "EMAIL_OK");
		
		// sessionAttribute에 저장된 session값을 clear 시키기
//		session.setComplete();
		return "join/join_email"; // 여기서는 userVO가 받는데 이전 두 화면에서 받은 데이터가 들어있다.
		
		
		// return ret; 
		// return userVO;
	}
	
	
//	@ResponseBody
	@RequestMapping(value = "/emailok", method = RequestMethod.GET)
	public String emailOk(@ModelAttribute("userVO") UserDetailsVO userVO, SessionStatus session, Model model) {
		
		boolean ret = userService.emailOk(userVO.getUsername(), userVO.getEmail());
		
		session.setComplete();
		if(ret) {
			return "redirect:/user/login";
		} else {
			return "join/join_email_fail";
		}
//		return PbeEncryptor.getDecrypt(username) + PbeEncryptor.getDecrypt(email);
		
	}	
	
	
	
	
}
