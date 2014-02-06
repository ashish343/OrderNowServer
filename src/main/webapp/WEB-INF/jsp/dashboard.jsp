<%@ include file="/WEB-INF/jsp/include.jsp" %>

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
			  margin:0 auto;
			}
			
			#vertical-ticker{
				height:400px;
				overflow:hidden;
				margin:0; padding:0;
				-webkit-box-shadow:0 1px 3px rgba(0,0,0, .4);
			}
			.hidden {
			    display:none;
			}
        </style>
    </head>
    <body>
        <div class="container">
            <section class="main demo-2">
                <!-- Table Information -->
                <div class="row">
                    <!-- Table Tickr -->
                    <!-- <div class="col col-lg-2 hidden-xs hidden-md">
                        <h3> Table Tickr </h3>
                        <div id="wrapper">--><!-- verical ticker start  -->        
                            <!-- <ul id="vertical-ticker" class="list-group"></ul>
                            <p><a href="#" id="ticker-previous">Previous</a> / <a href="#" id="ticker-next">Next</a> / <a id="stop" href="#">Stop</a> / <a id="start" href="#">Start</a></p>
                            <p>Roll over the ticker to stop scrolling</p>       
                        </div> --><!-- vertical ticker end  -->
                    <!-- </div>  -->
                    <!-- Table With Orders -->
                    <div class="col col-lg-8 col-md-8 col-xs-8">
                        <h3> Table Orders </h3>
                        <div id="grid" class="grid clearfix">
                            <c:set var="shouldShow" value="false"/>
                            <%@ include file="/WEB-INF/jsp/common/table.jsp" %>
                        </div><!-- / grid -->
                    </div>
                    <!-- Table With Out Orders -->
                    <div class="col col-lg-4 col-md-4 col-xs-4">
                        <h3> Other Tables </h3>
                        <div class="unordered-tables">
                            <c:set var="shouldShow" value="true"/>
                            <%@ include file="/WEB-INF/jsp/common/table.jsp" %>
                        </div>
                    </div>
                </div>
                <p class="info"><strong>Demo 3:</strong> Same as demo 2, item will be centered on its original position</p>
            </section>
        </div>
        
        
        <!-- Modal -->
        <%@ include file="/WEB-INF/jsp/common/modal.jsp" %>
        
        
        
        
        
        <script type="text/javascript" src="/resources/new-layout/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="/resources/js/favicon.js"></script>
        <script type="text/javascript" src="/resources/order-page/js/modernizr.custom.79639.js"></script>
        <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="/resources/js/pusher.min.js"></script>
        <script type="text/javascript" src="/resources/order-page/js/jquery.pfold.js"></script>
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
                        jQuery('.notification').css({'z-index':'-1'});
                    }
                } ).end().find( 'span.icon-cancel' ).on( 'click', function() {
                    pfold.fold();
                    
                    setTimeout(function(){jQuery('.notification').css({'z-index':'1'})}, 1000);
                } );
            } );
            var olderOrderData = '${restaurantData.orders}';
            var olderOrderDataJson = jQuery.parseJSON(olderOrderData); 
            republishData(olderOrderDataJson);
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
                    handleDataList(data);
                    var request = $.ajax({
                        url: "/restOrder?action=orderReceived&orderId=" + data.orderId,
                        type: "GET",
                      });
                    /* alert(data); */
                    handleNotifications(data);
                });
                channel.bind('update_order', function(data) {
                    alert(data.message);
                  });
                channel.bind('get_bill', function(data) {
                    alert(data.message);
                  });
    }

    var republishData = function(data) {
        if(data != null) {
            jQuery(data).each(function(index, value) {
                handleDataList(value);
                if(JSON.stringify(value.orderState) == JSON.stringify("interMediate")) {
                    handleNotifications(value);
                }
            });
        }
    }
    
    var handleDataList = function(data) {
        addTable(data.tableId);
        createOrderPage(data);
        insertIntoTicker(data);
        attachEvent();
    }

    var handleNotifications = function(data) {
    	faviconCount += 1;
        
        if(faviconCount > 0) {
            favicon.badge(faviconCount);
        }
        showNotification(data.orderId, 1);
    }
    
    var addTable = function(tableId) {
        jQuery('.unordered-tables > .' + tableId).hide();
        jQuery('#grid').find('.'+tableId).removeClass('hidden');
        
    }
        
	var insertIntoTicker= function(data){
		var html=''
		 
         html += getSubOrderList(data, 1);
         
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
                table.append(html);
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
        if(JSON.stringify(data.orderState) == JSON.stringify("interMediate")) {
            html += '<div class="alert alert-success alert-dismissable" style="display:none;">'+
                    '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
                    '<strong>Order Placed.</strong></div>';
                //Modified List Alert
            html += '<div class="alert alert-warning alert-dismissable" style="display:none;">'+
                    '<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
                    '<strong>Customer Is Notified About the Modification</strong></div>';
        }

        html += getDishList(data, addTableTitle);
        if(JSON.stringify(data.orderState) == JSON.stringify("interMediate")) {
            html += getNewOrderOptions();
        }
        
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
			row_height	:	'auto',
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