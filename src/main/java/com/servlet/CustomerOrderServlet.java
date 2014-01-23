package com.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

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
import com.parse.ParseNotificationHelper;
import com.pusher.PusherHelper;
import com.test.PusherTest;
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
		ServletOutputStream outputStream = response.getOutputStream();
    	boolean isDebug = RequestContext.isDebugEnabled();
    	/*
    	 * Get the Customer Order.
    	 */
    	CustomerOrder customerOrder = getCustomerOrder(request, outputStream, isDebug);
		
		/*
		 * Fetch the Menu From DB to create Restaurant Order.
		 */
		/*
		 * TODO: Remove the below hack.
		 */
		String restuarantId = customerOrder.getRestaurantId();
		//Menu menu = getMenu(restuarantId, outputStream, isDebug);
		Menu menu = getMenu();
    	
		/*
		 * Populate the Restaurant Order from Customer Order.
		 */
    	/*
    	 * TODO: Check if the order already exist, if yes then get the order id else create a new one.
    	 */
    	String orderId = OrderIdGenerator.generateUniqueOrderId();
    	String customerId = customerOrder.getCustomerId();
    	CustomerRestaurantHandshake customerRest = new CustomerRestaurantHandshake();
    	RestaurantOrder restaurantOrder = customerRest.getRestaurantOrder(menu, customerOrder, orderId, customerId);
    	
    	/*
    	 * TODO: Update DB with the order.
    	 */
    	DataConnection.setOrderDetailsToDB();
		
    	/*
    	 * Subscribe Customer to The Channel.
    	 */
    	ParseNotificationHelper.registerChannel(customerId, orderId, outputStream);
    	
    	/*
    	 * Send Notification to Restaurant about the order.
    	 */
    	Gson gson = new Gson();
    	String restaurantOrderJson = gson.toJson(restaurantOrder);
    	String json = "{\"data\":\"{\\\"message\\\":\\\"hello world\\\"}\",\"name\":\"notify_order\",\"channel\":\"R1\"}";
    	//PusherHelper.triggerPush(restuarantId, RestaurantClientSideEvents.NOTIFY_NEW_ORDER.toString(), json);
    	PusherTest.triggerPush("R1", "notify_order", json, "");
		/*
		 * Return a success message to the User.
		 */
    	outputStream.flush();
    }
    
    private Menu getMenu(String restuarantId, ServletOutputStream outputStream, boolean isDebug) throws IOException {
    	Menu menu = null;
    	Gson gson = new Gson();
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
    	return menu;
	}
    
    private static Menu getMenu() {
    	Menu menu = null;
    	try {
    		BufferedReader br = new BufferedReader(new FileReader("flare.json"));
    		System.out.println("In get Menu::");
    		String menuJson = br.readLine();
    		while ((br.readLine()) != null) {
				menuJson += br.readLine();
			}
    		System.out.println("Menu is:::" + menuJson);
    		Gson gson = new Gson();
    		menu = gson.fromJson(menuJson, Menu.class);
    	} catch(Exception e) {
    		System.out.println("Exception::" + e.toString());
    	}
    	return menu;
    }

	private CustomerOrder getCustomerOrder(HttpServletRequest request, ServletOutputStream outputStream, boolean isDebug) throws IOException {
    	String customerOrderJson = request.getParameter(UrlParameter.CUSTOMER_ORDER.toString());
		Gson gson = new Gson();
		CustomerOrder customerOrder = gson.fromJson(customerOrderJson, CustomerOrder.class);
		
		if(isDebug) {
			outputStream.write(("\nCustomer Json Order :: ").getBytes());
			outputStream.write(("\n" + customerOrderJson).getBytes());
		}
		return customerOrder;
	}
    

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
