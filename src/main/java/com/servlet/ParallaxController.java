package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.utility.UAgentInfo;

@SuppressWarnings("serial")
@WebServlet(
        name = "ParallaxServlet", 
        urlPatterns = {"/parallax"}
    )
public class ParallaxController extends HttpServlet {
    protected final Log logger = LogFactory.getLog(getClass());
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String now = (new Date()).toString();
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();
        String x = sc.getRealPath("/");
        String host = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(); 
        
        //ServletOutputStream out = response.getOutputStream();
        //out.write(host.getBytes());
        //out.flush();
        String userAgent = request.getHeader("User-Agent");
        UAgentInfo uAgent = new UAgentInfo(userAgent, null);
        
        request.setAttribute("path", host);
        request.setAttribute("now", now);
        if(uAgent.isMobilePhone || uAgent.isTierTablet) {
        	request.getRequestDispatcher("/WEB-INF/jsp/new-home.jsp").forward(request, response);
        } else {
        	request.getRequestDispatcher("/WEB-INF/jsp/home-parallax.jsp").forward(request, response);	
        }
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

