<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


  <table border="1">
  <thead>
  	<tr>
  	<td>상세페이지</td>
  	</tr>
  </thead>
  <tbody>
  <tr>
  	<th>번호 : </th>
  	<td>${pvo.pno }</td>
  </tr>
  <tr>
  	<th>상품명 : </th>
  	<td>${pvo.pname }</td>
  </tr>
  <tr>
  	<th>가격 : </th>
  	<td>${pvo.price }</td>
  </tr>
  <tr>
  	<th>등록일자 : </th>
  	<td>${pvo.regdate }</td>
  </tr>
  <tr>
  	<th>세부내용 : </th>
  	<td>${pvo.madeby }</td>
  </tr>
  	
  </tbody>
  
  </table>
  <a href="modify.pd?pno=${pvo.pno }" > <button type="button" >modify</button></a>
  <a href="remove.pd?pno=${pvo.pno }" > <button type="button" >delete</button></a>
  
</body>
</html>