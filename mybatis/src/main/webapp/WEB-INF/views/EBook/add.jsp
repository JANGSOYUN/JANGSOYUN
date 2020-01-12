<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서등록</title>
</head>
<body>
	<h1>도서등록</h1>
	<hr>
	<form method="POST">
		<p>
			<input name="bpicture" required placeholder="책사진">
		</p>
		<p>
			<input name="btitle" required placeholder="책제목">
		</p>
		<p>
			<input name="bfield" required placeholder="분야">
		</p>
		<p>
			<input name="bwriter" required placeholder="저자">
		</p>
		<p>
			<input name="btrans" required placeholder="옮김">
		</p>
		<p>
			<input name="bpublisher" required placeholder="출판사">
		</p>
		<p>
			<input name="bdate" required placeholder="출간일">
		</p>
		<p>
			<input name="bprice" required placeholder="가격">
		</p>
		<p>
			<textarea rows="100" cols="3000" name="bintro" placeholder="책소개"></textarea>
		</p>
		<p><input type="submit" value="등록">
			<input type="button" value="취소" onclick="location.replace('${pageContext.request.contextPath}')"></p>
	</form>
</body>
</html>