package com.test;

import java.util.HashMap;
import java.util.Map;

import com.data.menu.CustomerOrder;
import com.data.menu.OrderDish;
import com.google.gson.Gson;

public class CustomerOrderJson {
	
	public static String getCustomerOrder() {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomerId("NXwUdXIorg");
		customerOrder.setOrderId(null);
		customerOrder.setRestaurantId("R1");

		Map<String, OrderDish> dishes = new HashMap<String, OrderDish>();
		OrderDish currentOrderedDish = new OrderDish((float) 1, " Not too spicy", "medium");
		dishes.put("D1", currentOrderedDish);
		dishes.put("D2", currentOrderedDish);
		dishes.put("D3", currentOrderedDish);
		
		customerOrder.setDishes(dishes);
		Gson gson = new Gson();
		String json = gson.toJson(customerOrder);
		
		return json;
	}
	
	public static void main(String args[]) {
		String json = getCustomerOrder();
		System.out.println(json);
	}
}
