<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	'Lprod자료 가져오기' 버튼을 클릭하면 DB에 저장된 LPROD테이블의 전체 데이터를 
	가져와서 아래의 id속성값이'result'인 div태그 영역에 출력하는 페이지를 작성하시오.
	
	Ajax를 사용하고 응답 데이터는 JSON으로 받아서 처리하시오.
	
	전체 데이터 출력이다
	
	DAO역할 하나 만들구 mapper 변경하고 알아서 해랏 
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#lprodBtn").on("click", function() {
		$.ajax({
			url : "<%= request.getContextPath()%>/lprodList.do",
			type : "post",
			success : function(resData) {
				var htmlCode = "<table border='1'>";
				htmlCode += "<tr><td>LPROD_ID</td><td>LPROD_GU</td><td>LPROD_NM</td></tr>";
				$.each(resData, function(i,v) {
					htmlCode += "<tr><td>" + v.lprod_id + "</td>";
					htmlCode += "<td>" + v.lprod_gu + "</td>";
					htmlCode += "<td>" + v.lprod_nm + "</td></tr>";
				});
				htmlCode + "</table>";
				
				$("#result").html(htmlCode);
			},
			error : function(xhr) {
				alert("오류 코드: " + xhr.status);
			},
			dataType : "json"
		})	
	})
	//---------------------------------------
	$("#lprodBtn2").on("click", function() {
		location.href = "<%=request.getContextPath()%>/lprodList2.do"
	});
	
})

</script>
</head>
<body>
<form>
	<input type="button" id="lprodBtn" value="Lprod자료 가져오기(Ajax 비동기)">
	<input type="button" id="lprodBtn2" value="Lprod자료 가져오기(Non Ajax 동기)">
</form>
<h3>Lprod 자료 목록</h3>
<div id="result"></div>
</body>
</html>