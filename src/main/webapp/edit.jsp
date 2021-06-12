<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<%@ page import="java.util.*,com.notes.model.*,com.notes.controller.*"%>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css" />
	<title>Редактировать</title>
	<%
		String nome = request.getParameter("userName");
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		String note = request.getParameter("text");
		Integer note_id = Integer.parseInt(request.getParameter("note_id"));
	%>
</head>
<body>
<h1 style="text-align: center;">Редактировать</h1>
<hr />
<form action="main" method="post">
	<input type="hidden" name="action" value="edit" />
	<textarea name="newText" rows="10" cols="30"><%=note%></textarea>
	<select name="imp">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
	</select> <input type="hidden" name="note_id" value="<%=note_id%>" /> <input
		type="hidden" name="user_id" value="<%=user_id%>" /> <input
		type="hidden" name="userName" value="<%=nome%>" /> <input
		type="hidden" name="status" value="Отредактировано"> <input
		type="submit" value="Потвердить редактирование">
</form>
</body>
</html>