package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @WebSerlvet애노테이션을 이용하여 서블릿을 등록하는 예제
 * 
 * @WebServlet애노테이션은 Servlet버전 3.0 이상에서 사용할 수 있다.
 *
 * @WebServlet애노테이션의 속성들
 * 1. name : 서블릿 이름을 설정한다. (기본값 : 빈문자열("") )
 * 2. urlPatterns : 서블릿의 URL패턴의 목록을 설정한다. (기본값 : 빈배열({}))
 * 		예) urlPatterns = "/url" 또는 urlPatterns = {"/url"} == > 패턴이 1개 일 경우
 * 		예) urlPatterns = {"/url1", "/url2", ...} ==> 패턴이 여러개 일 경우 
 * 3. value : urlPatterns와 같다.
 * 4. description : 주석(설명글)을 설정한다.
 * 
 */

@WebServlet(name="servletTest02", urlPatterns = {"/servletTest02.do"},
			description = "애노테이션을 이용한 서블릿")
public class ServletTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8"); // 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=utf-8");  // 응답 문서의 ContentType 지정
		
		// 처리한 데이터를 응답으로 보내기 위한 출력 스트림 객체 생성 ==> PrintWriter객체 이용
		// 		==> Response객체를 이용해서 구할 수 있다.
		PrintWriter out = response.getWriter(); 
		
		// 처리한 내용을 출력한다.
		// 방법2 : print(), println(), printf()메서드를 이용하기
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>두번째 서블릿</title></head>");
		out.println("<body>");
		out.println("<h1 style='text-align:center;'>안녕하세요, 두번째 서블릿입니다.<br>");
		out.println("@WebServlet애노테이션을 이용한 서블릿입니다.</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
