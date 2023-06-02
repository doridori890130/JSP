<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보 변경</h1>
  <form action="/mem/edit" method="post" >
  
 	<input type="hidden" name="id" value="${ses.id }" > <br>
  ID : <input type="text" name="id" value="${ses.id }" disabled="disabled"  > <br>
  <!--id : <input type="text" readonly="readonly"  -->
  PassWord : <input type="text" name="password" value="${ses.password }" > <br>
  email : <input type="text" name="email" value="${ses.email }" > <br>
  age : <input type="text" name="age" value="${ses.age }"> <br>
  <input type="hidden" name="reg_date" value="${ses.reg_date }"> <br>
  <input type="hidden" name="last_login" value="${ses.last_login }"> <br>
  <br>
  <button type="submit" >변경완료</button>
  </form>
  <br>
  <a href="/mem/remove"><button>회원탈퇴</button></a>
</body>
</html>