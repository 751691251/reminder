package com.ibm.iga.reminder.service.inter;

import com.ibm.iga.reminder.model.ReminderUserDetails;

public interface ISendUserService {

	public void sendUserRegistrationMail (ReminderUserDetails user);
	public void sendUserUpdatePasswordMail (ReminderUserDetails user);
	public void sendUserForgotPasswordMail (ReminderUserDetails user);
}
