package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDelete.do")
public class SessionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 삭제하기
		
		// 1. Session객체 구하기
		HttpSession session = request.getSession();
		
		// 2. Session데이터 삭제하기
		// 2-1. 삭제를 원하는 데이터를 개별적으로 삭제하기 ==> removeAttribute()메서드 이용
		// 형식) session객체변수.removeAttribute("삭제할key값");
		//session.removeAttribute("testSession");
		
		// 2-2. 세션 자체를 삭제하기 ==> invaildate()메서드 이용
		// 형식) session객체변수.invalidate();
		session.invalidate();
		
		//---------------------------------------------------------------------------
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 삭제</title></head>");
		out.println("<body>");
		
		out.println("<h2>Session 정보가 삭제되었습니다</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/basic/session/sessionTest01.jsp" + "'> 시작문서로 이동</a>");
		
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
