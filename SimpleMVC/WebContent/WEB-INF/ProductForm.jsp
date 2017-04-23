<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product Form</title>
<style type="text/css">@import url(main.css);</style>
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
	<form action="product_save.enhance" method="post">
		<fieldset>
			<legend>Add a product</legend>
			<p>
				<label for="name"> Product Name: </label>
				<input type="text" id="name" name="name" tabindex=1></input>
			</p>
			<p>
				<label for="description"> Description: </label>
				<input type="text" id="description" name="description" tabindex=2></input>
			</p>
			<p>
				<label for="price"> Price: </label>
				<input type="text" id="price" name="price" tabindex=3></input>
			</p>
			<p id="buttons">
				<input id="reset" type="reset" tabindex=4></input>
				<input id="submit" type="submit" tabindex=5 value="Add Product"></input>
			</p>
		</fieldset>
	</form>
</div>
</body>
</html>