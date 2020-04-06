package com.biz.socket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.biz.socket.domain.MessageVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 서버 재시작
public class ChatController extends TextWebSocketHandler {

	List<WebSocketSession> sessionList;
	Map<String, MessageVO> messageMap;
	
	public ChatController() {
		sessionList = new ArrayList<WebSocketSession>();
		messageMap = new HashMap<String, MessageVO>();
	}

	// nodejs LifeCycle
	// spring pre, post
	
	// LifeCycle code 사용시에는 method에 맞는 코드를 작성을 잘 해줘야 한다.
	// 그렇지 않으면 아무리 좋은 코드라고 해도 잘못 작성이 되었으면 작동을 아예 하지 않는다.
	
	
	// 열기
	// open 이되고 나면 Established 작동
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
		sessionList.add(session);
		
		MessageVO mVO = MessageVO.builder()
				.wSession(session)
				.build();
		
		messageMap.put(session.getId(), mVO);
	}
	
	// 닫기
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		sessionList.remove(session);
	}

	// 수신
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		String user[] = message.getPayload().split(":");
		
		// jackson Bind 클래스인 ObjeceMapper를 사용하여
				// VO 클래스를 Json형 문자열로 바로 변환시키기
		ObjectMapper objMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<String, String>();
		
		if(user.length > 1) {
			if(user[0].equalsIgnoreCase("USERNAME")) {
				MessageVO mVO = messageMap.get(session.getId());
				mVO.setUserName(user[1]);
				
				// Map<String, String> map = new HashMap<String, String>();
				map.put("msg", "userName");
				map.put("userName", mVO.getUserName());
				
				// ObjectMapper obj = new ObjectMapper();
				session.sendMessage(new TextMessage(objMapper.writeValueAsString(map)));
				
				// String sendUserName = String.format("{msg : 'userName', userName : %s}", mVO.getUserName());
				// session.sendMessage(new TextMessage(sendUserName));
				return ;
			}
			
		}
		// TODO Auto-generated method stub
		
		if(message.getPayload().equalsIgnoreCase("GETUSERLIST")) {
			String userList = objMapper.writeValueAsString(messageMap);
			map.put("msg", "userList");
			map.put("userList", userList);
			
			String userListMap = objMapper.writeValueAsString(map);
			
			this.sendMessage(session, userListMap);
		}
		
		Gson gson = new Gson();
		/*
		 * Gson을 사용하여 문자열형 JSON 데이터를 VO로 변환
		 */
		MessageVO messageVO = gson.fromJson(message.getPayload(), MessageVO.class);
		
		String sendMessage = String.format("%s 로부터 : %s", messageVO.getUserName(), messageVO.getMessage());
		
		// TextMessage sendTextMessage = new TextMessage(sendMessage);
		
		
		
		String jsonTextMessage = objMapper.writeValueAsString(messageVO);
		// TextMessage sendTextMessage = new TextMessage(jsonTextMessage);
		
		this.sendMessage(session, jsonTextMessage);
		

	}
	
	private void sendMessage(WebSocketSession session, String textMessage) throws IOException {
		
		TextMessage sendMessage = new TextMessage(textMessage);
		
		for(WebSocketSession ws : sessionList) {
			// 자신이 보낸 메시지는 다시 자신에게 보낼 필요는 없기 때문에
			// 자신이 보낸 메시지를 제외하고 전송
			if(!ws.getId().equals(session.getId())) {
				ws.sendMessage(sendMessage);
				
			}
				
			
		}
	}
	
}
