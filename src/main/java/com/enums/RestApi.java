package com.enums;

public enum RestApi {
	QR_GENERATOR("https://chart.googleapis.com/chart?cht=qr&choe=UTF-8&chs=300x300&chl="),
	PUSHER_APPLICATION_ID("63953"),
	PUSHER_APPLICATION_KEY("1f7298f8e64c81a0d7de"),
	PUSHER_APPLICATION_SECRET("babae2b9781a276f1c7a");
	
	private String data;
	
	private RestApi(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
