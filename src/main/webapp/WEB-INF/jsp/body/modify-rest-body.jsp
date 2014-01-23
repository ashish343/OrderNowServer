
<!-- Load JS -->
<script type="text/javascript">
        P.when('jQuery').execute(function($){
            loadJS('/resources/js/pusher.min.js', function() { 
                myPusherFunc();
            });
        });
        var myPusherFunc = function() {
            Pusher.log = function(message) {
                  if (window.console && window.console.log) {
                    window.console.log(message);
                  }
                };

                var pusher = new Pusher('1f7298f8e64c81a0d7de');
                var channel = pusher.subscribe('R1');
                channel.bind('notify_order', function(data) {
                    console.log(data);
                    alert(data);
                });
                channel.bind('update_order', function(data) {
                    alert(data.message);
                  });
                channel.bind('get_bill', function(data) {
                    alert(data.message);
                  });
    } 
</script>