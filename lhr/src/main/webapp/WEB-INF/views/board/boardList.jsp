<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>게시글 목록</h1></div>
	<div>
		<form id="frm" method="post">
			<select name="state" id="state">
				<option value="1">전 체</option>
				<option value="2">작성자</option>
				<option value="3">제 목</option>
				<option value="4">내 용</option>
			</select>&nbsp;
			<input type="text" id="key" name="key">&nbsp;
			<button type="button" onclick='searchBoard()'>검 색</button>
		</form>
	</div><br>
	<div>
		<table id="tb" border="1">
			<thead>
				<tr>
					<th width="70">순 번</th>
					<th width="150">작성자</th>
					<th width="300">제 목</th>
					<th width="150">작성일자</th>
					<th width="70">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty board }">
					<c:forEach items="${board }" var="board">
						<tr onclick="eventList(${board.boardId})">
							<td>${board.boardId }</td>
							<td>${board.boardName }</td>
							<td>${board.boardTitle }</td>
							<td>${board.boardDate }</td>
							<td>${board.boardHit }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty board }">
					<tr>
						<td colspan="6" align="center">게시글이 없습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<form id="frm2" action="getContent.do" method="post">
			<input type="hidden" id="boardId" name="boardId">
		</form>
	</div><br>
	<div>
		<button type="button" onclick="location.href='boardInsertForm.do'">글 등록</button>
	</div>
</div>

<!-- 이벤트 -->
<script>
function eventList(data) {
	 frm2.boardId.value = data;
	 frm2.submit();
} 
	function searchList() {
		let list = document.querySelector('tbody');
		list.innerHTML = '';
		let field = ['boardId','boardName','boardTitle','boardDate','boardHit'];
		fetch('ajaxSearchList.do', {
			method : 'POST',
			body : new FormData(document.getElementById('frm'))
		})
		.then(response => response.json())
		.then(data => { 
			data.forEach(n => {
				console.log('d');
				let tr = document.createElement('tr');
				field.forEach(f => {
				let td = document.createElement('td');	
				td.innerHTML = n[f];
				tr.appendChild(td);
				})
				list.appendChild(tr);
			})
		})
	}
</script>
</body>
</html>