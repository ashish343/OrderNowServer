package com.data.restaurant;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.json.JSONException;

import com.database.DataConnection;

/*
 * To be returned from the DB to get the restaurant data.
 */
public class RestaurantDashboardData {
	/*
	 * 	
	 */
	Map<String, Integer> tableInformation;

	/*
	 * Json of List<RestaurantOrder>.
	 */
	String orders;

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public Map<String, Integer> getTableInformation() {
		return tableInformation;
	}

	public void setTableInformation(Map<String, Integer> tableInformation) {
		this.tableInformation = tableInformation;
	}

	public static RestaurantDashboardData loadFromDB(String restaurantId,
			ServletOutputStream debugger) throws IOException, JSONException {
		return DataConnection
				.getRestaurantDashboardData(restaurantId, debugger);
	}
}
