<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<title>Вход</title>
</head>
<body>
	<div class='container'>
		<div class='wrapper'>
			<h1>Вход</h1>
			<hr />
		
			<form action="${pageContext.request.contextPath}/login" method="post">
				Логин: <input type="text" name="name" />
				</br/> 
				Пароль:&nbsp;&nbsp; <input type="password" name="passwd" />
				</br/> 
				<input type="submit" value="Enter">
			</form>
		
			<a href="signup.jsp">Зарегистрироваться</a>
		</div>
	</div>
</body>
</html>