<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품목록</h2>
<button type="button" id="btnAdd">상품등록</button>
<table border="1" width="500px">
	<tr>
		<th>상품ID</th>
		<th>&nbsp;</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
	
	<c:forEach var="row" items="${list}">
		<tr align="center">
			<td>${row.product_id}</td>
			<td><img src="${path}/images/${row.picture_url}" width="70" height="70"></td>
			<td>
				<a href="${path}/shop/detail/${row.product_id}">${row.product_name}</a>
				<c:if test="${sessionScope.admin_userid != null}">
					<br>
					<a href="${path}/shop/edit/${row.product_id}">[편집]</a>
				</c:if>
			</td>
			<td>
				<fmt:formatNumber value="${row.price}" pattern="#,###" />
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>