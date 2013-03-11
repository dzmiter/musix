(function ($) {	  
	  page = {	 
	    onReady: function () {
	      this.switch_style_click();
	    },	 
	    switch_style_click: function(){
	        $(".pagestyleswitcher").click(function(){
	            var id = $(this).attr("id");
	            if(id == 'dark') {
	            	$("#main").attr("href", "resources/css/bootstrap.min.css");
	            	$("#responsive").attr("href", "resources/css/bootstrap-responsive.min.css");
	            } else if(id == 'light') {
	            	$("#main").attr("href", "");
	            	$("#responsive").attr("href", "");
	            }	            
	        });
	    },
	  };	 
	  $().ready(function () {
		  page.onReady();
	  });	 
})(jQuery);