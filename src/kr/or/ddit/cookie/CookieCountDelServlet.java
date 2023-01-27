package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountDelServlet.do")
public class CookieCountDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 전체 쿠키 정보 가져와 'count'라는 쿠키 이름을 찾는다.
		Cookie[] cookieArr =request.getCookies();
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) { // 쿠키찾기
					cookie.setMaxAge(0);	// 찾은 쿠키의 유지시간을 0으로 설정
					response.addCookie(cookie);	// 변경된 쿠키 저장하기
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'<title>Cookie Count 증가</title></head>");
		out.println("<body>");
		
		out.println("<h2>count가 초기화 되었습니다.</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'> 시작문서로 이동하기</a>");
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
