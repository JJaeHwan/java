package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장된 쿠키 정보 읽어오기
		
		// 저장된 쿠키 정보 삭제하기
		
		// 쿠키 데이터를 삭제하는 것은 쿠키의 남은 수명(유지시간)을 0으로 설정해서 처리한다.
		// ==> 쿠키를 저장하는 addCookie()메서드를 호출하기 전에 해당 쿠키의 setMaxAge()메서드를
		// 		이용해서 처리한다.
		// 형식) cookie객체변수.setMaxAge(0);
		
		// 1. 삭제할 쿠키를 찾기 위해 전체 쿠키 정보를 가져온다.
		Cookie[] cookieArr = request.getCookies();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'<title>쿠키 삭제</title></head>");
		out.println("<body>");
		out.println("<h2>저장된 Cookie정보 삭제하기</h2>");
		
		if(cookieArr == null || cookieArr.length == 0) {
			out.println("저장된 Cookie정보가 하나도 없습니다.");
		}else {
			// 2. 쿠키배열에서 삭제할 쿠키를 찾는다. (쿠키이름을 이용해서 찾는다.)
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName();		// 쿠키이름 구하기
				if(name.equals("gender")) {			// 삭제할 쿠키이름 찾기
					cookie.setMaxAge(0);			// 유지시간을 0으로 설정
					response.addCookie(cookie);		// 삭제할 쿠키를 저장한다.
				}
			}
			
		}
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest01.jsp" + "'> 시작문서로 이동</a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
