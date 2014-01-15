package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.handlers.restaurant.RestaurantEventsHandler;

@SuppressWarnings("serial")
@WebServlet(
        name = "RegisterRestaurantServlet", 
        urlPatterns = {"/registerRestaurant"}
    )
public class RegisterRestaurant  extends HttpServlet {

	protected final Log logger = LogFactory.getLog(getClass());
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int status = RestaurantEventsHandler.createRestaurant(request);
    	// If SUCCESS then return the success page.
    	
    	request.getRequestDispatcher("/WEB-INF/jsp/addRest.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
