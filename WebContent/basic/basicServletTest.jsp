<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function callGet() {
	location.href="http://localhost/webTest/servletTest03.do";
}


</script>
</head>
<body>
<h2>Servlet 요청 연습</h2><hr>

<h3>GET방식으로 요청하기1 ==> 링크 방식</h3>
<a href="http://localhost/webTest/servletTest03.do">Get방식 요청 1</a><hr>

<h3>GET방식으로 요청하기2 ==> form태그의 method속성 이용하기</h3>
<!-- <form>태그의 method속성을 생략하거나 method='get'으로설정하면 GET방식으로 요청하게 된다. -->
<form action="http://localhost/webTest/servletTest03.do" method="get">
	<input type="submit" value="Get방식 요청2">
</form>
<hr>

<h3>GET방식으로 요청하기3 ==> javascript의 location.href속성 이용하기</h3>
<form action="">
	<input type="button" value="Get방식 요청3" onclick="callGet()">
</form>
<hr>

<h3>POST방식으로 요청하기 ==> form태그의 method속성을 post로 지정하기</h3>
<form action="http://localhost/webTest/servletTest03.do" method="post">
	<input type="submit" value="Post방식 요청">
</form>
<hr>

<h3>GET방식 요청</h3>
<form action="http://localhost/webTest/RequestTest02.do" method="get">
	<input type="submit" value="Get방식 요청4">
</form>
<hr>

</body>
</html>