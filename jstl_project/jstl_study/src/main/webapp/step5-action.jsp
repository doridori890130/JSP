<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름 : ${param.name } <br>
	나이 : ${param.age } <br>
	<hr>
	
<c:choose>
	<c:when test="${param.age >= 20} ">
	성인입니다 <br>
	</c:when>
	<c:when test="${param.age >= 15 && param.age <20}">
	청소년입니다 <br>
	</c:when>
	<c:when test="${param.age >= 5 && param.age <15}">
	어린입입니다 <br>
	</c:when>
	<c:when test="${param.age >= 1 && param.age < 5}">
	유아입니다 <br> </c:when>
	
	<c:otherwise>
	아직태어나지않았습니다</c:otherwise>
	
</c:choose>

<a href="step6-foreach.jsp">step6으로 이동</a>
	
</body>
</html>