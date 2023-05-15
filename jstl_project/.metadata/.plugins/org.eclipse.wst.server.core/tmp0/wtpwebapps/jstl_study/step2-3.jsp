<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 연산 x 스클립틀릿 방식에서는 넘어오는 parameter값이 문자열로 인식
			age+1 의 값을 전달하면 201 나이 뒤에 1이 추가되는 형태로 전송-->
	1. 스클립틀릿 방식으로 파라미터를 전달받음 <br>
	<%=request.getParameter("nick") %> <br>
	<%=request.getParameter("age") %>세 <br>
	
	<!-- 연산 o El 방식은 내부적으로 형변환이 이루어져 연산이 가능-->	
	2. EL 방식으로 파라미터를 전달받음 <br>
	${param.nick } <br>
	${param.age+1 }세 <br>
	<br>
	<br>
	<hr>
	<form action="step2-4.jsp">
		<input type="checkbox" name="food" value="삼겹살" > 삼겹살 <br>
		<input type="checkbox" name="food" value="목살"> 목살 <br>
		<input type="submit" value="전송">
	</form>
</body>
</html>