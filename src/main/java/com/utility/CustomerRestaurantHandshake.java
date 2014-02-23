package com.utility;

import java.util.ArrayList;
import java.util.HashMap;
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
		CustomerOrder tmp = new CustomerOrder();
		tmp.setCustomerId(order.getCustomerId());
		tmp.setOrderId(order.getOrderId());
		tmp.setRestaurantId(order.getRestaurantId());
		tmp.setSubOrderId(order.getSubOrderId());
		tmp.setTableId(order.getTableId());
		List<OrderedDish> list = order.getDishes();
		Map<String, OrderDish> map = new HashMap<String, OrderDish>();
		for (OrderedDish od : list) {
			map.put(od.getDishId(), (OrderDish) od);
		}
		tmp.setDishes(map);
		return tmp;
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
		restaurantOrder.setCreatedAt(System.currentTimeMillis());

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
					orderedDish.setSpiceLevel(currentOrderedDish
							.getSpiceLevel());
					orderedDish.setDishNote(currentOrderedDish.getDishNote());

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