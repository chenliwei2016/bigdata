<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book Form</title>
<style type="text/css">@import url(/resources/main.css);</style>
</head>
<body>
<div id="global">
	<c:if test="${requestScope.errors != null}"> 
		<p id="errors">
		Errors!
		<ul>
			<c:forEach var="error" items="${requestScope.errors}">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form:form commandName="book" action="book_add" method="post">
		<fieldset>
			<legend>Add a book</legend>
			<p>
				<label for="category">Category: </label>
				<form:select id="category" path="category.id" items="${categories}" itemLabel="name" itemValue="id" />
			</p>
			<p>
				<label for="title"> Book title: </label>
				<form:input id="title" path="title"/>
			</p>
			<p>
				<label for="isbn"> ISBN: </label>
				<form:input id="isbn" path="isbn"/>
			</p>
			<p>
				<label for="author"> Author: </label>
				<form:input id="author" path="author" />
			</p>
			<p id="buttons">
				<input id="reset" type="reset" tabindex=4></input>
				<input id="submit" type="submit" tabindex=5 value="Add Book"></input>
			</p>
		</fieldset>
	</form:form>
</div>
</body>
</html>