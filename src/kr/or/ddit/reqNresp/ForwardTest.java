package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forwardTest.do")
public class ForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공유가 되는지 확인
		request.setCharacterEncoding("utf-8");
		
		// forward로 넘어오기 전 문서에서 사용하던 Request객체와 Response객체를 이 곳에서 
		// 같이 사용할 수 있다.
		String userName = request.getParameter("username");
		
		// 이전 문서에서 setAttribute()메서드로 작성한 데이터는 getAttribute()메서드로
		// 받을 수 있다.
		// 형식) request.getAttribute("key값");
		String tel = (String) request.getAttribute("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'<title>forward 방식 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>Forward방식 결과</h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");
		out.println("<tr><td>전화</td>");
		out.println("<td>" + tel + "</td></tr>");
		out.println("</table>");
		out.println("</body></html>");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
