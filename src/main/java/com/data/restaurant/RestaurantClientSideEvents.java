package com.data.restaurant;

public enum RestaurantClientSideEvents {
	NOTIFY_NEW_ORDER("notify_order"),
	UPDATE_EXISTING_ORDER("update_order"),
	GET_BILL("get_bill");
	
	private String data;
	
	private RestaurantClientSideEvents(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
