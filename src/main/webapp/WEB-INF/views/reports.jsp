<%@page import="java.sql.Timestamp"%>
<%@page import="eda.dto.Data"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report List</title>
</head>
<body>
<%List<Data> reportList= (List<Data>)request.getAttribute("reports");%>
	<table border="1">
		<tr>
			<th>File Name</th>
			<th>File Size</th>
			<th>Created At</th>
		</tr>
		<%for(Data d:reportList){ %>
			<tr>
				<%String fileName = d.getFileName();
					float size = (d.getFileSize()/(float)1024);
					Timestamp time = d.getCreatedTime();
					String url = d.getHash();%>
				<td><a href="./report?name=<%=url %>.csv" target="_blank"><%=fileName %></a></td>
				<% %>
				<td><%=size %> KB</td>
				<td><%=time %></td>
			</tr>
		<% }%>
		
	</table>
</body>
</html>