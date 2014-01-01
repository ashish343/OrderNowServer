package com.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DataConnection {

	private final MongoClient mongoClient;
	private String mongoURI;
	private String db;
	private static DB mongoDb;
	private static String TABLE_DATA;
	private static String RESTUARANT_DATA;
	private static String MENU_DATA;

	private static DBCollection table;
	private static DBCollection restaurant;
	private static DBCollection menu;

	private DataConnection() throws IOException {
		mongoURI = "mongodb://localhost:27017/OrderNow";
		db = "OrderNow";
		TABLE_DATA = "table_collection";
		RESTUARANT_DATA = "restaurant_table";
		MENU_DATA = "menu_table";
			
		if (mongoURI == null || db == null) {
			System.err.println("Connection Information or DB information not present.. Exiting");
			System.exit(1);
		}

		mongoClient = new MongoClient(new MongoClientURI(mongoURI)); //new MongoClient(new MongoClientURI(mongoURI));
		mongoDb = mongoClient.getDB(db);
		
		table = mongoDb.getCollection(TABLE_DATA);
		restaurant = mongoDb.getCollection(RESTUARANT_DATA);
		menu = mongoDb.getCollection(MENU_DATA);
	}

	public static DBCollection getCollection(String collection)
			throws IOException {
		if (mongoDb == null) {
			new DataConnection();
		}
		return mongoDb.getCollection(collection);
	}

	public static String getRestaurantId(String tableId) throws IOException {
		String restaurantId = null;
		if (mongoDb == null) {
			new DataConnection();
		}
		//BasicDBObject doc = new BasicDBObject("_id", "T1").append("restaurantId", "R1");
		//table.insert(doc);
		BasicDBObject query = new BasicDBObject("_id", tableId);
		DBCursor cursor = table.find(query);
		try {
			   if(cursor.hasNext()) {
			       BasicDBObject obj = (BasicDBObject) cursor.next();
			       restaurantId = obj.getString("restaurantId");
			   }
			} finally {
			   cursor.close();
			}
		return restaurantId;
	}
	
	public static void main(String args[]) throws IOException {
		String rId = DataConnection.getRestaurantId("T1");
		System.out.println("Completed with RId::" + rId);
		
		Restaurant data = getRestaurantData(rId);
		if(data != null) {
			System.out.println(data.getMenuId());
		}
	}

	public static Restaurant getRestaurantData(String restuarantId) throws IOException {
		Restaurant restaurantData = null;
		if (mongoDb == null) {
			new DataConnection();
		}
		BasicDBObject query = new BasicDBObject("_id", restuarantId);
		DBCursor cursor = restaurant.find(query);
		try {
			if(cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				restaurantData = getRestaurantData(obj);
			}
		} finally {
		   cursor.close();
		}
		return restaurantData;
	}

	private static Restaurant getRestaurantData(BasicDBObject obj) {
		Restaurant restaurant = null;
		
		String restaurantId = obj.getString("_id");
		String restaurantName = obj.getString("name");
		String restaurantAddress = obj.getString("address");
		String restaurantContactInfo = obj.getString("contactInfo");
		String restaurantImage = obj.getString("image");
		String restaurantMenuId = obj.getString("menuId");
		
		if(restaurantName != null && restaurantMenuId != null) {
			restaurant = new Restaurant();
			restaurant.setAddress(restaurantAddress);
			restaurant.setContactInfo(restaurantContactInfo);
			restaurant.setImg(restaurantImage);
			restaurant.setMenuId(restaurantMenuId);
			restaurant.setName(restaurantName);
			restaurant.setrId(restaurantId);
		}
		
		return restaurant;
	}

	public static Menu getRestaurantMenu(String menuId) throws IOException {
		Menu menuData = null;
		if (mongoDb == null) {
			new DataConnection();
		}
		BasicDBObject query = new BasicDBObject("_id", menuId);
		DBCursor cursor = menu.find(query);
		try {
			if(cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				menuData = getMenuData(obj);
			}
		} finally {
		   cursor.close();
		}
		return menuData;
	}

	private static Menu getMenuData(BasicDBObject obj) {
		// TODO Auto-generated method stub
		return null;
	}
}