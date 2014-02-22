package com.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.data.menu.Category;
import com.data.menu.CustomerOrder;
import com.data.menu.Dish;
import com.data.menu.Menu;
import com.data.menu.OrderDish;
import com.data.restaurant.OrderedDish;
import com.data.restaurant.RestaurantOrder;
import com.enums.UrlParameter;

public class CustomerRestaurantHandshake {

	public static CustomerOrder getCustomerOrder(RestaurantOrder order) {

		return null;
	}

	public RestaurantOrder getRestaurantOrder(Menu menu,
			CustomerOrder customerOrder) {
		RestaurantOrder restaurantOrder = new RestaurantOrder();
		List<Dish> allDishes = new ArrayList<Dish>();

		restaurantOrder.setCustomerId(customerOrder.getCustomerId());
		restaurantOrder.setOrderId(customerOrder.getOrderId());
		restaurantOrder.setRestaurantId(customerOrder.getRestaurantId());
		restaurantOrder.setTableId(customerOrder.getTableId());
		restaurantOrder.setSubOrderId(customerOrder.getSubOrderId());
		restaurantOrder.setOrderState(UrlParameter.INTERMEDIATE.toString());
		restaurantOrder.setTableId(customerOrder.getTableId());

		List<Category> categories = menu.getCategories();
		getAllDishes(categories, allDishes);

		List<OrderedDish> restaurantDishes = new ArrayList<OrderedDish>();

		Map<String, OrderDish> customerDishes = customerOrder.getDishes();

		for (String key : customerDishes.keySet()) {
			for (Dish dish : allDishes) {
				String dishId = dish.getDishId();
				if (key.equals(dishId)) {
					OrderedDish orderedDish = new OrderedDish();
					orderedDish.setDishId(dish.getDishId());
					orderedDish.setName(dish.getName());
					orderedDish.setPrice(dish.getPrice());
					OrderDish currentOrderedDish = customerDishes.get(key);
					orderedDish.setQuatity(currentOrderedDish.getDishQty());
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