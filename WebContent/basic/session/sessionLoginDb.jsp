<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  table,tr,td{
  	border: 1px solid black;
  	border-collapse: separate;
  }
</style>
</head>
<body>
<%
// 세션 정보를 확인하기 위해 세션값을 구한다.
// JSP문서에서 세션은 'session'이라는 이름으로 이미 생성되어 저장되어 있다.
	MemberVO loginMemVo = (MemberVO)session.getAttribute("LoginMember");

%>
<%if(loginMemVo == null) { %>
<!-- 로그인이 안 되었을때 나타날 영역 -->
<form action="<%=request.getContextPath()%>/sessionLoginDb.do" method="post">
<table style=" margin: 0 auto "; border="1" >
<tr>
<td>ID : </td>
<td><input type="text" size="20" name="userid" placeholder="ID를 입력하세요."> </td>
</tr>

<tr>
<td>PASS : </td>
<td><input type="password" size="20" name="userpass" placeholder="PassWord를 입력하세요."> </td>
</tr>

<tr>
	<td colspan="2" style="text-align:center;"><input type="submit" value="LogIn"></td>
</tr>

</table>

</form>
<!--  --------------------------------------------------------------------------------------------- -->
<%
}else{
%>
<!-- 로그인이 성공했을때 나타날 영역 -->
<h3><%=loginMemVo.getMem_id() %>님 반갑습니다.</h3><br><br>

<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>
<!--  --------------------------------------------------------------------------------------------- -->
<%
}
%>
</body>
</html>