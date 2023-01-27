package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
   private static final long serialVersionUID = 1L;


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 저장된 Session정보 읽어오기
      
      // 1. Session객체 구하기
      //   형식 1) request객체변수.getSession(); 또는 request객체변수.getSession(true);
      //         ==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성한다.
      //   형식 2) request객체변수.getSession(false);
      //         ==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성하지 않고 null을 반환한다.
      HttpSession session = request.getSession();
      
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      
      out.println("<html>");
      out.println("<head><meta charset='utf-8'><title>Session데이터 읽기</title></head>");
      out.println("<body>");
      out.println("<h2>저장된 Session데이터 읽어오기</h2><hr>");

      
      // 2. Session정보 얻어오기 ==> Session객체의 getAttribute() 메서드를 이용한다.
      // 형식) session객체변수.getAttribute("key값");
      out.println("<h3>Session데이터 1개 읽어오기</h3>");
      String sessionValue = (String) session.getAttribute("testSession"); //반환을 오브젝트로 하기때문에 형변환 필요.
      
      if(sessionValue == null) {
         out.println("<h4>testSession의 세션값이 없습니다.</h4>");
      }else {
         out.println("<h4>testSession의 세션값 : " + sessionValue +"</h4>");
      }
      
      out.println("<hr>");
      
      // 세션의 전체 데이터 가져오기
      out.println("<h3>전체의 Session데이터 확인하기</h3>");
      int count = 0;
      out.println("<ol>");
      
      // 전체 세션 정보는 Session객체의 getAttributeNames()메서드를 이용한다.
      // getAttributeNames() ==> Session에 저장된 모든 key값들을 Enumeration형식으로 반환한다.
      Enumeration<String> sessionNames = session.getAttributeNames();
      while(sessionNames.hasMoreElements()) {
    	  count++;
    	// 데이터를 가리키는 포인터를 다음번째로 이동하고 그 곳의 데이터를 가져온다.
    	  String sessionkey = sessionNames.nextElement(); 
    	  out.println("<li>" + sessionkey + " : " + session.getAttribute(sessionkey) + "</li>");
      }
      
      if(count == 0) {
    	  out.println("<li>세션 데이터가 하나도 없습니다...</li>");
      }
      out.println("</ol>");
      
      out.println(" <a href='"+request.getContextPath()+"/basic/session/sessionTest01.jsp'>시작 문서로 이동하기</a> ");
      out.println("</body>");
      out.println("</html>");
      
   }


   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}