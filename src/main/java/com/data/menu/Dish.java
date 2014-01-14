package com.data.menu;

public class Dish {
	public Dish(String dishId, String name, String description, String img, float price, FoodType type) {
		this.dishId = dishId;
		this.name = name;
		this.description = description;
		this.img = img;
		this.price = price;
		this.type = type;
	};
	
	public Dish() {};
	
	private String dishId;
	private String name;
	private String description;
	private String img;
	private float price;
	private FoodType type; //Veg or non-veg
	
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public FoodType getType() {
		return type;
	}
	public void setType(FoodType type) {
		this.type = type;
	}
	
}
