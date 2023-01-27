package kr.or.ddit.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
        String userid = request.getParameter("userid");
        String userpass = request.getParameter("userpass");
        
        HttpSession session = request.getSession();
        
        // 로그인 성공 여부 검사
        if("admin".equals(userid) && "1234".equals(userpass)) {
        	// 로그인에 성공하면 로그인 정보(사용자의 ID)를 세션에 저장한다.
        	session.setAttribute("LoginID", userid);
        	
        }
	    
        // 로그인 처리 후 페이지 이동하기
        response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
	     
	     
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
