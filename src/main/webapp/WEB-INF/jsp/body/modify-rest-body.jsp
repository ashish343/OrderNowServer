<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Orders</div>
  
  <!-- Table -->
  <table id="sample" class="table" border="1">
    <tr>
        <th>S.No:</th>
        <th>Item</th>
        <th>Quantity</th>
        <th>Price</th>
    </tr>
    <tr>
        <td>row 1, cell 1</td>
        <td>row 1, cell 2</td>
        <td>row 1, cell 1</td>
        <td>row 1, cell 2</td>
    </tr>
    <tr>
        <td>row 1, cell 1</td>
        <td>row 1, cell 2</td>
        <td>row 1, cell 1</td>
        <td>row 1, cell 2</td>
    </tr>
  </table>
</div>

<!-- Load JS -->
<script type="text/javascript">
var d;
        P.when('jQuery').execute(function($){
            loadJS('/resources/js/pusher.min.js', function() { 
                myPusherFunc();
            });
            attachEvent(this.$);
        });

        var attachEvent = function() {
        	jQuery(".orderItemRow").click(function() {
                alert("here");
            });
        }
        var myPusherFunc = function() {
            Pusher.log = function(message) {
                  if (window.console && window.console.log) {
                    window.console.log(message);
                  }
                };

                var pusher = new Pusher('1f7298f8e64c81a0d7de');
                var channel = pusher.subscribe('R1');
                channel.bind('notify_order', function(data) {
                    d = data;
                    createOrderPage(data);
                    attachEvent();
                    var request = $.ajax({
                        url: "/restOrder?action=orderReceived&orderId=" + data.orderId,
                        type: "GET",
                      });
                    alert(data);
                });
                channel.bind('update_order', function(data) {
                    alert(data.message);
                  });
                channel.bind('get_bill', function(data) {
                    alert(data.message);
                  });
    } 
    var createOrderPage = function(data) {
        if(data !== null && typeof data !== undefined){
        	var oldOrder = jQuery('#'+data.orderId);
            var orderHtml = '';
            
            if(oldOrder === null || typeof oldOrder === undefined || oldOrder.length === 0) {
                orderHtml =  '<table id="' + data.orderId + '" class="table" border="1"><tr><th>S.No:</th><th>Item</th><th>Quantity</th><th>Price</th></tr>';
                jQuery(data.dishes).each(function(index, value) {
                    orderHtml +='<tr id='+ value.dishId +'><td>' + (index+1) + '</td><td>' + value.name 
                    + '</td><td>' + value.quatity + '</td><td>' + value.price + '</td></tr>'; 
                });
                jQuery(".panel").html(orderHtml);
            } else {
            	jQuery(data.dishes).each(function(index, value) {
                    orderHtml +='<tr id='+ value.dishId +'><td>' + (index+1) + '</td><td>' + value.name 
                    + '</td><td>' + value.quatity + '</td><td>' + value.price + '</td></tr>'; 
                });
            	oldOrder.append(orderHtml);
            }
        }
    }
</script>