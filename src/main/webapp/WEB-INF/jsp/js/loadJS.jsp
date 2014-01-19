<script>
	P.when('jQuery').execute(function($){
 	    loadJS("${path}/resources/js/jquery.als-1.2.min.js", function() {
 	        P.register('als');
 		}); 	   
 	  	loadJS("${path}/resources/js/d3.v3.min.js", function() {
	        P.register('d3');
		}); 		
 	  	loadJS('https://d3dy5gmtp8yhk7.cloudfront.net/2.1/pusher.min.js', function() { 
            myPusherFunc();
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
	var myPusherFunc = function() {
        Pusher.log = function(message) {
              if (window.console && window.console.log) {
                window.console.log(message);
              }
            };

            var pusher = new Pusher('1f7298f8e64c81a0d7de');
            var channel = pusher.subscribe('test_channel');
            channel.bind('my_event', function(data) {
              alert(data.message);
            });
    } 
	</script>