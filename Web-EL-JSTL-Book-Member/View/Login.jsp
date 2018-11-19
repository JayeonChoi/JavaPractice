<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 화면</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/login.do" method="post">
		<table border="1">
			<tr>
				<td style="text-align:center; background-color:gray;" colspan="2">로그인</td>
			</tr>
			<tr>
				<td style="text-align:right">아이디</td>
				<td><input type="text" name="id"><br/></td>
			</tr>
			<tr>
				<td style="text-align:right">비밀번호</td>
				<td><input type="password" name="pwd"><br/></td>
			</tr>
			<tr>
				<td style="text-align:center" colspan="2">
					<input type="submit" value="로그인"/>		
					<input type="reset" value="취소"/>
				</td>		
			</tr>
		</table>
		
	</form>
</body>
</html>