package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.data.menu.Restaurant;
import com.enums.UrlParameter;
import com.google.gson.Gson;
import com.handlers.table.TableHandler;

@SuppressWarnings("serial")
@WebServlet(name = "ServeTableServlet", urlPatterns = { "/serveTable" })
public class ServeTable extends HttpServlet {

	protected final Log logger = LogFactory.getLog(getClass());
	private String IS_VALID = "1";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = response.getOutputStream();
		ServletOutputStream debugger = null;
		// out.write(host.getBytes());
		//
		String tableId = request.getParameter(UrlParameter.TABLE_ID.toString());
		boolean isDebug = IS_VALID.equals(request
				.getParameter(UrlParameter.DEBUG.toString()));

		if (isDebug) {
			debugger = outputStream;
		}

		Restaurant data = null;

		String restuarantId = TableHandler
				.getValidRestuarant(tableId, debugger);
		if (restuarantId != null) {
			data = TableHandler
					.getRestaurantInformation(restuarantId, debugger);
		} else {
			// Return a response to try scan the QR code again.
		}

		// If SUCCESS then return the success page.
		if (data != null) {
			// Return the JSON Response.
		} else {
			// Tray again, if that fails return false.
		}

		Gson gson = new Gson();
		String json = gson.toJson(data);

		// if Debug mode
		if (isDebug) {
			debugger.write(("\nResult::" + json).getBytes());
			debugger.flush();
			debugger.close();
		} else {
			outputStream.write(json.getBytes());
			outputStream.flush();
			outputStream.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
