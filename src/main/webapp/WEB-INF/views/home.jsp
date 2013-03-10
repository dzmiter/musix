<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">	
	<title>Home</title>
	<link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link rel="shortcut icon" href="resources/img/icon.png" type="image/x-icon" />
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>

  </head>

  <body>

    <div class="container">
	  
	  <c:import url="header.jsp"></c:import>

      <div class="hero-unit">
        <h1>Welcome to music web site!</h1>
      </div>

      <hr>

      <div class="row-fluid marketing">
        <div class="span6">
          <h3>Most listened tracks</h3>
          <ol>
	          <c:forEach items="${mostListened}" var="track">
	          	<li>id:${track.id} rating:${track.rating} <b>playsnumber:${track.playsnumber}</b></li>
	          </c:forEach>
          </ol>
        </div>

        <div class="span6">
          <h3>Most popular tracks</h3>
          <ol>
	          <c:forEach items="${mostPopular}" var="track">
	          	<li>id:${track.id} <b>rating:${track.rating}</b> playsnumber:${track.playsnumber}</li>
	          </c:forEach>
          </ol>
        </div>
      </div>

	  <c:import url="footer.jsp"></c:import>

    </div> <!-- /container -->

  </body>
</html>
