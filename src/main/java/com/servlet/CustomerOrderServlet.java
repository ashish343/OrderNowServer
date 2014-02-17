package com.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.data.menu.CustomerOrder;
import com.data.menu.Menu;
import com.data.menu.Restaurant;
import com.data.restaurant.RestaurantClientSideEvents;
import com.data.restaurant.RestaurantOrder;
import com.database.DataConnection;
import com.enums.EventState;
import com.enums.UrlParameter;
import com.google.gson.Gson;
import com.handlers.table.TableHandler;
import com.parse.ParseNotificationHelper;
import com.test.PusherTest;
import com.utility.CustomerRestaurantHandshake;
import com.utility.OrderIdGenerator;
import com.utility.RequestContext;

@SuppressWarnings("serial")
@WebServlet(name = "CustomerOrderServlet", urlPatterns = { "/order" })
public class CustomerOrderServlet extends HttpServlet {
	/*
	 * Sample JSON:
	 * {"dishes":{"d2":1.0,"d3":0.5,"d1":1.0},"restaurantId":"r1","customerId"
	 * :"NXwUdXIorg", "tableId":"t1"}
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = response.getOutputStream();
		boolean isDebug = RequestContext.isDebugEnabled();

		/*
		 * Get the Customer Order.
		 */
		CustomerOrder customerOrder = getCustomerOrder(request, outputStream,
				isDebug);
		JSONObject bdo = new JSONObject();
		try {
			bdo.put(UrlParameter.ORDER_ID.toString(),
					customerOrder.getOrderId());
			bdo.put(UrlParameter.SUBORDER_ID.toString(),
					customerOrder.getSubOrderId());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		outputStream.write(bdo.toString().getBytes());

		/*
		 * Fetch the Menu From DB to create Restaurant Order.
		 */
		/*
		 * TODO: Remove the below hack.
		 */
		String restuarantId = customerOrder.getRestaurantId();
		Menu menu = getMenu(restuarantId, outputStream, isDebug);

		/*
		 * Populate the Restaurant Order from Customer Order.
		 */
		CustomerRestaurantHandshake customerRest = new CustomerRestaurantHandshake();
		RestaurantOrder restaurantOrder = customerRest.getRestaurantOrder(menu,
				customerOrder);

		/*
		 * TODO: Update DB with the order.
		 */
		DataConnection.setOrderDetailsToDB(restaurantOrder);

		/*
		 * Subscribe Customer to The Channel.
		 */
		ParseNotificationHelper.registerChannel(customerOrder.getCustomerId(),
				customerOrder.getOrderId(), outputStream);

		/*
		 * Send Notification to Restaurant about the order.
		 */
		Gson gson = new Gson();
		String restaurantOrderJson = gson.toJson(restaurantOrder);
		System.out.println(restaurantOrderJson);
		System.out.println(restaurantOrderJson.replace("\\\"", "\"").replace(
				"\"", "\\\""));
		String json = "{\"data\":\""
				+ restaurantOrderJson.replace("\\\"", "\"").replace("\"",
						"\\\"") + "\",\"name\":\""
				+ RestaurantClientSideEvents.NOTIFY_NEW_ORDER.toString()
				+ "\",\"channel\":\"" + restuarantId + "\"}";
		PusherTest.triggerPush(restuarantId,
				RestaurantClientSideEvents.NOTIFY_NEW_ORDER.toString(), json,
				"");
		/*
		 * Return a success message to the User.
		 */
		outputStream.flush();
	}

	private Menu getMenu(String restuarantId, ServletOutputStream outputStream,
			boolean isDebug) throws IOException {
		Menu menu = null;
		Gson gson = new Gson();
		if (restuarantId != null) {
			Restaurant restaurantData = TableHandler.getRestaurantInformation(
					restuarantId, outputStream);
			menu = restaurantData.getMenu();
		} else {
			// Return a response to try scan the QR code again.
			outputStream.write(EventState.FAILURE.toString().getBytes());
		}

		if (isDebug) {
			outputStream.write(("\nMenu Data from DB::" + gson.toJson(menu))
					.getBytes());
		}
		return menu;
	}

	private static Menu getMenu() {
		Menu menu = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("flare.json"));
			String menuJson = br.readLine();
			while ((br.readLine()) != null) {
				menuJson += br.readLine();
			}
			Gson gson = new Gson();
			menu = gson.fromJson(menuJson, Menu.class);
		} catch (Exception e) {
			System.out.println("Exception::" + e.toString());
		}
		return menu;
	}

	private CustomerOrder getCustomerOrder(HttpServletRequest request,
			ServletOutputStream outputStream, boolean isDebug)
			throws IOException {
		String customerOrderJson = request
				.getParameter(UrlParameter.CUSTOMER_ORDER.toString());
		Gson gson = new Gson();
		CustomerOrder customerOrder = gson.fromJson(customerOrderJson,
				CustomerOrder.class);
		String orderId = customerOrder.getOrderId();
		if (orderId == null)
			orderId = RestaurantOrder.getOrderId(customerOrder.getTableId(),
					customerOrder.getRestaurantId());

		if (orderId == null) {
			/**
			 * No record in database and hence subOrderId = 0
			 */
			customerOrder.setOrderId(OrderIdGenerator.generateUniqueOrderId());
			customerOrder.setSubOrderId(0);
		} else {
			/**
			 * generating sub order Id as last subOrderId+1
			 */
			int count = DataConnection.getSubOrderCount(orderId, outputStream);
			customerOrder.setSubOrderId(count);
		}

		if (isDebug) {
			outputStream.write(("\nCustomer Json Order :: ").getBytes());
			outputStream.write(("\n" + customerOrderJson).getBytes());
		}
		return customerOrder;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
