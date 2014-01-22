package com.enums;

public enum UrlParameter {
	RESTUARANT_NAME("restName"),
	RESTUARANT_ADDRESS("restAdd"),
	RESTUARANT_CONTACT_INFO("restConatctInfo"),
	RESTUARANT_TABLES("restTables"),
	DEBUG("debug"),
	TABLE_ID("tableId"),
	CUSTOMER_ORDER("order"),
	USER_ACTION("actio");
	
	private String data;
	
	private UrlParameter(String string) {
		data = string;
	}
	
	public String toString() {
		return data;
	}
}
