package com.parse;

public enum ParseRestApi {
	PARSE_HOST("https://api.parse.com/"),
	INSTALLATION("1/installations"),
	PUSH("1/push");
	
	private String data;
	
	private ParseRestApi(String string) {
		data = string;
	}
	
	public String toString(){
		return data;
	}
}
