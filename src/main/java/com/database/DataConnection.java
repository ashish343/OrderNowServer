package com.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.data.restaurant.RestaurantDashboardData;
import com.data.restaurant.RestaurantOrder;
import com.enums.UrlParameter;
import com.google.gson.Gson;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;
import com.utility.RequestContext;

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
	private static Gson gs;

	public static void loader(ServletOutputStream debugger) throws IOException {
		boolean isDebug = RequestContext.isDebugEnabled();
		if (mongoDb == null) {
			new DataConnection();
			if (isDebug)
				debugger.write(("\nNew DB connection Formed.").getBytes());
		} else {
			if (isDebug)
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
		gs = new Gson();
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
		boolean isDebug = RequestContext.isDebugEnabled();
		if (mongoDb == null) {
			new DataConnection();
			if (isDebug)
				debugger.write(("\nNew DB connection Formed.").getBytes());
		} else {
			if (isDebug)
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
		boolean isDebug = RequestContext.isDebugEnabled();

		if (mongoDb == null) {
			new DataConnection();
			if (isDebug)
				debugger.write(("\nNew DB connection Formed.").getBytes());
		} else {
			if (isDebug)
				debugger.write(("\nUsing old DB connection.").getBytes());
		}
		BasicDBObject query = new BasicDBObject("_id", restuarantId);
		DBCursor cursor = restaurant.find(query);

		try {
			if (cursor.hasNext()) {
				BasicDBObject obj = (BasicDBObject) cursor.next();
				restaurantData = getRestaurantData(obj, debugger);
				if (isDebug)
					debugger.write(("\nValid result returned from DB.")
							.getBytes());
			} else {
				if (isDebug)
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
		boolean isDebug = RequestContext.isDebugEnabled();

		try {
			menu = gs.fromJson(restaurantMenu, Menu.class);
			if (menu.getCategories() == null)
				throw new Exception("categories are null");

			if (isDebug)
				debugger.write(("\nGson successfully converted the response.")
						.getBytes());
			String json = gs.toJson(menu);
			if (isDebug)
				debugger.write(("\n" + json).getBytes());
		} catch (Exception e) {
			if (isDebug) {
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
		try {
			loader(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BasicDBObject doc = (BasicDBObject) JSON.parse(gs
				.toJson(restaurantOrder));
		order.insert(doc);
		return true;
	}

	public static boolean setRestaurantDataToDB(Restaurant rest,
			ServletOutputStream debugger) throws IOException {
		try {
			loader(debugger);
			BasicDBObject doc = (BasicDBObject) JSON.parse(gs.toJson(rest));
			restaurant.insert(doc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * ensures a index on restaurantId
	 * 
	 * @param restaurantId
	 * @param state
	 * @param debugger
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<RestaurantOrder> getOrders(String restaurantId,
			String state, ServletOutputStream debugger) throws IOException {
		loader(debugger);
		ArrayList<RestaurantOrder> result = new ArrayList<RestaurantOrder>();
		BasicDBObject bdo = new BasicDBObject();
		bdo.put(UrlParameter.RESTAURNAT_ID.toString(), restaurantId);
		bdo.put(UrlParameter.ORDERSTATE.toString(), state);

		DBCursor cursor = order.find(bdo);
		while (cursor.hasNext()) {
			DBObject temp = cursor.next();
			result.add(gs.fromJson(temp.toString(), RestaurantOrder.class));
		}
		return result;
	}

	/**
	 * 
	 * @param restaurantId
	 * @param debugger
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	@Deprecated
	public static RestaurantDashboardData getRestaurantDashboardData(
			String restaurantId, ServletOutputStream debugger)
			throws IOException, JSONException {
		loader(debugger);
		RestaurantDashboardData rdd = new RestaurantDashboardData();

		/**
		 * populate the table map
		 */

		DBObject match = new BasicDBObject("$match", BasicDBObjectBuilder
				.start().append(UrlParameter.RESTAURNAT_ID.toString(),
						restaurantId));
		DBObject project = new BasicDBObject("$project", BasicDBObjectBuilder
				.start().append(UrlParameter.TABLEINFORMATION.toString(), 1));

		AggregationOutput out = restaurant.aggregate(match, project);
		Iterator<DBObject> iter = out.results().iterator();
		String ti = iter.next().get(UrlParameter.TABLEINFORMATION.toString())
				.toString();

		@SuppressWarnings("unchecked")
		Iterator<String> iter1 = new JSONObject(ti).keys();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while (iter1.hasNext()) {
			String key = iter1.next().toString();
			map.put(key, new JSONObject(ti).getInt(key));
		}
		rdd.setTableInformation(map);

		/**
		 * populate order String
		 */

		match = new BasicDBObject("$match", BasicDBObjectBuilder.start(
				UrlParameter.RESTAURNAT_ID.toString(), restaurantId));
		DBCursor cursor = order.find(match);
		ArrayList<RestaurantOrder> orderList = new ArrayList<RestaurantOrder>();
		while (cursor.hasNext()) {
			orderList.add(gs.fromJson(cursor.next().toString(),
					RestaurantOrder.class));
		}
		rdd.setOrders(gs.toJson(orderList));

		return rdd;
	}

	/**
	 * 
	 * @param restaurantId
	 * @param orderId
	 */
	public static void moveCurrentOrderToCompletedOrders(String restaurantId,
			String orderId, ServletOutputStream debugger) {
		// TODO check if there is some trigger kind of concept in mongo to auto
		// pull the documents

		// TODO check the number of collections possible in mongo. Should we
		// have something like a relational DB structure or something like a
		// proper NOSql style design where each rest, user has its own
		// collections
	}

	/**
	 * 
	 * @param customerId
	 * @param debugger
	 * @param startDate
	 * @param chronological
	 *            true increasing chronological false decreasing chronological
	 * @param count
	 */
	public static void getOrdersForCustomer(String customerId,
			ServletOutputStream debugger, String startDate,
			boolean chronological, int count) {
		// TODO fetch all orders for customer
	}

	public static void main(String[] args) throws IOException, JSONException {
		// new DataConnection();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("siddhanth", 1);
		map.put("jain", 2);
		Gson gs = new Gson();
		String ti = gs.toJson(map);
		System.out.println(ti);
		JSONObject obj = new JSONObject(ti);
		Iterator<String> iter = obj.keys();
		while (iter.hasNext()) {
			System.out.println(iter.next().toString());
		}

		DBObject d = new BasicDBObject();
		d.put("sid", "jain");
		System.out.println(d.toString());

	}
}