<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Modify Page</title>
</head>
<body>
<h1>Board Modify Page</h1>
<form action="/brd/edit" method="post" enctype="multipart/form-data">
bno : <input type="text" name="bno" value="${bvo.bno }" readonly="readonly"><br>
title : <input type="text" name="title" value="${bvo.title }"><br>
writer : <input type="text" name="writer" value="${bvo.writer }" readonly="readonly"><br>
reg_date : <input type="text" name="reg_date" value="${bvo.reg_date }" readonly="readonly"><br>
content : <textarea rows="3" cols="30" name="content">${bvo.content }</textarea><br>
image_file : <img alt="없음" src="/_fileUpload/th_${bvo.image_file }"> 
		<input type="hidden" name="image_file" value="${bvo.image_file }" >
		<input type="file" name="new_file" >
<button>수정완료</button>
</form>
</body>
</html>