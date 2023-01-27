package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



// Cookie정보를 추가하는 서블릿...
@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		
		// Cookie 정보를 저장하는 방법
		
		// 1. Cookie객체를 생성한다. ==> '쿠키이름'과 '쿠키값'을 문자열로 지정하여 생성한다.
		// 형식) Cookie cookie변수 = new Cookie("쿠키이름", "쿠키값");
		// 		  ==> '쿠키값'이 한글일 경우에는 URLEncoder.encode()메서드로 인코딩 한 후 저장한다.
		Cookie nameCookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
		Cookie ageCookie = new Cookie("age", "28");
		Cookie genderCookie = new Cookie("gender", "Male");
		
		// 2. Cookie의 속성을 설정한다.
		// 1) cookie변수.setPath("적용경로");
		//		==> 지정한 경로와 그 하위 경로에서 사용 가능하다.(생략하면 쿠키를 저장할 당시의 경로
		// 2) cookie변수.setdoMain("적용할 당시 그 당사의 경로가 설정된다.
		
		
		// 3) cookie변수.setMaxAge(유지시간) ==> 단위 : 초
//				==> -1 : 브라우저가 종료될대까지 유지(기본값), 0: 쿠키가 즉시 삭제된다.
		
		// 4) cookie변수.setSecure(보안여부) ==> true : 적용 false : 미적용
		
//		3. Response객체를 이용해서 쿠키를 웹브라우저로 보내면 웨브라우저가 이 쿠키를 받아서 저장한다.
//		형식) reponse.addCookie(1번에서 생성한 cookie객체변수)
		
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(genderCookie);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'<title>Cookie연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>Cookie 데이터가 저장되었습니다</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest01.jsp" + "'> 시작문서로 이동</a>");
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
