<%@page import="java.sql.Timestamp"%>
<%@page import="eda.dto.Data"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reports</title>
    <link rel="stylesheet" href="<c:url value = "/resources/css/reportsStyle.css"/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
	<header class="header">
	
			<a href="./dashboard"><img src="<c:url value="/resources/images/logo.jpg"/>" alt="logo" class="logo"></a>
      <nav class="navbar">
            <a href="./dashboard">HOME</a>
            <a href="./dashboard#about">ABOUT</a>
            <a href="./upload">UPLOAD</a>
            <a href="./reports">REPORT</a>
            <a href="./dashboard#contact">CONTACT</a>
        </nav>
	
			<div class="icons">
				<div class="fas fa-search" id="search-btn"></div>
				<div class="fas fa-user" id="profile-btn"></div>
	
			</div>
	
			<div class="search-form">
				<input type="search" id="search-box" placeholder="search here...">
				<label for="search-box" class="fas fa-search"></label>
			</div>
	
			<div class="profile-items-container">
		        <div class="profile-item">
		            <div class="content">
		                <h3><a href="./profile">Profile</a></h3>
		            </div>
		        </div>
		        <div class="profile-item">
		            <div class="content">
		               <h3><a href="./logout">Sign out</a></h3>
		            </div>
		        </div>
		    </div>
		</header>
<a href="<c:url value = "/dashboard"/>"><img src="<c:url value = "/resources/images/logo.jpg"/>" alt="logo" class="logo"></a>
<%List<Data> reportOwnedList= (List<Data>)request.getAttribute("reportOwned");
List<Data> reportSharedList= (List<Data>)request.getAttribute("reportShared");%>
<div class = "container" style="margin-top: 50px;">
	<div class="reports-table">
		<h3>Owned</h3>
		<table>
		<thead>
			<tr>
				<th>File Name</th>
				<th>File Size</th>
				<th>Created At</th>
				<th>Action</th>
			</tr>
		</thead>
			<%for(Data d:reportOwnedList){ %>
				<tr>
					<%String fileName = d.getFileName();
						float size = (d.getFileSize()/(float)1024);
						Timestamp time = d.getCreatedTime();
						int url = d.getId();%>
					<td><a href="./report?id=<%=url %>" target="_blank"><%=fileName %></a></td>
					<% %>
					<td><%=size %> KB</td>
					<td><%=time %></td>
					<td>
						<span class="action_btn">
	                    <a href="./popup?id=<%=url%>">Share</a>
	                    <a href="#">Delete</a>
                    </span>
					</td>
				</tr>
			<% }%>
			
		</table>
	</div>
	
	<div class="reports-table">
		<h3>Shared</h3>
		<table>
		<thead>
			<tr>
				<th>File Name</th>
				<th>File Size</th>
				<th>Shared At</th>
			</tr>
		</thead>
			<%for(Data d:reportSharedList){ %>
				<tr>
					<%String fileName = d.getFileName();
						float size = (d.getFileSize()/(float)1024);
						Timestamp time = d.getCreatedTime();
						int url = d.getId();%>
					<td><a href="./report?id=<%=url %>" target="_blank"><%=fileName %></a></td>
					<% %>
					<td><%=size %> KB</td>
					<td><%=time %></td>
				</tr>
			<% }%>
			
		</table>
	</div>
</div>
<script src="<c:url value="/resources/js/dashScript.js"/>"></script>
</body>
</html>