<%@page import="java.util.Set"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="eda.report.constants.Constants"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.reflect.TypeToken"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Report</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/reportStyle.css"/>">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
</head>
<body>
	<%if(request.getAttribute("error")!=null){ %>
		<h1>${error }</h1>
	<%return;} 
	int currentNumericRow = 0;
	DecimalFormat df = new DecimalFormat("#.#####");
	String jsonString = (String)request.getAttribute("json"); 
	JsonObject convertedObject = new Gson().fromJson(jsonString, JsonObject.class);
	%>
	<canvas id="myChart"></canvas>

    <script>
    var xValues = [0, 15, 30, 35, 40];
    var yValues = [55, 49, 64, 34, 35];

    new Chart("myChart", {
      type: "bar",
      data: {
        labels: xValues,
        datasets: [{
          backgroundColor: "blue",
          data: yValues
        }]
      },
      options: {
        legend: {display: false},
      }
    });
    </script>
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
  <section class="overview">
      <table class="overview-table">
        <h2>OVERVIEW</h2>
        <tr>
          <th>Number of variables</th>
          <td><%=convertedObject.get("dataSetReport").getAsJsonObject().get("columns").getAsJsonArray().size() %></td>
        </tr>
        <tr>
          <th>Number of observations</th>
          <td><%=convertedObject.get("dataSetReport").getAsJsonObject().get("records").getAsInt() %></td>
        </tr>
        <tr>
          <th>Missing cells</th>
          <td><%=convertedObject.get("dataSetReport").getAsJsonObject().get("missing").getAsInt() %></td>
        </tr>
      </table>
  </section>

  <section class="variable-type">
    <table class="overview-table">
    <%Integer intCount,catCount,boolCount;
    try{
    	intCount = convertedObject.get("dataTypeCount").getAsJsonObject().get("Numeric").getAsInt();
    }catch(NullPointerException e){
    	intCount = 0;
    }
    try{
    	catCount = convertedObject.get("dataTypeCount").getAsJsonObject().get("Categorical").getAsInt();
    }catch(NullPointerException e){
    	catCount = 0;
    }try{
    	boolCount = convertedObject.get("dataTypeCount").getAsJsonObject().get("Boolean").getAsInt();
    }catch(NullPointerException e){
    	boolCount = 0;
    }%>
      <h2>Variable types</h2>
      <tr>
        <th>NUM</th>
        <td><%=intCount %></td>
      </tr>
      <tr>
        <th>STRING</th>
        <td><%=catCount %></td>
      </tr>
      <tr>
        <th>BOOLEAN</th>
        <td><%=boolCount %></td>
    </table>
  </section>

	<h2>VARIABLES</h2>
	
  
  <% 
  	JsonArray columnReportArray = convertedObject.get("columnReport").getAsJsonArray();
  	for(int i = 0;i<columnReportArray.size();i++){
  		JsonObject obj = columnReportArray.get(i).getAsJsonObject();
%>

	<section class="variable">
	<h2 style="padding-left: 2vw; margin-bottom:0px;"><%=obj.get("columnName").getAsString() %></h2>
      <p style="padding-left:3.5vw; margin: 0px; text-decoration-line: underline; text-decoration-style: dotted; "><%=obj.get("columnType").getAsString() %></p>
      <br>
    <table class="overview-table">
      
      <tr>
        <th>Distinct count</th>
        <td><%=obj.get("columnMetadata").getAsJsonObject().get("Distinct").getAsInt()%></td>
      </tr>
      <tr>
        <th>Missing</th>
        <td><%=obj.get("columnMetadata").getAsJsonObject().get("Missing").getAsInt()%></td>
      </tr>
      <%if(obj.get("columnType").getAsString().equals(Constants.NUMERIC)){ %>
      <tr>
        <th>Mean</th>
        <td><%=df.format(obj.get("columnMetadata").getAsJsonObject().get("Mean").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Minimum</th>
        <td><%=df.format(obj.get("columnMetadata").getAsJsonObject().get("Minimum").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Maximum</th>
        <td><%=df.format(obj.get("columnMetadata").getAsJsonObject().get("Maximum").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Zeros</th>
        <td><%=obj.get("columnMetadata").getAsJsonObject().get("Zeros").getAsInt()%></td>
      </tr>
      <%}else if(obj.get("columnType").getAsString().equals(Constants.BOOLEAN)){ %>
      <tr>
        <th>False</th>
        <%try{ 
        obj.get("boolFrequency").getAsJsonObject().get("false").getAsInt();%>
        <td><%=obj.get("boolFrequency").getAsJsonObject().get("false").getAsInt()%></td>
        <%}catch(NullPointerException e){%>
        	<td>0</td>
        <%}%>
      </tr>
      <tr>
        <th>True</th>
        <%try{ 
        obj.get("boolFrequency").getAsJsonObject().get("true").getAsInt();%>
        <td><%=obj.get("boolFrequency").getAsJsonObject().get("true").getAsInt()%></td>
        <%}catch(NullPointerException e){%>
        	<td>0</td>
        <%}%>
      </tr>
      
      <%} %>
    </table>
    <%if(obj.get("columnType").getAsString().equals(Constants.NUMERIC)){ %>
    <button id="toggle-btn" onclick="toggleAction(<%=currentNumericRow%>)" style="margin-bottom:1.5vh;">toggle details</button><br>
    
    <div class="target-div" style="display:none">
  <section class="quantile-details">
    <table class="overview-table">
      <h2>Quantile statistics</h2>
      <tr>
        <th>Minimum</th>
        <td><%=df.format(obj.get("columnMetadata").getAsJsonObject().get("Minimum").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Median</th>
        <td><%=df.format(obj.get("numStats").getAsJsonObject().get("Median").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Maximum</th>
        <td><%=df.format(obj.get("columnMetadata").getAsJsonObject().get("Maximum").getAsDouble())%></td>
      </tr>
    </table>
  </section>
  <section class="statistics-details">  
    <table class="overview-table">  
      <h2>Discriptive statistics</h2>
      <tr>
        <th>Standard deviation</th>
        <td><%=df.format(obj.get("numStats").getAsJsonObject().get("Standard Deviation").getAsDouble()) %></td>
      </tr>
      <tr>
        <th>Cofficient of variation</th>
        <td><%=df.format(obj.get("numStats").getAsJsonObject().get("Coefficient of Variation").getAsDouble()) %></td>
      </tr>
      <tr>
        <th>Mean</th>
        <td><%=df.format(obj.get("columnMetadata").getAsJsonObject().get("Mean").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Skewness</th>
        <td><%=df.format(obj.get("numStats").getAsJsonObject().get("Skewness").getAsDouble())%></td>
      </tr>
      <tr>
        <th>Variance</th>
        <td><%=df.format(obj.get("numStats").getAsJsonObject().get("Variance").getAsDouble())%></td>
      </tr>
    </table>
  </section>
  </div>
  <%currentNumericRow++;
  }else if(obj.get("columnType").getAsString().equals(Constants.CATEGORICAL)){
    	  %>
    	  <h2>Frequency Analysis</h2>
    	  <section class="statistics-details"> 
    	  <table class="overview-table">  
      	<%
    	  JsonObject jsonObject = obj.get("catFrequency").getAsJsonObject();
    	  Iterator<String> keys = jsonObject.keySet().iterator();
    	  
			int count = 0;
    	  while(keys.hasNext()) {
    	      String key = keys.next();
    	      count++;
    	      if(key == null){
    	    	  break;
    	      }
    	      %>
      				<tr>
	    	          <th><%=key %></th>
	    	          <td><%=obj.get("catFrequency").getAsJsonObject().get(key)%></td>
	    	  	    </tr>
    	  <%if(count==5){
    		  break;
    	  }
    	  }%>
    	   </table>
    	   </section>
      <%}%>
  </section>
<%} %>
	<%
	JsonObject corrJson = convertedObject.get("correlationTable").getAsJsonObject();
	Set<String> set=  corrJson.keySet();
	Iterator<String> iterator = set.iterator();
	List<String> list = new ArrayList<>();
	iterator.forEachRemaining(list::add);
	
	int size = convertedObject.get("correlationTable").getAsJsonObject().size();
	%>
	<table id="corr-table">
        <thead   class="head-table">
			<tr>
				<%if(list.size()>0){ %>
					<th></th>
				<%} %>
				<%for(int i = 0;i<list.size();i++){ %>
					<th><%=list.get(i) %></th>
				<%}%>
			</tr>
        </thead>            
        <tbody class="body-table">
        	<%for(int i=0;i<size;i++){ %>
	            <tr>
	            	<td class="label-mimic"><%=list.get(i)%></td>
	            	<%for(int j = 0;j<size;j++){ %>
	            		<td  class="data"><%=corrJson.get(list.get(i)).getAsJsonObject().get(list.get(j)) %>
	            	<%} %>
	            </tr>
            <%} %>
        </tbody>
    </table>
  <script>
    function toggleAction(index){
      var x = document.getElementsByClassName("target-div")[index];
      if (x.style.display === "none") {
        x.style.display = "block";
      } else {
        x.style.display = "none";
      }
    }
  </script>
</body>
</html>