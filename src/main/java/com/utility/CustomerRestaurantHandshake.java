package com.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.menu.Category;
import com.data.menu.CustomerOrder;
import com.data.menu.Menu;
import com.data.menu.Dish;
import com.data.restaurant.RestaurantOrder;

public class CustomerRestaurantHandshake {

	private static List<Dish> allDishes = new ArrayList<Dish>();
	
	public RestaurantOrder getRestaurantOrder(Menu menu, CustomerOrder customerOrder, String orderId, String customerId) {
		RestaurantOrder restaurantOrder = new RestaurantOrder();
		restaurantOrder.setCustomerId(customerId);
		restaurantOrder.setOrderId(orderId);
		
		List<Category> categories = menu.getCategories();
		getAllDishes(categories);
		
		Map<Dish, Float> restaurantDishes = new HashMap<Dish, Float>();
		
		Map<String, Float> customerDishes = customerOrder.getDishes();
		
		for (String key: customerDishes.keySet()) {
			for(Dish dish: allDishes) {
				String dishId = dish.getDishId();
				if(key.equals(dishId)) {
					restaurantDishes.put(dish, customerDishes.get(key));
				}
			}
		}
		
		restaurantOrder.setDishes(restaurantDishes);
		
		return restaurantOrder;
	}

	private void getAllDishes(List<Category> categories) {
		for(Category category:categories) {
			List<Category> subCategories = category.getCategories();
			List<Dish> dishes = category.getDishes();
			
			if(dishes != null && !dishes.isEmpty()) {
				allDishes.addAll(dishes);
			}
			getAllDishes(subCategories);
		}
	}

}