package com.ibm.iga.reminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.iga.reminder.model.ReminderUserDetails;
import com.ibm.iga.reminder.service.inter.ISendUserService;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service
public class SendUserService implements ISendUserService {


	@Autowired
	private SendGrid mailSender;
	@Autowired
	private SendGrid.Email registrationEmail;
	
	@Autowired
	private SendGrid.Email updatePasswordEmail;
	
	@Autowired
	SendGrid.Email forgotPasswordEmail;
	
	@Override
	public void sendUserRegistrationMail(ReminderUserDetails user) {

		registrationEmail.addTo(user.getUsername());
		registrationEmail.setSubject(registrationEmail.getSubject() + "Welcome " + user.getUsername());
		registrationEmail.setText("hello buddy, \n\n Thanks for registering and have fun. \n Your username : " + user.getUsername() + "\npassword:"
		+ user.getPassword() +"\n\n"+ registrationEmail.getText());

		try {
			mailSender.send(registrationEmail);
		} catch (SendGridException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendUserUpdatePasswordMail(ReminderUserDetails user) {
		updatePasswordEmail.addTo(user.getUsername());
		updatePasswordEmail.setSubject(updatePasswordEmail.getSubject() + "You updated your password!");
		updatePasswordEmail.setText("hello buddy, \n\n You updated your password to " + user.getPassword() + "\n\n" + updatePasswordEmail.getText());
		try {
			mailSender.send(updatePasswordEmail);
		} catch (SendGridException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendUserForgotPasswordMail(ReminderUserDetails user) {
		forgotPasswordEmail.addTo(user.getUsername());
		forgotPasswordEmail.setSubject(forgotPasswordEmail.getSubject() + "Here is your password!");
		forgotPasswordEmail.setText("hello buddy,\n\n Here is your password: " + user.getPassword() + "\n\n" + forgotPasswordEmail.getText());
		try {
			mailSender.send(forgotPasswordEmail);
		} catch (SendGridException e) {
			e.printStackTrace();
		}
	}

}
