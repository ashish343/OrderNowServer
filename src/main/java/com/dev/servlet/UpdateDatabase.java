package com.dev.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.data.menu.Menu;
import com.google.gson.Gson;
import com.utility.DishIdGenerator;

@WebServlet(name = "UpdateDatabase", urlPatterns = { "/updateDatabase" })
public class UpdateDatabase extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = null;
		Gson gson = new Gson();
		try {
			obj = new JSONObject(request.getParameter("json"));
			String json = createCleanJSON(obj).toString();
			Menu m = gson.fromJson(json, Menu.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static JSONObject createCleanJSON(JSONObject obj)
			throws JSONException {
		JSONObject ret = new JSONObject();
		JSONArray categories = new JSONArray();
		JSONArray dishes = new JSONArray();

		if (obj.has("children") || obj.has("_children")) {

			JSONArray jarr = null;
			if (obj.has("children"))
				jarr = obj.getJSONArray("children");
			else
				jarr = obj.getJSONArray("_children");

			for (int jx = 0; jx < jarr.length(); jx++) {
				JSONObject tmp = (JSONObject) jarr.get(jx);
				if (tmp.has("children")
						&& tmp.getJSONArray("children").length() > 0)
					categories.put(createCleanJSON(tmp));
				else
					dishes.put(createCleanJSON(tmp));
			}
		}
		ret.put("categories", categories);
		if (obj.get("name").equals("flare"))
			return ret;

		ret.put("dishes", dishes);

		Iterator<String> iter = obj.keys();
		while (iter.hasNext()) {
			String key = iter.next().toString();
			if ("desc".equals(key)) {
				ret.put("description", obj.get(key));
			} else if ("name".equals(key)) {
				ret.put(key, obj.get(key));
			} else if ("price".equals(key)) {
				ret.put(key, obj.get(key));
			} else if ("img".equals(key)) {
				ret.put(key, obj.get(key));
			} else if ("type".equals(key)) {
				ret.put(key, obj.get(key));
			} else if ("dishId".equals(key)) {
				if (obj.get(key) == "-1")
					ret.put(key, DishIdGenerator.generateUniqueDishId());
				else
					ret.put(key, obj.get(key));
			}
		}
		return ret;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
