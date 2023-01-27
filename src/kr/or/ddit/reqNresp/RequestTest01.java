package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 전달되는 문자 인코딩 방식 설정
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 보내온 데이터 받기
		
		// request객체의 getParameter()메서드 이용
		// 형식) request.getParameter("파라미터명")
		//		 반환값 자료형: String
		// ==> 지정한 '파라미터명'에 설정될 '값'을 가져온다.
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// 파라미터명이 같은 것이 여러개일 경우에는
		// request객체의 getParameterValues()메서드를 사용한다.
		// 형식) request.getParameterValues("파라미터명");
		//		반환값 자료형 : String[] (문자열 배열)
		
		// 보통 checkbox로 설정된 데이터를 구할 때 많이 사용된다.
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'> <title>Request객체연습</title></head>");
		out.println("<body>");
		
		out.println("<h2> Request 테스트 결과 </h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");
		out.println("<tr><td>직업</td>");
		out.println("<td>" + job + "</td></tr>");
		out.println("<tr><td>취미</td>");
		out.println("<td>");
			if(hobbies == null) {
				out.println("선택한 취미가 하나도 없습니다...");
			}else {
				for(String hobby : hobbies) {
					out.println(hobby + "<br>");
				}
			}
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<h2>Request객체의 메서드들...</h2>");
		out.println("<ul>");
		out.println("<li>클라이언트의 IP주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li>요청 메서드 : " + request.getMethod() + "</li>");
		out.println("<li>ContextPate : " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li>URL 정보 : " + request.getRequestURL() + "</li>");
		out.println("<li>URI 정보 : " + request.getRequestURI() + "</li>");
		out.println("</ul>");
		out.println("<hr>");
		
		// 전체 파라미터 정보 구하기 
		// Request객체의 getparameterMap()메서드 이용 
		// 형식) request.getparameterMap() 
		// 		반환값 : 모든 파라미터 정보가 저장된 Map객체가 반환된다. 
		//				 Map의 key값 : 파라미터 명 (자료형 : String)
		//				 Map의 value값 : 해당 파라미터에 저장된 값이 된다. (자료형 : String[]) 
		out.println("<h2>Request객체의 getParameterMap() 메서드 처리 결과 </h2>");
		out.println("<table border='1'>");
		out.println("<tr><td>파라미터 name</td><td>파라미터value</td></tr>");
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		for(String paramName : paramMap.keySet()) {
			out.println("<tr><td>" + paramName + "</td>");	// 파라미터 name값 출력
			out.println("<td>");
			
			String[] paramValues = paramMap.get(paramName);  // 파라미터 value값 구하기
			if(paramValues == null || paramValues.length == 0) {  // 파라미터 값이 없을 경우
				continue;
												 // 
			}else if(paramValues.length == 1) {  // 파라미터 값이 배열이 아닌 경우 ex)job, userName처럼 하나인 것들
				out.println(paramValues[0]);				 
			}else {			// 파라미터 값이 배열인 경우 ex) hobbies
				for(String value : paramValues) {
					out.println(value + "<br>");
				}
			}
			
			out.println("</td></tr>");
			
			
		}
		
		out.println("</table>");
		
		
		
		out.println("</body></html>");
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
