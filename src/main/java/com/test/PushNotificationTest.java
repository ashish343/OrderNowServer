package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.handlers.restaurant.RestaurantEventsHandler;

@SuppressWarnings("serial")
@WebServlet(
        name = "PushNotificationTestServlet", 
        urlPatterns = {"/test/push/"}
    )
public class PushNotificationTest  extends HttpServlet {

	/*
	 * Accept  
	 * 1) custId as Custoemr Push ID;
	 * 2) orderId as the channel.
	 * 
	 * Eg:
	 * 	test/push?orderId=Oid1&custId=ABC
	 */
	
	protected final Log logger = LogFactory.getLog(getClass());
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletOutputStream outputStream = response.getOutputStream();
    	outputStream.write("PushNotificationTest Servlet".getBytes());
        String orderId = request.getParameter("orderId");
        
        outputStream.write(orderId.getBytes());
        try {
        	outputStream.write("\nTesting Received Order.".getBytes());
        	testOrderReceived(request, response, outputStream);
        	outputStream.write("\n\nSleeping for 1 second.".getBytes());
        	Thread.currentThread().sleep(2000);
         
        	outputStream.write("\nTesting Accepted Order.".getBytes());
        	testOrderAccepted(request, response, outputStream);
        	outputStream.write("\n\nSleeping for 1 second.".getBytes());
        	Thread.currentThread().sleep(2000);
        
        	outputStream.write("\nTesting Completed Order.".getBytes());
        	testOrderCompleted(request, response, outputStream);
        	outputStream.write("\n\nSleeping for 1 second.".getBytes());
        	Thread.currentThread().sleep(2000);
        
        	outputStream.write("\nTesting Bill Order.".getBytes());
        	testOrderBill(request, response, outputStream);
        	outputStream.write("\n\nSleeping for 1 second.".getBytes());
        	Thread.currentThread().sleep(2000);
        
        	outputStream.write("\nTesting Modify Order.".getBytes());
        	testModifyOrder(request, response, outputStream);
        	//ParseNotificationHelper.registerChannel(customerId, orderId, outputStream);
        	outputStream.flush();
        } catch (InterruptedException e) {
        	outputStream.write(e.toString().getBytes());
		}
        //String data = "{\"channels\":[\"" + orderId + "\"],\"data\": {\"name\": \"Ashish\", \"newsItem\": \"Man bites dog\"}}";
		//ParseNotificationHelper.notifyChannel(orderId, data , outputStream);
        outputStream.flush();
    }
    
    private void testOrderReceived(HttpServletRequest request, HttpServletResponse response, ServletOutputStream outputStream) throws IOException {
    	RestaurantEventsHandler.handleOrderReceived(response, request, outputStream);
	}
    
    private void testOrderAccepted(HttpServletRequest request, HttpServletResponse response, ServletOutputStream outputStream) throws IOException {
    	RestaurantEventsHandler.handleOrderAccepted(response, request, outputStream);
	}
    
    private void testOrderCompleted(HttpServletRequest request, HttpServletResponse response, ServletOutputStream outputStream) throws IOException {
    	RestaurantEventsHandler.handleOrderCompleted(response, request, outputStream);
	}
    
    private void testOrderBill(HttpServletRequest request, HttpServletResponse response, ServletOutputStream outputStream) throws IOException {
    	RestaurantEventsHandler.handleBill(response, request, outputStream);
	}
    
    private void testModifyOrder(HttpServletRequest request, HttpServletResponse response, ServletOutputStream outputStream) throws IOException {
    	RestaurantEventsHandler.handleModifyOrder(response, request, outputStream);
	}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
