package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enums.UrlParameter;
import com.handlers.restaurant.RestaurantEvents;
import com.handlers.restaurant.RestaurantEventsHandler;
import com.utility.RequestContext;

@SuppressWarnings("serial")
@WebServlet(
        name = "RestaurantOrderServlet", 
        urlPatterns = {"/restOrder"}
    )
public class RestaurantOrderSevlet  extends HttpServlet {
	/*
	 *  There are 5 events a restaurant can send to the Server which has to be communicated to the Client:
	 *  	ORDER_RECEIVED("orderReceived"),
	 * 		ORDER_ACCEPTED("orderAccepted"),
	 *		BILL_GENERATED("generateBill"),
	 * 		ORDER_COMPLETED("orderCompleted"),
	 * 		MODIFY_ORDER("modifyOrder");
	 * 
	 * The Restaurant will return the following information:
	 * 1) Client ID
	 * 2) Table ID
	 * 3) RestaurantId
	 * 4) Order ID
	 * 
	 * Order Id is the Channel to publish the notification to the client.
	 * 
	 * Eg URL:
	 * ORDER_RECEIVED
	 *		restOrder?action=orderReceived&orderId=Oid
	 * ORDER_ACCEPTED
	 * 		restOrder?action=orderAccepted&orderId=Oid
	 * BILL_GENERATED
	 * 		restOrder?action=generateBill&orderId=Oid
	 * ORDER_COMPLETED
	 * 		restOrder?action=orderCompleted&orderId=Oid
	 * MODIFY_ORDER
	 * 		restOrder?action=modifyOrder&orderId=Oid
	 */
	
	private static final String EMPTY_STRING = "";
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = response.getOutputStream();;
    	boolean isDebug = RequestContext.isDebugEnabled();
    	/*
    	 * Get the Customer Order.
    	 */
		String action = request.getParameter(UrlParameter.USER_ACTION.toString());
		
		if(isDebug)
		outputStream.write(("\n Action::" + action).getBytes());
		
		if(action != null && !EMPTY_STRING.equals(action)) {
			if(RestaurantEvents.ORDER_RECEIVED.toString().equals(action)) {
				RestaurantEventsHandler.handleOrderReceived(response, request, outputStream);
			}
			if(RestaurantEvents.ORDER_ACCEPTED.toString().equals(action)) {
				RestaurantEventsHandler.handleOrderAccepted(response, request, outputStream);
			}
			if(RestaurantEvents.ORDER_COMPLETED.toString().equals(action)) {
				RestaurantEventsHandler.handleOrderCompleted(response, request, outputStream);
			}
			if(RestaurantEvents.BILL_GENERATED.toString().equals(action)) {
				RestaurantEventsHandler.handleBill(response, request, outputStream);
			}
			if(RestaurantEvents.MODIFY_ORDER.toString().equals(action)) {
				RestaurantEventsHandler.handleModifyOrder(response, request, outputStream);
			}
		}
		
    	outputStream.flush();
    }
}
