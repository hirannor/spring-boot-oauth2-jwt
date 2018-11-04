package com.example.springreactdemo.service;

import java.util.List;

import com.example.springreactdemo.domain.User;

/**
 * User service API
 * 
 * @author hirannor
 *
 */
public interface UserService
{
	List<User> getUsers();

	User findUserById(Long id);

	User addUser(User user);

	void removeUserById(Long id);
}
