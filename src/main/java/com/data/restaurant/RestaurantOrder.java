package com.data.restaurant;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import com.database.DataConnection;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.Gson;
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
	private long createdAt;

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
		ArrayList<RestaurantOrder> restOrder = DataConnection
				.getCurrentOrdersInState(restaurantId, state, debugger);
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
		orderCustomerIDList.remove(table_rest);
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
		/**
		 * adding current customer to list of participating customers
		 */
		ArrayList<String> list = null;
		if (orderCustomerIDList.containsKey(orderId))
			list = orderCustomerIDList.get(orderId);
		else {
			list = DataConnection.getCustomersList(tableId, restaurantId);
		}
		if (!list.contains(customerId))
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

	public static ArrayList<RestaurantOrder> getAllCurrentOrder(String tableId,
			String restaurantId) {
		return DataConnection.getCurrentOrders(restaurantId, tableId);
	}

	public static Map<String, ArrayList<RestaurantOrder>> getAllCompletedOrders(String restaurantId) {
		ArrayList<RestaurantOrder> orders = DataConnection
				.getAllCompletedOrders(restaurantId);
		HashMap<String, ArrayList<RestaurantOrder>> allOrders = new HashMap<String, ArrayList<RestaurantOrder>>();
		HashMap<String, RestaurantOrder> orderIdMap = new HashMap<String, RestaurantOrder>();
		for (RestaurantOrder tmp : orders) {
			if (!orderIdMap.containsKey(tmp.getOrderId())) {
				orderIdMap.put(tmp.getOrderId(), tmp);
			} else {
				RestaurantOrder curOrder = orderIdMap.get(tmp.getOrderId());
				if (tmp.getCreatedAt() < curOrder.getCreatedAt()) {
					curOrder.setCreatedAt(tmp.getCreatedAt());
				}
				List<OrderedDish> lod = tmp.getDishes();
				for (OrderedDish td : lod) {
					List<OrderedDish> curDishes = curOrder.getDishes();
					for (OrderedDish cd : curDishes) {
						if (cd.getDishId().equals(td.getDishId())) {
							cd.setDishQty(cd.getDishQty() + td.getDishQty());
						}
					}
				}
			}
		}
		DateFormat df = new SimpleDateFormat("dd:MM:yy");
		Set<String> orderIds = orderIdMap.keySet();
		for (String orderId : orderIds) {
			RestaurantOrder y = orderIdMap.get(orderId);
			Date x = new Date(y.getCreatedAt());
			String key = df.format(x);
			ArrayList<RestaurantOrder> tmp;
			if (allOrders.containsKey(key))
				allOrders.get(key).add(y);
			else {
				tmp = new ArrayList<RestaurantOrder>();
				tmp.add(y);
				allOrders.put(key, tmp);
			}
		}
		return allOrders;
	}

	public static void updateOrderState(String orderId, int subOrderId,
			String state) {
		DataConnection.updateOrderState(orderId, subOrderId, state);
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public static void main(String[] args) {
		System.out.println(RestaurantOrder.getAllCompletedOrders("R1"));
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
