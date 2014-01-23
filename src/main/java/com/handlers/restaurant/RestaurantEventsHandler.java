package com.handlers.restaurant;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.RestaurantData;
import com.enums.EventState;
import com.enums.UrlParameter;
import com.parse.ParseNotificationHelper;
import com.utility.RequestContext;

public class RestaurantEventsHandler {

	public static int createRestaurant(HttpServletRequest request) {
		int status = EventState.FAILURE.ordinal();
		RestaurantData restaurantData = generateRestaurantData(request);
		if(restaurantData != null) {
			status = addRestuarantToDB(restaurantData);
		}
		return status;
	}
	
	public static int modifyRestaurant(HttpServletRequest request) {
		return EventState.SUCCESS.ordinal();
	}
	
	private static RestaurantData generateRestaurantData(HttpServletRequest request) {
		RestaurantData data;
		
		String restuarantName = request.getParameter(UrlParameter.RESTUARANT_NAME.toString());
		String restuarantAddress = request.getParameter(UrlParameter.RESTUARANT_ADDRESS.toString());
		String restuarantConatctInfo = request.getParameter(UrlParameter.RESTUARANT_CONTACT_INFO.toString());
		String restuarantTableNumber = request.getParameter(UrlParameter.RESTUARANT_TABLES.toString());
		
		if(restuarantName != null && restuarantTableNumber != null) {
			
		}
		
		return null;
	}
	
	private static int addRestuarantToDB(RestaurantData restaurantData) {
		return EventState.SUCCESS.ordinal();
	}

	public static void handleOrderReceived(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) throws IOException {
		boolean isDebug = RequestContext.isDebugEnabled();		
		String channel = getOrderId(request);
		if(isDebug) {
			outputStream.write(("\nCalling notify Channel for Channel::" + channel).getBytes());
		}
		ParseNotificationHelper.notifyChannel(channel , "Order Received", outputStream);
		
	}

	public static void handleOrderAccepted(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		String channel = getOrderId(request);
		ParseNotificationHelper.notifyChannel(channel , "Order Accepted", outputStream);
		
	}

	public static void handleOrderCompleted(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		String channel = getOrderId(request);
		String customerId = getCustomerId(request);
		
		ParseNotificationHelper.notifyChannel(channel , "Order Completed", outputStream);
		ParseNotificationHelper.registerChannel(customerId, "", outputStream);
	}

	public static void handleBill(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		String channel = getOrderId(request);
		ParseNotificationHelper.notifyChannel(channel , "Bill Generated", outputStream);		
	}

	public static void handleModifyOrder(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		String channel = getOrderId(request);
		ParseNotificationHelper.notifyChannel(channel , "Order Need Modificatio", outputStream);		
	}
	
	private static String getCustomerId(HttpServletRequest request) {
		return request.getParameter(UrlParameter.CUSTOMER_ID.toString());
	} 
	
	private static String getTableId(HttpServletRequest request) {
		return request.getParameter(UrlParameter.TABLE_ID.toString());
	}
	
	private static String getrestaurantId(HttpServletRequest request) {
		return request.getParameter(UrlParameter.RESTAURNAT_ID.toString());
	}
	private static String getOrderId(HttpServletRequest request) {
		return request.getParameter(UrlParameter.ORDER_ID.toString());
	}
}
