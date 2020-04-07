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

/*
 * LifeCycle Method
 * 어떤 동작이 수행되는 과정에서 자동으로 호출되는 method들
 * 
 * LifeCycle Method를 잘구현하므로써
 * 별도 어떤 동작에 해당하는 복잡한 코드를 만들지 않아도
 * 충분한 효과를 발휘할 수 있다.
 * 
 * afterConnectionEstablished
 * 서버와 클라이언트가 서로 Socket으로 연결되었을 때 호출되는 method
 * session을 별도로 저장하거나, 하는 일들을 코딩
 * 
 * handleTextMessage
 * 클라이언트에서 메시지를 보내면 메시지를 수신하고
 * 연산코드를 수행한 후
 * 그 결과를 다시 client에게 보내는 코딩
 * nodejs등 다른 서버에서는
 * 메시지별로 별도로 method를 독립적으로 작성하기도 한다.
 * socket.on("메시지", ) >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 * 
 * afterConnectionClosed
 * client와 연결이 정상, 비정상적으로 종료되었을 때
 * 수행할 코딩
 * 
 * 
 */

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
		
		// 
		/*
		 * 최초 어떤 사용자가 접속하면
		 * 사용자에 대한 메시지 정보를 담을 변수 초기화
		 * 
		 * session id를 key값으로 하는 비어있는 사용자 정보는 map에 저장
		 */
		sessionList.add(session);
		
		
		MessageVO mVO = new MessageVO();
		
		messageMap.put(session.getId(), mVO);
	}
	
	// 닫기
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
		sessionList.remove(session);
		messageMap.remove(session.getId());
	}

	// 수신
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		// jackson Bind 클래스인 ObjeceMapper를 사용하여
		// VO 클래스를 Json형 문자열로 바로 변환시키기
ObjectMapper objMapper = new ObjectMapper();
Map<String, String> map = new HashMap<String, String>();



// 여기서부터는 임의의 command protocol을 선언
		// 전달받은 메시지에 command가 포함되어 있는가를 구분하는 코드
		String user[] = message.getPayload().split(":");
		

		// userName:값 또는 getUserList:값 이런 식의 메시지가 전달되었으면?
		if(user.length > 1) {
			// command가 USERNAME 이면
			// 채팅어플에 접속했을 때 최초 사용자 이름을 입력하면
			// 사용자 이름과 session을 정보에 저장한 후
			// 다시 client에게 알려주는 부분
			if(user[0].equalsIgnoreCase("USERNAME")) {
				MessageVO mVO = messageMap.get(session.getId());
				mVO.setUserName(user[1]);

				map.put("msg", "userName");
				map.put("userName", mVO.getUserName());
				
				String userName = objMapper.writeValueAsString(map);
				this.sendMeMessage(session, userName);
				return ;
				
				// command가 GETUSERLIST 이면
				// 현재 접속자 정보를 모두 client로 보내기
			} else if(user[0].equalsIgnoreCase("GETUSERLIST")) {
				log.debug("GETUSERLIST");
				String userList = objMapper.writeValueAsString(messageMap);
				map.put("msg", "userList");
				map.put("userList", userList);
				
				String userListMap = objMapper.writeValueAsString(map);
				
				log.debug(userListMap);
				
				/*
				 * 
				 * {msg:''userList', 'userList':userList}
				 * {userList:{session:{userName : '홍길동'}}}
				 * 배열과 비슷 
				 * 10:04:54.504 [http-nio-8080-exec-5] DEBUG com.biz.socket.controller.ChatController - 
				 * {
				 * "msg":"userList",
				 * "userList":"{\"t2ilt5g4\":{\"userName\":\"홍\",\"message\":null,\"sendUser\":null}}"}
				 */
				
				this.sendAllMessage(session, userListMap);
				
				return ;
			}
		}
		// TODO Auto-generated method stub
		

		// 채팅이 진행되는 과정에서 메시지 전파
		Gson gson = new Gson();
		/*
		 * Gson을 사용하여 문자열형 JSON 데이터를 VO로 변환
		 */
		MessageVO messageVO = gson.fromJson(message.getPayload(), MessageVO.class);
		
		String sendMessage = String.format("%s 로부터 : %s", messageVO.getUserName(), messageVO.getMessage());

		String jsonTextMessage = objMapper.writeValueAsString(messageVO);

		if(messageVO.getToUser().equalsIgnoreCase("ALL")) {
			// 나를 제외한 전체에게 메시지 보내기
			this.sendNotMeMessage(session, jsonTextMessage);
		} else {
			// 전체가 아니면 전송된 session id값을 sessionList에서
			// 조회하여 일치하는 값이 있으면
			// 해당 접속자에게만 메시지 보내기
			for(WebSocketSession ws : sessionList) {
				if(ws.getId().equals(messageVO.getToUser())) {
					this.sendMeMessage(ws, jsonTextMessage);
					break;
				}
			}
		}

		

	}
	
	// 요청한 접속자에게만 메시지 보내기
	private void sendMeMessage(WebSocketSession session, String textMessage) throws IOException {
		TextMessage sendMessage = new TextMessage(textMessage);
		session.sendMessage(sendMessage);
		
	}
	
	// 무조건 전체 접속자에게 메시지 보내기
	private void sendAllMessage(WebSocketSession session, String textMessage) throws IOException {
		
		TextMessage sendMessage = new TextMessage(textMessage);
		
		for(WebSocketSession ws : sessionList) {
				ws.sendMessage(sendMessage);
		}
	}
	
	// 현재 접속자를 제외한 나머지 접속자에게 메시지 보내기
	private void sendNotMeMessage(WebSocketSession session, String textMessage) throws IOException {
		
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
