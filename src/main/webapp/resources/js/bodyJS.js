
P.when('jQuery').execute(function($) {
	myFunc(this.$);
});

var myFunc = function($) {
	$("#action-home").click(function(e) {
		$('.carousel').carousel(0);
		$('.carousel').carousel('pause');
		e.preventDefault();
	});

	$("#action-app").click(function(e) {
		$('.carousel').carousel(1);
		$('.carousel').carousel('pause');
		e.preventDefault();
	});

	$("#action-blog").click(function(e) {
		$('.carousel').carousel(2);
		$('.carousel').carousel('pause');
		e.preventDefault();
	});

	$("#action-sign-in").click(function(e) {
		$('.carousel').carousel(3);
		$('.carousel').carousel('pause');
		e.preventDefault();
	});
}

function HeaderController($scope, $location) 
{ 
    $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };
}