package com.ibm.iga.reminder.service.inter;

import java.util.List;

import com.ibm.iga.reminder.model.ReminderEntry;

/**
 * 
 * @author Jerry Wang
 * The interface to prvoide service of reminder entry
 */
public interface IReminderEntryService {

	/**
	 * 
	 * @param reminderEntry The reminder entry you want to insert/add
	 * @return 0 if failed or greater than 0 for success.
	 */
	public long add (ReminderEntry reminderEntry); 
	public long delete (long id); //id=primary key, not request id
	public long deleteByRequestId (long id);
	public ReminderEntry getById (long id);
	public List<ReminderEntry> getByRequestId(long id);
	public long update (ReminderEntry reminderEntry);
	public long setSent(ReminderEntry reminderEntry);
	public List<ReminderEntry> getTodayReminders ();

}
