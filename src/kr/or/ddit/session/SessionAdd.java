package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session정보 저장하기
		
		// 1. Session객체 구하기
		
		// 형식1) request객체변수.getSession(); 또는 request객체변수.getSession(true);
		//			==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성한다.
		// 형식2) request객체변수.getSession(false);
//					==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성하지않고 null을 반환한다.
		HttpSession session = request.getSession();
		
		// 2. Session에 데이터 저장하기 ==> setAttribute()메서드를 이용한다.
		// 형식) session객체변수.setAttribute("key값", 저장할데이터)
		//				==> 'key값은 문자열, '저장할데이터'는 Java의 모든 종류의 데이터
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 28);
		
		List<String> list = new ArrayList<String>();
		list.add("한강");
		list.add("금강");
		list.add("영산강");
		list.add("낙동강");
		
		session.setAttribute("myList", list);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'<title>Session저장</title></head>");
		out.println("<body>");
		
		out.println("<h2>Session정보가 저장되었습니다</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/basic/session/sessionTest01.jsp" + "'> 시작문서로 이동</a>");
		
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
