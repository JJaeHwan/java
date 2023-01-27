<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Count</title>
</head>
<body>
<h2>CookieTest02.jsp</h2><hr>
<a href="<%=request.getContextPath() %>/cookieCountServlet.do">Cookie Count 증가하기</a><br><br>
<a href="<%=request.getContextPath() %>/cookieCountDelServlet.do">Cookie Count 초기화 하기</a><br><br>
</body>
</html>