<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL if</title>
</head>
<body>
	<!-- JSTL if 조건  -->
	<c:if test="true">
	<!-- test 조건이 참일경우에만 실행 -->
		true 이므로 실행되어야함.
	</c:if>
	
	<!-- Query String 방식 -->
	<a href="step3-if.jsp?age=12&nick=apple">step3-if 다시 호출</a>
	<br> <br>
	닉네임 : ${param.nick } <br>
	나이 : ${param.age } <br>
	
	<!-- age 가 5를 초과 -->
	<c:if test="${param.age > 5 && param.nick =='apple' }">
		닉네임 : ${param.nick } <br>
		나이 : ${param.age } <br>
	</c:if>
	
</body>
</html>