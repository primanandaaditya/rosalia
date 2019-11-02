var initialScreenSize = window.innerHeight;
  window.addEventListener("resize", function() {
	if(window.innerHeight < initialScreenSize){
		$("[data-role=footer]").hide();
	}
	else{				
		$("[data-role=footer]").show();
	}
});