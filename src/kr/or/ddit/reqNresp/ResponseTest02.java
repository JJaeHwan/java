package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- redirect방식
		 	==> 다른 페이지로 페이지 이동을 한다. 
		 	==> 다른 페이지로 이동할 때 Request객체와 Response객체를 공유할 수 없다.
		 	==> 이동하기 전 페이지에서 응답으로 브라우저에게 이동할 페이지의 'URL주소'를 전송하면
		 		웹브라우저가 이 'URL주소'로 다시 요청하는 방식으로 이동한다.
		 		(이 때 요청은'GET방식'으로 요청하기 때문에 URL의 길이에 제한이 있다.)
		 */
		
		// redirect방식에서는 setAttribute()메서드를 이용해서 데이터를 보낼 수 없다.
//		request.setAttribute("tel", "010-0000-0000");
		
		request.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("username");
		
		
		// redirect방식으로 이동하기 ==> response객체의 sendRedirect()메서드를 이용한다.
		// 형식) response.sendRedirect("전체URL주소");
		
		response.setCharacterEncoding("utf-8");
		
		// 이동되는 주소로 데이터를 보내려면 GET방식으로 데이터를 구성해서 보낼 수 있다.
		response.sendRedirect(request.getContextPath() + "/redirectTest.do?username=" +
							URLEncoder.encode(userName, "utf-8") + "&tel=010-0000-0000");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
