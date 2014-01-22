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
        name = "CustomerOrderServlet", 
        urlPatterns = {"/restOrder"}
    )
public class RestaurantOrderSevlet  extends HttpServlet {
	/*
	 * Sample JSON:
	 * {"dishes":{"D2":1.0,"D3":0.5,"D1":1.0},"restaurantId":"R1","customerId":"NXwUdXIorg"}
	 */
	
	private static final String EMPTY_STRING = "";
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = null;
    	boolean isDebug = RequestContext.isDebugEnabled();
    	/*
    	 * Get the Customer Order.
    	 */
    	if(isDebug) {
    		outputStream = response.getOutputStream();
    	}
    	
		String action = request.getParameter(UrlParameter.USER_ACTION.toString());
		
		if(action != null && !EMPTY_STRING.equals(action)) {
			if(action == RestaurantEvents.ORDER_RECEIVED.toString()) {
				RestaurantEventsHandler.handleOrderReceived(response, request, outputStream);
			}
			if(action == RestaurantEvents.ORDER_ACCEPTED.toString()) {
				RestaurantEventsHandler.handleOrderAccepted(response, request, outputStream);
			}
			if(action == RestaurantEvents.ORDER_COMPLETED.toString()) {
				RestaurantEventsHandler.handleOrderCompleted(response, request, outputStream);
			}
			if(action == RestaurantEvents.BILL_GENERATED.toString()) {
				RestaurantEventsHandler.handleBill(response, request, outputStream);
			}
			if(action == RestaurantEvents.MODIFY_ORDER.toString()) {
				RestaurantEventsHandler.handleModifyOrder(response, request, outputStream);
			}
		}
		
    	outputStream.flush();
    }
}
