<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<h1>modify Page</h1>
<c:set var="bvo" value="${bdto.bvo }" ></c:set>
<form action="/board/modify" method="post" enctype="multipart/form-data" >
번호 : <input type="text" name="bno" value="${bvo.bno }" readonly="readonly" > <br>
제목 : <input type="text" name="title" value="${bvo.title }" > <br>
작성자 : <input type="text" name="writer" value="${bvo.writer }" readonly="readonly" > <br>
작성일 : <input type="text" name="reg_date" value="${bvo.reg_date }" readonly="readonly" > <br>
내용 : 
<textarea rows="10" cols="30" name="content">${bvo.content }</textarea>
<br>
<!-- 파일 표시 라인  -->
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
					<div>${fvo.file_name }</div>
					<button type="button" class="file-x" data-uuid="${fvo.uuid }">X</button>
		   </li>
		</c:forEach>
	</ul>
</div>

<!--파일 등록 라인  -->
file : <input type="file" id="file" name="files" multiple style="display:none">
<button type="button" id="trigger" >FileUpload</button>
<br> <br>
<div id="fileZone">

</div>
<button type="submit" id="regBtn" >수정</button>
</form>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<script type="text/javascript" src="/resources/js/boardModify.js"></script>
</body>
</html>