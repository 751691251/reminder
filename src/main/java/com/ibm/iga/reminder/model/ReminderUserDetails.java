package com.ibm.iga.reminder.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;



public class ReminderUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3224655302634571983L;
	private String user;
	private String password;
	private String authority;
	private boolean enabled = true;
	
	public ReminderUserDetails() {
		super();
	}



	public ReminderUserDetails(String user, String password, String authority) {
		super();
		this.user = user;
		this.password = password;
		this.authority = authority;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return user;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getAuthority() {
		return authority;
	}



	public void setAuthority(String authority) {
		this.authority = authority;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public boolean validatePassoword (String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public String toString() {
		return "ReminderUserDetails [user=" + user + ", password=" + password
				+ ", authority=" + authority + "]";
	}

	
}
