package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 쿠키 이름 : count로 하기로 한다. 
		
		// count라는 쿠키가 있는지 검사하기
		Cookie[] cookieArr = request.getCookies();
		int count=0;	// 현재의 쿠키값이 저장 될 변수
		
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) {
					String value = cookie.getValue();
					count = Integer.parseInt(value);
				}
			}
		}
		count++;
		
		// 증가된 count값이 '쿠키값'으로 설정된 Cookie객체 생성
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		response.addCookie(countCookie); 	// 쿠키 추가하기
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'<title>Cookie Count 증가</title></head>");
		out.println("<body>");
		
		out.println("<h2>어서오세요. 당신은 " + count + "번째 방문입니다</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'> 카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'> 시작문서로 이동하기</a>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
