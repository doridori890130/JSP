<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="edit.pd" method="post">
	<input type="hidden" name="pno" value="${pvo.pno }" >
	Pno : <input type="text" name="pno" value="${pvo.pno }" disabled="disabled" > <br>
	Name : <input type="text" name="pname" value="${pvo.pname }"> <br>
	Price : <input type="text" name="price" value="${pvo.price }"> <br>
	regdate : <input type="text" name="regdate" value="${pvo.regdate }" disabled="disabled" > <br>
	Madeby : <input type="text" name="madeby" value="${pvo.madeby }"> <br>
	
  	<button type="submit" >완료</button>
	</form>
  
</body>
</html>