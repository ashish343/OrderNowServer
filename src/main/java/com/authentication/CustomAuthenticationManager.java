package com.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/*
 * Reference:
 * http://krams915.blogspot.in/2010/12/spring-security-mvc-integration-using_26.html
 * 
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication arg0)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return new UsernamePasswordAuthenticationToken("ashish", "ashish", getAuthorities(1));
	}

	/**
	  * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	  * Basically, this interprets the access value whether it's for a regular user or admin.
	  * 
	  * @param access an integer value representing the access of the user
	  * @return collection of granted authorities
	  */
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
	    // Create a list of grants for this user
	    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
	    
	    // All users are granted with ROLE_USER access
	    // Therefore this user gets a ROLE_USER by default
	    authList.add(new GrantedAuthorityImpl("ROLE_USER"));
	   
	    return authList;
	}
}
