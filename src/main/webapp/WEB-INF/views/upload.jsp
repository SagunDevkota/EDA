<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>upload csv file</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/uploadStyle.css"/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
  </head>
  <body>
	<div class="popup" id="popup-1">
	   <div class="content" style="background: red;">
	     <span class="message">Invalid File Format</span>
	     <span class="close-btn" onclick="togglePopup()">×</span>
	   </div>
	</div>
	
	 <div class="upload-area">
	    <div class="icon"><i class="fas fa-cloud-upload-alt"></i></div>
	    <header>Upload CSV File</header>
	    <form:form action="uploadFile?${_csrf.parameterName}=${_csrf.token}" method="post" class="form upload" enctype="multipart/form-data" id="upload" modelAttribute="uploadedFile" onsubmit="return validateUploadForm()">
	      <form:input type="file" path="file" name="file" class="form-control-file" id="exampleFormControlFile1"/><br><br>
	      <button class="btn-outline-success btn">Upload</button>
	    </form:form>
	  </div>
  <script src="<c:url value="/resources/js/upload.js"/>"></script>
  </body>
</html>