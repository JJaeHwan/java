<%@page import="kr.or.ddit.vo.FileInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	//컨트롤러가 보낸 데이터 받기
	List<FileInfoVO> fileList = (List<FileInfoVO>)request.getAttribute("fileList");
%>
</head>
<body>
<h3>전체 파일 목록</h3><br><hr><br>
<a href="<%=request.getContextPath()%>/fileUpload.do">파일 업로드</a>
<table border="1">
<tr>
	<td>번호</td><td>작성자</td><td>저장파일명</td><td>원본 파일명</td><td>파일크기</td><td>날짜</td><td>비고</td>
</tr>

<%
if(fileList==null || fileList.size()==0){
%>

<tr><td colspan="7" style="text-align: center"> 저장된 파일 목록이 하나도 없습니다.</td></tr>

<% 
}else{
	for(FileInfoVO fileVo : fileList){
%>

<tr>
	<td><%=fileVo.getFile_no() %></td>
	<td><%=fileVo.getFile_writer() %></td>
	<td><%=fileVo.getSave_file_name() %></td>
	<td><%=fileVo.getOrigin_file_name() %></td>
	<td><%=fileVo.getFile_size() %></td>
	<td><%=fileVo.getFile_date() %></td>
	<td>DownLoad</td>
</tr>
<%
	}
}
%>
</table>


</body>
</html>