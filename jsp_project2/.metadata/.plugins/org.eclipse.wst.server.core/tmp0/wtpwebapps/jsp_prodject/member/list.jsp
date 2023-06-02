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
<h1>Member List</h1>
<table border="1" >
	<thead>
		<tr>
		<th>id</th>
		<th>email</th>
		<th>age</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="mvo" >
			<tr>
			<td>${mvo.id }</td>
			<td>${mvo.email }</td>
			<td>${mvo.age }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>