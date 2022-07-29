<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%if(request.getAttribute("error")==null){ %>
		<pre>${json }</pre>
	<%}else{ %>
		<h1>${error }</h1>
	<%} %>
</body>
</html>