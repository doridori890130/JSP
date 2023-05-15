<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL param 연습2</title>
</head>
<body>
	<a href="step2-1.jsp">step2-1로 이동</a>
	<hr>
	1. 스크립틀릿 방식으로 파라미터를 전달받음 <br>
	<%=request.getParameter("name")%> <br>
	<%=request.getParameter("address") %> <br>
	<br>
	<br>	
	<hr>
	2. EL 방식으로 파라미터를 전달받음 <br>
	${param.name } <br>
	${param.address } <br>
	
	<!--  EL 방식으로 값이 NULL이 오게되면 브라우저에 아무값도 출력하지 않는다 -->
	
	<hr>
	
	<form action="step2-3.jsp">
		닉네임 : <input type="text" name="nick" /> <br>
		나이 : <input type="text" name="age" /> <br>
		<button type="submit">전송</button>  
	</form>
	
</body>
</html>