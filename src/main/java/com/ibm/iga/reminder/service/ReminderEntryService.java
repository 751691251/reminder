package com.ibm.iga.reminder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.iga.reminder.dao.ReminderEntryMapper;
import com.ibm.iga.reminder.model.ReminderEntry;
import com.ibm.iga.reminder.service.inter.IReminderEntryService;
/**
 * 
 * @author Jerry Wang
 * The service to help you get / update / create /delete reminder entry
 *
 */
@Transactional
@Service
public class ReminderEntryService implements IReminderEntryService {

	@Autowired
	ReminderEntryMapper reminderEntryMapper;
	
	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#add(com.ibm.iga.reminder.model.ReminderEntry)
	 */
	@Override
	public long add(ReminderEntry reminderEntry) {
		return reminderEntryMapper.add(reminderEntry);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#delete(long)
	 */
	@Override
	public long delete(long id) {
		return reminderEntryMapper.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#deleteByRequestId(long)
	 */
	@Override
	public long deleteByRequestId(long id) {
		return reminderEntryMapper.deleteByRequestId(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#getById(long)
	 */
	@Override
	public ReminderEntry getById(long id) {
		return reminderEntryMapper.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#getByRequestId(long)
	 */
	@Override
	public List<ReminderEntry> getByRequestId(long id) {
		return reminderEntryMapper.getByRequestId(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#update(com.ibm.iga.reminder.model.ReminderEntry)
	 */
	@Override
	public long update(ReminderEntry reminderEntry) {
		return reminderEntryMapper.update(reminderEntry);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#getTodayReminders()
	 */
	@Override
	public List<ReminderEntry> getTodayReminders() {
		return reminderEntryMapper.getTodayReminderEntries();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ibm.iga.reminder.service.inter.IReminderEntryService#setSent(com.ibm.iga.reminder.model.ReminderEntry)
	 */
	@Override
	public long setSent(ReminderEntry reminderEntry) {
		reminderEntry.setSent();
		return update(reminderEntry);
	}

}
