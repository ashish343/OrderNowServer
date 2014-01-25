<!-- 
Customer ID Div
    Main Order Id Div
        List for suborders
            List Element
                Table
                Buttons
 -->

<div id="R1"class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">R1</div>
</div>
<!-- Modal -->
<div class="modal fade" id="modifyOrderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Modify Order</h4>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button id="modalSaveChanges" type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- Load JS -->
<script type="text/javascript">
//if($('#radio_button').is(':checked')) { alert("it's checked"); }
var d;
        P.when('jQuery').execute(function($){
            loadJS('/resources/js/pusher.min.js', function() { 
                myPusherFunc();
            });
            attachEvent(this.$);
        });
        
        var attachEvent = function() {
            jQuery(".orderItemRow").click(function() {
            });
            jQuery("#acceptBtn").click(function() {
                console.log(jQuery(this).parent('table'));
            });
            jQuery("#declineBtn").click(function() {
                console.log(jQuery(this).parent('table'));
            });
            jQuery("#modifyBtn").click(function() {
                var orderList = jQuery(this).parents('li');
                var orderTable;
                if(orderList !== null && typeof orderList !== undefined) {
                    orderTable = jQuery(orderList).children('.table');

                    clonedOrderTable = jQuery(orderTable).clone();
                    
                    console.log(orderTable);
                    jQuery(clonedOrderTable).find('tr').each(function(index, value){
                        console.log(jQuery(this).attr('id'));
                        jQuery(this).append('<td>' + getCheckBoxRow(index) + '</td>');
                    })
                    jQuery('#modifyOrderModal .modal-body').html(clonedOrderTable);
                    jQuery('#modifyOrderModal').modal();
                }
                
            });
            jQuery("#modalSaveChanges").click(function(){
            	var body = jQuery('#modifyOrderModal .modal-body');
            	var orderTable;
            	if(body !== null && typeof body !== undefined) {
            		orderTable = jQuery(body).children('.table');
            		jQuery(orderTable).find('tr').each(function(index, value){
            			if(jQuery(this).find("[type=radio]").is(':checked')) {
            			    alert("checked");
            			    console.log(jQuery(this).attr('id'));
                		}
                    })
                }
            });
        }

        var getCheckBoxRow = function(index) {
        	return '<label class="btn btn-default"><input type="radio" name="availability'+ index +'" id="availability'+ index +'" value="Not Available"> Not Available </label>'
//             var html = '<input type="radio" name="year" value="Available"> Available';
//             return html;
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
                   // alert(data);
                });
                channel.bind('update_order', function(data) {
                    alert(data.message);
                  });
                channel.bind('get_bill', function(data) {
                    alert(data.message);
                  });
    } 
    var createOrderPage = function(data) {
        var html = '';
        if(data !== null && typeof data !== undefined) {
            var customer = jQuery('#'+data.customerId);
            if(customer === null || customer.length ===0) {
                // New Customer on the table.
                html += '<div id="'+ data.customerId +'">';
                html += '<ul id="' + data.orderId + '" class="list-group">';
                html += getSubOrderList(data);
                html += '</ul></div>';

                var table = jQuery("#" + data.tableId);
                if(table === null || table.length === 0) {
                	var rest = jQuery("#" + data.restaurantId);
                	var tableHtml = '<div id="' + data.tableId + '">'+ html +'</div>';
                	rest.append(html);
                } else {
                	table.append(html);
                }
            } else {
                var orderList = jQuery('#'+data.orderId);
            	html = getSubOrderList(data);
            	orderList.append(html);
            }
        }
    }

    var getSubOrderList = function(data) {
    	var html = '<li id="' + data.subOrderId + '" class="list-group-item">';
        html += getDishList(data);
        html += getNewOrderOptions();
        html+='</li>';
        return html;
    }

    var getNewOrderOptions = function() {
        var html = '<div class="row"><div class="col-xs-12 col-md-12 col-lg-12">' 
        html += '<div class="btn-group btn-group-lg">'+
                    '<div id="acceptBtn" class="col-lg-4 col-xs-4 col-md-4"><button type="button" class="btn btn-lg">Accept</button></div>'+
                    '<div id="declineBtn" class="col-lg-4 col-xs-4 col-md-4"><button type="button" class="btn btn-lg">Decline</button></div>'+
        	        '<div id="modifyBtn" class="col-lg-4 col-xs-4 col-md-4"><button type="button" class="btn btn-lg">Modify</button></div>'+
        	    '</div>';
        html += '</div></div>';
        return html;
    }
    //data-toggle="modal" data-target="#modifyOrderModal"
    var getDishList = function(data) {
        var orderHtml = '<table class="table" border="1">';
        jQuery(data.dishes).each(function(index, value) {
            orderHtml +='<tr id='+ value.dishId +'><td>' + value.name 
            + '</td><td>' + value.quatity + '</td><td>' + value.price + '</td></tr>'; 
        });
        orderHtml += '</table>'
        return orderHtml;
    }
</script>