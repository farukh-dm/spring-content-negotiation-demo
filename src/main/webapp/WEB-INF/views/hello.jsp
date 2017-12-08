<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello page</title>
</head>
<body>
<h2>Hello there!</h2>
<p>Please use following links to demonstrate:</p>
<ul>
<li><a href="<%= request.getContextPath()%>/user/1">/user/1</a></li>
<li><a href="<%= request.getContextPath()%>/user/1.xml">/user/1.xml</a></li>
<li><a href="<%= request.getContextPath()%>/user/1.json">/user/1.json</a></li>
<li><a href="<%= request.getContextPath()%>/user/1.xlsx">/user/1.xlsx</a></li>
<li><a href="<%= request.getContextPath()%>/user/1.pdf">/user/1.pdf</a></li>
</ul>

<p>Please use following links to demonstrate Error scenarios:</p>
<ul>
<li><a href="<%= request.getContextPath()%>/error">/error</a></li>
<li><a href="<%= request.getContextPath()%>/error.xml">/error.xml</a></li>
<li><a href="<%= request.getContextPath()%>/error.json">/error.json</a></li>
<li><a href="<%= request.getContextPath()%>/error.xlsx">/error.xlsx</a></li>
<li><a href="<%= request.getContextPath()%>/error.pdf">/error.pdf</a></li>
</ul>

</body>
</html>
