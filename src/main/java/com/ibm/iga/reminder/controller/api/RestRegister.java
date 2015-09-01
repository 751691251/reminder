package com.ibm.iga.reminder.controller.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.iga.reminder.model.ReminderRequest;
import com.ibm.iga.reminder.model.ReminderUserDetails;
import com.ibm.iga.reminder.service.inter.IUserService;

/**
 * 
 * @author Jerry Wang
 * 
 * /api/user/register -- post to create new account
 *  /api/user/password/update -- post to update password
 *  /api/user/password/forgot -- post to receive mail with current password
 *
 */
@RestController
@RequestMapping("/api/user")
public class RestRegister {
	@Autowired
	private IUserService userService;
	
	
	private Principal principal;
	
	@RequestMapping("")
	public Principal user (Principal user) {
		return user;
	}
	
	@ModelAttribute
	public void getCurrentUser (Principal principal) {
		this.principal = principal;
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RestAPIStatus register (@RequestParam String username, @RequestParam String password) {
		ReminderUserDetails user = new ReminderUserDetails(username, password, "USER");
		userService.register(user);
		return new RestAPIStatus().success();
	}
	
	@RequestMapping(value = "/password/update", method = RequestMethod.POST)
	public RestAPIStatus updatePassword (@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
		if (!principal.getName().equalsIgnoreCase(username)) {
			return new RestAPIStatus().failure();
		}
		
		ReminderUserDetails user = userService.get(username);
		if (!user.validatePassoword(oldPassword)) {
			return new RestAPIStatus().failure();
		}
		user.setPassword(newPassword);
		userService.updatePassword(user);
		return new RestAPIStatus().success();
	}
	
	@RequestMapping(value = "/password/forgot", method = RequestMethod.POST)
	public RestAPIStatus forgotPassword (@RequestParam String username) {
		if (!principal.getName().equalsIgnoreCase(username)) {
			return new RestAPIStatus().failure();
		}
		userService.forgotPassword(userService.get(username));
		return new RestAPIStatus().success();
	}
	
}
