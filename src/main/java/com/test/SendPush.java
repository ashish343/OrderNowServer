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


@SuppressWarnings("serial")
@WebServlet(
        name = "TestSendServlet", 
        urlPatterns = {"/test/send"}
    )
public class SendPush  extends HttpServlet {

	protected final Log logger = LogFactory.getLog(getClass());
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletOutputStream out = response.getOutputStream();
        out.write("Send servlet".getBytes());
        
    	String json = "{\"data\":\"{\\\"message\\\":\\\"hello world\\\"}\",\"name\":\"my_event\",\"channel\":\"test_channel\"}";
    	
		PusherTest.triggerPush("test_channel", "my_event", json, "");
		out.flush();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}
