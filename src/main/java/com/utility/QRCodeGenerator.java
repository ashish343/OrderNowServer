package com.utility;

import com.enums.RestApi;


public class QRCodeGenerator {
	
	public static String createQRCode(String id) {
		return RestApi.QR_GENERATOR.toString() + id;
	}
	
}
