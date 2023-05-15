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
<!-- get방식 : 쿼리스트링을 달고 URL 상에서 이동하는 방식(기본)  -->
<!-- post방식 : 데이터를 숨겨서 가는 방식 보안상, 내용이 많을경우-->
	<form action="step6-action.jsp" method="post" >
		주문자 : <input type="text" name="name" > <br>
		구입물건 : <br>
		<input type="checkbox" name="food" value="삼겹살" >삼겹살<br>
		<input type="checkbox" name="food" value="목살" >목살<br>
		<input type="checkbox" name="food" value="차돌박이" >차돌박이<br>
		<input type="checkbox" name="food" value="콜라" >콜라<br>
		<input type="checkbox" name="food" value="사이다" >사이다<br>
		<input type="submit" value="전송">
	</form>
</body>
</html>