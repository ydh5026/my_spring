<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="${path}/shop/list.do">상품목록</a> |
<a href="${path}/shop/write.do">상품등록</a> |
<a href="${path}/pdf/list.do ">PDF</a> |

<a href="${path}/chart/chart1.do">구글차트(jason)</a> |
<a href="${path}/chart/chart2.do">구글차트(db)</a> |
<a href="${path}/jchart/chart1.do">JFreeChart(png)</a> |
<a href="${path}/jchart/chart2.do">JFreeChart(pdf)</a> |

<c:choose>
	<c:when test="${sessionScope.admin_userid == null}">
		<a href="${path}/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.admin_name}님이 로그인중입니다.
		<a href="${path}/admin/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
<hr>

