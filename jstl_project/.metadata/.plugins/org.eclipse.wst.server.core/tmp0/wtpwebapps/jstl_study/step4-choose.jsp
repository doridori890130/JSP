<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.PersonVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL choose 연습</title>
</head>
<body>
 <!--model 안에 PersonVO class 생성 맴버변수는 name, age 만 추가  -->
 	<%
 		PersonVO person = new PersonVO("홍길동",21);
 		request.setAttribute("pvo", person);
 	%>
 	
 	이름 : ${pvo.name } <br>
 	나이 : ${pvo.age } <br>
 	
 
 <!--EL 방식으로 NAME AGE 출력  -->
 <c:if test="${pvo.age > 20}">
 	이름 : ${pvo.name } <br>
 	나이 : ${pvo.age } <br>
 	성인입니다.
 </c:if>
 <br> <br>
 <!-- 다중조건을 사용할 경우 choose when, otherwise  -->
 <c:choose>
 	<c:when test="${pvo.age >=10 && pvo.age <20}">
 	청소년입니다. <br>
 	</c:when>
 	<c:when test="${pvo.age >=20}">
 	성인입니다 <br>
 	</c:when>
 	<c:otherwise>
 	유아입니다 <br>
 	</c:otherwise>
 </c:choose>
 
<!-- step5-form.jsp : 이름과 나이를 입력받기 step-5-action.jsp 로 전송  -->
<!-- 나이가 20이상이면 성인, 15세 이상이면 청소년, 5살이상이면 어린이, 1상이면 유아, 나머지는 아직태어나지않았습니다  -->
</body>
</html>