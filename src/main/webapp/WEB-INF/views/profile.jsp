<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/profileStyle.css"/>">
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
    </header>        
    <form:form action="./logout" class="card" method="POST">
        <div class="fas fa-user" id="profile-icon"></div>
        <h1>${user.getFullName()}</h1>
        <p class="email">${user.getEmail()}</p>
        <div class="socials">
            <a href="#"><i class="fab fa-facebook" id="social"></i></a> 
            <a href="#"><i class="fab fa-linkedin"></i></a> 
            <a href="#"><i class="fab fa-instagram"></i></a> 
        </div>
			<input type="submit" value="Logout" class="logout btn">
		</form:form>
</body>
</html>