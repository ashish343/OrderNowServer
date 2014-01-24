package com.utility;

import org.apache.commons.lang.RandomStringUtils;

public class RestaurantIdGenerator {
	public static final int ID_LENGTH = 10;

	public static String generateUniqueOrderId() {
		return RandomStringUtils.randomAlphabetic(ID_LENGTH);
	}
}
