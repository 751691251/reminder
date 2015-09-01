package com.ibm.iga.reminder.dao.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ibm.iga.reminder.config.RootConfiguration;
import com.ibm.iga.reminder.dao.ReminderUserMapper;
import com.ibm.iga.reminder.model.ReminderUserDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={RootConfiguration.class})
public class TestReminderUserMapper {

	@Autowired
	ReminderUserMapper reminderUserMapper;
	
	@Before
	public void setup () {
	}
	
	
	@Test
	public void testAddNewUser () {
		ReminderUserDetails user = new ReminderUserDetails("chuchu","abc","USER");
		reminderUserMapper.addUser(user);
		reminderUserMapper.addUserRole(user);
		
	}
	@Test
	public void testGet() {
		System.out.println(reminderUserMapper.get("chuchu"));
	}
}
