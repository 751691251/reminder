package com.ibm.iga.reminder.service.inter;

import com.ibm.iga.reminder.model.ReminderUserDetails;

public interface IReminderUserService {
	
	public long register (ReminderUserDetails user) ;
	public long forgotPassword (ReminderUserDetails user);
	public long updatePassword(ReminderUserDetails user, String password);
	public long updateRole(ReminderUserDetails user);
}
