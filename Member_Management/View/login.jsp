<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty errorMessage}">
		<ul>
			<c:forEach items="${errorMessage}" var="error">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form action="<%=request.getContextPath() %>/main.do?action=login" method="post">
		아이디 : <input type="text" name="id"><br/>
		비밀번호 : <input type="password" name="pwd"><br/>
		
		<input type="submit" value="로그인"/>		
		<input type="reset" value="취소"/>
	
	</form>
</body>
</html>