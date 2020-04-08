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
