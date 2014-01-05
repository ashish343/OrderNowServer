	<script>
    function loadJS(src, callback) {
    var s = document.createElement('script');
    s.src = src;
    s.async = true;
    s.onreadystatechange = s.onload = function() {
        var state = s.readyState;
        if (!callback.done && (!state || /loaded|complete/.test(state))) {
            callback.done = true;
            callback();
        }
    };
    document.getElementsByTagName('head')[0].appendChild(s);
}

    loadJS('http://code.jquery.com/jquery-2.0.3.min.js', function() { 
        P.register('jQuery');
    });

	P.when('jQuery').execute(function($){
	    P.load.js("${path}/resources/js/bootstrap.min.js");	
 	    loadJS("${path}/resources/js/jquery.als-1.2.min.js", function() {
 	        P.register('als');
 		});
	});
	P.when('jQuery', 'als').execute(function($,a) {
    this.$("#demo1").als({
        visible_items: 1,
        scrolling_items: 1,
        orientation: "horizontal",
        circular: "no",
        autoscroll: "no"
    });
	});
	</script>