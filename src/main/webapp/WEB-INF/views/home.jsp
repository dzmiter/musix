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
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
	<link rel="shortcut icon" href="resources/img/icon.png" type="image/x-icon" />
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
     
	  <c:import url="player.jsp"></c:import>
	  
     
      <c:import url="footer.jsp"></c:import>

    </div> <!-- /container -->
    
	<script type="text/javascript" src="resources/js/jquery.jplayer.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	  $("#jquery_jplayer_1").jPlayer( {
	    ready: function () {
	      $(this).jPlayer("setMedia", {
	        mp3: "http://s3.amazonaws.com/audiojs/02-juicy-r.mp3" 
	      });
	 	}
	  });
	});
	</script>    
	<script type="text/javascript" src="resources/js/playerStyleSwitcher.js"></script>
  </body>
</html>
