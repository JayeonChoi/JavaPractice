<%@page import="com.scsa.model.vo.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/css/table1.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1 style="text-align:center">도서 목록 화면</h1>
	<table>
		<thead>
			<tr>
				<th>도서번호</th>
				<th>도서제목</th>
				<th>저자</th>
				<th>가격</th>
			</tr>
		</thead>
		
		<tbody>
		<c:choose>
			<c:when test="${not empty bookList}"> 
				<c:forEach items="${bookList}" var="book">
					<tr>
					<td>${book.booknum}</td>
					<td><a href="<%=request.getContextPath()%>/view.do?booknum=${book.booknum}">
					${book.title}
					</a></td>
					<td>${book.writer}</td>
					<td>${book.price}원</td>
				</tr>
				</c:forEach>
			</c:when>
			
			<c:otherwise>
				<tr>
					<td colspan="2">조회된 데이터가 없습니다.</td>
				</tr>			
			</c:otherwise>
		</c:choose>

		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><a href="<%=request.getContextPath()%>/book/BookInput.jsp">
				도서등록</a></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>