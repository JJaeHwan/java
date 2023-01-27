package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 이 예제는 배포서술자(web.xml)를 이용해서 실행할 Servlet을 설정하여 처리하는 예제이다.
/*
 
 ==> 서블릿 정보를 등록하는 부분
 <servlet>
 	<servlet-name>서블릿이름</servlet-name>
 	<servlet-class>서블릿클래스의 전체이름(패키지명까지 포함)</servlet-class>
 </servlet>
 
 
 ==> url주소와 서블릿을 연결하는 설정
 <servlet-mapping>
 	<servlet-name>서블릿이름</servlet-name>
 	<url-pattern>URL패턴</url-pattern>
 </servlet-mapping>
 */

// URL주소 ==> http://localhost:8080/webTest/servletTest01.do
// - http        ==> 프로토콜
// - localhost   ==> 컴퓨터이름(도메인명) 또는 IP주소
// - 8080        ==> 포트번호(80 번 포트번호는 생략가능하다)
// - /webTest    ==> 컨텍스트 패스(보통 웹프로젝트의 이름으로 설정된다.)
// - /servletTest01.do  ==> 서블릿 요청 URL패턴
//


// Servlet 클래스는 HttpServlet클래스를 상속해서 작성한다.
public class ServletTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 이 곳에서는 service()메서드나 doGet()메서드 또는 doPost()메서드를 재정의 해서 작성한다.
	
	// doGet() 메서드나 doPost()메서드는 service()메서드를 통해서 자동으로 호출된다.
	// 이 메서드의 매개변수로 주어지는 객체
	// 1. HttpServletRequest객체	==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체 
	// 2. HttpServletResponse객체	==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8"); // 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=utf-8");  // 응답 문서의 ContentType 지정
		
		// 처리한 데이터를 응답으로 보내기 위한 출력 스트림 객체 생성 ==> PrintWriter객체 이용
		// 		==> Response객체를 이용해서 구할 수 있다.
		PrintWriter out = response.getWriter(); 
		
		// 처리한 내용을 출력한다.
		// 방법1) append()메서드 이용하기
		out.append("<html>")
		   .append("<head><meta charset='utf-8'><title>첫번째 servelt</title></head>")
		   .append("<body>")
		   .append("<h1 style='text-align : center;'>")
		   .append("안녕하세요. 첫번째 servlet 프로그램 입니다. <br>")
		   .append("서버 정보 : " + request.getContextPath() + "</h1>")
		   .append("</body>")
		   .append("</html>");
		
	}
	
	
	// doPost()메서드 ==> Post방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
