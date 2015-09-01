package com.ibm.iga.reminder.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.ibm.iga.reminder.config.RootConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={RootConfiguration.class})

public class TestLoginService {

	@Autowired
//	private ILoginService loginService;
	
	@Test
	public void test () {
	//	loginService.validate("jianjunw@cn.ibm.com", "nhy65tgb");
	}
}
