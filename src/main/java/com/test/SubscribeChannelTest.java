package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.parse.ParseNotificationHelper;


@SuppressWarnings("serial")
@WebServlet(
        name = "SubscribeChannelTestServlet", 
        urlPatterns = {"/test/subscribe/"}
    )
public class SubscribeChannelTest  extends HttpServlet {

	/*
	 * Accept  
	 * 1) custId as Custoemr Push ID;
	 * 2) orderId as the channel.
	 * 
	 * Eg:
	 * 	test/subscribe?orderId=Oid1&custId=ABC
	 */
	
	protected final Log logger = LogFactory.getLog(getClass());
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletOutputStream outputStream = response.getOutputStream();
    	outputStream.write("SubscribeChannelTestServlet servlet".getBytes());
        String customerId = request.getParameter("custId");
        String orderId = request.getParameter("orderId");
        
        outputStream.write(customerId.getBytes());
        outputStream.write(orderId.getBytes());
        ParseNotificationHelper.registerChannel(customerId, orderId, outputStream);
        outputStream.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
