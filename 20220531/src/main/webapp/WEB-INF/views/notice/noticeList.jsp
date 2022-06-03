<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/jquery-3.6.0.min.js"></script>
</head>
<style>
	table tr : hover {
		cursor : pointer;
		background : gray;
	}
</style>
<body>
<div align="center">
	<div><h1>공지사항 목록</h1></div>
	<div>
		<form id="frm">
			<select id="state" name="state">
				<option value="1">전 체</option>
				<option value="2">작성자</option>
				<option value="3">제 목</option>
				<option value="4">내 용</option>
			</select>&nbsp;
			<input type="text" id="key" name="key">&nbsp;
			<button type="button" onclick="searchList2()">검색</button>
		</form>
	</div><br>
	<div>
		<table id="tb" border="1">
			<thead>
				<tr>
					<th width="70">순번</th>
					<th width="150">작성자</th>
					<th width="300">제목</th>
					<th width="150">작성일자</th>
					<th width="70">조회수</th>
					<th width="200">첨부파일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty notices }">
					<c:forEach items="${notices }" var="n">
						<tr onclick="eventList(${n.noticeId })">
							<td>${n.noticeId }</td>
							<td>${n.noticeName }</td>
							<td>${n.noticeTitle }</td>
							<td>${n.noticeDate }</td>
							<td>${n.noticeHit }</td>
							<td>${n.noticeAttech }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty notices }">
					<tr>
						<td colspan="6" align="center">게시글이 존재하지 않습니다</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<form id="frm2" action="" method="post">
			<input type="hidden" id="noticeId" name="noticeId">	
		</form>
	</div><br>
	<div>
		<button type="submit" onclick="location.href='noticeInsertForm.do'">글 등록</button>
	</div>
</div>
</body>

<!-- 그룹이벤트 생성(상세조회) -->

<script>
function eventList(data) {
	let list = document.querySelector('tbody');
	list.addEventListener('click',function(ev) {
		if(ev.target.tagName === 'TD') {
		//console.log(ev.target.parentNode.children[0].textContent);			
		//location.href = 'getContent.do?noticeId='+ev.target.parentNode.children[0].textContent; //get방식
		frm2.noticeId.value=ev.target.parentNode.children[0].textContent; //post방식
		frm2.action="getContent.do";
		frm2.submit();
		}
	})
}
	
	function searchList() {
		let list = document.querySelector('tbody');
		list.innerHTML = '';
		let field = ['noticeId','noticeName','noticeTitle','noticeDate','noticeHit','noticeAttech'];
		fetch('ajaxSearchList.do', {
			method : 'POST',
			body : new FormData(document.getElementById('frm'))
		})
		.then(response => response.json()) //promise객체가 생성되고 (console에 찍으면 promise로 찍히니까 promise객체임)
		.then(data => { //data 속에 json의 배열값이 담기게 된다(출력하고자하는 값)
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

<script>
	//jquery를 이용한 ajax 호출
	
	function searchList2(){
	let state = $("#state").val(); //document.getElementById('state').value와 같다
	let key = $("#key").val();
	
	$.ajax({
		url : "ajaxSearchList.do", //url
		type : "post", //전송방식
		data : {"state" : state, "key" : key}, //전달할 데이터
		datatype : "json", //돌려받을 결과 데이터의 타입 지정 (html, text, xml, json, jsonp.. 가능)
		success : function(data){ //성공했을 때 실행할 함수 결과는 변수(datda)에 담긴다
			//수행할 영역의 함수 만들기
			console.log(data);
			htmlConvert(data);
			},
		error:function(){ //실패했을 때 실행할 함수
			alert("의도치 않은 오류가 발생했습니다.");
		}
	});
	
	function htmlConvert(data) {
		   $("tbody").remove(); //tbody삭제
		   console.log(data);
		   let tbody = $("<tbody/>");
		   $.each(data,function(index, n) {
		      var row = $("<tr onclick='eventList("+n.noticeId+")'/>").append(
		            $("<td/>").text(n.noticeId),
		            $("<td/>").text(n.noticeName),
		            $("<td/>").text(n.noticeTitle),
		            $("<td/>").text(n.noticeDate),
		            $("<td/>").text(n.noticeHit),
		            $("<td/>").text(n.noticeAttech),
		      );
		      tbody.append(row); //행을 추가
		   });
		   $("#tb").append(tbody); //tbody를 추가
		}
	}
</script>
</html>