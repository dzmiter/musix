<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Upload files</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <style>
      body {
        padding-top: 80px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
    <c:import url="links.jsp"></c:import>
    <link href="./resources/css/fileUp.css" rel="stylesheet">
	<script type="text/javascript" src="./resources/js/fileUp.js"></script>
  </head>

  <body onload="new uploader('drop', 'status', 'upload', 'list');">
    
    <c:if test="${myId==null||email==null}">
    	<c:redirect url="/"></c:redirect>
    </c:if>
    <div class="container" >
    
	    <c:import url="header.jsp"></c:import>
	
	<div id="box">
		<div id="status">Drag the files from a folder to a selected area ...</div>
		<div id="drop"></div>
		<p>Files are not stored on the server Weeby, you see only a preview.</p>
	</div>
	<div id="list"></div>	
		
		<c:import url="footer.jsp"></c:import>

    </div> 

  </body>
</html>
