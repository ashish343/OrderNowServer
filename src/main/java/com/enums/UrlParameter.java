package com.enums;

public enum UrlParameter {
	RESTUARANT_NAME("restName"), RESTUARANT_ADDRESS("restAdd"), RESTUARANT_CONTACT_INFO(
			"restConatctInfo"), RESTUARANT_TABLES("restTables"), DEBUG("debug"), TABLE_ID(
			"tableId"), CUSTOMER_ID("customerId"), ORDER_ID("orderId"), RESTAURNAT_ID(
			"restaurantId"), CUSTOMER_ORDER("order"), USER_ACTION("action"), DISH_IDS(
			"dishIds"), TIMESTAMP("time"), ORDERSTATE("orderState"), DISH_QTY(
			"dishQty"), SUBORDER_ID("subOrderId"), INTERMEDIATE("interMediate"), TABLEINFORMATION(
			"tableInformation"), CURRENTORDERS("currentOrders"), USER_NAME(
			"user_name"), PASSWORD("password");

	private String data;

	private UrlParameter(String string) {
		data = string;
	}

	public String toString() {
		return data;
	}
}
