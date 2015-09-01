package com.ibm.iga.reminder.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ibm.iga.reminder.service.inter.ILoginUserDetailsService;

@Service
public class LoginAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private ILoginUserDetailsService userDetailsService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String pass = (String)authentication.getCredentials();
		if (pass == null) {
			throw new BadCredentialsException("Bad credentials.");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String pass = (String) authentication.getCredentials();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username, pass);
		if (userDetails == null) {
			throw new AuthenticationServiceException("Wrong credential!");
		}
		return userDetails;
	}

}
