(function ($) {	 
	
	function setCookie (name, value, expires, path, domain, secure) {
	      document.cookie = name + "=" + escape(value) +
	        ((expires) ? "; expires=" + expires : "") +
	        ((path) ? "; path=" + path : "") +
	        ((domain) ? "; domain=" + domain : "") +
	        ((secure) ? "; secure" : "");
	}
	function getCookie(name) {
		var cookie = " " + document.cookie;
		var search = " " + name + "=";
		var setStr = null;
		var offset = 0;
		var end = 0;
		if (cookie.length > 0) {
			offset = cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = cookie.indexOf(";", offset);
				if (end == -1) {
					end = cookie.length;
				}
				setStr = unescape(cookie.substring(offset, end));
			}
		}
		return(setStr);
	}
	function changePlayerStyle(value) {
		var cl = $("#" + value).attr("class");
		if(cl == 'dark') {
			$("#player").attr("href", "./resources/skin/pink.flag/jplayer.pink.flag.css");
		} else if(cl == 'light'){
			$("#player").attr("href", "./resources/skin/blue.monday/jplayer.blue.monday.css");
		}
	}
	
	page = {	 
			onReady: function () {
				this.switch_style_click();
				var value = null;
				var style = getCookie("musix-style");
				if(style == null) {
					value = $("#pagestyleswitcher").val();	
				} else {
					value = style;
					$("#" + value).prop("selected", "selected");	
					changePlayerStyle(value);
				}
				$("#main").attr("href", "./resources/css/" + value + ".min.css");
			},	 
		    switch_style_click: function(){	        
		        $("#pagestyleswitcher").on('change', function() {
		        	var value = $(this).val();	        	        	        	
			        $("#main").attr("href", "./resources/css/" + value + ".min.css");
			        changePlayerStyle(value);	
			        setCookie("musix-style", value);
		        });
		    },
	  	};	 
	$().ready(function () {
		page.onReady();
	});	 
})(jQuery);