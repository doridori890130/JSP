<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UF-8">
<title>상품등록 페이지</title>
</head>
<body>
	<h1>Product Register Page</h1>
	<br>
	<hr>
	<form action="insert.pd" method="post">
		상품명 : <input type="text" name="pname" > <br>
		가격 : <input type="text" name="price" > <br>
		Madeby : <input type="text" name="madeby" >
		
		<button type="submit">register</button>
	</form>
</body>
</html>