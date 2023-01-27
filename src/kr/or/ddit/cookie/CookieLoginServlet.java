package kr.or.ddit.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// userid, userpass, checkID 정보 받아오기
		String userid = request.getParameter("userid");
		String userpass = request.getParameter("userpass");
		String checkId= request.getParameter("checkID");
		
		// 입력받은 userid값을 갖는 쿠키 객체 생성
		Cookie cookie = new Cookie("USERID", userid);
		
		// 체크박스가 체크되지 않았으면 쿠키를 삭제한다.
		if(checkId==null) { //체크가 안되면 유지시간을 0으로 설정
			cookie.setMaxAge(0);
			
		}
		
		response.addCookie(cookie);
		
		// ID와 PASS가 각각 'test', '1234'이면 로그인 성공이고 그렇지 않으면 로그인 실패이다.
		if("test".equals(userid) && "1234".equals(userpass)) {
			
			//로그인 성공이면 cookieMain.jsp 문서로 이동
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		}else {
			//로그인 실패이면 cookieLogin.jsp문서로 이동
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
