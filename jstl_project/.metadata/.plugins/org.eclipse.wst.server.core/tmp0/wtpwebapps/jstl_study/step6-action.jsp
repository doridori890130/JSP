<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//post 방식의 한글처리(인코딩설정)
		request.setCharacterEncoding("utf-8");
	%>
	${param.name } 의 주문물건 
	<hr>
 <c:forEach items="${paramValues.food}" var="fname" varStatus="order" >
 	${order.count }.
 	${fname }
 	<br>
 </c:forEach>

 

</body>
</html>