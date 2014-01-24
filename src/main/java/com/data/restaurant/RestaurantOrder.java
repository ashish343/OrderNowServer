package com.data.restaurant;

import java.util.List;

public class RestaurantOrder {
	private List<OrderedDish> dishes;
	private String orderId;
	private String customerId;

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
}
