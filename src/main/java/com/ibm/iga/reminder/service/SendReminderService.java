package com.ibm.iga.reminder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ibm.iga.reminder.model.ReminderEntry;
import com.ibm.iga.reminder.service.inter.IReminderEntryService;
import com.ibm.iga.reminder.service.inter.ISendReminderService;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;
import com.sendgrid.SendGridException;

@Service
public class SendReminderService implements ISendReminderService {

	@Autowired
	private SendGrid mailSender;
	@Autowired
	private SendGrid.Email reminderEmail;
	
	@Autowired
	private IReminderEntryService reminderEntryService;
	
	@Override
	//@Async
	public void sendReminder(ReminderEntry reminderEntry) {
		String[] members = reminderEntry.getReminderRequest().getMembers();
		for (String member: members) {
			
			//new email in case if we want to process multiple entries
			
			SendGrid.Email m =new Email();
			m.setFrom(reminderEmail.getFrom());
			m.addTo(member);
			m.setSubject(reminderEmail.getSubject() + reminderEntry.getReminderRequest().getSubject());
			String body = "hello buddy, \n\n" + reminderEntry.getReminderRequest().getDescription() + reminderEmail.getText() + reminderEntry.getReminderRequest().getOwner();
			m.setText(body);
			try {
				mailSender.send(m);
				reminderEntryService.setSent(reminderEntry);
			} catch (SendGridException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@Async
	public void sendReminders(List<ReminderEntry> reminderEntries) {
		for (ReminderEntry o : reminderEntries) {
			sendReminder(o);
		}
	}

}
