<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/book.do" method="post">
		<table border="1">
			<thead style="background-color:gray;">
				<tr>
					<th style="font-size:30px" width="800" height="150" colspan="2">도서 등록 화면<br/></th>
				</tr>
			</thead>
		
			<tbody>
				<tr><td style="text-align:right;" colspan="2"> *표시가 된 항목은 필수 입력 항목입니다. <br/></td></tr>
				<tr><td style="background-color:gray;" colspan="2"><br/></td></tr>
			
				<tr>
					<td>* 도서번호 : </td>
					<td><input type="text" name="booknum" required="required"/><br/></td>
				</tr>
				<tr>
					<td>* 도서제목 : </td>
					<td><input type="text" name="title" required="required"/> <br/></td>
				</tr>
				<tr>
					<td>* 도서 종류 : </td>
					<td>	<select name="sort" style="height:30px" required="required">
							<option value="" disabled selected>해당 항목을 선택하세요</option>
							<option>프로그래밍</option>
							<option>운영체제</option>
							<option>데이터베이스</option>
							<option>네트워크</option>
							</select><br/></td>
				</tr>
				<tr>
					<td> 출판 국가 : </td>
					<td><input type="radio" name=country value="국내"/> 국내도서 
					<input type="radio" name=country value="국외"/> 외국도서 <br/></td>
				</tr>
				<tr>
					<td> 출판일 : </td>
					<td><input type="date" name="date"/> <br/></td>
				</tr>
				<tr>
					<td> 출판사 : </td>
					<td><select name="publisher" style="height:30px">
						<option value="" disabled selected>해당 항목을 선택하세요</option>
						<option>영진출판사</option>
						<option>한빛미디어</option>
						<option>프리렉출판사</option>
						<option>네오솔루션</option>					
						<option>사이버출판사</option>					
					</select> <br/></td>
				</tr>
				<tr>
					<td>* 저자 : </td>
					<td><input type="text" name="writer" required="required"/> <br/></td>
				</tr>
				<tr>
					<td> 도서 가격 : </td>
					<td><input type="number" name="price" height="20px"/> 
					<select name = "won_or_dollar" style="height:20px">
						<option>원</option>
						<option>달러</option>
					</select> <br/></td>
				<tr>
				<tr>
					<td> 요약 내용 : </td>
					<td style="background-color:grey;"><textarea rows="5" cols = "50" name = "desc"> </textarea> <br/>
					</td>
				</tr>
		</tbody>
		
		<tfoot style="background-color:gray;">
			<tr>
				<td colspan="2" style="text-align:center;">
					<input type="submit" value="도서등록"/>		
					<input type="reset" value="취소"/>	
				</td>
			</tr>	
		</tfoot>
	</table>
	</form>	
</body>
</html>