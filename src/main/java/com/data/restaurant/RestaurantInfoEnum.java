package com.data.restaurant;

public enum RestaurantInfoEnum {
	INFO("info"),
	HISTORY("history"),
	ANALYSIS("analysis"),
	CONTACT("contact");
	
	private String data;
	
	private RestaurantInfoEnum(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
