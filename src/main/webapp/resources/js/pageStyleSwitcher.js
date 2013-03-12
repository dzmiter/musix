(function ($) {	  
	  page = {	 
	    onReady: function () {
	      this.switch_style_click();
	    },	 
	    switch_style_click: function(){
	        $(".pagestyleswitcher").click(function(){
	            var id = $(this).attr("id");
	            if(id == 'dark') {
	            	$("#main").attr("href", "./resources/css/readable.min.css");
	            } else if(id == 'light') {
	            	$("#main").attr("href", "./resources/css/bootstrap.min.css");
	            }	            
	        });
	    },
	  };	 
	  $().ready(function () {
		  page.onReady();
	  });	 
})(jQuery);