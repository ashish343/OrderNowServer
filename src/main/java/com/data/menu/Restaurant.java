package com.data.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.database.DataConnection;

public class Restaurant {
	private String rId;
	private String name;
	private String address;
	private String contactInfo;
	private String img;
	private Menu menu;

	public static HashMap<String, Restaurant> _cache = new HashMap<String, Restaurant>();
	private Map<String, Integer> tableInformation;

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Map<String, Integer> getTableInformation() {
		return tableInformation;
	}

	public void setTableInformation(Map<String, Integer> tableInformation) {
		this.tableInformation = tableInformation;
	}

	public static Restaurant loadFromDB(String restaurantId,
			ServletOutputStream debugger) throws IOException {
		return DataConnection.getRestaurantData(restaurantId, debugger);
	}
}
