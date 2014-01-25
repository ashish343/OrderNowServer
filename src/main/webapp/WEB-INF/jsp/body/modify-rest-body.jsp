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
            	var unorderList = jQuery(this).parents('ul');
            	var orderId = jQuery(unorderList).attr('id');
            	
            	if(orderId !== null && orderId.length > 0) {
            	    var request = $.ajax({
            	        url: "/restOrder?action=orderAccepted&orderId=" + orderId,
                        type: "GET",
                    });
                    var identifier = 'alert-success';
                    
            	    jQuery(unorderList).find('.'+identifier).show();
                    setTimeout(function(){hideAlert(unorderList,identifier)}, 3000);
                    jQuery(unorderList).find('li').find(".order-handler-btn-group").hide();
                }
            });
            
            jQuery("#modifyBtn").click(function() {
                var orderList = jQuery(this).parents('li');
                var orderTable;
                if(orderList !== null && typeof orderList !== undefined) {
                    orderTable = jQuery(orderList).children('.table');

                    clonedOrderTable = jQuery(orderTable).clone();
                    
                    jQuery(clonedOrderTable).find('tr').each(function(index, value){
                        if(index > 0) {
                            jQuery(this).append('<td>' + getCheckBoxRow(index) + '</td>');
                        } else {
                        	jQuery(this).append('<th>Availability</th>');
                        }
                    })
                    var model = jQuery('#modifyOrderModal .modal-body');
                    var unorderList = jQuery(this).parents('ul');
                    
                    model.attr("data-a-orderId", jQuery(unorderList).attr('id'));
                    model.html(clonedOrderTable);
                    jQuery('#modifyOrderModal').modal();
                }
                
            });
            jQuery("#modalSaveChanges").click(function(e){
            	e.preventDefault();
            	var dishIds=[];
            	var body = jQuery('#modifyOrderModal .modal-body');
            	var orderTable;
            	if(body !== null && typeof body !== undefined) {
            		orderTable = jQuery(body).children('.table');
            		jQuery(orderTable).find('tr').each(function(index, value){
            			if(jQuery(this).find("[type=radio]").is(':checked')) {
            			    dishIds.push(jQuery(this).attr('id'));
                		}
                    })
                }
                var orderId = jQuery(body).attr('data-a-orderId');
                if(dishIds.length > 0) {
                    rmvDishFrmOrdr(orderId, dishIds);
                    var request = $.ajax({
                        url: "/restOrder?action=modifyOrder&orderId=" + orderId + "&dishIds=" + dishIds,
                        type: "GET",
                    });
                }
            	$('#modifyOrderModal').modal('hide');

            	var identifier = 'alert-warning';
            	jQuery('#'+orderId).find('.'+identifier).show();
                setTimeout(function(){hideAlert(jQuery('#'+orderId), identifier)}, 3000);
                var orderList = jQuery('#'+orderId).find('li');
                jQuery(orderList).find(".order-handler-btn-group").hide();
            });
        }

        var hideAlert = function(unorderList, identifier) {
            jQuery(unorderList).find('.'+identifier).hide();
        }
        
        var rmvDishFrmOrdr = function(orderId, dishIds) {
            console.log("Order Id::" + orderId);
            var orderList = jQuery('#' + orderId);
            var i = 0;
            orderTables = jQuery(orderList).find('.table');
            jQuery(orderTables).each(function(index, value){
                jQuery(this).find('tr').each(function(index, value) {
                	for (i = 0; i < dishIds.length; i++) {
                        if(jQuery(this).attr('id')  === dishIds[i]) {
                            jQuery(this).remove();
                        }
                    }
                });
            });
        }
        var getCheckBoxRow = function(index) {
        	return '<label class="btn btn-default"><input type="radio" name="availability'+ index +'" id="availability'+ index +'" value="Not Available"> Not Available </label>'
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
                html += getSubOrderList(data, 1);
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
            	html = getSubOrderList(data, 0);
            	orderList.append(html);
            }
        }
    }

    var getSubOrderList = function(data, addTableTitle) {
        
    	var html = '<li id="' + data.subOrderId + '" class="list-group-item">';
    	//Success Alert
    	html += '<div class="alert alert-success alert-dismissable" style="display:none;">'+
    	        '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
    	        '<strong>Order Placed.</strong></div>';
    	//Modified List Alert
    	html += '<div class="alert alert-warning alert-dismissable" style="display:none;">'+
                '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
                '<strong>Customer Is Notified About the Modification</strong></div>';
        html += getDishList(data, addTableTitle);
        html += getNewOrderOptions();
        html+='</li>';
        return html;
    }

    var getNewOrderOptions = function() {
        var html = '<div class="row"><div class="col-xs-12 col-md-12 col-lg-12">' 
        html += '<div class="order-handler-btn-group btn-group btn-group-lg">'+
                    '<div id="acceptBtn" class="col col-lg-6 col-xs-6 col-md-6"><button type="button" class="btn btn-lg">Accept</button></div>'+
        	        '<div id="modifyBtn" class="col col-lg-6 col-xs-6 col-md-6"><button type="button" class="btn btn-lg">Modify</button></div>'+
        	    '</div>';
        html += '</div></div>';
        return html;
    }
    //data-toggle="modal" data-target="#modifyOrderModal"
    var getDishList = function(data, addTableTitle) {
        var orderHtml = '<table class="table" border="1">';
        if(addTableTitle == '1') {
        	orderHtml += '<tr><th>Item</th><th>Quantity</th><th>Price</th></tr>';
        } 
        jQuery(data.dishes).each(function(index, value) {
            orderHtml +='<tr id='+ value.dishId +'><td>' + value.name 
            + '</td><td>' + value.quatity + '</td><td>' + value.price + '</td></tr>'; 
        });
        orderHtml += '</table>'
        return orderHtml;
    }
</script>