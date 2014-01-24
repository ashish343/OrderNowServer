package com.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.data.menu.Category;
import com.data.menu.Dish;
import com.data.menu.FoodType;
import com.google.gson.Gson;

public class GsonTest {
	public static void main(String args[]) throws JSONException {
		Category mainCategory1 = new Category();
		mainCategory1.setName("Continental");

		/*
		 * Subcategory Soups.
		 */
		Category subCategory1 = new Category();
		subCategory1.setName("Soups");

		/*
		 * Dishes under Soups.
		 */

		/*
		 * D1
		 */
		Dish dish1 = new Dish();

		dish1.setDescription("Vegetable broth served in selection of veggies topped with fresh cream.");
		dish1.setDishId("D1");
		dish1.setImg(null);
		dish1.setName("Cream of Veg/Mushroom/Asparagus/Tomato");
		dish1.setPrice(95);
		dish1.setType(FoodType.Veg);

		/*
		 * D2
		 */
		Dish dish2 = new Dish();

		dish2.setDescription("Vegetable stock added with tings of mexican souce");
		dish2.setDishId("D2");
		dish2.setImg(null);
		dish2.setName("Roasted Bell Pepper Soup.");
		dish2.setPrice(115);
		dish2.setType(FoodType.Veg);

		List<Dish> dishes1 = new ArrayList<Dish>();
		dishes1.add(dish1);
		dishes1.add(dish2);
		subCategory1.setDishes(dishes1);

		/*
		 * Subcategory Starters.
		 */
		Category subCategory2 = new Category();
		subCategory2.setName("Starters");

		/*
		 * Dishes under Starters.
		 */

		/*
		 * D1
		 */
		Dish dish3 = new Dish();

		dish3.setDescription("An all time favourite of mexican chips topped with mezza cheese and served with salsa.");
		dish3.setDishId("D3");
		dish3.setImg(null);
		dish3.setName("Nachos with cheese souce");
		dish3.setPrice(165);
		dish3.setType(FoodType.Veg);

		/*
		 * D2
		 */
		Dish dish4 = new Dish();

		dish4.setDescription("Vegetable stock added with tings of mexican souce");
		dish4.setDishId("D2");
		dish4.setImg(null);
		dish4.setName("Roasted Bell Pepper Soup.");
		dish4.setPrice(115);
		dish4.setType(FoodType.Veg);

		List<Dish> dishes2 = new ArrayList<Dish>();
		dishes2.add(dish3);
		dishes2.add(dish4);
		subCategory2.setDishes(dishes2);
		/*
		 * Categories List
		 */

		List<Category> categories = new ArrayList<Category>();
		categories.add(subCategory1);
		categories.add(subCategory2);

		Category cat = new Category();

		cat.setCategories(categories);

		Gson gson = new Gson();
		String json = gson.toJson(cat);
		System.out.println(json);
		String jsonString = json.replace("\"", "\\\"");
		
		System.out.println(jsonString);
		JSONObject o1 = new JSONObject(json);
		o1.put("tt", "tt");

		Category x = gson.fromJson(o1.toString(), Category.class);

		//System.out.println(gson.toJson(x));

		// Restaurant obj = gson.fromJson(json, Restaurant.class);
		// System.out.println(obj.getMenu().getCategories().get(0).getDishes()
		// .get(0).getName());
	}
}
