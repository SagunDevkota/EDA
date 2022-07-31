<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EDA</title>

    <!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <!-- css file link  -->
    <link rel="stylesheet" href="<c:url value="/resources/css/dashStyle.css"/>">

</head>
<body>
    
<!-- header starts  -->

<header class="header">


    <a href="#"><img src="<c:url value="/resources/images/logo.jpg"/>" alt="logo" class="logo"></a>


    <nav class="navbar">
        <a href="./">HOME</a>
        <a href="#about">ABOUT</a>
        <a href="./login">LOG IN</a>

    </nav>




</header>

<!-- header ends -->

<!-- home starts  -->

<section class="home" id="home">

    <div class="content">
        <br /><p><strong>EDA</strong> is an online platform for Exploratory Data Analysis. Data analyst logs in
            to analyze and investigate data sets and summarize their main characteristics. It helps determine 
            how best to manipulate data.</p>
            <p>
            When the users visit the website, the user uploads data set (CSV file) then system generates report 
            containing univariate graphical and non-graphical analysis. It makes analysis to discover patterns 
            (mathematical) and spot anomalies (missing). The users are given option to save and share the report.
        </p>
    </div>

</section>

<!-- home ends -->

<!-- about starts  -->

<section class="about" id="about">
    <h1 class="heading"> about us </h1>

    <div class="row">
        <div class="image">
            <img src="<c:url value="/resources/images/cover.jpeg"/>" >
        </div>

        <div class="content">
            <h3>what makes our EDA special?</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptatibus qui ea ullam, enim tempora ipsum fuga alias quae ratione a officiis id temporibus autem? Quod nemo facilis cupiditate. Ex, vel?</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Odit amet enim quod veritatis, nihil voluptas culpa! Neque consectetur obcaecati sapiente?</p>
            <a href="#" class="btn">learn more</a>
        </div>
    </div>
</section>



<!-- footer starts  -->

<section class="footer">

    <div class="share">
        <a href="#" class="fab fa-facebook-f"></a>
        <a href="#" class="fab fa-twitter"></a>
        <a href="#" class="fab fa-instagram"></a>
        <a href="#" class="fab fa-linkedin"></a>
    </div>

    <div class="links">
        <a href="./">HOME</a>
        <a href="#about">ABOUT</a>
        <a href="./login">LOG IN</a>
    </div>

    <div class="credit"> all rights reserved</div>

</section>

<!-- footer ends -->


<!-- js file link  -->
<script src="<c:url value="/resources/js/dashScript.js"/>"></script>

</body>
</html>