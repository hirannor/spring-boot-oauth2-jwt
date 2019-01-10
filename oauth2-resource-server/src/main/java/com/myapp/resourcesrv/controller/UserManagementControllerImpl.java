package com.myapp.resourcesrv.controller;

import com.myapp.resourcesrv.controller.api.UsermanagementApi;
import com.myapp.resourcesrv.dto.GetUsersReply;
import com.myapp.resourcesrv.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring controller implementation of {@link UsermanagementApi}
 * 
 * @author mate.karolyi
 */
@RestController
@RequestMapping("/api/")
public class UserManagementControllerImpl implements UsermanagementApi
{
	private UserManagementService userManagementService;

	public UserManagementControllerImpl(UserManagementService userManagementService)
	{
		this.userManagementService = userManagementService;
	}

	@Override
	public ResponseEntity<GetUsersReply> getUsers() {
		GetUsersReply reply = new GetUsersReply();
		reply.setUsers(userManagementService.getUsers());
		return new ResponseEntity<GetUsersReply>(reply,HttpStatus.OK);
	}
}
