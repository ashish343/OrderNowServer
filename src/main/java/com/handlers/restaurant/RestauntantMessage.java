package com.handlers.restaurant;

public enum RestauntantMessage {
	ORDER_RECEIVED_MESSAGE("Order Received."),
	ORDER_ACCEPTED_MESSAGE("Order Accepted."),
	BILL_GENERATED_MESSAGE("Bill would be on you table in just a minute."),
	ORDER_COMPLETED_MESSAGE("Thanks for Ordering, hope you had a wonderful time."),
	MODIFY_ORDER_MESSAGE("Some Dishes you ordered are not availale, please visit your cart.");
	
	private String data;
	
	private RestauntantMessage(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
