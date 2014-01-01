package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.data.menu.Restaurant;
import com.enums.UrlParameter;
import com.handlers.table.TableHandler;

@SuppressWarnings("serial")
@WebServlet(
        name = "Servlet", 
        urlPatterns = {"/serveTable"}
    )
public class ServeTable  extends HttpServlet {

	protected final Log logger = LogFactory.getLog(getClass());
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tableId = request.getParameter(UrlParameter.TABLE_ID.toString());
    	Restaurant data = null;
    	String restuarantId = TableHandler.isValidRestuarant(tableId);
    	if(restuarantId != null) {
    		data = TableHandler.server(restuarantId);
    	} else {
    		//Return a response to try scan the QR code again.
    	}
    	
    	// If SUCCESS then return the success page.
    	if(data != null) {
    		// Return the JSON Response.
    	} else {
    		//Tray again, if that fails return false.
    	}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
