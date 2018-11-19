<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.scsa.model.vo.Book"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>입력된 도서 정보</h1>
	<table border="1">	
		<tr>
			<td style="background-color:grey; text-align:center;" colspan="2">도서 정보<br/></td>
		</tr>
		<tr>
			<td style="background-color:grey;">도서명</td>
			<td> ${book.title} </td></tr>
		<tr>
			<td style="background-color:grey;">도서번호</td>
			<td>${book.booknum}</td>
		</tr>
		<tr>
			<td style="background-color:grey;">도서분류</td>
			<td>${book.sort}</td>
		</tr>
		<tr>
			<td style="background-color:grey;">도서국가</td>
			<td>${book.country}</td>
		</tr>
		<tr>
			<td style="background-color:grey;">출판일</td>
			<td>${book.date}</td>
		</tr>
		<tr>
			<td style="background-color:grey;">출판사</td>
		 	<td>${book.publisher}</td>
		</tr>
		<tr>
			<td style="background-color:grey;">저자</td>
			<td>${book.writer}</td>
		</tr>
		<tr>
			<td style="background-color:grey;">도서가격</td>
			<td>${book.price} 원</td>
		</tr>
		<tr>
			<td style="background-color:grey;">도서설명</td>
			<td>${book.desc}</td>
		</tr>
		
		</table>
		<a href="<%=request.getContextPath() %>/book/BookInput.jsp" style="text-align:center; text-decoration: underline;">도서등록</a>
		<a href="<%=request.getContextPath() %>/delete.do?booknum=${book.booknum}" style="text-decoration: underline;">도서삭제</a>

</body>
</html>