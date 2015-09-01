package com.ibm.iga.reminder.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.iga.reminder.service.inter.ILoginService;
import com.ibm.iga.reminder.service.inter.ILoginUserDetailsService;

@Service ("userDetailsService")
public class UserDetailsServiceImpl  implements ILoginUserDetailsService {

	@Autowired
	private ILoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username, String password)
			throws UsernameNotFoundException {
		if (!loginService.validate(username, password)) {
			return null;
		}
		ReminderUserDetails user = new ReminderUserDetails(username, password);
		
		return user;
	}

}
