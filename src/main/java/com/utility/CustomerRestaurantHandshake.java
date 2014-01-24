package com.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.GapContent;

import com.data.menu.Category;
import com.data.menu.CustomerOrder;
import com.data.menu.Menu;
import com.data.menu.Dish;
import com.data.restaurant.OrderedDish;
import com.data.restaurant.RestaurantOrder;
import com.google.gson.Gson;

public class CustomerRestaurantHandshake {

	private static List<Dish> allDishes = new ArrayList<Dish>();
	
	public RestaurantOrder getRestaurantOrder(Menu menu, CustomerOrder customerOrder, String orderId, String customerId) {
		RestaurantOrder restaurantOrder = new RestaurantOrder();
		restaurantOrder.setCustomerId(customerId);
		restaurantOrder.setOrderId(orderId);
		
		List<Category> categories = menu.getCategories();
		getAllDishes(categories);
		
		List<OrderedDish> restaurantDishes = new ArrayList<OrderedDish>();
		
		Map<String, Float> customerDishes = customerOrder.getDishes();
		Gson gson = new Gson();
		
		for (String key: customerDishes.keySet()) {
			for(Dish dish: allDishes) {
				String dishId = dish.getDishId();
				if(key.equals(dishId)) {
					OrderedDish orderedDish = new OrderedDish();
					orderedDish.setDishId(dish.getDishId());
					orderedDish.setName(dish.getName());
					orderedDish.setPrice(dish.getPrice());
					orderedDish.setQuatity(customerDishes.get(key));
					orderedDish.setType(dish.getType());
					
					restaurantDishes.add(orderedDish);
				}
			}
		}
		
		restaurantOrder.setDishes(restaurantDishes);
		
		return restaurantOrder;
	}

	private void getAllDishes(List<Category> categories) {
		if(categories != null && !categories.isEmpty()){
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

}