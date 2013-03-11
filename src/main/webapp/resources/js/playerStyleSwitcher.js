(function ($) {	  
	  player = {	 
	    onReady: function () {
	      this.switch_style_click();
	    },	 
	    switch_style_click: function(){
	        $(".styleswitcher").click(function(){
	            var id = $(this).attr("id");
	            if(id == 'blue') {
	            	$("#player").attr("href", "resources/skin/blue.monday/jplayer.blue.monday.css");
	            } else if(id == 'pink') {
	            	$("#player").attr("href", "resources/skin/pink.flag/jplayer.pink.flag.css");
	            }	            
	        });
	    },
	  };	 
	  $().ready(function () {
		  player.onReady();
	  });	 
})(jQuery);