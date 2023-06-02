<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List Page</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<h1>Board List Page</h1>
	<!-- 검색라인 -->
	<div class="col-sm-12 col-md-6">
<form action="/brd/page" method="post">
	<div  class="input-group mb-3">
		<c:set value="${pgh.pgvo.type }" var="typed"></c:set>
		<select name="type" class="form-select">
		<!-- selected : 현재 내가 선택한 값 -->
			<option ${typed == null ? 'selected':'' }>Choose...</option>
			<option value="t" ${typed eq 't' ? 'selected':'' }>title</option>
			<option value="c" ${typed eq 'c' ? 'selected':'' }>content</option>
			<option value="w" ${typed eq 'w' ? 'selected':'' }>writer</option>
			<option value="tc" ${typed eq 'tc' ? 'selected':'' }>title or content</option>
			<option value="tw" ${typed eq 'tw' ? 'selected':'' }>title or writer</option>
			<option value="cw" ${typed eq 'cw' ? 'selected':'' }>content or writer</option>
		</select>
		<input class="form-control" type="text" name="keyword" placeholder="Search">
		<input type="hidden" name="pageNo" value="${pgh.pgvo.pageNo }">
		<input type="hidden" name="qty" value="${pgh.pgvo.qty }">
		<button type="submit" class="btn btn-success position-relative">Search
		<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    		${pgh.totalCount }
    		<span class="visually-hidden">unread messages</span></span>
		</button>
		
	</div>
</form>
</div>
	<table border="1">
		<thead>
			<tr>
				<th>Bno</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Content</th>
				<th>Reg_date</th>
				<th>Read_count</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					
					<td>
					<c:if test="${bvo.image_file ne null }">
						<img alt="없음" src="/_fileUpload/th_${bvo.image_file }">
					</c:if>
					<a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.content }</td>
					<td>${bvo.reg_date }</td>
					<td>${bvo.read_count }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="/"><button>index</button></a> <br>
	
	<!-- 페이지네이션 위치  -->
	<!-- 컨트롤러에서 page 정보를 가져와야함 -->
	<!-- startpage~endpage까지 숫자반복 foreach-->
	<!-- 이전페이지  -->
<c:if test="${pgh.prev }">
	<a href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ◁ </a>
</c:if>
<c:forEach begin="${pgh.startPage }" end="${pgh.endpage }" var="i">
	<a href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i } | </a>
</c:forEach>
<!-- 다음페이지 -->
<c:if test="${pgh.next }">
	<a href="/brd/page?pageNo=${pgh.endpage+1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}"> ▷ </a>
</c:if>
</body>
</html>