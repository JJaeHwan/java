<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript">
$(function() {
	// 문자열 데이터 받기 
	$('#strBtn').on('click', function() {
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonDataTest.do",
			data : "choice=string" ,	// 요청할 때 보낼 데이터 셋팅하기
			type : "post",	// 전송방식
			//--------------------------------------------------------------------
			success : function(resData) { // resData ==>  응답 데이터가 저장될 변수
				$("#result").html(resData);
			},
			error : function(xhr) {
				alert("오류코드: " + xhr.status);
			} ,
			dataType : "json"
			
		});	
	});
	//-------------------------------------------------
	// 배열
	$("#arrBtn").on("click", function() {
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonDataTest.do",
			data : "choice=array" ,	
			type : "post",	
			success : function(resData) { 
				var htmlCode = "";
				$.each(resData, function(i,v){
					htmlCode += i + "번재 데이터 : " + v + "<br>";	
				});
				
				$("#result").html(htmlCode);
			},
			error : function(xhr) {
				alert("오류코드: " + xhr.status);
			} ,
			dataType : "json"
			
		});	
	});
	//----------------------------------------------------
	// 객체
	$("#objBtn").on("click", function() {
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonDataTest.do",
			data : "choice=obj" ,	
			type : "post",	
			success : function(resData) { 
				var htmlCode = "mem_id : " + resData.mem_id + "<br>";
				htmlCode += "mem_pass : " + resData.mem_pass + "<br>";
				htmlCode += "mem_name : " + resData.mem_name + "<br>";
				htmlCode += "mem_tel : " + resData.mem_tel + "<br>";
				htmlCode += "mem_addr : " + resData.mem_addr + "<br>";
				
				$("#result").html(htmlCode);
				
				
			},
			error : function(xhr) {
				alert("오류코드: " + xhr.status);
			} ,
			dataType : "json"
			
		});	
	});
	//--------------------------------------------
	// 리스트
	$("#listBtn").on("click", function() {
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonDataTest.do",
			data : "choice=list" ,	
			type : "post",	
			success : function(resData) { 
				var htmlCode = "";
				$.each(resData, function(i,v) {
					htmlCode += i + "번째 데이터<br>";
					htmlCode += "NO: " + v.no + "<br>";
					htmlCode += "NAME: " + v.name + "<hr>";
				});
				
				$("#result").html(htmlCode);
				
				
			},
			error : function(xhr) {
				alert("오류코드: " + xhr.status);
			} ,
			dataType : "json"
			
		});	
	});	
	//-----------------------------------------------
	//Map 데이터
	$("#mapBtn").on("click", function() {
		$.ajax({
			url : "<%=request.getContextPath()%>/jsonDataTest.do",
			data : "choice=map" ,	
			type : "post",	
			success : function(resData) { 
				var htmlCode = "이름: " + resData.name + "<br>";
				htmlCode += "전화: " + resData.tel + "<br>";
				htmlCode += "주소: " + resData.addr + "<br>";

				$("#result").html(htmlCode);
				
				
			},
			error : function(xhr) {
				alert("오류코드: " + xhr.status);
			} ,
			dataType : "json"
			
		});	
	});	
});
</script>


</head>
<body>

<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrBtn" value="배열">
	<input type="button" id="objBtn" value="객체 ">
	<input type="button" id="listBtn" value="리스트">
	<input type="button" id="mapBtn" value="Map객체">
</form>
<hr>
<h3>결과 출력</h3>
<div id="result"></div>

</body>
</html>