package handlers.table;

import java.io.IOException;

import data.menu.Menu;
import data.menu.Restaurant;
import database.DataConnection;

public class TableHandler {

	public static Restaurant server(String restuarantId) {
		Restaurant restaurant = null;
		Menu menu = null;
		try {
			restaurant = DataConnection.getRestaurantData(restuarantId);
			if(restaurant != null) {
				menu = DataConnection.getRestaurantMenu(restaurant.getMenuId());
				restaurant.setMenu(menu);
			}
		} catch (IOException e) {
			
		}
		return restaurant;
	}

	public static String isValidRestuarant(String tableId) {
		String restaurantId = null;
		try {
			restaurantId = DataConnection.getRestaurantId(tableId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurantId;
	}

}
