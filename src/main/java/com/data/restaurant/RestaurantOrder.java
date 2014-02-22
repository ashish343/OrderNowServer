package com.data.restaurant;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.database.DataConnection;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.utility.OrderIdGenerator;

public class RestaurantOrder {

	private static BiMap<Pair, String> tableRestOrderID_cache;
	private static HashMap<Pair, ArrayList<String>> orderCustomerIDList;

	private List<OrderedDish> dishes;
	private String orderId;
	private String customerId;
	private String restaurantId;
	private String tableId;
	private int tableNo;
	private int subOrderId;
	private String orderState;
	private Timestamp createdAt;

	static {
		tableRestOrderID_cache = HashBiMap.create();
		orderCustomerIDList = new HashMap<Pair, ArrayList<String>>();
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderedDish> getDishes() {
		return dishes;
	}

	public void setDishes(List<OrderedDish> dishes) {
		this.dishes = dishes;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public int getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(int subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public static synchronized ArrayList<RestaurantOrder> getOrdersFronDB(
			String restaurantId, String state, ServletOutputStream debugger)
			throws IOException {
		System.out.println("Called");
		ArrayList<RestaurantOrder> restOrder = DataConnection.getCurrentOrders(
				restaurantId, state, debugger);
		for (RestaurantOrder order : restOrder) {
			Pair key = new Pair(order.getTableId(), order.getRestaurantId());
			tableRestOrderID_cache.put(key, order.getOrderId());
		}
		return restOrder;
	}

	public static synchronized void completeOrder(String orderId) {
		BiMap<String, Pair> inverted = tableRestOrderID_cache.inverse();
		Pair table_rest = inverted.get(orderId);
		tableRestOrderID_cache.remove(table_rest);
		DataConnection.completeOrder(orderId);
	}

	public static synchronized String getOrderId(String tableId,
			String restaurantId) {
		String orderId = null;
		Pair key = new Pair(tableId, restaurantId);
		if (tableRestOrderID_cache.containsKey(key))
			orderId = tableRestOrderID_cache.get(key);
		else {
			orderId = DataConnection.getOrderId(tableId, restaurantId);
		}
		return orderId;
	}

	public static synchronized String registerOrderIdAndCustomer(
			String tableId, String restaurantId, String customerId) {
		Pair key = new Pair(tableId, restaurantId);
		String orderId = null;
		if (tableRestOrderID_cache.containsKey(key))
			orderId = tableRestOrderID_cache.get(key);
		else {
			orderId = DataConnection.getOrderId(tableId, restaurantId);
			if (orderId == null)
				orderId = OrderIdGenerator.generateUniqueOrderId();
			tableRestOrderID_cache.put(key, orderId);
		}

		ArrayList<String> list = null;
		if (orderCustomerIDList.containsKey(orderId))
			list = orderCustomerIDList.get(orderId);
		else {
			list = DataConnection.getCustomersList(tableId, restaurantId);
		}
		list.add(customerId);
		orderCustomerIDList.put(new Pair(tableId, restaurantId), list);
		return orderId;
	}

	public static ArrayList<String> getCustomerList(String tableId,
			String restaurantId) {
		ArrayList<String> list;
		if (orderCustomerIDList.containsKey(new Pair(tableId, restaurantId)))
			return orderCustomerIDList.get(new Pair(tableId, restaurantId));
		else {
			list = DataConnection.getCustomersList(tableId, restaurantId);
			orderCustomerIDList.put(new Pair(tableId, restaurantId), list);
			return list;
		}
	}

	public static ArrayList<String> getCustomerList(String orderId) {
		BiMap<String, Pair> reverseMap = tableRestOrderID_cache.inverse();
		Pair pair = reverseMap.get(orderId);
		return getCustomerList(pair.getTableId(), pair.getRestaurantId());
	}

	public static ArrayList<RestaurantOrder> getAllOrder(String tableId,
			String restaurantId) {
		return DataConnection.getOrders(restaurantId, tableId);
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}

class Pair {
	private String tableId;
	private String restaurantId;

	public Pair(String tableId, String restaurantId) {
		this.tableId = tableId;
		this.restaurantId = restaurantId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((restaurantId == null) ? 0 : restaurantId.hashCode());
		result = prime * result + ((tableId == null) ? 0 : tableId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (restaurantId == null) {
			if (other.restaurantId != null)
				return false;
		} else if (!restaurantId.equals(other.restaurantId))
			return false;
		if (tableId == null) {
			if (other.tableId != null)
				return false;
		} else if (!tableId.equals(other.tableId))
			return false;
		return true;
	}

}
