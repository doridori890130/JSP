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
<h1>Product List Page</h1>
	<table border="1">
	<thead>
	<tr>
		<th>pno</th>
		<th>pname</th>
		<th>regDate</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="pvo" >
			<tr>
				<td>${pvo.pno }</td>
				<td> <a href="detail.pd?pno=${pvo.pno }"> ${pvo.pname }</a></td>
				<td>${pvo.regdate }</td>
			</tr>
		</c:forEach>	
	</tbody>
	</table>
	<a href="register.pd"><button type="button">상품등록</button></a>
	<a href="index.pd"><button type="button">index</button></a>
</body>
</html>