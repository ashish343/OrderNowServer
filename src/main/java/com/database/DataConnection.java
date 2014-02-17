package com.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

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

	private String db;
	private String mongoURI;
	private static DB mongoDb;
	private final MongoClient mongoClient;

	private static final String USER_INFO = "user_info";
	private static final String RESTUARANT_DATA = "rest";
	private static final String TABLE_DATA = "table_rest";
	private static final String LOGIN = "login_credentials";
	private static final String ORDER_DATA = "order";;
	private static final String COMPLETED_ORDERS = "completed_orders";

	private static DBCollection table;
	/**
	 * contains restaurant details
	 */
	private static DBCollection restaurant;
	/**
	 * all the current orders and completed orders
	 */
	private static DBCollection current_orders;
	private static DBCollection completed_orders;
	/**
	 * contains login and user information
	 */
	private static DBCollection login;
	private static DBCollection user;

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

		if (mongoURI == null || db == null) {
			System.err
					.println("Connection Information or DB information not present.. Exiting");
			System.exit(1);
		}

		mongoClient = new MongoClient(new MongoClientURI(mongoURI)); // new
																		// MongoClient(new
																		// MongoClientURI(mongoURI));
		gs = new Gson();
		mongoDb = mongoClient.getDB(db);

		login = mongoDb.getCollection(LOGIN);
		user = mongoDb.getCollection(USER_INFO);
		table = mongoDb.getCollection(TABLE_DATA);
		current_orders = mongoDb.getCollection(ORDER_DATA);
		restaurant = mongoDb.getCollection(RESTUARANT_DATA);
		completed_orders = mongoDb.getCollection(COMPLETED_ORDERS);

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
				restaurantData = gs.fromJson(obj.toString(), Restaurant.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		return restaurantData;
	}

	public static boolean setOrderDetailsToDB(RestaurantOrder restaurantOrder) {
		try {
			loader(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BasicDBObject doc = (BasicDBObject) JSON.parse(gs
				.toJson(restaurantOrder));
		current_orders.insert(doc);
		return true;
	}

	public static boolean setCompletedOrderDetailsToDB(
			RestaurantOrder restaurantOrder) {
		try {
			loader(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BasicDBObject doc = (BasicDBObject) JSON.parse(gs
				.toJson(restaurantOrder));
		completed_orders.insert(doc);
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
	public static ArrayList<RestaurantOrder> getCurrentOrders(
			String restaurantId, String state, ServletOutputStream debugger)
			throws IOException {
		loader(debugger);
		ArrayList<RestaurantOrder> result = new ArrayList<RestaurantOrder>();
		BasicDBObject bdo = new BasicDBObject();
		bdo.put(UrlParameter.RESTAURNAT_ID.toString(), restaurantId);
		bdo.put(UrlParameter.ORDERSTATE.toString(), state);

		DBCursor cursor = current_orders.find(bdo);
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
		 * populate current_orders String
		 */

		match = new BasicDBObject("$match", BasicDBObjectBuilder.start(
				UrlParameter.RESTAURNAT_ID.toString(), restaurantId));
		DBCursor cursor = current_orders.find(match);
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

	public static boolean checkIfAuthenticUser(String username,
			String password, ServletOutputStream debugger) throws IOException {
		loader(debugger);
		BasicDBObject obj = new BasicDBObject();
		obj.append(UrlParameter.USER_NAME.toString(), username).append(
				UrlParameter.PASSWORD.toString(), encodeString(password));
		DBCursor res = login.find(obj);
		int len = res.count();
		if (len == 1) {
			return true;
		} else
			return false;
	}

	public static String encodeString(String unencoded) {
		return new Md5PasswordEncoder().encodePassword(unencoded, null);
	}

	public static ArrayList<String> getRestaurantsAssociatedWithUser(
			String username) throws IOException {
		loader(null);

		DBObject match = new BasicDBObject("$match",
				new BasicDBObject().append(UrlParameter.USER_NAME.toString(),
						username));
		DBObject project = new BasicDBObject("$project",
				new BasicDBObject().append(
						UrlParameter.RESTAURNAT_ID.toString(), 1));
		ArrayList<String> restIds = new ArrayList<String>();
		AggregationOutput out = user.aggregate(match, project);
		Iterator<DBObject> iter = out.results().iterator();
		while (iter.hasNext()) {
			DBObject temp = iter.next();
			restIds.add((String) temp.get(UrlParameter.RESTAURNAT_ID.toString()));
		}

		return restIds;
	}

	public static RestaurantOrder getCurrentOrderForOrderId(String orderId) {
		BasicDBObject bdo = new BasicDBObject();
		bdo.append(UrlParameter.ORDER_ID.toString(), orderId);
		DBCursor cursor = current_orders.find(bdo);
		RestaurantOrder rs = null;
		if (cursor.hasNext()) {
			String temp = cursor.next().toString();
			rs = gs.fromJson(temp, RestaurantOrder.class);
		}
		return rs;
	}

	/**
	 * deletes and returns the deleted {@link RestaurantOrder} object
	 * 
	 * @param orderId
	 * @return
	 * @throws IOException
	 */
	public static RestaurantOrder removeCurrentOrder(String orderId)
			throws IOException {
		loader(null);
		BasicDBObject bdo = new BasicDBObject();
		bdo.append(UrlParameter.ORDER_ID.toString(), orderId);
		DBCursor cursor = current_orders.find(bdo);
		RestaurantOrder obj = null;
		if (cursor.hasNext()) {
			obj = gs.fromJson(cursor.next().toString(), RestaurantOrder.class);
			current_orders.remove(bdo);

		}
		return obj;
	}

	public static boolean completeOrder(String orderId) {
		RestaurantOrder obj = null;
		try {
			obj = removeCurrentOrder(orderId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (obj != null) {
			setCompletedOrderDetailsToDB(obj);
			return true;
		} else
			return false;
	}

	public static String getOrderId(String tableId, String restaurantId) {
		String orderId = null;
		BasicDBObject bdo = new BasicDBObject();
		bdo.put(UrlParameter.RESTAURNAT_ID.toString(), restaurantId);
		bdo.put(UrlParameter.TABLE_ID.toString(), tableId);
		DBObject obj = current_orders.findOne(bdo);

		if (obj != null)
			orderId = (String) obj.get(UrlParameter.ORDER_ID.toString());
		return orderId;
	}

	public static void main(String[] args) throws IOException, JSONException {

		System.out.println(DataConnection.encodeString("test123"));

	}

}