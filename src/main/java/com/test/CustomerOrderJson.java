package com.test;

import java.util.HashMap;
import java.util.Map;

import com.data.menu.CustomerOrder;
import com.google.gson.Gson;

public class CustomerOrderJson {
	
	public static String getCustomerOrder() {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setCustomerId("NXwUdXIorg");
		customerOrder.setOrderId(null);
		customerOrder.setRestaurantId("R1");

		Map<String, Float> dishes = new HashMap<String, Float>();
		dishes.put("D1", (float) 1);
		dishes.put("D2", (float) 1);
		dishes.put("D3", (float) 0.5);
		
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
