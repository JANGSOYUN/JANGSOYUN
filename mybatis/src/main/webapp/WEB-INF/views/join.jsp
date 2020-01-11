<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JOIN</title>
</head>
<body>
<h1>JOIN</h1>
<input type="button" value="HOME" onclick="location.replace('${pageContext.request.contextPath}')">
<hr>
<form method="POST">
	<p><input name="userid" placeholder="아이디" required></p>
	<p><input name="userpw" type="password" placeholder="비밀번호" required></p>
	<p><input name="confirmpw" type="password" placeholder="비밀번호 확인" required></p>
	<p><input name="username" placeholder="이름"></p>
	<p><input type="submit" value="회원가입"></p>
</form>
</body>
</html>