package com.ibm.iga.reminder.service.inter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ILoginUserDetailsService{
	public UserDetails loadUserByUsername(String username, String password)
			throws UsernameNotFoundException;
}
