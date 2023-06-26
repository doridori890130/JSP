<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
text-align: center;
}
body{
	
	text-align: center;
}
h1{
height : 300px;
background-image:url("/resources/img/오둥이2.jpg");
padding-top:20px;
text-align: center;
color: white;
}
form{
background-color: #F5F5DC;
margin: auto;
width: 800px;
height: 650px;
padding-top:50px;
text-align: center;
padding-left: 30px;
padding-right: 30px;
}
img{
margin-top:70px;
float: right;
}
#homebtn{
margin-right: 29%;
	float: right;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Register Page</title>
</head>
<body>
<h1>오둥오둥</h1>

<br> 
<form action="/board/register" method="post" enctype="multipart/form-data">
<h3>게시물 등록</h3> <br> <br>
<div class="input-group mb-3">
  <span class="input-group-text" id="inputGroup-sizing-default">제목</span>
  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="title" placeholder="제목"> <br>
</div>
<div class="input-group mb-3">
  <span class="input-group-text" id="inputGroup-sizing-default">작성자</span>
  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="writer" value="${ses.id }" readonly="readonly"> <br>
</div>
<div class="input-group mb-3">
  <span class="input-group-text" id="inputGroup-sizing-default">내용</span>
  <br> <textarea rows="10" cols="100" name="content"></textarea><br>
  
</div>
file : <input type="file" id="file" name="files" multiple style="display:none">
<button type="button" id="trigger" >FileUpload</button>
<br> <br>
<div id="fileZone">

</div>

<button type="submit" class="btn btn-primary" id="regBtn" >등록</button>
</form> <br>
<a href="/"><button type="button" class="btn btn-primary" id="homebtn">home</button></a>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
</body>
</html>