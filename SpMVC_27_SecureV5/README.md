## Spring MVC Security Project
### 2020-04-08

### 개요
* Spring MVC 기반 Security Coding
* Security 기능을 포함한 WebSocket Coding
* React 기반 Front-End 기능 Coding

* JDBC, MyBatis 연동
* MySQL DB 연동
* Transaction 설정

* 회원가입 E-mail 인증
* 비밀번호 분실 E-mail 인증 후 재 설정

### Spring Security Dependency
#### spring security 설정
* spring security-core
* spring security-web
* spring security-config
* spring security-taglibs

* jasypt
* jasypt-spring31

### DB Dependency(MySQL, MyBatis 연동)
#### DB 설정하기
* mysql-connector-java
- MySQL과 Java를 연결 해주는데 사용하는 DB Driver

* commons-dbcp2
- JDBC와 Driver 사이에서 DB Connection Pool을 만들고, Connection, DiConnection을 수행해주는 보조 Class

* spring-jdbc
- Java(Spring)와 DB Driver 사이에서 명령어, 데이터를 변환 시켜주는 보조 Class

* mybatis
- spring-jdbc와 DB Driver 사이에서 Mybatis Mapper 등으로 작성된 SQL을 변환하고, 데이터를 VO에 쉽게 매핑시키는 용도의 Class

* mybatis-spring
-spring-jdbc와 mybatis 엔진사이에서 사로 잘 맞지 앉는 부분을 잘 조정하여 버전에 관계없이 연동이 쉽도록 만들어주는 보조 Class

## Security와 관련 용어
#### 접근주체(principal)
* 보호된 대상에 접근하는 유저(User)

#### 인증(Authenticate)
* 접근하는 유저가 누구인가 확인(로그인 절차)

#### 인가(Authorize)
* 접근한 유저가 어떤 서비스, 어떤 페이지에 접근할 수 있는 권한이 있는가 하는 것을 검사

#### 권한(Role)
* 인증(Authenticate)된 주체(User)가 어떤 페이지, 기능, 서비스에 접근할 수 있는 권한이 있다는 것을 보증, 증명

#### 무결성, 보안
* 무결성 파괴 : 인가된 사용자에 의해 손상될 수 있는 것들
* 보안 파괴 : 인가되지 않은 사용자에 의해 손상될 수 있는 것들

## Spring Security
* Filter를 사용하여 접근하는 사용자의 인증 절차와 인가를 통해 권한이 있는가를 파악하고, 적절한 조치(되돌리기, Redirect, 사용가능)를 비교적 적은 코드 양으로 처리할 수 있도록 만든 framework

* spring security는 세션과 쿠키 방식을 병행하여 사용한다.

### 유저가 로그인을 시도하면
1. Authentication Filter에서 부터 users table까지 접근하여 사용자 정보를 인증하는 절차를 거친다.

2. 인증이 되면 user table, user detail table에서 사용자 정보를 fetch(select)하여 session에 저장

3. 일반적인 HttpSession은 서버의 활동 영역 메모리에 session을 저장하는데 비해 Spring Security는 "SecurityContextHolder" 라는 메모리에 저장

4. view로 유저 정보가 담긴 session을 session ID와 함꼐 응답으로 전달
* JSESSIONID 라는 쿠키에 session ID를 담아서 보내고

5. 이후 유저가 접근을 하면 JSESSION에서 쿠키를 추출하여 사용자 인증을 시도한다.
* ?JSESSIONID-asdfsadlfdsl 이러한 값이 URL 뒤에 따라 붙기도 한다.

6. JSESSION에서 추출한 Session ID가 유효하면 접근 request에게 Authentication을 부착한다.

### Spring-Security와 form 데이터 
* Web browser에서 서버로 요청하는 것을 request라고 하며, 요청할 때 사용하는 주소를 URL, URI라고 한다.

* Web browser에서 서베에 request 하는 method 방식에는 GET, POST, PUT, DELETE 가 있고, 이중 SpringMVC에서는 GET, POST를 주로 사용한다.

* GET method는 주소창에 URL을 입력하고 Enter를 누르거나, anchor tag를 마우스로 클릭하거나, 또는 form tag의 method가 없는 경우 서버로 요청하는 방식이다.

* GET method는 단순히 리스트를 요구하거나, 입력 form 화면을 요구하는 용도로 주로 사용된다.

* POST method는 입력 화면에 값들을 입력한 후 서버로 전송할 때 주로 사용하며, 입력 화면의 form, input 등의 tag에 값을 저장한 후 서버로 submit을 수행하는 경우이다.

* POST method는 데이터의 양에 관계없이 서버로 전송할 수 있으며 file upload 등도 수행할 수 있다.

* Spring-Security를 적용한 프로젝트에서는 GET method 방식은 아무런 제약이 없으나, POST method 방식은 서버로 부터 전달받은 _csrf Token을 데이터들과 함께 보내야만 정상적으로 서버로 보낼 수 있다.

* 그 때문에 POST 방식의 form 코드에는 다음과 같은 코드를 추가해 주어야 한다.

	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

* 매번 form 화면구현을 하면서 코드를 추가하다보면, 빠뜨리는 경우가 발생할 수 있고, 해당 페이지의 데이터를 전송하면 서버는 수신을 거부하고 403 오류를 보낸다.

