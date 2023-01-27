package kr.or.ddit.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- forward방식
		 	==> 특정 서블릿이나 JSP에 대한 요청을 다른 서블릿이나 JSP로 넘겨 준다.
		 		(이 때 Request 객체와 Response 객체를 넘겨 준다.) ==> 데이터를 공유할 수 있다.
		 	==> URL주소는 처음 요청할 때의 주소값이 바뀌지 않는다.
		 	==> 서버 내부에서만 접근이 가능하다.
		 */
		
		// 현재의 페이지에서 작성한 데이터를 이동되는 다른 페이지로 넘기려면 Request객체의 setAttribute()메서드를 이용한다.
		// 형식) request.setAttribute("key값", 데이터)
		//		==> 'key값'은 문자열, '데이터'는 모든 종류의 자료형
		
		request.setAttribute("tel", "010-4724-7168");
		
		// forward방식으로 이동하기
		// 형식)request.getRequestDispatcher("이동할 문서 URL");
		// '이동할 문서 URL'은 전체 URL주소 중 ContextPath이후의 주소를 기술한다.
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest.do");
		rd.forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
