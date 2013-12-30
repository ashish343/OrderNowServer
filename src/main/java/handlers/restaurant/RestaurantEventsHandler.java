package handlers.restaurant;

import javax.servlet.http.HttpServletRequest;

import data.RestaurantData;
import enums.EventState;
import enums.UrlParameter;

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
}
