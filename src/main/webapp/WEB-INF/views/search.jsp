<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home</title>	
    <style type="text/css">
      body {
        padding-top: 80px;
        padding-bottom: 40px;
      }
    </style>
	<c:import url="links.jsp"></c:import>
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
          <h3>Search result</h3>
          <ol>
	          <c:forEach items="${searchResult}" var="track">
	          	<li>id:${track.id} rating:${track.name} description:${track.description}</li>
	          </c:forEach>
          </ol>
        </div>


      </div>
     
     
      <c:import url="footer.jsp"></c:import>

    </div> <!-- /container -->
    
  </body>
</html>