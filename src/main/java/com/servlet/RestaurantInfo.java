package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

import com.data.restaurant.RestaurantDashboardData;
import com.data.restaurant.RestaurantInfoEnum;
import com.data.restaurant.RestaurantOrder;
import com.database.DataConnection;
import com.enums.UrlParameter;
import com.google.gson.Gson;
import com.handlers.restaurant.RestaurantEvents;
import com.handlers.restaurant.RestaurantEventsHandler;

@SuppressWarnings("serial")
@WebServlet(name = "RestaurantInfoServlet", urlPatterns = { "/info" })
public class RestaurantInfo extends HttpServlet {
	Gson gs = new Gson();

	protected final Log logger = LogFactory.getLog(getClass());
	private static final String EMPTY_STRING = "";
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Get the reataurant Id from the DB.
		 */
		String restaurantId = "R1";//getRestaurantData();
		request.setAttribute("restaurantId", restaurantId);
		
		String action = request.getParameter(UrlParameter.USER_ACTION.toString());
		
		if(action != null && !EMPTY_STRING.equals(action)) {
			if(RestaurantInfoEnum.HISTORY.toString().equals(action)) {
				Map<String, ArrayList<RestaurantOrder>> history = RestaurantOrder.getAllCompletedOrders(restaurantId);
				request.setAttribute("history", history);
				request.getRequestDispatcher("/WEB-INF/jsp/restaurant/history.jsp").forward(request, response);
			}
			if(RestaurantInfoEnum.ANALYSIS.toString().equals(action)) {
				request.getRequestDispatcher("/WEB-INF/jsp/restaurant/analysis.jsp").forward(request, response);
			}
			if(RestaurantInfoEnum.INFO.toString().equals(action)) {
				request.getRequestDispatcher("/WEB-INF/jsp/restaurant/info.jsp").forward(request, response);
			}
			if(RestaurantInfoEnum.CONTACT.toString().equals(action)) {
				request.getRequestDispatcher("/WEB-INF/jsp/common/contact-us.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/restaurant-info-home.jsp").forward(request, response);
		}
	}

	private String getRestaurantData() throws IOException {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		ArrayList<String> resIds = DataConnection
				.getRestaurantsAssociatedWithUser(userName);

		assert resIds.size() != 0;

		RestaurantDashboardData restaurantData = new RestaurantDashboardData();
		String restaurantId = resIds.get(0);
		
		return restaurantId;
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