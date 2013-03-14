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
	<link id="player" href="./resources/skin/blue.monday/jplayer.blue.monday.css" rel="stylesheet">	
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
	          	<li>id:${track.id} rating:${track.name} description:${track.description}</li>
	          </c:forEach>
          </ol>
        </div>

        <div class="span6">
          <h3>Most popular tracks</h3>
          <ol>
	          <c:forEach items="${mostPopular}" var="track">
	          	<li>id:${track.id} rating:${track.name} description:${track.description}</li>
	          </c:forEach>
          </ol>
        </div>
      </div>
     
	  <c:import url="player.jsp"></c:import>
	  	  	  
      <c:import url="footer.jsp"></c:import>

    </div> <!-- /container -->
    
	<script type="text/javascript" src="./resources/js/jquery.jplayer.min.js"></script>
	<script type="text/javascript" src="http://www.jplayer.org/latest/js/jplayer.playlist.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	  $("#jquery_jplayer_1").jPlayer( {	     
	    swfPath: "./resources/swf"
	  });
	  
	  
	  new jPlayerPlaylist({
			jPlayer: "#jquery_jplayer_1",
			cssSelectorAncestor: "#jp_container_1"
		}, ${json}, {
			swfPath: "./resources/swf",
			supplied: "ogg, mp3",
			wmode: "window"
		});

	});
	</script>    
  </body>
</html>