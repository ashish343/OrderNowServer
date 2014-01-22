package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.menu.CustomerOrder;
import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.data.restaurant.RestaurantClientSideEvents;
import com.data.restaurant.RestaurantOrder;
import com.database.DataConnection;
import com.enums.EventState;
import com.enums.UrlParameter;
import com.google.gson.Gson;
import com.handlers.table.TableHandler;
import com.pusher.PusherHelper;
import com.utility.CustomerRestaurantHandshake;
import com.utility.OrderIdGenerator;
import com.utility.RequestContext;

@SuppressWarnings("serial")
@WebServlet(
        name = "CustomerOrderServlet", 
        urlPatterns = {"/order"}
    )
public class CustomerOrderServlet  extends HttpServlet {
	/*
	 * Sample JSON:
	 * {"dishes":{"D2":1.0,"D3":0.5,"D1":1.0},"restaurantId":"R1","customerId":"NXwUdXIorg"}
	 */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = null;
    	boolean isDebug = RequestContext.isDebugEnabled();
    	/*
    	 * Get the Customer Order.
    	 */
		String customerOrderJson = request.getParameter(UrlParameter.CUSTOMER_ORDER.toString());
		Gson gson = new Gson();
		CustomerOrder customerOrder = gson.fromJson(customerOrderJson, CustomerOrder.class);
		
		if(isDebug) {
			outputStream = response.getOutputStream();
			outputStream.write(("\nCustomer Json Order :: ").getBytes());
			outputStream.write(("\n" + customerOrderJson).getBytes());
		}
		
		/*
		 * Fetch the Menu From DB to create Restaurant Order.
		 */
		Menu menu = null;
		String restuarantId = customerOrder.getRestaurantId();
    	if(restuarantId != null) {
    		Restaurant restaurantData = TableHandler.getRestaurantInformation(restuarantId, outputStream);
    		menu = restaurantData.getMenu();
    	} else {
    		//Return a response to try scan the QR code again.
    		outputStream.write(EventState.FAILURE.toString().getBytes());
    	}
    	
    	if(isDebug) {
    		outputStream.write(("\nMenu Data from DB::" + gson.toJson(menu)).getBytes());
    	}
		/*
		 * Populate the Restaurant Order from Customer Order.
		 */
    	
    	String orderId = OrderIdGenerator.generateUniqueOrderId();
    	String customerId = customerOrder.getCustomerId();
    	CustomerRestaurantHandshake customerRest = new CustomerRestaurantHandshake();
    	RestaurantOrder restaurantOrder = customerRest.getRestaurantOrder(menu, customerOrder, orderId, customerId);
    	
    	/*
    	 * Update DB with the order.
    	 */
    	DataConnection.setOrderDetailsToDB();
		
    	
    	/*
    	 * Send Notification to Restaurant about the order.
    	 */
    	String restaurantOrderJson = gson.toJson(restaurantOrder);
    	PusherHelper.triggerPush(restuarantId, RestaurantClientSideEvents.NOTIFY_NEW_ORDER.toString(), restaurantOrderJson);
		
		/*
		 * Return a success message to the User.
		 */
    	outputStream.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
