<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join Page</title>
</head>
<body>
<h1>Join Page</h1>
<form action="/mem/register" method="post" > <br>
	ID : <input type="text" name="id"  placeholder="ID"> <br>
	PW : <input type="password" name="pw" > <br>
	EMAIL : <input type="email" name="email" placeholder="abc@example.com" > <br>
	AGE : <input type="text" name=age > <br>
	
	<button type="submit" >가입완료</button>
</form>
</body>
</html>