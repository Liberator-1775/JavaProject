<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ page
	import="java.util.*,com.notes.model.*,com.notes.controller.*"%>

<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<title>Зарегистрироваться</title>
</head>
<body>
	<div class='container'>
		<div class='wrapper'>
			<h1>Зарегистрироваться</h1>
			<hr />
		
			<form action="${pageContext.request.contextPath}/signup" method="post">
				Логин: <input type="text" name="nome" />
				</br/> 
				Пароль:&nbsp;&nbsp; <input type="password" name="passwd" />
				</br/>
				<input type="submit" value="Enter">
			</form>
			
			<a href="index.jsp">Войти</a>
		</div>
	</div>
</html>