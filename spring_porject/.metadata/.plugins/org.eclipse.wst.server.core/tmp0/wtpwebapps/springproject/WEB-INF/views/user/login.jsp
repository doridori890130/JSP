<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Loing page</title>
<style type="text/css">
body{
	background-image : url("/resources/img/배경3.jpg");
	background-repeat:no-repeat;
	background-size:cover;
	text-align: center;
}
h1{
padding-top:20px;
text-align: center;
}
form{
background-color: #F5F5DC;
margin: auto;
width: 800px;
height: 300px;
padding-top:50px;
text-align: center;
padding-left: 30px;
padding-right: 30px;
}
img{
float: right;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<h1>로그인</h1>
<br><br><br><br>
<form action="/user/login" method="post">
<div class="input-group mb-3">
  <span class="input-group-text" id="inputGroup-sizing-default">아이디</span>
  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="id" placeholder="아이디"> <br>
</div>
<div class="input-group mb-3">
  <span class="input-group-text" id="inputGroup-sizing-default">패스워드</span>
  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="pw" placeholder="패스워드"><br>
</div>
<br> <br> <br>
<button type="submit">로그인</button>
</form>
</body>
</html>