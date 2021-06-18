<%@ page import="com.notes.model.User" %>
<%@ page import="com.notes.model.DAO" %>
<%@ page import="com.notes.model.Note" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/view/style.css" />
	<title>Заметки</title>
</head>
<body>
<%
	User user = (User) request.getAttribute("user");
	Integer imp = (Integer) request.getAttribute("imp");
	DAO dao = new DAO();
	String name = user.getName();
	Integer user_id = user.getId();

	String action = (String) request.getAttribute("action");

	System.out.println(action);
	String search = (String) request.getAttribute("search");

	List<Note> notes = dao.getNotes(user.getId(), imp, search);

%>
<a href="view/index.jsp"><button>exit</button></a>

<h1>
	Добро пожаловать, <%= name %>
</h1>
<hr />
<div class='container'>
	<form action="main" method="post" class='new-note-wrapper'>
		<input type="hidden" name="action" value="add" />

		<label>
<textarea
		name="note"
		placeholder="Заметка"
		rows="10" cols="30">
</textarea>
		</label>

		<br/>

		<label for="imp">Выберите важность заметки.</label>
		<label>
			<select name="imp">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
			</select>
		</label>

		<br/>

		<input type="hidden" name="user_id" value="<%=user_id%>" />
		<input type="hidden" name="userName" value="<%=name%>" />
		<input type="submit" value="Создать заметку">
	</form>

	<br>

	<form action="main" method="get" class='filter-wrapper'>
		Поиск:
		<% if (search !=null){ %>
		<label>
			<textarea type="text" name="textSearch"><%=search%></textarea>
		</label>
		<%} else{%>
		<label>
			<textarea type="text" name="textSearch"></textarea>
		</label>
		<%}%>
		<label>
			<select name="imp">
				<option value="1">По убыванию важности</option>
				<option value="2">По возрастанию важности</option>
				<option value="0">По умолчанию</option>
			</select>
		</label>
		<input type="hidden" name="user_id" value="<%=user_id%>" />
		<input type="hidden" name="userName" value="<%=name%>" />
		<input type="submit" value="Фильтровать" />
	</form>

	<br/>

	<table border="1">
		<% for (Note note : notes) {
			String text = note.getNote();
			int note_id = note.getId();%>
		<tr>
			<td><%= note.getNote() %></td>
			<td><%= note.getImportance() %></td>

			<td>
				<form action="main" method="post">
					<input type="hidden" name="action" value="edit" />
					<input type="hidden" name="note_id" value="<%=note_id%>" />
					<input type="hidden" name="text" value="<%=text%>" />
					<input type="hidden" name="user_id" value="<%=user_id%>" />
					<input type="hidden" name="userName" value="<%=name%>" />
					<input type="hidden" name="status" value="editing">
					<input type="submit" value="Редактировать">
				</form>
			</td>

			<td>
				<form action="main" method="post">
					<input type="hidden" name="action" value="del" />
					<input type="hidden" name="note_id" value="<%=note_id%>" />
					<input type="hidden" name="user_id" value="<%=user_id%>" />
					<input type="hidden" name="userName" value="<%=name%>" />
					<input type="submit" value="Удалить">
				</form>
			</td>
		</tr>


		<% } %>
	</table>
</div>
</body>
</html>