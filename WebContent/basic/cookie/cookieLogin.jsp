<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 쿠키에 저장된 userid값 가져오기 -->
<%
	String cookieUserid = "";

	Cookie[] cookieArr = request.getCookies();
	if(cookieArr != null){
		for(Cookie cookie : cookieArr){
			if("USERID".equals(cookie.getName())){	// 내가 원하는 쿠키이름으로 해당 쿠키를 찾는다.
				cookieUserid = cookie.getValue();
			
			}
		}
	}

%>


<form action="<%=request.getContextPath()%>/cookieLoginServlet.do" method="post">
<table style="margin: 0 auto">
<tr>
<td>ID : </td>
<td><input type="text" size="15" name="userid" placeholder="ID를 입력하세요."> </td>
</tr>

<tr>
<td>PASS : </td>
<td><input type="password" size="15" name="userpass" placeholder="PassWord를 입력하세요."> </td>
</tr>

<tr>
	<td colspan="2"><input type="checkbox" name="checkID">ID기억하기</td>
</tr>

<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="LogIn"></td>
</tr>

</table>

</form>

</body>
</html>