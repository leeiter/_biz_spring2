package com.biz.sec.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.sec.domain.AuthorityVO;
import com.biz.sec.domain.UserDetailsVO;
import com.biz.sec.persistence.AuthoritiesDao;
import com.biz.sec.persistence.UserDao;

//classdp @AutoWired Annotaion을 붙인것과 같은 역할
//2020-04-14 생성자를 별도로 만드려고 required~~ 주석처리
//@RequiredArgsConstructor
@Service
public class UserService {
	
	// 생성자부분에 테이블을 create하는 변수를 만들고 Dao의 create_table 호출
	public UserService(PasswordEncoder passwordEncoder, UserDao userDao, AuthoritiesDao authDao) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDao = userDao;
		this.authDao = authDao;
		
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
	
	// @Autowired
	private final PasswordEncoder passwordEncoder;
	private final UserDao userDao;
	private final AuthoritiesDao authDao;
	


	/**
	 * @since 2020-04-09
	 * @author leeiter
	 * 
	 * @param username
	 * @param password
	 * @return
	 * 
	 * 회원가입을 신청하면 비밀번호는 암호화하고
	 * 아이디와 비밀번호를 DB insert 수행
	 * 
	 * @update 2020-04-10
	 * Map<String, String> 구조의 VO 데이터를
	 * UserVO로 변경
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
		List<AuthorityVO> authList = new ArrayList();
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("ROLE_USER").build());
		authList.add(AuthorityVO.builder().username(userVO.getUsername()).authority("USER").build());
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
		// TODO Auto-generated method stub
		return userVO;
	}

	public boolean check_password(String password) {
		
		
		// 본인만 수정 가능
		// 관리자 수정은 불가능
		UserDetailsVO userVO = (UserDetailsVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		// TODO Auto-generated method stub
		return passwordEncoder.matches(password, userVO.getPassword());
	}
	
	public int update(UserDetailsVO userVO, String[] authList) {
		int ret = userDao.update(userVO);
		
		if(ret > 0) {
			
			
			List<AuthorityVO> authCollection = new ArrayList();
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
		
		
		int ret = userDao.update(userVO);
		
		// DB update가 성공하면
		// 로그인된 session정보를 update 수행
		if(ret > 0) {
			
			Authentication newAuth = new UsernamePasswordAuthenticationToken(oldUserVO, // 변경된 사용자 정보
					oldAuth.getCredentials(),
					oldAuth.getAuthorities() // 변경된 ROLE 정보
					);
			
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	public UserDetailsVO findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}

	
	


}
