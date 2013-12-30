package handlers.table;

import java.io.IOException;

import data.menu.Restaurant;
import database.DataConnection;

public class TableHandler {

	public static Restaurant server(String restuarantId) {
		return null;
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
