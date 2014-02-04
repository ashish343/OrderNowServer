<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <title>Order Now</title>
        <link rel="icon" type="image/png" href="/resources/img/icon.png" />
        <link type="text/css" href="${path}/resources/css/bootstrap.min.css" rel="stylesheet"/>
        <link rel="stylesheet" type="text/css" href="/resources/order-page/css/demo.css" />
        <link rel="stylesheet" type="text/css" href="/resources/order-page/css/pfold.css" />
        <link rel="stylesheet" type="text/css" href="/resources/order-page/css/custom2.css" />
        <script type="text/javascript" src="/resources/order-page/js/modernizr.custom.79639.js"></script>
        <!--[if lte IE 8]><style>.support-note .note-ie{display:block;}</style><![endif]-->
        <style>
            body { 
                background-image: url(/resources/order-page/images/bg.jpg); }
            }
            .title-header {
                margin-top:0px !important;margin-bottom:0px !important;
            }
            .icon-image {
                margin:auto;
                margin-top:10px;
            }
            .notification {
                display: block;
                background: #E43C03;
                font-size: 16px;
                font-weight: bold;
                -moz-border-radius: 6px;
                -webkit-border-radius: 6px;
                width: 16px;
                margin-left: 120px;
                /* margin-top: auto; */
                position: absolute;
                text-align: center;
                border-radius:6px;
            }
            #wrapper{
			  padding:30px;
			  margin:0 auto;
			}
			
			#vertical-ticker{
				height:400px;
				overflow:hidden;
				margin:0; padding:0;
				-webkit-box-shadow:0 1px 3px rgba(0,0,0, .4);
			}
			#vertical-ticker li{
				padding:35px 20px;
				display:block;
				background:#efefef;
				color:#333;
				border-bottom:1px solid #ddd;
				text-align:center;
				font-size:25px;
				font-weight:bold;
				font-family: Helvetica Neue, times, serif;
			}
			
            
        </style>
    </head>
    <body>
        <div class="container page-layout">
            <section class="main demo-2">
                
                <div id="grid" class="grid clearfix">
                
                    <div class="uc-container">
                        <div class="uc-initial-content">
                            <h1 style="margin-top:0px !important;margin-bottom:0px !important; margin-left:40px"> Table 1 </h1>
                            <div>
                                <span class="notification" style="display:none">0</span>
                                <img src="/resources/img/order_icon.png" class="icon-image"/>
                            </div>
                            <span class="icon-eye"></span>
                        </div>
                        <div class="uc-final-content">
                            <div id="R1"class="panel panel-default">
                            <!-- Default panel contents -->
                                <div class="panel-heading">Table 1</div>
                            </div>
                            <span class="icon-cancel"></span>
                        </div>
                    </div><!-- / uc-container -->

                    <div class="uc-container">
                        <div class="uc-initial-content">
                            <h1 style="margin-top:0px !important;margin-bottom:0px !important; margin-left:40px"> Table 2 </h1>
                            <div>
                                <span class="notification" style="display:none">0</span>
                                <img src="/resources/img/order_icon.png" class="icon-image"/>
                            </div>
                            <span class="icon-eye"></span>
                        </div>
                        <div class="uc-final-content">
                            <img src="/resources/order-page/images/large/1.jpg" alt="image01-large" />
                            <span class="icon-cancel"></span>
                        </div>
                    </div><!-- / uc-container -->

                    <div class="uc-container">
                        <div class="uc-initial-content">
                            <h1 style="margin-top:0px !important;margin-bottom:0px !important; margin-left:40px"> Table 3 </h1>
                            <div>
                                <span class="notification" style="display:none">0</span>
                                <img src="/resources/img/order_icon.png" class="icon-image"/>
                            </div>
                            <span class="icon-eye"></span>
                        </div>
                        <div class="uc-final-content">
                            <img src="/resources/order-page/images/large/2.jpg" alt="image02-large" />
                            <span class="icon-cancel"></span>
                        </div>
                    </div><!-- / uc-container -->

                    <div class="uc-container">
                        <div class="uc-initial-content">
                            <h1 style="margin-top:0px !important;margin-bottom:0px !important; margin-left:40px"> Table 4 </h1>
                            <div>
                                <span class="notification" style="display:none">0</span>
                                <img src="/resources/img/order_icon.png" class="icon-image"/>
                            </div>
                            <span class="icon-eye"></span>
                        </div>
                        <div class="uc-final-content">
                            <img src="/resources/order-page/images/large/4.jpg" alt="image04-large" />
                            <span class="icon-cancel"></span>
                        </div>
                    </div><!-- / uc-container -->

                </div><!-- / grid -->
                <p class="info"><strong>Demo 3:</strong> Same as demo 2, item will be centered on its original position</p>
            </section>
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

	<div id="wrapper"><!-- verical ticker start  -->		
		<ul id="vertical-ticker">
			<li>empty</li>
		</ul>
		<p><a href="#" id="ticker-previous">Previous</a> / <a href="#" id="ticker-next">Next</a> / <a id="stop" href="#">Stop</a> / <a id="start" href="#">Start</a></p>
		<p>Roll over the ticker to stop scrolling</p>		
	</div><!-- vertical ticker end  -->

        <script type="text/javascript" src="/resources/new-layout/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="/resources/js/pusher.min.js"></script>
        <script type="text/javascript" src="/resources/order-page/js/jquery.pfold.js"></script>
        <script type="text/javascript" src="/resources/js/favicon.js"></script>
       	<script type="text/javascript" src="/resources/js/jquery.totemticker.js"></script>
        <script type="text/javascript">
        var favicon;
        var faviconCount = 0;
        $(function() {
        	favicon=new Favico({
                bgColor : '#ff0000',
                textColor : '#ff0',
                type: 'rectangle',
                animation:'fade'
            });
                	
            // say we want to have only one item opened at one moment
            var opened = false;
            attachEvent($);
            myPusherFunc();
            startTicker();
            $( '#grid > div.uc-container' ).each( function( i ) {
                var $item = $( this ); 
                var direction = ['left','top'];

                var pfold = $item.pfold( {
                    folddirection : direction,
                    speed : 200,
                    onEndFolding : function() { opened = false; },
                    centered : true,
                    folds:3
                } );
                
                $item.find( 'span.icon-eye' ).on( 'click', function() {
                    if( !opened ) {
                        opened = true;
                        pfold.unfold();
                    }
                } ).end().find( 'span.icon-cancel' ).on( 'click', function() {
                    pfold.fold();
                } );
            } );
        });
        var d;
       
        var attachEvent = function() {
            jQuery(".orderItemRow").click(function() {
            });
            
            jQuery(".acceptBtn.notbound").click(function() {
                var unorderList = jQuery(this).parents('ul');
                var orderList = jQuery(this).parents('li');
                var orderId = jQuery(unorderList).attr('id');
                var subOrderId = jQuery(orderList).attr('id');
                if(orderId !== null && orderId.length > 0) {
                    var request = $.ajax({
                        url: "/restOrder?action=orderAccepted&orderId=" + orderId + "&subOrderId=" + subOrderId,
                        type: "GET",
                    });
                    var identifier = 'alert-success';
                    
                    jQuery(orderList).find('.'+identifier).show();
                    setTimeout(function(){hideAlert(orderList,identifier)}, 3000);
                    jQuery(unorderList).find('#'+subOrderId).find(".order-handler-btn-group").hide();
                }
                faviconCount -=1;
                if(faviconCount > 0) {
                    favicon.badge(faviconCount);
                } else {
                	faviconCount = 0;
                    favicon.reset();
                }
                showNotification(orderId, -1);
            });

            jQuery('.acceptBtn ').removeClass('notbound');
            
            jQuery(".modifyBtn.notbound").click(function() {
                var orderList = jQuery(this).parents('li');
                var subOrderId = jQuery(orderList).attr('id');
                var orderTable;
                if(orderList !== null && typeof orderList !== undefined) {
                    orderTable = jQuery(orderList).children('.table');

                    clonedOrderTable = jQuery(orderTable).clone();

                    if(subOrderId != '0') {
                        jQuery(clonedOrderTable).prepend('<tr><th>Item</th><th>Quantity</th><th>Price</th></tr>');
                    }
                    
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
                    model.attr("data-a-subOrderId", jQuery(orderList).attr('id'));
                    
                    model.html(clonedOrderTable);
                    jQuery('#modifyOrderModal').modal();
                }
                
            });
            jQuery('.modifyBtn').removeClass('notbound');
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
                var subOrderId = jQuery(body).attr('data-a-subOrderId');
                if(dishIds.length > 0) {
                    rmvDishFrmOrdr(orderId, subOrderId, dishIds);
                    var request = $.ajax({
                        url: "/restOrder?action=modifyOrder&orderId=" + orderId + "&subOrderId=" + subOrderId + "&dishIds=" + dishIds,
                        type: "GET",
                    });
                }
                $('#modifyOrderModal').modal('hide');

                var identifier = 'alert-warning';
                jQuery('#'+orderId).find('#'+subOrderId).find('.'+identifier).show();
                setTimeout(function(){hideAlert(jQuery('#'+orderId).find('#'+subOrderId), identifier)}, 3000);
                var orderList = jQuery('#'+orderId).find('#'+subOrderId);
                jQuery(orderList).find(".order-handler-btn-group").hide();
                faviconCount -=1;
                if(faviconCount > 0) {
                    favicon.badge(faviconCount);
                } else {
                    favicon.reset();
                    faviconCount = 0;
                }
                showNotification(orderId, -1);
            });
        }

        var hideAlert = function(unorderList, identifier) {
            jQuery(unorderList).find('.'+identifier).hide();
        }
        
        var rmvDishFrmOrdr = function(orderId, subOrderId, dishIds) {
            var orderList = jQuery('#' + orderId).find('#'+subOrderId);
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
                    insertIntoTicker(data);
                    attachEvent();
                    showNotification(data.orderId, 1);
                    var request = $.ajax({
                        url: "/restOrder?action=orderReceived&orderId=" + data.orderId,
                        type: "GET",
                      });
                    /* alert(data); */
                    faviconCount +=1;
                    
                    if(faviconCount > 0) {
                        favicon.badge(faviconCount);
                    }
                });
                channel.bind('update_order', function(data) {
                    alert(data.message);
                  });
                channel.bind('get_bill', function(data) {
                    alert(data.message);
                  });
    } 
	var insertIntoTicker= function(data){
		var html=''
		 html += '<div id="'+ data.customerId +'">';
         html += '<ul id="' + data.orderId + '" class="list-group">';
         html += getSubOrderList(data, 1);
         html += '</ul></div>';
		$('#wrapper ul').append(html);         
	}
	
    var showNotification = function(orderId, modifier) {
        var orderList = jQuery('#'+orderId);
        var container = jQuery(orderList).parents('.uc-container');
        var outerDisplay = jQuery(container).children(".uc-initial").find(".notification");
        var count = parseInt(jQuery(outerDisplay).html(),10);
        count += modifier;
        if(count > 0) {
        	jQuery(outerDisplay).html(count);
        	jQuery(outerDisplay).show()
        } else {
        	jQuery(outerDisplay).html("0");
        	jQuery(outerDisplay).hide()
        }
    };
    var createOrderPage = function(data) {
        var html = '';
        if(data !== null && typeof data !== undefined) {
            var customer = jQuery('#'+data.customerId);
            if(customer === null || customer.length == '0') {
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
                    '<div class="acceptBtn notbound col col-lg-6 col-xs-6 col-md-6"><button type="button" class="btn btn-lg">Accept</button></div>'+
                    '<div class="modifyBtn notbound col col-lg-6 col-xs-6 col-md-6"><button type="button" class="btn btn-lg">Modify</button></div>'+
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

    var startTicker= function(){
		$('#vertical-ticker').totemticker({
			row_height	:	'100px',
			next		:	'#ticker-next',
			previous	:	'#ticker-previous',
			stop		:	'#stop',
			start		:	'#start',
			mousestop	:	true,
		});
	}
    </script>
    </body>
</html>