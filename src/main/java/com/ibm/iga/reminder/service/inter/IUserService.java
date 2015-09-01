package com.ibm.iga.reminder.service.inter;

import com.ibm.iga.reminder.model.ReminderUserDetails;

public interface IUserService {
	
	public void register (ReminderUserDetails user);
	public void updatePassword(ReminderUserDetails user);
	public void forgotPassword(ReminderUserDetails user);
	public ReminderUserDetails get (String username);
}
