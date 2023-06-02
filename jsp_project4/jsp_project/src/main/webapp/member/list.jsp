<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Page</title>
</head>
<body>
<h1>List Page</h1>

<table border="1">
<tr>
<th>id</th>
<th>passWord</th>
<th>email</th>
<th>age</th>
<th>reg_date</th>
<th>last_login</th>
</tr>
<c:forEach items="${list }" var="mvo">
<tr>
<td>${mvo.id }</td>
<td>${mvo.password }</td>
<td>${mvo.email }</td>
<td>${mvo.age }</td>
<td>${mvo.reg_date }</td>
<td>${mvo.last_login }</td>
</tr>
</c:forEach>
</table>
</body>
</html>