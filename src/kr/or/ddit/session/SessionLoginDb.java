package kr.or.ddit.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;

@WebServlet("/sessionLoginDb.do")
public class SessionLoginDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 사용자가 입력한 로그인 정보를 가져온다.
		String userId= request.getParameter("userid");
		String userPass= request.getParameter("userpass");
		
		// 로그인 정보를 VO에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(userId);
		memVo.setMem_pass(userPass);
		
		// Dao 객체 생성
		MemberDao dao = MemberDao.getInstance();
		
		// iBatis를 이용하여 id와 password가 일치하는 회원 정보를 가져온다.
		MemberVO loginMember = dao.getMember(memVo);
		
		// Session객체 생성
		HttpSession session = request.getSession();
		
		// 로그인 성공 여부 검사...
		if(loginMember != null) { // 로그인 성공
			// 세션에 로그인 정보를 저장한다.
			session.setAttribute("LoginMember", loginMember);
		}
		
		// 페이지 이동
		response.sendRedirect(request.getContextPath() + "/basic/session/sessionLoginDb.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
