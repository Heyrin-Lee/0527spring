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
	<div><h1>게시글 수정</h1></div>
	<div>
		<form id="frm" action="boardUpdate.do" method="post">
		<input type="hidden" id="boardId" name="boardId" value="${board.boardId }">
			<table border="1">
			<tr>
				<th width="100">작성자</th>
				<td width="150">
				${board.boardName }
				</td>
				<th width="100">작성일자</th>
				<td  width="150">
					${board.boardDate }
				</td>
			</tr>
			<tr>
				<th width="100">제목</th>
				<td colspan="3">
					<input size="58" type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle }">
				</td>
			</tr>
			<tr>
				<th width="100">내용</th>
				<td colspan="3">
					<textarea rows="10" cols="60" id="boardContents" name="boardContents">
					${board.boardContents }
					</textarea>
				</td>
			</tr>
			</table>
			</div><br>
			<div>
				<input type="submit" value="수정">&nbsp;&nbsp;
				<input type="button" value="목록" onclick="location.href='boardList.do'">&nbsp;&nbsp;
			</div>
		</form>
	</div>
</body>
</html>