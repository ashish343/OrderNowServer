package com.data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.data.restaurant.RestaurantOrder;

public class RestaurantData {
	private String restaurantId;
	private String name;
	private String address;
	private List<String> contactInfo;
	private int tables;
	private List<String> tableIds;
	private String menuId;
	private Timestamp lastUpdatedAt;

	public Timestamp getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(List<String> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getTables() {
		return tables;
	}

	public void setTables(int tables) {
		this.tables = tables;
	}

	public List<String> getTableIds() {
		return tableIds;
	}

	public void setTableIds(List<String> tableIds) {
		this.tableIds = tableIds;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public ArrayList<RestaurantOrder> getAllOrders(String restaurantId) {

		return null;
	}

	public static void main(String[] args) {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		System.out.println(t);
	}
}
