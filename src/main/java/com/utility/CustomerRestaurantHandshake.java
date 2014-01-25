package com.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.data.menu.Category;
import com.data.menu.CustomerOrder;
import com.data.menu.Dish;
import com.data.menu.Menu;
import com.data.restaurant.OrderedDish;
import com.data.restaurant.RestaurantOrder;

public class CustomerRestaurantHandshake {

	public RestaurantOrder getRestaurantOrder(Menu menu,
			CustomerOrder customerOrder, String orderId, String customerId,
			String restuarantId) {
		RestaurantOrder restaurantOrder = new RestaurantOrder();
		List<Dish> allDishes = new ArrayList<Dish>();

		restaurantOrder.setCustomerId(customerId);
		restaurantOrder.setOrderId(orderId);
		restaurantOrder.setRestaurantId(restuarantId);

		List<Category> categories = menu.getCategories();
		getAllDishes(categories, allDishes);

		List<OrderedDish> restaurantDishes = new ArrayList<OrderedDish>();

		Map<String, Float> customerDishes = customerOrder.getDishes();

		for (String key : customerDishes.keySet()) {
			for (Dish dish : allDishes) {
				String dishId = dish.getDishId();
				if (key.equals(dishId)) {
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

	private void getAllDishes(List<Category> categories, List<Dish> allDishes) {
		if (categories != null && !categories.isEmpty()) {
			for (Category category : categories) {
				List<Category> subCategories = category.getCategories();
				List<Dish> dishes = category.getDishes();

				if (dishes != null && !dishes.isEmpty()) {
					allDishes.addAll(dishes);
				}
				getAllDishes(subCategories, allDishes);
			}
		}
	}

}