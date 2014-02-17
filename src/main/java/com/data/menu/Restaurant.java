package com.data.menu;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.data.menu.filter.MenuPropertyKey;
import com.data.menu.filter.MenuPropertyValue;
import com.database.DataConnection;

public class Restaurant {
	private String _id;
	private String img;
	private String name;
	private String address;
	private String contactInfo;
	private Menu menu;
	private Map<String, Integer> tableInformation;
	private Map<MenuPropertyKey, List<MenuPropertyValue>> availableFilters;


	public static HashMap<String, Restaurant> _cache = new HashMap<String, Restaurant>();

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getrId() {
		return _id;
	}

	public void setrId(String rId) {
		this._id = rId;
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
		if (_cache.containsKey(restaurantId))
			return _cache.get(restaurantId);
		return DataConnection.getRestaurantData(restaurantId, debugger);
	}

	public void updateDB(ServletOutputStream debugger) throws IOException {
		_cache.put(getrId(), this);
		DataConnection.setRestaurantDataToDB(this, debugger);
	}

	public void storeToDB(ServletOutputStream debugger) throws IOException {
		_cache.put(getrId(), this);
		DataConnection.setRestaurantDataToDB(this, debugger);

	}

	public Map<MenuPropertyKey, List<MenuPropertyValue>> getAvailableFilters() {
		return availableFilters;
	}

	public void setAvailableFilters(
			Map<MenuPropertyKey, List<MenuPropertyValue>> availableFilters) {
		this.availableFilters = availableFilters;
	}

	@Override
	public String toString() {
		return "Res = " + name + " " + menu.toString();
	}
}
