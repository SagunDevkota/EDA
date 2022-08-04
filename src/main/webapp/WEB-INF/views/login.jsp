<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>EDA</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700,800&display=swap" rel="stylesheet">
    
</head>
<body>
<h1>
	<%
	String error = null;
	Boolean responseStatus = null;
	try{
		responseStatus = ((Boolean)request.getAttribute("success"));
	}catch(Exception e){
		
	}
	  error = ((String)request.getAttribute("error"));
	  String active = "";
	  if(error != null || responseStatus != null){
		  active = "active";
	  }
	%>
	<div class="popup <%=active %>" id="popup-1">
	<%if(responseStatus != null){ %>
		  <%if(responseStatus == false){ %>
		   <div class="content" style="background: red;">
		   <%}else if(responseStatus == true){ %>
		   <div class="content" style="background: green;">
		   <span class="message">Account Created Successfully</span>
		   <%}if(error != null){%>
			   <span class="message"><%=error%></span>
		   <%} else if(responseStatus == false){%>
		     <span class="message">Account Creation Failed</span>
		     <%} %>
		     <span class="close-btn" onclick="togglePopup()">×</span>
		   </div>
		   <%} %>
		</div>
  <div class="cont">
    <div>
      <!-- form ma action, method rakheko xaina.   -->
        <form:form id="signIn" class="form sign-in" onsubmit="return validateSinForm()" >
        
          <a href="<c:url value="/"/>"><img src="<c:url value="/resources/images/logo.jpg"/>" alt="logo" class="logo"></a>
          <h2>Sign In</h2>
          <label>
            <span>Email</span>
            <input type="text" name="username" id="email">
            <p id="emError"></p>
          </label>
          <label>
            <span>Password</span>
            <input type="password" name="password" id="password">
            <p id="pwError"></p>
          </label>
<!--           <button class="submit" type="submit" id="sInButton"> SIGN IN </button> -->
  		<button type="submit" class="submit"> SIGN IN </button>
          <p class="forgot-pass">Forgot Password ?</p>
  
      <div class="social-media">
        <ul>
          <li><img src="<c:url value="/resources/images/facebook.png"/>"></li>
          <li><img src="<c:url value="/resources/images/twitter.png"/>"></li>
          <li><img src="<c:url value="/resources/images/linkedin.png"/>"></li>
          <li><img src="<c:url value="/resources/images/instagram.png"/>"></li>
        </ul>
      </div>
    </form:form>

    <div class="sub-cont">
      <div class="img">
        <div class="img-text m-up">
          <h2>New here?</h2>
          <p>Sign up now if you're new!!</p>
        </div>
        <div class="img-text m-in">
          <h2>Have an account already?</h2>
          <p>Sign in, if you already have an account!!</p>
        </div>
        <div class="img-btn">
          <span class="m-up">Sign Up</span>
          <span class="m-in">Sign In</span>
        </div>
      </div>
      <form:form action="process-signup" class="form sign-up" id="signUp" onsubmit="return validateSupForm()">
        <h2>Sign Up</h2>
        <label>
          <span>Name</span>
          <input type="text" name="name" id="sUpName">
          <p id="sUpNameError"></p>
        </label>
        <label>
          <span>Email</span>
          <input type="text" name="username" id="sUpEmail">
          <p id="sUpEmError"></p>
        </label>
        <label>
          <span>Password</span>
          <input type="password" name="password" id="sUpPassword">
          <p id="sUpPwError"></p>
        </label>
        <label>
          <span>Confirm Password</span>
          <input type="password" name="conPassword" id="sUpConPassword">
          <p id="sUpConfirmPwError"></p>
        </label>
        <button class="submit" type="submit" id="sUpButton"> SIGN UP </button>
      </form:form>
    </div>
  </div>
<script type="text/javascript" src="<c:url value="/resources/js/script.js"/>"></script>
</body>
</html>