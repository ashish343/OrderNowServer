package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.data.menu.Restaurant;
import com.data.restaurant.RestaurantDashboardData;
import com.data.restaurant.RestaurantOrder;
import com.database.DataConnection;
import com.enums.UrlParameter;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(name = "RestaurantDashboardServlet", urlPatterns = { "/dashboard" })
public class RestaurantDashboardServlet extends HttpServlet {
	Gson gs = new Gson();

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RestaurantDashboardData restaurantData = getRestaurantData();

		request.setAttribute("restaurantData", restaurantData);
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(
				request, response);
	}

	private RestaurantDashboardData getRestaurantData() throws IOException {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		ArrayList<String> resIds = DataConnection
				.getRestaurantsAssociatedWithUser(userName);

		assert resIds.size() != 0;

		RestaurantDashboardData restaurantData = new RestaurantDashboardData();
		String restaurantId = resIds.get(0);
		restaurantData.setRestId(restaurantId);
		Restaurant r = new Restaurant();
		try {
			r = Restaurant.loadFromDB(restaurantId, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		restaurantData.setTableInformation(r.getTableInformation());
		restaurantData.setOrders(getOrders(restaurantId));
		return restaurantData;
	}

	private String getOrders(String restaurantId) {

		List<RestaurantOrder> restOrderList = new ArrayList<RestaurantOrder>();
		try {
			restOrderList = RestaurantOrder.getOrdersFronDB(restaurantId,
					UrlParameter.INTERMEDIATE.toString(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return gs.toJson(restOrderList);
	}

	private Map<String, Integer> getTableInformation() {
		Map<String, Integer> tableInformation = new HashMap<String, Integer>();
		tableInformation.put("T1", 1);
		tableInformation.put("T2", 2);
		tableInformation.put("T3", 3);
		tableInformation.put("T4", 4);
		tableInformation.put("T5", 5);
		tableInformation.put("T6", 6);
		return tableInformation;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return null;
	}

}