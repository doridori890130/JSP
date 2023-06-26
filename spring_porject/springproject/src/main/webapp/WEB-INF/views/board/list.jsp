<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
div{
	text-align: center;
}
h1{
 text-align: center;
}
#searchwindow{
margin: auto;
}
</style>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>List Page</h1>
<div id="searchwindow" class="col-sm-12 col-md-6">
<form action="/board/list" method="get">
<div  class="input-group mb-3">
	<c:set value="${ph.pgvo.type }" var="typed" ></c:set>
	<select name="type" class="form-select" >
		<option ${typed == null ? 'select':'' } >choose...</option>
		<option value="t" ${typed eq 't' ? 'select':'' } >title</option>
		<option value="c" ${typed eq 'c' ? 'select':'' } >content</option>
		<option value="w" ${typed eq 'w' ? 'select':'' } >writer</option>
		<option value="tc" ${typed eq 'tc' ? 'select':'' } >title of content</option>
		<option value="tw" ${typed eq 'tw' ? 'select':'' } >title or writer</option>
		<option value="cw" ${typed eq 'cw' ? 'select':'' } >content or writer</option>
	</select>
	<input class="formt-conrol" type="text" name="keyword" placeholder="Search" >
	<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo }" >
	<input type="hidden" name="qty" value="${ph.pgvo.qty }" >
	<button type="submit" class="btn btn-success position-relative" >Search</button>
	<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    		${ph.totalCount }
    		<span class="visually-hidden">unread messages</span></span>
		</button>
</div>
</form>
</div>

	<table class="table table-hover">
	<tr>
	<th width="10%">번호</th>
	<th width="40%">제목</th>
	<th width="20%">작성자</th>
	<th width="20%">작성일</th>
	<th width="10%">조회수</th>
	</tr>
	<c:forEach items="${list }" var="bvo" >

	<tr>
	<td>${bvo.bno }</td>
	<td> <a href="/board/detail?bno=${bvo.bno }"> ${bvo.title }</a></td>
	<td>${bvo.writer }</td>
	<td>${bvo.reg_date }</td>
	<td>${bvo.read_count }</td>
	</tr>
	
	</c:forEach>
	</table>
	<!-- paging line -->
	<div>
	<c:if test="${ph.prev }">
	 <a href="/board/list?pageNo=${ph.startPage -1 }&qty=${ph.pgvo.qty}" >◀</a>
	</c:if>
	<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		<a href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}"> ${ i}|</a>
	</c:forEach>
	
	<c:if test="${ph.next }">
	 <a href="/board/list?pageNo=${ph.endPage +1 }&qty=${ph.pgvo.qty}" >▶</a>
	</c:if>
	
	</div>
</body>
</html>