package com.data.restaurant;

import java.util.Map;

/*
 * To be returned from the DB to get the restaurant data.
 */
public class RestaurantDashboardData {
	/*
	 * Table Information contains the total number of tables and the table numbers.
	 */
	Map<String, String> tableInformation;
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

	public Map<String, String> getTableInformation() {
		return tableInformation;
	}

	public void setTableInformation(Map<String, String> tableInformation) {
		this.tableInformation = tableInformation;
	}
}
