package com.handlers.restaurant;

public enum RestaurantEvents {
	ORDER_RECEIVED("orderReceived"),
	ORDER_ACCEPTED("orderAccepted"),
	BILL_GENERATED("generateBill"),
	ORDER_COMPLETED("orderCompleted"),
	MODIFY_ORDER("modifyOrder");
	
	private String data;
	
	private RestaurantEvents(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
