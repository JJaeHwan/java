<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	HTML 주석
-->
<%--
 	JSP 주석입니다.
--%>
<%
	// 이 영역은 JSP문서에서 JAVA명령을 사용할 수 있는 영역으로 '스크립트릿' 이라고 한다.
	String name = "고윤정";
%>  
<%--
<%= 변수명 또는 수식%> ==> JSP에서 변수나 수식의 결과를 출력할 때 사용한다. 
 --%>
 
 <!-- 
 	<form>태그의 속성
 	1. action ==> form의 데이터를 받아서 처리할 문서명 또는 서블릿 URL 
 				(이 속성을 설정하지 않으면 현재 문서가 처리할 문서가 된다.)
 	2. method ==> 전송방식(GET 또는 POST) => 기본값 : GET
 	3. target ==> 응답이 나타날 프레임명
 	4. enctype ==> 서버로 파일을 전송할 때는 이 속성에 'multipart/form-data'라고 지정해야 한다.
 					(보통 생략한다.)
  -->
 
 <h2><%=name %>의 Request연습 Form <%=3+4*6 %></h2><hr>
 <form action="/webTest/requestTest01.do" method="post">
 <table border="1">
 <tr>
 	<td>이름</td>
 	<td><input type="text" size="10" name="username"></td>
 </tr>
 
 <tr>
 	<td>직업</td>
 	<td>
 		<select name="job">
 			<option value="무직"> ==무직==</option>
 			<option value="회사원"> ==회사원==</option>
 			<option value="전문직"> ==전문직==</option>
 			<option value="학생"> ==학생==</option>
 		</select>
 	</td>
 </tr>
 <tr>
 	<td>취미</td>
 	<td>
 		<input type="checkbox" name="hobby" value="여행">여행
 		<input type="checkbox" name="hobby" value="낚시">낚시
 		<input type="checkbox" name="hobby" value="세차">세차
 		<input type="checkbox" name="hobby" value="코딩">코딩
 	</td>
 </tr>
 <tr>
 	<td colspan="2" style="text-align:center;">
 		<input type="submit" value="전송">
 		<input type="reset" value="취소">
 	</td>
 </tr>
 
 </table>
 </form>
</body>
</html>