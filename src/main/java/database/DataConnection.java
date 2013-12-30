package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	private static DBCollection restuarant;
	private static DBCollection menu;

	private DataConnection() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("config"));
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] arr = line.split(" ");
			switch (arr[0]) {
			case "uri":
				mongoURI = arr[1];
				break;
			case "db":
				db = arr[1];
				break;
			case "table_collection":
				TABLE_DATA = arr[1];
				break;
			case "restaurant_data_table":
				RESTUARANT_DATA = arr[1];
				break;
			case "menu_table":
				MENU_DATA = arr[1];
				break;
			}
		}
		br.close();
		if (mongoURI == null || db == null) {
			System.err.println("Connection Information or DB information not present.. Exiting");
			System.exit(1);
		}

		mongoClient = new MongoClient(new MongoClientURI(mongoURI)); //new MongoClient(new MongoClientURI(mongoURI));
		mongoDb = mongoClient.getDB(db);
		
		table = mongoDb.getCollection(TABLE_DATA);
		restuarant = mongoDb.getCollection(RESTUARANT_DATA);
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
	}
}