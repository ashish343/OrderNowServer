package com.data.restaurant;

import com.data.menu.FoodType;

public class OrderedDish {
	private String dishId;
	private String name;
	private float price;
	private FoodType type;
	private float quatity;
	private String dishNote;
	private String spiceLevel;
	
	public String getDishNote() {
		return dishNote;
	}
	public void setDishNote(String dishNote) {
		this.dishNote = dishNote;
	}
	
	public String getSpiceLevel() {
		return spiceLevel;
	}
	public void setSpiceLevel(String spiceLevel) {
		this.spiceLevel = spiceLevel;
	}
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
	
	public float getQuatity() {
		return quatity;
	}
	public void setQuatity(float quatity) {
		this.quatity = quatity;
	}
}
