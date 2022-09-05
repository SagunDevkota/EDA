<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EDA</title>

<!-- font awesome cdn link  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- css file link  -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashStyle.css"/>">

</head>
<body>
	<%
	String error = null;
	Boolean responseStatus = null;
	try {
		responseStatus = ((Boolean) request.getAttribute("success"));
	} catch (Exception e) {

	}
	String message = (String) request.getAttribute("message");
	error = ((String) request.getAttribute("error"));
	String active = "";
	if (error != null || message != null) {
		active = "active";
	}
	%>
	<div class="popup <%=active%>" id="popup-1">
		<%
		if (responseStatus != null) {
			if (error != null) {
		%>
		<div class="content" style="background: red;">
			<span class="message"><%=error%></span>
			<%
			} else if (message != null) {
			%>
			<div class="content" style="background: green;">
				<span class="message"><%=message%></span>
				<%
				}
				%>
				<span class="close-btn" onclick="togglePopup()">×</span>
			</div>
			<%
			}
			%>
		</div>
		<!-- header starts  -->

		<header class="header">

			<a href="./" class="logo">
				<div class="title">EDA</div>
			</a>

			<nav class="navbar">
				<a href="#home">HOME</a> <a href="#about">ABOUT</a> <a
					href="./upload">UPLOAD</a> <a href="./reports">REPORT</a> <a
					href="#contact">CONTACT</a>

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
						<h3>
							<a href="./profile">Profile</a>
						</h3>
					</div>
				</div>
				<div class="profile-item">
					<div class="content">
						<form:form action="./logout">
							<button>Logout</button>
						</form:form>
						<!-- 		               <h3><a href="#">Sign out</a></h3> -->
					</div>
				</div>
			</div>
		</header>

		<!-- header ends -->

		<!-- home starts  -->

		<section class="home" id="home">

			<div class="content">
				<br />
				<p>
					<strong>EDA</strong> is an online platform for Exploratory Data
					Analysis. Data analyst logs in to analyze and investigate data sets
					and summarize their main characteristics. It helps determine how
					best to manipulate data.
				</p>
				<p>When the users visit the website, the user uploads data set
					(CSV file) then system generates report containing univariate
					graphical and non-graphical analysis. It makes analysis to discover
					patterns (mathematical) and spot anomalies (missing). The users are
					given option to save and share the report.</p>
			</div>

		</section>

		<!-- home ends -->

		<!-- about starts  -->

		<section class="about" id="about">
			<h1 class="heading">about us</h1>

			<div class="row">
				<div class="image">
					<img src="<c:url value="/resources/images/cover.jpeg"/>">
				</div>

				<div class="content">
					<h3>what makes our EDA special?</h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Voluptatibus qui ea ullam, enim tempora ipsum fuga alias quae
						ratione a officiis id temporibus autem? Quod nemo facilis
						cupiditate. Ex, vel?</p>
					<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.
						Odit amet enim quod veritatis, nihil voluptas culpa! Neque
						consectetur obcaecati sapiente?</p>
					<a href="#" class="btn">learn more</a>
				</div>
			</div>
		</section>

		<!-- contact starts  -->

		<section class="contact" id="contact">

			<h1 class="heading">contact us</h1>

			<div class="row">

				<div class="mapouter">
					<div class="gmap_canvas">
						<iframe width="600" height="500" id="gmap_canvas"
							src="https://maps.google.com/maps?q=pokhara%20lamachour&t=&z=13&ie=UTF8&iwloc=&output=embed"
							frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
						<a href="https://www.whatismyip-address.com/divi-discount/">divi
							discount</a><br>
						<style>
.mapouter {
	position: relative;
	text-align: right;
	height: 500px;
	width: 600px;
}
</style>
						<a href="https://www.embedgooglemap.net">google maps insert</a>
						<style>
.gmap_canvas {
	overflow: hidden;
	background: none !important;
	height: 500px;
	width: 600px;
}
</style>
					</div>
				</div>

				<form:form id="supportForm">
					<h3>get in touch</h3>
					<div class="inputBox">
						<span class="fas fa-user"></span> <input type="text"
							placeholder="name" name="name">
					</div>
					<div class="inputBox">
						<span class="fas fa-envelope"></span> <input type="email"
							placeholder="email" name="email">
					</div>
					<div class="inputBox">
						<span class="fas fa-phone"></span> <input type="number"
							placeholder="number" name="phoneNo">
					</div>
					<div class="inputBox">
						<span class="fas fa-envelope"></span>
						<textarea rows="5"
							style="width: 100%; background: transparent; color: white;"
							placeholder="Message"></textarea>
					</div>
					<input type="submit" value="contact now" class="btn" id="btn-submit-contact">
				</form:form>
			</div>
		</section>

		<!-- contact ends -->

		<!-- footer starts  -->

		<section class="footer">

			<div class="share">
				<a href="#" class="fab fa-facebook-f"></a> <a href="#"
					class="fab fa-twitter"></a> <a href="#" class="fab fa-instagram"></a>
				<a href="#" class="fab fa-linkedin"></a>
			</div>

			<div class="links">
				<a href="#">HOME</a> <a href="#about">ABOUT</a> <a href="#">UPLOAD</a>
				<a href="#">REPORT</a> <a href="#">VIEW SHARED</a> <a
					href="#contact">CONTACT</a>
			</div>

			<div class="credit">all rights reserved</div>

		</section>

		<!-- footer ends -->


		<!-- js file link  -->
		<script src="<c:url value="/resources/js/dashScript.js"/>"></script>
</body>
</html>