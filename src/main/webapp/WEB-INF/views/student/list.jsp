<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>학생 목록</h2>

<table border="1">
	<tr>
		<td>학번</td>
		<td>이름</td>
		<td>전공</td>
		<td>학년</td>
	</tr>
<!-- var="레코드변수" items="테이블변수" -->
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.student_no}</td>
		<td>${row.name}</td>
		<td>${row.major}</td>
		<td>${row.year}</td>
	</tr>
</c:forEach>	
</table>

</body>
</html>