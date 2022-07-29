<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%if(request.getAttribute("Error") != null){ %>
		<h1>Upload Filed due to ${Error}</h1>
	<%} %>
	<%if((boolean)request.getAttribute("response")){ %>
		<h1>File Uploaded ${response }</h1>
	<%} %>
</body>
</html>