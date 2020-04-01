# Spring MVC의 Mockito Test

### Spring MVC 의 LifeCycle 흐름도
* 사용자가 web browser에서 url을 입력하여 서버에 접속
* Tomcat이 사용의 url을 받고 Context 분석하여 해당 프로젝트가 실행중인지 검사
* 프로젝트가 실행중임이 확인되면 프로젝트의 DispetcherServlet에게 해당 요청을 전달
* Dispacher는 URL 중에서 path 부분을 분석하여 응답을 처리할 수 있는 Controller와 method가 있는가 확인
* 해당 요청을 Controller와 method에게 전달하여 Service -> Dao -> DB 등과 연계하여 처리를 수행
* DB -> Service -> Controller로 VO에 담긴 데이터가 전달
* Controller는 VO 데이터를 view와 rendering 하거나, ResponseBody가 설정되어 있으면, 문자열, 객체, Map 등을 JacksonBind를 사용하여 JSON 객체로 변화한 후 web browser로 전송

## 프로젝트를 Run as Server를 실행하여 Test
* 모든 로직이 갖추어진 상태에서 사용자가 전송한 테이터를 Controller로 받는 것부터 시작하여 Test가 이루어진다.
* 이러한 Test는 아주 작은 코드 하나를 수정해도 전체적인 프로젝트를 재 시작해야 하는 번거로움이 있다.
* 프로젝트가 커지면 커질수록 생산성 면에서 매우 불리하게 작용한다.

## JUnit Spring-test 를 사용한 단위 Test
* servlet-context.xml을 기준으로 서로 의존성 주입이 완료된 MVC 프로젝트를 실제로 작동하는 것처럼 데이터를 주입하고, return된 데이터를 되돌려 받아 Test를 수행
* 이 방법은 서버를 직접 실행해서 Test하는 것보다는 매우 효율적이다.
* 또한 통합테스트를 수행할 때는 이러한 방법으로 Test를 진행한다.

* 하지만 개발과정에서 클래스단위, method 단위의 테스트에는 다소 부정적인 방법니다.

* Test하는 과정에서 실제 DB의 데이터가 추가, 수정, 삭제 되어 변형될 수 있고
* 변형된 데이터를 SELECT하여 결과를 비교하는 것은 Test 실패로 이어져 불필요한 코드가 추가되거나 할 수 있다.

## Mock을 사용한 단위테스트
* Mockito를 사용하는 단위테스트는 한가지의 동작에 중점을 두고 Test하는 것으로

* Controller가 사용자의 Request를 받았을 때 Service에게 잘 요청을 하고 그 결과를 잘 받는지 테스트하는 것
* Service가 Dao(Repository)에게 CRUD를 요청했을 때 적절한 결과가 나오는지 테스트 하는 것

* 만약 Controller의 method를 호출한다면 Service의 요청 method를 Mock(모형)으로 설정하고, Service는 항상 정상적인 데이터를 return한다는 가정 하에서 Controller의 Test를 수행한다.

* Service의 method를 Test 한다면, Dao의 method Mock으로 설정하고, dao는 항상 정상적인 데이터를 return한다는 가정하에서 Service의 Test를 수행한다.

* 또한 한 Service method에서 다른 비즈니스 로직의 method를 호출하여 동작이 이루어진다면 이들의 동작이 실제로 잘 이루어지고, 데이터들이 잘 이동되는지  Test를 수행한다.
