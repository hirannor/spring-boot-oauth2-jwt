package com.example.springreactdemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.springreactdemo.domain.User;
import com.example.springreactdemo.service.UserService;

/**
 * Implementation of {@link UserController}
 * 
 * @author hirannor
 *
 */
@RestController
public class UserControllerImpl implements UserController
{
	private UserService userService;

	public UserControllerImpl(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public List<User> getUsers()
	{
		return userService.getUsers();
	}

	@Override
	public User create(User user)
	{
		return userService.addUser(user);
	}

	@Override
	public void delete(Long id)
	{
		userService.removeUserById(id);
	}

	@Override
	public User getUserById(Long id)
	{
		return userService.findUserById(id);
	}

}
