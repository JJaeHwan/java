package kr.or.ddit.fileupload.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileInfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

@WebServlet("/fileList.do")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Service객체 생성
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		
		// DB에서 전체 파일 정보 목록을 가져와 List에 저장한다.
		List<FileInfoVO> fileList = service.getAllFileInfo();
		
		// 가져온 파일 정보 목록을 View페이지로 보낸다.
		request.setAttribute("fileList", fileList);
		request.getRequestDispatcher("/basic/fileupload/fileList.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