* 이러한 불편을 방지하기 위해 Spring form taglibs를 사용하여 form을 작성한다.

	<form:form>...</form:form>
* 형식의 form 화면을 작성하면 Spring form taglibs는 자동을 토큰을 form 화면코드에 추가하여 별도의 조치를 취하지 않아도 문제가 발생하지 않도록 만들어준다.

## E-mail 인증 회원가입

* 회원가입을 할 때 username, password, email을 입력받고 email을 사용자에게 보낸 후 email 인증을 수행하여 회원가입을 완료한다.

* 회원가입 화면을 username, password를 입력받는 화면과, email을 입력받은 화면을 분리하는 2개의 화면으로 이루어진 기능을 구현

* sessionAttributes, ModelAttribute를 활용하여 구현

* sessionAttributes는 보통 vo 객체를 서버 메모리에 저장한 후 form화면과 연동하는 구현으로 이때 반드시 ModelAttribute가 동반되어 구현되어야 한다.

* sessionAttributes에 등록된 ModelAttribute vo 객체는 서버 메모리에 데이터를 보관하고 있다가 form:form를 통해서 서버로 전달되는 param vo 객체를 받고, form:form에서 누락된 input 항목들이 있으면 메모리에 보관된 ModelAttribute vo에서 param vo 데이터를 완성하여 사용할 수 있도록 만들어준다.

## 트렌젝션 : transactional
* mybatis와 common-dbcp 환경에서는 context.xml에 <tx:annotation-driven/> 설정을 통해서 자동으로 transaction을 구현할 수 있다.

* mybatis 환경에서 실제 dao interface와 mapper.xml 등을 연동하여 DB와 query를 주고 받을 때는 SqlSessionTemplete라는 클래스를 통해서 사용한다.

* DataSourceTransactionManager를 context.xml에 설정을 하게 되면 SqlSessionTemplete를 사용하지 않아도 내부적으로 자체 처리가 된다.

* DataSourceTransactionManager가 SqlSessionTemplete 역할을 대신 수행하기도 한다.

* 여기에서 <tx:annotation-driven/> 항목이 없고, class나 method에서 @Transactional 설정이 없으면 DataSourceTrans...은 SqlSessionTemp...과 같은 역할만 수행한다.

* 혹시 <tx:annotation-driven/>을 설정했는데 @Transactional 설정된 method에서 transaction이 적용이 안 될때가 있는데 이때는 <tx:annotation-driven/> 코드 위쪽에  <context:annotation-config/>을 설정해 주어야 한다.

* @Transactional에는 특별히 세세하게 설정할 수 있는 옵션들이 있다.

### Transactional의 옵션

#### isolation
* 현재 transaction이 작동되는 과정에서 다른 transaction 등이 접근하는 정도를 설정하기

* READ_UNCMMITTED : level 0
- 트랜젝션 처리 중 또는 COMMIT이 완료되기 전에 다른 트랜잭션이 일기를 수행할 수 있다.
- EX) 은행 송금 업무

* READ_COMMITED : level 1
- 트랜젝션이 COMMIT 된 이후에만 다른 트렌젝션이 읽을 수 있다.

* REPEATABLE_READ : level 2
- 트랜젝션이 진행되는 동안에 SELECT 문장이 사용된 TABLE에 Lock을 걸기, SELECT가 실행되거나 실행될 예정이 DB(Table) CUD를 수행할 수 없도록하며 단, 다른 트랜젝션에서 제한적으로 SELECT가 가능하다.

- 다수의 트랜젝션이 SELECT를 수행할 떄 일관된 무결성 있는 데이터를 가져갈 수 있도록 보장

* SELIALZABLE : level 3
- 완벽한 일관성 있는 SELECT를 보장


#### propagation :  전파옵션
* 현재 트렌잭션이 시작되었음을 다른 트랜젝션에 어떻게 알릴 것인가

* REQUIRED
- 부모 트렌젝션이 실행되는 과정에서 또 자식(세부적인) 트렌잭션을 실행할 수 있도록 허용
- 이미 자식 트렌젝션이 실행되고 있으면 새로 생성 금지

* REQUIRE_NEW
- REQUIRED와 비슷하지만 자식 트렌젝션이 이미 실행되고 있지만 무조건 새로 다시 생성하라

### readonly
* 해당 트렌젝션을 읽기 전용으로 설정하겠다. 기본값은 false

### rollbackFor
* rollback 조건을 무엇으로 하겠느냐. 특별히 어떤 예외가 발생했을 때만 rollback이 되도록 설정할 때 추가, 기본값은 Exception.class

### noRollbackFor
* rollbackFor와 반대되는 개념, 특별한 예외에서는 rollback을 무시하라. 기본값 null

### timeout
* DB와 연결하여, transaction이 실행되는 시간이 과도하게 진행될 경우 rollback을 수행하도록 설정. 기본값 -1, timeout rollback 금지

### List INSERT  수행할 때 주의 사항 !!!
* 서비스 코드에서 다음과 같은 코드 절대 사용 금지
for(DataVO vo : dataList) {
	dao.insert(vo)
}
