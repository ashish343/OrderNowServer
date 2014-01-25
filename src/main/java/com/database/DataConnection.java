package com.database;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.data.restaurant.OrderedDish;
import com.data.restaurant.RestaurantOrder;
import com.enums.UrlParameter;
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
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
	private static String ORDER_DATA;

	private static DBCollection table;
	private static DBCollection restaurant;
	private static DBCollection order;

	public static void loader(ServletOutputStream debugger) throws IOException {

		if (mongoDb == null) {
			new DataConnection();
			if (debugger != null)
				debugger.write(("\nNew DB connection Formed.").getBytes());
		} else {
			if (debugger != null)
				debugger.write(("\nUsing old DB connection.").getBytes());
		}
	}

	private DataConnection() throws IOException {
		mongoURI = "mongodb://orderNow:orderNow@troup.mongohq.com:10032/app21434483";
		db = "app21434483";
		TABLE_DATA = "table_rest";
		RESTUARANT_DATA = "rest";
		ORDER_DATA = "order";

		if (mongoURI == null || db == null) {
			System.err
					.println("Connection Information or DB information not present.. Exiting");
			System.exit(1);
		}

		mongoClient = new MongoClient(new MongoClientURI(mongoURI)); // new
																		// MongoClient(new
																		// MongoClientURI(mongoURI));
		mongoDb = mongoClient.getDB(db);

		table = mongoDb.getCollection(TABLE_DATA);
		restaurant = mongoDb.getCollection(RESTUARANT_DATA);
		order = mongoDb.getCollection(ORDER_DATA);
	}

	public static boolean checkOrderExists(String orderId,
			ServletOutputStream debugger) {
		int count = getSubOrderCount(orderId, debugger);
		if (count == 0)
			return false;
		return true;
	}

	public static int getSubOrderCount(String orderId,
			ServletOutputStream debugger) {
		try {
			loader(debugger);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DBCollection collection = mongoDb.getCollection(ORDER_DATA);
		BasicDBObject obj = new BasicDBObject();
		obj.append(UrlParameter.ORDER_ID.toString(), orderId);
		return collection.find(obj).count();
	}

	public static DBCollection getCollection(String collection)
			throws IOException {
		if (mongoDb == null) {
			new DataConnection();
		}
		return mongoDb.getCollection(collection);
	}

	public static String getRestaurantId(String tableId,
			ServletOutputStream debugger) throws IOException {
		String restaurantId = null;
		if (mongoDb == null) {
			new DataConnection();
			if (debugger != null)
				debugger.write(("\nNew DB connection Formed.").getBytes());
		} else {
			if (debugger != null)
				debugger.write(("\nUsing old DB connection.").getBytes());
		}
		// BasicDBObject doc = new BasicDBObject("_id",
		// "T1").append("restaurantId", "R1");
		// table.insert(doc);
		BasicDBObject query = new BasicDBObject("_id", tableId);
		DBCursor cursor = table.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				restaurantId = obj.getString("restaurantId");
			}
		} finally {
			cursor.close();
		}

		return restaurantId;
	}

	public static Restaurant getRestaurantData(String restuarantId,
			ServletOutputStream debugger) throws IOException {
		Restaurant restaurantData = null;
		if (mongoDb == null) {
			new DataConnection();
			if (debugger != null)
				debugger.write(("\nNew DB connection Formed.").getBytes());
		} else {
			if (debugger != null)
				debugger.write(("\nUsing old DB connection.").getBytes());
		}
		BasicDBObject query = new BasicDBObject("_id", restuarantId);
		DBCursor cursor = restaurant.find(query);

		try {
			if (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				restaurantData = getRestaurantData(obj, debugger);
				if (debugger != null)
					debugger.write(("\nValid result returned from DB.")
							.getBytes());
			} else {
				if (debugger != null)
					debugger.write(("\nNo  valid result returned for Rest_Id:::" + restuarantId)
							.getBytes());
			}
		} finally {
			cursor.close();
		}
		return restaurantData;
	}

	private static Restaurant getRestaurantData(BasicDBObject obj,
			ServletOutputStream debugger) throws IOException {
		Restaurant restaurant = null;

		String restaurantId = obj.getString("_id");
		String restaurantName = obj.getString("name");
		String restaurantAddress = obj.getString("address");
		String restaurantContactInfo = obj.getString("contactInfo");
		String restaurantImage = obj.getString("image");
		String restaurantMenu = obj.getString("menu");

		if (restaurantName != null && restaurantMenu != null) {
			restaurant = new Restaurant();
			restaurant.setAddress(restaurantAddress);
			restaurant.setContactInfo(restaurantContactInfo);
			restaurant.setImg(restaurantImage);
			restaurant.setMenu(getMenu(restaurantMenu, debugger));
			restaurant.setName(restaurantName);
			restaurant.setrId(restaurantId);
		}

		return restaurant;
	}

	private static Menu getMenu(String restaurantMenu,
			ServletOutputStream debugger) throws IOException {
		Menu menu = null;
		Gson gson = new Gson();
		try {
			menu = gson.fromJson(restaurantMenu, Menu.class);
			if (menu.getCategories() == null)
				throw new Exception("categories are null");

			if (debugger != null)
				debugger.write(("\nGson successfully converted the response.")
						.getBytes());
			String json = gson.toJson(menu);
			if (debugger != null)
				debugger.write(("\n" + json).getBytes());
		} catch (Exception e) {
			if (debugger != null) {
				debugger.write(("\nGson failed to convert the response, or menu was null" + e
						.toString()).getBytes());
				debugger.write(("\nMenu::" + restaurantMenu).getBytes());
			}
		}
		return menu;
	}

	public static Menu getRestaurantMenu(String menuId) throws IOException {
		Menu menuData = null;
		if (mongoDb == null) {
			new DataConnection();
		}
		BasicDBObject query = new BasicDBObject("_id", menuId);
		DBCursor cursor = order.find(query);
		try {
			if (cursor.hasNext()) {
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

	public static boolean setOrderDetailsToDB(RestaurantOrder restaurantOrder) {
		BasicDBObject doc = new BasicDBObject();
		doc.append(UrlParameter.CUSTOMER_ID.toString(),
				restaurantOrder.getCustomerId());
		doc.append(UrlParameter.ORDER_ID.toString(),
				restaurantOrder.getOrderId());
		doc.append(UrlParameter.SUBORDER_ID.toString(),
				restaurantOrder.getSubOrderId());
		doc.append(UrlParameter.TIMESTAMP.toString(),
				System.currentTimeMillis());
		doc.append(UrlParameter.RESTAURNAT_ID.toString(),
				restaurantOrder.getRestaurantId());
		doc.append(UrlParameter.ORDERSTATE.toString(), "INTERMEDIATE");
		doc.append(UrlParameter.TABLE_ID.toString(),
				restaurantOrder.getTableId());

		BasicDBList dbList = new BasicDBList();
		doc.append(UrlParameter.CUSTOMER_ORDER.toString(), dbList);

		DBCollection collection = mongoDb.getCollection(ORDER_DATA);
		List<OrderedDish> list = restaurantOrder.getDishes();
		for (OrderedDish od : list) {
			dbList.add(new BasicDBObject().append(
					UrlParameter.DISH_IDS.toString(), od.getDishId()).append(
					UrlParameter.DISH_QTY.toString(), od.getQuatity()));
		}
		collection.insert(doc);
		return true;
	}
}