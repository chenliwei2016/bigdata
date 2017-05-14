<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Details</title>
<style type="text/css">@import url(/resources/main.css);</style>
</head>
<body>
<div id="global">
	<h1>Book List</h1>
	<a href="<c:url value="/book_input" />">Add a book</a>
	<table>
		<tr>
			<th>Category:</th>
			<th>Title:</th>
			<th>ISBN:</th>
			<th>Author:</th>
		</tr>
		<c:forEach items="${books}" var="book">
			<tr>
			<td>${book.category.name}</td>
			<td>${book.title}</td>
			<td>${book.isbn}</td>
			<td>${book.author}</td>
			<td><a href="<c:url value="/book_edit/${book.id}" />">Edit</a></td>
			<td><a href="<c:url value="/book_delete/${book.id}" />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>