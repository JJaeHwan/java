<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>File Upload 연습</h3>
<!-- 
	파일을 업로드 하려면 <form>태그의 method는 'POST'로 
	enctype속성은 'multipart/form-data'로 반드시 설정해야 한다.
 -->
<form action="<%=request.getContextPath()%>/fileUpload.do" method="post" enctype="multipart/form-data">
  - 작성자 이름: <input type="text" name="username"><br><br>
  - 파일 선택(1개) : <input type="file" name="upFile1"><br><br>
  - 파일 선택(여러개) : <input type="file" name="upFile2" multiple><br><br>
  <input type="submit" value="전송">

</form>
<br><hr><br>
<a href="<%=request.getContextPath()%>/fileList.do">전체 파일 목록 보기</a>

</body>
</html>