package com.biz.sec.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.biz.sec.domain.AuthorityVO;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.persistence.AuthoritiesDao;
import com.biz.sec.persistence.UserDao;
import com.biz.sec.utils.PbeEncryptor;

import lombok.extern.slf4j.Slf4j;

// class에 @AutoWired Annotaion을 붙인것과 같은 역할
// 2020-04-14 생성자를 별도로 만드려고 required~~ 주석처리
// @RequiredArgsConstructor
// @Transactional 왠만하면 각 method 에 설정해주는 것이 좋다.
@Slf4j
@Service
public class UserService {
	
	// @Autowired
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final AuthoritiesDao authDao;
	private final MailSendService mailService;
	
	// 생성자부분에 테이블을 create하는 변수를 만들고 Dao의 create_table 호출
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao, AuthoritiesDao authDao, MailSendService mailService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao = authDao;
		this.mailService = mailService;
		
		String create_user_table = " CREATE TABLE IF NOT EXISTS tbl_users( " + 
				" id BIGINT PRIMARY KEY AUTO_INCREMENT, " + 
				" user_name VARCHAR(50) UNIQUE, " + 
				" user_pass VARCHAR(125), " + 
				" enabled BOOLEAN default true, " +				
				" email VARCHAR(50), " + 
				" phone VARCHAR(20), " + 
				" address VARCHAR(125) " + 
				" ) ";
		
		String create_auth_table = " CREATE TABLE IF NOT EXISTS authorities( " + 
				" id BIGINT PRIMARY KEY AUTO_INCREMENT, " + 
				" username VARCHAR(50), " + 
				" authority VARCHAR(50) " + 
				" ) ";
		
