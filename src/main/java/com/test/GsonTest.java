package com.test;

import java.util.ArrayList;
import java.util.List;

import com.data.menu.Category;
import com.data.menu.Dish;
import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.google.gson.Gson;

public class GsonTest {
	public static void main(String args[]) {
	     List<Dish> dishes = new ArrayList<Dish>();
	        Dish dish = new Dish();
	        dish.setDescription("Blah Blah");
	        dish.setName("Kabab");
	        dish.setPrice(100);
	        
	        dishes.add(dish);
	        
	        List<Category> categories = new ArrayList<Category>();
	        Category category = new Category();
	        
	        category.setName("MAIN COURSE");
			category.setDishes(dishes);
	        
	        categories.add(category);
	        
	        Menu menu = new Menu();
	        menu.setCategories(categories);
	        
	        Restaurant restaurant = new Restaurant();
	        restaurant.setAddress("ABC Street");
	        restaurant.setContactInfo("00432");
	        restaurant.setImg("ejnbelkj");
	        restaurant.setMenu(menu);
	        restaurant.setMenuId("dedewd");
	        restaurant.setName("Ashish");
	        restaurant.setrId("ABC");
	        
	        
	        
	        Gson gson = new Gson();
	        String json = gson.toJson(restaurant);
	        System.out.println(json);
	        
	        Restaurant obj = gson.fromJson(json, Restaurant.class);
	        System.out.println(obj.getMenu().getCategories().get(0).getDishes().get(0).getName());
	}
}
