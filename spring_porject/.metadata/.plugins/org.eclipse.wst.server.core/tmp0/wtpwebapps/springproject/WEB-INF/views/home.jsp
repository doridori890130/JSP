<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<style type="text/css">
*{
margin: 0;
padding: 0;
}

body{
text-align: center;
magin:0;
background-image: url("/resources/img/배경3.jpg");
background-repeat: no-repeat;
background-size: cover;
}

#main{
margin: auto;
width: 500px;
height: 400px;
text-align:center;
position: relative; 
background-color: #F5F5DC;

}
#mainbase{
width: 300px;
height: 300px;
text-align: center;
border:dotted; 
margin:0;
position: absolute;
top: 12%;
left: 20%;
transform:tranlate(-50%,-50%); 
background-color: #FFE4C4;
}

img{
background-repeat: no-repeat;
float: right;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<title>Main</title>
</head>
<body>
<jsp:include page="./layout/header.jsp"></jsp:include>
<h1>
	Hello Project!  
</h1>


<div id=main>
	<div class="d-grid gap-2 d-md-block" id="mainbase">
	  <h3>Main Page</h3> <br>
	  <c:if test="${ses.id != null }">
	  <a href="/user/logout"><button class="btn btn-primary" type="button">로그아웃</button></a>	  <br><br>
	  <a href="/board/register"><button class="btn btn-primary" type="button">게시글등록</button></a> <br><br>
	  <a href="/board/list"><button class="btn btn-primary" type="button">게시글목록</button></a> <br><br>
	  </c:if>
	  <c:if test="${ses.id==null }">
	  <a href="/user/login"><button class="btn btn-primary" type="button">로그인</button></a>
	  <a href="/user/signup"><button class="btn btn-primary" type="button">회원가입</button></a>
	  <a href="/board/list"><button class="btn btn-primary" type="button">게시글목록</button></a>	  
	  </c:if>
	  <c:if test="${ses.id!=null }">
	  <h4>${ses.id }님 환영합니다</h4>
	  </c:if>
	</div>
</div>


<img alt="없음" src="/resources/img/오둥이3.jpg">


<script type="text/javascript">
const msg_login = `<c:out value="${msg_login}"/>`;
const msg_logout = `<c:out value="${msg_logout}"/>`;
console.log(msg_login);
if(msg_login === '0'){
	alert("로그인실패");
}
if(msg_logout ==="1"){
	alert("로그아웃 되었습니다");
}
</script>
</body>
</html>
