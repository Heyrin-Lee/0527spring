<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<div><h1>공지사항</h1></div>
	<br>
	<div>
	<table border="1">
					<tr>
						<th width="70">작 성 자</th>
						<td width="870">${content.boardName }</td>
					</tr>
					<tr>
						<th>제 목</th>
						<td>${content.boardTitle }</td>
					</tr>
					<tr>
						<th>작성일</th>
						<td>${content.boardDate }</td>
					</tr>
					<tr>
						<th>조회수</th>
						<td>${content.boardHit }</td>
					</tr>
					<tr>
						<th height="400">작성글</th>
						<td>${content.boardContents }</td>
					</tr>
		</table>
	</div><br>
	<div>
		<button type="submit" onclick="location.href='boardUpdateForm.do?boardId=${content.boardId}'">글 수정</button>
		<button type="submit" onclick="removeCheck()">글 삭제</button>
		<button type="submit" onclick="location.href='boardList.do'">글 목록</button>
	</div>
</div>
</body>
<script>
	function removeCheck() {
		if(confirm("해당 글을 지우시겠습니까?") == true) {
			location.href="boardDelete.do?boardId="+${content.boardId};
		}else
			false;
	}
	
</script>
</body>	
</html>