<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Details</title>
<style type="text/css">@import url(resources/main.css);</style>
</head>
<body>
<div id="global">
	<h4>Employee saved</h4>
	<h4>${message}</h4>
	<p>
		<h5>Details:</h5>
		Employee Name: ${employee.name} <br/>
		Employee Birthday: ${employee.birthday} <br/>
</div>
</body>
</html>