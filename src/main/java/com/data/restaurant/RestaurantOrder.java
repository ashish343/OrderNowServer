package com.data.restaurant;

import java.util.Map;
import com.data.menu.Dish;

public class RestaurantOrder {
	private Map<String, Float> dishes;
	private String orderId;
	private String customerId;

	public Map<String, Float> getDishes() {
		return dishes;
	}
	public void setDishes(Map<String, Float> dishes) {
		this.dishes = dishes;
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

}
