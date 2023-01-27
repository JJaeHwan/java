<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Cookie 연습</h2><hr>
<a href="<%=request.getContextPath() %>/cookieAdd.do">Cookie정보 저장하기</a><br><br>
<a href="<%=request.getContextPath() %>/cookieRead.do">Cookie정보 확인하기</a><br><br>
<a href="<%=request.getContextPath() %>/cookieDelete.do">Cookie정보 삭제하기</a><br><br>
</body>
</html>