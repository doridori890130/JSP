<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.PersonVO" %>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//personvo 객체를 이용하여 list에 여러명을 추가(5명)
		
		ArrayList<PersonVO> pvoList = new ArrayList<>();
		pvoList.add(new PersonVO("이수",40));
		pvoList.add(new PersonVO("이석훈",5));
		pvoList.add(new PersonVO("박효신",15));
		pvoList.add(new PersonVO("고윤정",25));
		pvoList.add(new PersonVO("한가인",30));
		
		session.setAttribute("pvo", pvoList);
	%>
	
	<!-- 테이블을 생성하여 -->
<table class="table table-hover" border="1">
  <thead>
  <tr>
  <th>번호</th>
  <th>이름</th>
  <th>나이</th>
  <th>연령대</th>
  </tr>
  </thead>
  <tbody>
  
  <c:forEach items="${pvo }" var="qst" varStatus="num" >

  	<tr>
  		<th>${num.count }</th>
  		<th>${qst.name }</th>
  		<th>${qst.age }</th>
  		<th>  <c:choose>
  	<c:when test="${qst.age >=20 }">성인</c:when>
  	<c:when test="${qst.age <20 }">미성년</c:when>
  </c:choose></th></tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>