package com.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.enums.UrlParameter;

@Component
public class RequestContext {
	@Autowired(required=true)
	private HttpServletRequest request;
	
	public static boolean isDebug;
	private static final String IS_VALID = "1";
	
	
	public static boolean isDebugEnabled() {
		HttpServletRequest curRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		isDebug = IS_VALID.equals(curRequest.getParameter(UrlParameter.DEBUG.toString()));
		System.out.println("\n Debug::" + isDebug);
		return isDebug;
	}
}