		userDao.create_table(create_user_table);
		userDao.create_table(create_auth_table);
	}

	/**
	 * 회원가입을 신청하면 비밀번호는 암호화하고
	 * 아이디와 비밀번호를 DB insert 수행
	 * 
	 * @since 2020-04-09
	 * @author leeiter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 * 2020-04-10 Map 구조의 VO 데이터를 UserVO로 변경
	 */
	@Transactional
	public int insert(String username, String password) {
		// 회원가입 form에서 전달 받은 password 값을 암호화 시키는 과정
		String encPassword = passwordEncoder.encode(password);
		UserDetailsVO userVO = UserDetailsVO.builder()
				.username(username)
				.password(encPassword)
				.build();
		userVO.setPhone("010-1111-1234");
		userVO.setEmail("iterlees@gmail.com");
		userVO.setAddress("광주광역시 북구");
		
		int ret = userDao.insert(userVO);
		
		List<AuthorityVO> authList = new ArrayList<>();
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
		
		authDao.insert(authList);
		return ret;
	}
	
	/*
	 * for()문으로 insert를 반복 실행을 한다고 하면
	 * 현재 insert method에는 @Transactional이 있기 때문에
	 * 10번을 한다고 하면 첫번째 COMMIT이 되고 나면 바로 멈춰버린다. 
	 * 그래서 dao에서 insert가 진행되도록 해야 한다.
	 * 심하게는 DB연결까지 막아버리는 최악의 상황이 생길 수도 있다.
	 */
	/**
	 * @since 2020-04-20
	 * @author leeiter
	 * 
	 * 새로 작성된 회원가입에서 회원가입을 처리할 method
	 * 
	 * email 인증방식으로 회원가입을 처리할 것이므로
	 * userVO를 파라메터로 받아서
	 * enabled를 false로 처리하고
	 * role 정보는 업데이트 하지 않는 것으로 처리해 놓는다.
	 * 
	 * 이후 email 인증이 오면
	 * enabled와 role 정보를 설정하도록 한다.
	 * 
	 * @param userVO
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public int insert(UserDetailsVO userVO) {
		// 회원정보에 저장할 준비가 되지만
		// 로그인을 했을 때 접근금지가 된 사용자가 된다.
		userVO.setEnabled(false);
		userVO.setAuthorities(null);
		
		// 회원가입 form에서 전달 받은 password 값을 암호화 시키는 과정
		String encPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		
		String sRet;
		try {
			sRet = mailService.join_send(userVO);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int ret = userDao.insert(userVO);
		return ret;
	}
	
	public boolean isExistsUserName(String username) {
		UserDetailsVO userDetailsVO = userDao.findByUserName(username);
		
		// 이미 DB에 회원정보(username)이 저장되어 있다.		
		if(userDetailsVO != null && userDetailsVO.getUsername().equalsIgnoreCase(username)) {
			return true;
		}
		return false;
	}

	public UserDetailsVO findById(long id) {
		UserDetailsVO userVO = userDao.findById(id);
		return userVO;
	}

	public boolean check_password(String password) {
		// 본인만 수정 가능
		// 관리자 수정은 불가능
		UserDetailsVO userVO = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return passwordEncoder.matches(password, userVO.getPassword());
	}
	
	@Transactional
	public int update(UserDetailsVO userVO, String[] authList) {
		int ret = userDao.update(userVO);
		
		if(ret > 0) {
			List<AuthorityVO> authCollection = new ArrayList<>();
			for(String auth : authList) {
				if(!auth.isEmpty()) {
					AuthorityVO authVO = AuthorityVO.builder()
							.username(userVO.getUsername())
							.authority(auth)
							.build();
					authCollection.add(authVO);
				}
			}
			authDao.delete(userVO.getUsername());
			authDao.insert(authCollection);
		}
		
		return ret;
	}

	@Transactional
	public int update(UserDetailsVO userVO) {
		Authentication oldAuth = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetailsVO oldUserVO = (UserDetailsVO) oldAuth.getPrincipal();
		oldUserVO.setEmail(userVO.getEmail());
		oldUserVO.setPhone(userVO.getPhone());
		oldUserVO.setAddress(userVO.getAddress());
		
		int ret = userDao.update(oldUserVO);
		
		// DB update가 성공하면
		// 로그인된 session정보를 update 수행
		if(ret > 0) {
			Authentication newAuth = new UsernamePasswordAuthenticationToken(oldUserVO, // 변경된 사용자 정보
					oldAuth.getCredentials(),
					oldAuth.getAuthorities() // 변경된 ROLE 정보
			);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		return ret;
	}
	
	private Collection<GrantedAuthority> getAuthorities(String[] authList) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(String auth : authList) {
			if(!auth.isEmpty()) {
				SimpleGrantedAuthority sAuth = new SimpleGrantedAuthority(auth);
				authorities.add(sAuth);
			}
		}
		
		return authorities;
	}

	@Transactional
	public List<UserDetailsVO> selectAll() {
		return userDao.selectAll();
	}

	public UserDetailsVO findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Transactional
	public boolean emailOk(String username, String email) {
		String strUserName = PbeEncryptor.getDecrypt(username);
		UserDetailsVO userVO = userDao.findByUserName(strUserName);
		
		String strEmail = PbeEncryptor.getDecrypt(email);
		if(strEmail.equalsIgnoreCase(userVO.getEmail())) {
			userVO.setEnabled(true);
			userDao.update(userVO);
			
			List<AuthorityVO> authList = new ArrayList<>();
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
			authDao.insert(authList);
			return true;
		}
		return false;
	}

	/**
	 * @since 2020-04-21
	 * 회원 정보를 받아서 DB에 저장하고
	 * 회원정보를 활성화 할수 있도록 하기 위해
	 * 인증정보를 생성한 후
	 * controller return
	 * 
	 * @param userVO
	 * @return
	 */
	@Transactional
	public String insert_getToken(UserDetailsVO userVO) {
		// DB에 저장
		userVO.setEnabled(false);
		
		String encPassword = passwordEncoder.encode(userVO.getPassword());
		userVO.setPassword(encPassword);
		userDao.insert(userVO);
		
		// UUID : dsfsdf-sdfsdf-sdfsdf..
		String email_token = UUID.randomUUID().toString().split("-")[0].toUpperCase();
		/*
			email_token = UUID.randomUUID().toString();
			String[] _t = email_token.split("-");
			email_token = _t[0];
			email_token = email_token.toUpperCase();
		*/
		
		log.debug("EMAIL-TOKEN : " + email_token);
		// 암호화
		String enc_email_token = PbeEncryptor.getEncrypt(email_token);
		
		// Email 보내기
		mailService.email_auth(userVO, email_token);
		return enc_email_token;
	}

	public boolean email_token_ok(String username, String secret_key, String secret_value) {
		boolean bKey = PbeEncryptor.getDecrypt(secret_key).equals(secret_value);
		
		if(bKey) {
			String strUserName = PbeEncryptor.getDecrypt(username);
			UserDetailsVO userVO = userDao.findByUserName(strUserName);
			
			userVO.setEnabled(true);
			userDao.update(userVO);
			authDao.delete(userVO.getUsername());
			
			List<AuthorityVO> authList = new ArrayList<>();
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
			authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
			authDao.insert(authList);
		}
		return bKey;
	}

}
