package com.parse;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import javax.servlet.ServletOutputStream;

import com.utility.ConnectionUtilility;


public class ParseNotificationHelper {
	
	
	private static void initializeConnection(HttpURLConnection con) {
		con.setRequestProperty("X-Parse-Application-Id", ParseApplicationData.APLICATION_ID.toString());
		con.setRequestProperty("X-Parse-REST-API-Key", ParseApplicationData.REST_API_KEY.toString());
		con.setRequestProperty("Content-Type", "application/json");
	}
	
	
	public static void registerChannel(String customerId, String channel, ServletOutputStream outputStream) {
		
		HttpURLConnection con = null;
		String reqbody = "{\"channels\": [\""+  channel + "\"]}";
		try {
			con = ConnectionUtilility.getHttpConnection(ParseRestApi.PARSE_HOST.toString() + ParseRestApi.INSTALLATION.toString() + "/" + customerId, "PUT");
			initializeConnection(con);
				
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(reqbody);
			out.flush();
			out.close();
			con.connect();
			//TODO: No need to print.
			ConnectionUtilility.getResponseData(con);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void notifyChannel(String channel, String data, ServletOutputStream outputSteam) {
		HttpURLConnection con = null;
		/*
		 * String msg = "{\"channels\":[\"" + channel + "\"],\"data\": {\"action\":\"com.example.UPDATE_STATUS\","
		 *		+ "\"name\": \"Vaughn\", \"newsItem\": \"Man bites dog\"}}";
		 */
		try {
			con = ConnectionUtilility.getHttpConnection(ParseRestApi.PARSE_HOST.toString() + ParseRestApi.PUSH.toString(), "POST");
			initializeConnection(con);
			
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(data);
			out.flush();
			out.close();
			con.connect();
			//TODO: No need to print.
			ConnectionUtilility.getResponseData(con);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
