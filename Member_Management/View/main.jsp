<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.id != null}">
			<h2>${sessionScope.id}님 로그인 중</h2>
			<h2>로그인 사용자수 : ${applicationScope.loginCount}명</h2>
			<ul>
				<li><a href="${pageContext.request.contextPath}/main.do?action=list">사용자 목록</a></li>
				<li><a href="<%=request.getContextPath() %>/main.do?action=logout">로그아웃</a></li>
			</ul>
		</c:when>
		
		<c:otherwise>
			<h2>로그인 후 이용하세요.</h2>
			<a href="<%=request.getContextPath()%>/login.jsp">로그인하러 가기</a>
		</c:otherwise>
	</c:choose>
	
</body>
</html>