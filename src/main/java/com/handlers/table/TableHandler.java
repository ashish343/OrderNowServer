package com.handlers.table;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.database.DataConnection;

public class TableHandler {

	public static Restaurant getRestaurantInformation(String restuarantId, ServletOutputStream debugger) {
		Restaurant restaurant = null;
		Menu menu = null;
		try {
			restaurant = DataConnection.getRestaurantData(restuarantId, debugger);
		} catch (IOException e) {
			
		}
		return restaurant;
	}

	public static String getValidRestuarant(String tableId, ServletOutputStream debugger) {
		String restaurantId = null;
		try {
			restaurantId = DataConnection.getRestaurantId(tableId, debugger);
			if(debugger != null) {
				debugger.write(("Restuarant ID::" + restaurantId).getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurantId;
	}

}
