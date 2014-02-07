package com.data.restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.json.JSONException;

import com.data.menu.Restaurant;
import com.enums.UrlParameter;
import com.google.gson.Gson;

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
		RestaurantDashboardData rdd = new RestaurantDashboardData();

		Restaurant rest = Restaurant.loadFromDB(restaurantId, debugger);
		rdd.setTableInformation(rest.getTableInformation());

		ArrayList<RestaurantOrder> list = RestaurantOrder.getOrdersFronDB(
				restaurantId, UrlParameter.CURRENTORDERS.toString(), debugger);
		Gson gs = new Gson();
		rdd.setOrders(gs.toJson(list));
		return rdd;
	}
}
