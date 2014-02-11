package com.data.restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.database.DataConnection;

public class RestaurantOrder {

	private List<OrderedDish> dishes;
	private String orderId;
	private String customerId;
	private String restaurantId;
	private String tableId;
	private int tableNo;
	private int subOrderId;
	private String orderState;

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

	public static ArrayList<RestaurantOrder> getOrdersFronDB(
			String restaurantId, String state, ServletOutputStream debugger)
			throws IOException {
		return DataConnection.getCurrentOrders(restaurantId, state, debugger);
	}

	public static void completeOrder(String orderId) {
		DataConnection.completeOrder(orderId);
	}

}
