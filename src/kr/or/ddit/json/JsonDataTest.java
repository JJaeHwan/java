package kr.or.ddit.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.MemberVO;

@WebServlet("/jsonDataTest.do")
public class JsonDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 응답으로 JSON데이터를 보내기 위한 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		//---------------------------------------
		
		String choice = request.getParameter("choice");

		// 전송할 JSON데이터 만들기
		Gson gson = new Gson();		// Gson객체 생성
		String jsonData = null;		// 변환된 JSON데이터가 저장될 변수
		
		switch (choice) {
		case "string":
			String str = "안녕하세요";	// 응답으로 보낼 데이터 구하기
			jsonData = gson.toJson(str);	// 응답으로 보낼 데이터를 JSON데이터로 변환하기
			break;
			
		case "array" :
			int[] intArr = {100, 200, 300, 400, 500};
			jsonData = gson.toJson(intArr);
			break;
		
		case "obj" :
			MemberVO memVo = new MemberVO();
			memVo.setMem_id("abcdef");
			memVo.setMem_name("JAE");
			memVo.setMem_pass("12345");
			memVo.setMem_tel("010-4724-7168");
			memVo.setMem_addr("대전시 중구 대사동");
			
			jsonData = gson.toJson(memVo);
			break;
		
		case "list" :
			List<Sample> samlist = new ArrayList<Sample>();
			
			samlist.add(new Sample(10, "홍길동"));
			samlist.add(new Sample(20, "이순신"));
			samlist.add(new Sample(30, "강감찬"));
			samlist.add(new Sample(40, "이몽룡"));
			samlist.add(new Sample(50, "성춘향"));
			
			jsonData = gson.toJson(samlist);
			
			break;
			
		case "map" :
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "정재환");
			map.put("tel", "010-4724-7168");
			map.put("addr", "대전");
			
			jsonData = gson.toJson(map);
			
			break;
			
		default:
			break;
		}
		
		System.out.println("변환된 JSON 데이터 : " + jsonData);
		
		// 변환된 JSON데이터를 응답으로 보내기
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		response.flushBuffer();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
