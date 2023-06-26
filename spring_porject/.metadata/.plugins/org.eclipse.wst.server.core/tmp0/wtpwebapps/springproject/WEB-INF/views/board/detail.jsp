<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
h1{
text-align: center;
}
#tablecover{
width: 1000px;
margin: auto;
}
li{
 list-style-type: none;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Detail Page</title>
</head>
<body>
<h1>상세 게시판</h1>
<c:set var="bvo" value="${bdto.bvo }" ></c:set>
<div id="tablecover">
<table class="table table-hover">
<tr>
	<th>번호</th>
	<td>${bvo.bno }</td>
</tr>
<tr>
	<th>제목</th>
	<td>${bvo.title }</td>
</tr>
<tr>
	<th>작성자</th>
	<td>${bvo.writer }</td>
</tr>
<tr>
	<th>조회수</th>
	<td>${bvo.read_count }</td>
</tr>
<tr>
	<th>작성일</th>
	<td>${bvo.reg_date }</td>
</tr>
<tr>
	<th>내용</th>
	<td>${bvo.content }</td>
</tr>

</table>
 <!--file 표현 영역  -->
<c:set var="flist" value="${bdto.flist }" ></c:set> 
<div>
	<ul>
		<c:forEach items="${flist }" var="fvo">
			<li>
				<c:choose>
					<c:when test="${fvo.file_type > 0 }">
						<div>
							<img alt="없음" src="/upload/${fn: replace(fvo.save_dir,'\\','/')}/${fvo.uuid}_th_${fvo.file_name}">
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<!-- 파일 아이콘 모양 값을 넣을 수 있음. -->
						</div>
					</c:otherwise>
				</c:choose>
				<div>
					<div>${fvo.file_name }</div>
					${fvo.reg_date }
				</div>
				<span>${fvo.file_size }Byte</span>
		   </li>
		</c:forEach>
	</ul>
</div>
<a href="/board/list" > <button type="button" class="btn btn-info">목록</button> </a>
<!-- 로그인 id 와 게시글의 작성자가 같지 않으면 ,수정 삭제버튼 안보이게  -->
<c:if test="${ses.id !=null && ses.id==bvo.writer}">
<a href="/board/modify?bno=${bdto.bvo.bno }" ><button type="button" class="btn btn-info">수정</button> </a>
<a href="/board/delete?bno=${bdto.bvo.bno }" ><button type="button" class="btn btn-info">삭제</button> </a>
</c:if>

<!-- comment line  -->
<div>
<!-- 댓글 작성 라인  -->
<br><br><br>
<div>
	<span id="cmtWriter">${ses.id }</span>
	<input type="text" id="cmtText" placeholder="Test Add Comment" >
	<button type="button" id="cmtPostBtn" >post</button>
</div>
<!-- 댓글 표시 라인 -->
<div>

		<!-- li 하나 가 하나의 댓글 객체  -->
	<ul id="cmtListArea" >
	</ul>

</div>
</div>
</div>
<script type="text/javascript">
const bnoVal= `<c:out value="${bdto.bvo.bno}"/>`;
console.log("bno : "+bnoVal);
</script>
<script type="text/javascript" src="/resources/js/boardComment.js">
</script>

<script type="text/javascript">
getCommentList(bnoVal);
</script>

</body>
</html>