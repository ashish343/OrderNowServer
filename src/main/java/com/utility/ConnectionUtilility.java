package com.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionUtilility {
	public static HttpURLConnection getHttpConnection(String url, String type) {
		URL uri = null;
		HttpURLConnection con = null;
		try {
			uri = new URL(url);
			con = (HttpURLConnection) uri.openConnection();
			con.setRequestMethod(type); // type: POST, PUT, DELETE, GET
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setConnectTimeout(60000); // 60 secs
			con.setReadTimeout(60000); // 60 secs
		} catch (Exception e) {
		}
		return con;
	}
	public static void getResponseData(HttpURLConnection con) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String temp = null;
		
		StringBuilder sb = new StringBuilder();
		while ((temp = in.readLine()) != null) {
			sb.append(temp).append(" ");
		}
		String result = sb.toString();
		System.out.println("Result is:" + result);
		in.close();
	}
}
