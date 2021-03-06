package com.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.database.DataConnection;

/*
 * Reference:
 * http://krams915.blogspot.in/2010/12/spring-security-mvc-integration-using_26.html
 * 
 */
/**
 * A custom authentication manager that allows access if the user details exist
 * in the database and if the username and password are not the same. Otherwise,
 * throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		boolean authentic = false;
		try {
			authentic = DataConnection.checkIfAuthenticUser(auth.getName(),
					(String) auth.getCredentials(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (authentic) {
			return new UsernamePasswordAuthenticationToken(auth.getName(),
					auth.getCredentials(), getAuthorities(1));
		} else {
			throw new BadCredentialsException("Username/password do not match");
		}

	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where
	 * access level is an Integer. Basically, this interprets the access value
	 * whether it's for a regular user or admin.
	 * 
	 * @param access
	 *            an integer value representing the access of the user
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
