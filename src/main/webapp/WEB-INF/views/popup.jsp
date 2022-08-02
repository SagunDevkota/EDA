<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>pop up box</title>
      
      <link rel="stylesheet" href="<c:url value = "/resources/css/popupStyle.css"/>">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
      <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
   <body>
      <div class="center modal-box">
         <!-- <div class="fas fa-times" ></div> -->
         ${id }
         <a href="<c:url value = "/reports"/>" class="fas fa-times"></a>
         <div class="fas fa-envelope icon1"></div>
         <header>Share this to..</header>
         <p>
            the contact you add here will be able to view data.
         </p>
         <form action="./processShare">
            <div class="fas fa-envelope icon2"></div>
            <input type="email" required name = "email" placeholder="abc@example.com">
            <input type="hidden" name = "id" value = ${id }>
            <button>Share</button>
         </form>
      </div>
    
   </body>
</html>