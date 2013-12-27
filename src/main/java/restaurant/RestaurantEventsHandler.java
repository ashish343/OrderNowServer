package restaurant;

import javax.servlet.http.HttpServletRequest;

import utility.UrlParameter;
import data.RestaurantData;

public class RestaurantEventsHandler {
	private static final int SUCCESS = 1;
	private static final int FAILURE = 0;

	public static int createRestaurant(HttpServletRequest request) {
		int status = FAILURE;
		RestaurantData restaurantData = generateRestaurantData(request);
		if(restaurantData != null) {
			status = addRestuarantToDB(restaurantData);
		}
		return status;
	}
	
	public static int modifyRestaurant(HttpServletRequest request) {
		return SUCCESS;
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
		return 1;
	}
}
