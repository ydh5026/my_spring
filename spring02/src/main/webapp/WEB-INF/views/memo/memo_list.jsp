<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한줄메모장</title>
<%@ include file="../include/header.jsp" %>
<script>
	function memo_view(idx) { 
		location.href="${path}/memo/view/"+idx;  //파마리터로 보내는것이 아닌 고유 페이지 주소가 됨
	}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>한줄메모장</h2>

<form method="post" action="${path}/memo/insert.do">
	이름 : <input name="writer" size="10">
	메모 : <input name="memo" size="40">
	<input type="submit" value="확인">
</form>
<br>

<table border="1" style="width:500px; text-align:center;" >
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>메모</th>
		<th>날짜</th>
	</tr>
	<c:forEach var="row" items="${list}">
		<tr>
			<td>${row.idx}</td>
			<td>${row.writer}</td>
			<td><a href="#" onclick="memo_view('${row.idx}')">${row.memo}</a></td>
			<td>
				<fmt:formatDate value="${row.post_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>