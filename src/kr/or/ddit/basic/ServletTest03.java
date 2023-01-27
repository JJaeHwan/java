package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * - 서블릿의 동작 방식 및 순서(Servlet의 LifeCycle)
 * 1. 사용자(클라이언트)가 URL을 클릭하면 Http Request를 Servlet Container로 전송(요청)한다.
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 * 		(이 때 실행할 서블릿이 로딩이 안된 상태이면 로딩을 한다. 처음 로딩시 init()메서드를 호출한다.)
 * 		(Servlet버전 3.0이상에서는 애노테이션(@WebServlet)으로 설정할 수 있다.)
 * 3. Servlet Container는 요청을 처리할 개별 쓰레드 객체를 생성해서 해당 서블릿 객체의
 * 	  service()메서드를 호출한다.
 * 	  (이때 HttpServletRequest객체와 HttpServletResponse객체를 생성해서 파라미터로 넘겨준다.)
 * 4. service()메서드는 요청방식(get, post, delete ...)을 체크해서 적절한 메서드를 호출한다.
 * 		(doGet(), doPost(), doDelete(), ...)
 * 5. 요청 및 응답처리가 완료되면 HttpServletRequest객체와 HttpServletResponse객체는 자동으로
 * 		소멸된다.
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 자동으로 호출된다.
 * 
 */





@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println(this.getServletName() + "에서 init()메서드 호출...");
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()메서드 시작...");
		
		// GET방식과 POST방식에 맞는 메서드를 호출하기 
		
		// 방법1 ==> HttpServlet(부모클래스)의 service()메서드로 위임하기
//		super.service(request, response);
		
		// 방법2 ==> 클라이언트의 전송방식을 구분해서 직접 메서드 호출하기
		//			request객체의 getmethod()메서드 이용
		String method = request.getMethod();	// 전송방식이 문자열로 반환된다.(대문자로...)
		System.out.println("method = " + method);
		if("GET".equals(method)) {
			doGet(request, response);
		}else if("POST".equals(method)) {
			doPost(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>"
				+ "<body><h2 style='color : blue;'>doGet()메서드를 처리한 결과입니다...</h2></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>"
				+ "<body><h2 style='color : red;'>doPost()메서드를 처리한 결과입니다...</h2></body></html>");
	}
	@Override
	public void destroy() {
		System.out.println(this.getServletName() + "에서 destroy()메서드 호출...");
	}

}
