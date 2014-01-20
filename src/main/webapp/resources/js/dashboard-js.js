
P.when('jQuery').execute(function($) {
	loadJS("/resources/js/d3.v3.min.js", function() {
        P.register('d3');
	});
	addRest(this.$);
	displayDiv(this.$)
});

var addRest = function($) {
	$("#action-Add").click(function(e) {
		jQuery(".page-layout").children("div").each(function(){$(this).hide()});
		jQuery("#add-rest").show();
		e.preventDefault();
	});

	$("#action-Modify").click(function(e) {
		jQuery(".page-layout").children("div").each(function(){$(this).hide()});
		jQuery("#modify-rest").show();
		e.preventDefault();
	});
	
	$("#action-sign-in").click(function(e) {
		jQuery(".page-layout").children("div").each(function(){$(this).hide()});
		jQuery("#rest-sign-in").show();
		e.preventDefault();
	});
}

var displayDiv = function($){
	$('#addType').change(function(){ 
	    if($(this).val() == "category"){	    	
	    	$('#addCategory').show();
	    	$('#addDish').hide();	    	
	    }
	    if($(this).val() == "dish"){
	    	$('#addCategory').hide();
	    	$('#addDish').show();
	    	
	    }
	});
}
