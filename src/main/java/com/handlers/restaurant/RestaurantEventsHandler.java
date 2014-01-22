package com.handlers.restaurant;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.RestaurantData;
import com.enums.EventState;
import com.enums.UrlParameter;

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
			HttpServletRequest request, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		
	}

	public static void handleOrderAccepted(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		
	}

	public static void handleOrderCompleted(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		
	}

	public static void handleBill(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		
	}

	public static void handleModifyOrder(HttpServletResponse response,
			HttpServletRequest request, ServletOutputStream outputStream) {
		// TODO Auto-generated method stub
		
	}
}
