<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee Form</title>
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
	<form:form commandName="employee" action="employee_save" method="post">
		<fieldset>
			<legend>Add an Employee</legend>
			<p>
				<label for="name"> Name: </label>
				<form:input id="name" path="name"/>
			</p>
			<p>
				<form:errors path="birthday" cssClass="error" />
			</p>
			<p>
				<label for="birthday"> Birthday: </label>
				<form:input id="birthday" path="birthday" />
			</p>
			<p id="buttons">
				<input id="reset" type="reset" tabindex=4></input>
				<input id="submit" type="submit" tabindex=5 value="Add Employee"></input>
			</p>
		</fieldset>
	</form:form>
</div>
</body>
</html>