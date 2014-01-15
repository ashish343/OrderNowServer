
P.when('jQuery').execute(function($) {
	addRest(this.$);
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
