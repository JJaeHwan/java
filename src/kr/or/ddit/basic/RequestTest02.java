package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String strNum1 = request.getParameter("num1");
		String strNum2 = request.getParameter("num2");
		String op = request.getParameter("op");
		
		int num1 = Integer.parseInt(strNum1);
		int num2 = Integer.parseInt(strNum2);
		
		double result = 0;	// 계산된 결과가 저장될 변수
		boolean calcOK = true;
		
		switch(op) {
			case "+" : result = num1 + num2; break;
			case "-" : result = num1 - num2; break;
			case "*" : result = num1 * num2; break;
			case "/" :
				if(num2==0) {
					calcOK =false;
				}else {
					result =(double)num1 / num2;
				}
				break;
			case "%" : result = num1 % num2; break;
		
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>계산결과출력</title></head>");
		out.println("<body>");
		
		out.println("<h2> 계 산 결 과 </h2><hr>");
//		out.printf("%d %s %d = %.1f\n", num1, op, num2, result);
		out.printf("%d %s %d = ", num1, op, num2);
		
		if(calcOK ==true) {
			out.println(result);
		}else {
			out.println("계산 불능(0으로 나누기)");
		}
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
