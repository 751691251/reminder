package com.ibm.iga.reminder.controller.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.iga.reminder.model.ReminderRequest;
import com.ibm.iga.reminder.service.inter.IReminderRequestService;
import com.ibm.iga.reminder.service.inter.IUserService;

/**
 * 
 * @author Jerry Wang
 * 
 * GET / DELETE / PUT / POST
 * 
 * /api/reminder/request/id/{id} - get by id
 * /api/reminder/request/id/{id} - put to update
 * /api/reminder/request/id/{id}  - delete to delete
 * /api/reminder/request  - post to create
 * /api/reminder/request/owner/{owner} - get by owner
 */


@RestController
@RequestMapping("/api/reminder/request")
public class RestReminderRequest {


	@Autowired
	private IReminderRequestService reminderRequestService;
	
	private Principal principal;
	
	@ModelAttribute
	public void getCurrentUser (Principal principal) {
		this.principal = principal;
	}
	
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ReminderRequest getById (@PathVariable String id) {
		System.out.println("current user is " + principal.getName());
		return reminderRequestService.getById(Integer.valueOf(id));
	}
	
	@RequestMapping(value = "/owner/{owner}", method = RequestMethod.GET)
	public List<ReminderRequest> getByOwner (@PathVariable String owner) {
		return reminderRequestService.getByOwner(owner);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public RestAPIStatus createReminderRequest (@RequestBody ReminderRequest request) {
		
		if (reminderRequestService.add(request) > 0) {
			return new RestAPIStatus().success();
		} else {
			return new RestAPIStatus().failure();
		}
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.PUT)
	public RestAPIStatus updateReminderRequest(@PathVariable String id, @RequestBody ReminderRequest request) {
		request.setId(Integer.valueOf(id));
		if (reminderRequestService.update(request) > 0) {
			return new RestAPIStatus().success();
		} else {
			return new RestAPIStatus().failure();
		}
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
	public RestAPIStatus deleteReminderRequest(@PathVariable String id) {
		if (reminderRequestService.delete(Integer.valueOf(id)) > 0) {
			return new RestAPIStatus().success();
		} else {
			return new RestAPIStatus().failure();
		}
	}
	
}
