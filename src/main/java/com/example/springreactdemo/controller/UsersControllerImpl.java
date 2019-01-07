package com.example.springreactdemo.controller;

import com.example.springreactdemo.api.UsersApi;
import com.example.springreactdemo.dto.GetUsersReply;
import com.example.springreactdemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring controller implementation of {@link UsersApi}
 * 
 * @author mate.karolyi
 *
 */
@RestController
public class UsersControllerImpl implements UsersApi
{
	private UserService userService;

	public UsersControllerImpl(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public ResponseEntity<GetUsersReply> users() {
		GetUsersReply reply = new GetUsersReply();
		reply.setUsers(userService.getUsers());
		return new ResponseEntity<GetUsersReply>(reply,HttpStatus.OK);
	}
}
