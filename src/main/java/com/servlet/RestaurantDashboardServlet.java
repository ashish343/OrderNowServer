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
import org.springframework.web.servlet.ModelAndView;
import com.data.menu.FoodType;
import com.data.restaurant.OrderedDish;
import com.data.restaurant.RestaurantDashboardData;
import com.google.gson.Gson;
import com.data.restaurant.RestaurantOrder;
import com.enums.UrlParameter;


@SuppressWarnings("serial")
@WebServlet(
        name = "RestaurantDashboardServlet", 
        urlPatterns = {"/dashboard"}
    )
public class RestaurantDashboardServlet extends HttpServlet {
    protected final Log logger = LogFactory.getLog(getClass());
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RestaurantDashboardData restaurantData = getTestRestaurantData();
        
        request.setAttribute("restaurantData", restaurantData);
        request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
    }
    
    private RestaurantDashboardData getTestRestaurantData() {
		RestaurantDashboardData restaurantData = new RestaurantDashboardData();
		
		restaurantData.setTableInformation(getTableInformation());
		restaurantData.setOrders(getOrders());
		
		return restaurantData;
	}
    
    private String getOrders() {
    	Gson gson = new Gson();
    	
    	List<RestaurantOrder> restOrderList = new ArrayList<RestaurantOrder>();
    	List<OrderedDish> dishes = new ArrayList<OrderedDish>();
    	
    	OrderedDish orderedDish1 = new OrderedDish();
    	dishes.add(orderedDish1);
    	
    	orderedDish1.setDishId("d1");
    	orderedDish1.setName("Test1");
    	orderedDish1.setPrice(100);
    	orderedDish1.setQuatity((float)1);
    	orderedDish1.setType(FoodType.Veg);
    	
    	OrderedDish orderedDish2 = new OrderedDish();
    	dishes.add(orderedDish2);
    	
    	orderedDish2.setDishId("d2");
    	orderedDish2.setName("Test2");
    	orderedDish2.setPrice(100);
    	orderedDish2.setQuatity((float)1);
    	orderedDish2.setType(FoodType.NonVeg);
    	
    	
    	
    	RestaurantOrder restOrder1= new RestaurantOrder();
    	restOrder1.setCustomerId("abc");
    	
		restOrder1.setDishes(dishes);
    	restOrder1.setOrderState(UrlParameter.INTERMEDIATE.toString());
    	restOrder1.setRestaurantId("R1");
    	restOrder1.setSubOrderId(0);
    	restOrder1.setTableId("T1");
    	restOrder1.setTableNo(1);
    	restOrder1.setOrderId("dfref");
    	
    	RestaurantOrder restOrder2= new RestaurantOrder();
    	restOrder2.setCustomerId("bdw");
    	restOrder2.setDishes(dishes);
    	restOrder2.setOrderState("");
    	restOrder2.setRestaurantId("R1");
    	restOrder2.setSubOrderId(0);
    	restOrder2.setTableId("T2");
    	restOrder2.setTableNo(2);
    	restOrder2.setOrderId("ddffref");
    	
    	restOrderList.add(restOrder1);
    	restOrderList.add(restOrder2);
    	
    	return gson.toJson(restOrderList);
    }
    
    private Map<String, String> getTableInformation() {
    	Map<String, String> tableInformation = new HashMap<String, String>();
		tableInformation.put("T1", "1");
		tableInformation.put("T2", "2");
		tableInformation.put("T3", "3");
		tableInformation.put("T4", "4");
		tableInformation.put("T5", "5");
		tableInformation.put("T6", "6");
		
		return tableInformation;
    }

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	doGet(req, resp);
    }
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    	return null;
    	// String now = (new Date()).toString();
        // logger.info("Returning hello view with " + now);
        // return null;//new ModelAndView("/WEB_INF/jsp/hello.jsp", "now", now);
    }
}